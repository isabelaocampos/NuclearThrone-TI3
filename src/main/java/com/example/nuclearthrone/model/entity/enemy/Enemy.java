package com.example.nuclearthrone.model.entity.enemy;

import com.example.nuclearthrone.model.entity.util.Vector;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.lang.Math;
import java.util.ArrayList;

public class Enemy extends Thread{
    private Canvas canvas;

    private GraphicsContext graphicsContext;

    private Vector position;

    private Vector direction;

    private boolean isAlive;

    private boolean isAttacking;

    private int state;

    private int frame;

    private int damage;

    private ArrayList<Image> idleImages;

    private ArrayList<Image> attackImages;

    public Enemy(Canvas canvas, Vector position, Vector direction){
        this.state = 0;
        this.frame = 0;
        this.damage = 0;
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.position = position;
        this.direction = direction;
        this.isAlive = true;
        this.isAttacking = false;

        idleImages = new ArrayList<>();
        attackImages = new ArrayList<>();

        for(int i = 0; i < 2; i++){
            //Image image = new Image(getClass().getResourceAsStream("/Robot Warfare Asset Pack 22-11-24/0.avispon/avispa-"+i+".png"));
            Image image = new Image(getClass().getResourceAsStream("/animations/Enemies/idle/wasp-"+i+".png"));
            idleImages.add(image);
        }

        for(int i = 0; i < 2; i++){
            Image image = new Image(getClass().getResourceAsStream("/animations/Enemies/attack/wasp-"+i+".png"));
            attackImages.add(image);
        }
    }

    public Vector getPosition() {
        return position;
    }

    public boolean getAttacking (){
        return isAttacking;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getDamage(){
        return damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void paint(){
        if (state == 0){
            graphicsContext.drawImage(idleImages.get(frame%2), position.getX(), position.getY(), 40, 40);
            frame++;
            position.setX(position.getX() + direction.getX());
            position.setY(position.getY() + direction.getY());
        }
        if (state == 1){
            graphicsContext.drawImage(attackImages.get(frame%2), position.getX(), position.getY(), 40, 40);
            frame++;
            position.setX(position.getX() + direction.getX());
            position.setY(position.getY() + direction.getY());
        }
    }

    public void followPlayer(Vector positionPlayer){
        double distance = Math.sqrt(
                Math.pow(position.getX() - positionPlayer.getX(), 2) +
                        Math.pow(position.getY() - positionPlayer.getY(), 2));

        if(distance <= 200){
            state = 1;
            double diffX = (positionPlayer.getX()-position.getX());
            double diffY = (positionPlayer.getY()-position.getY());

            Vector diff = new Vector(diffX, diffY);
            diff.normalize();;
            diff.setSpeed(5);
            direction = diff;
        }
        if(distance > 200){
            state = 0;
            direction = new Vector(0,0);
        }
    }

    public void attackPlayer(Vector positionPlayer){
        double distance = Math.sqrt(
                Math.pow(position.getX()-positionPlayer.getX(), 2)
                        + Math.pow(position.getY() - positionPlayer.getY(), 2));

        if(distance <= 200){
            isAttacking = true;
        }
        else{
            isAttacking = false;
        }
    }

    @Override
    public void run(){}
}
