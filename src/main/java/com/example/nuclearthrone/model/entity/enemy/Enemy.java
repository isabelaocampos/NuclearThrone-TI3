package com.example.nuclearthrone.model.entity.enemy;

import com.example.nuclearthrone.model.entity.Entity;
import com.example.nuclearthrone.model.entity.util.Vector;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.lang.Math;
import java.util.ArrayList;

public class Enemy extends Entity, Thread {
    private Canvas canvas;

    private GraphicsContext graphicsContext;

    private Vector position;

    private Vector direction;

    private boolean isAlive;

    private boolean isAttacking;

    private int state;

    private int frame;

    private int damage;

    private boolean movingRight;

    private ArrayList<Image> idleImages;

    private ArrayList<Image> attackImages;

    private ArrayList<Image> lookleftImages;

    private ArrayList<Image> deathImages;

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
        this.movingRight = true;

        idleImages = new ArrayList<>();
        attackImages = new ArrayList<>();
        lookleftImages = new ArrayList<>();
        deathImages = new ArrayList<>();

        for(int i = 0; i <= 3; i++){
           //Image image = new Image(getClass().getResourceAsStream("/Robot Warfare Asset Pack 22-11-24/0.avispon/avispa-"+i+".png"));
           Image image = new Image(getClass().getResourceAsStream("/animations/Enemies/idle/enemy-0"+i+".png"));
           idleImages.add(image);
        }

        for(int i = 0; i <= 15; i++){
            Image image = new Image(getClass().getResourceAsStream("/animations/Enemies/attack/enemy-"+i+".png"));
            attackImages.add(image);
        }

        for(int i = 0; i < 1; i++){
            Image image = new Image(getClass().getResourceAsStream("/animations/Enemies/left/enemy-"+i+".png"));
            lookleftImages.add(image);
        }

        for(int i = 0; i <= 3; i++){
            Image image = new Image(getClass().getResourceAsStream("/animations/Enemies/death/enemy-"+i+".png"));
            deathImages.add(image);
        }
    }

    public Vector getPosition() {
        return position;
    }

    public boolean getAttacking (){
        return isAttacking;
    }

    public void setState (int state){ this.state = state; }

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
        //Idle
        if (state == 0){
            graphicsContext.drawImage(idleImages.get(frame%4), position.getX(), position.getY(), 40, 40);
            frame++;
            position.setX(position.getX() + direction.getX());
            position.setY(position.getY() + direction.getY());
        }
        //Attack
        if (state == 1){
            graphicsContext.drawImage(attackImages.get(frame%16), position.getX(), position.getY(), 40, 40);
            frame++;
            position.setX(position.getX() + direction.getX());
            position.setY(position.getY() + direction.getY());
        }
        //Look left
        if (state == 2){
            graphicsContext.drawImage(lookleftImages.get(frame%1), position.getX(), position.getY(), 40, 40);
            frame++;
            position.setX(position.getX() + direction.getX());
            position.setY(position.getY() + direction.getY());
        }
        //Death
        if (state == 3){
            graphicsContext.drawImage(deathImages.get(frame%4), position.getX(), position.getY(), 40, 40);
            frame++;
            position.setX(position.getX() + direction.getX());
            position.setY(position.getY() + direction.getY());
        }
    }


    /*public void moveRoutine(double key) {
        double limR = this.canvas.getWidth() - 50;
        if (position.getX() < limR && movingRight) {
            position.setX(position.getX() + key);
        } else if(position.getX() == limR || position.getX() > limR) {
            movingRight = false;
            lookLeft(movingRight);
            position.setX(position.getX()-key);
        } else {
            resetPosition();
        }
    }

    private void resetPosition() {
        position.setX(0);
        movingRight = true;
    }

    public void lookLeft(boolean movingRight){
        if (!movingRight){
            state = 2;
        }
    }*/

    public void followPlayer(Vector positionPlayer){
        double distance = Math.sqrt(
                Math.pow(position.getX() - positionPlayer.getX(), 2) +
                        Math.pow(position.getY() - positionPlayer.getY(), 2));

        if(distance <= 200){
            state = 1;
            //calcular diferencia
            double diffX = (positionPlayer.getX()-position.getX());
            double diffY = (positionPlayer.getY()-position.getY());

            Vector diff = new Vector(diffX, diffY);
            diff.normalize();;
            diff.setSpeed(3);
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
    public void run(){
    }

    @Override
    public void takeDamage(Entity other) {

    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}