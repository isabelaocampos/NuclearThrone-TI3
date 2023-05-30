package com.example.nuclearthrone.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;

public class Enemy extends Thread{
    private Canvas canvas;

    private GraphicsContext graphicsContext;

    private Vector position;

    private Vector direction;

    private boolean isAlive;

    public Enemy(Canvas canvas, Vector position, Vector direction){
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.position = position;
        this.direction = direction;
        this.isAlive = true;
    }

    public Vector getPosition() {
        return position;
    }



    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void paint(){
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(position.getX(), position.getY(), 20, 20);
        position.setX(position.getX() + direction.getX());
        position.setY(position.getY() + direction.getY());
    }

    public void followPlayer(Vector positionPlayer){
        double distance = Math.sqrt(
                Math.pow(position.getX() - positionPlayer.getX(), 2) +
                        Math.pow(position.getY() - positionPlayer.getY(), 2));

        if(distance < 200){
            //calcular diferencia
            double diffX = (positionPlayer.getX()-position.getX());
            double diffY = (positionPlayer.getY()-position.getY());

            Vector diff = new Vector(diffX, diffY);
            diff.normalize();;
            diff.setSpeed(6);
            direction = diff;
        }

    }
    @Override
    public void run(){

    }
}
