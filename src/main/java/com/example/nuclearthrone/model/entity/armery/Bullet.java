package com.example.nuclearthrone.model.entity.armery;

import com.example.nuclearthrone.MainApplication;
import com.example.nuclearthrone.model.entity.util.Vector;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet {

    // Elementos graficos
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    // referencias espaciales
    private Vector position;
    private Vector direction;
    private int size;
    private int speed;

    private Image bulletImage;

    public Bullet(Canvas canvas, Vector position, Vector direction) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.position = position;
        this.direction =  direction;
        this.size = 10;
        this.speed = 10;
        this.bulletImage = MainApplication.getImage("animations/hero/Objects/bulletSphereplanet.png");
    }

    public void paint() {
        graphicsContext.drawImage(bulletImage, position.getX()-5, position.getY()-5, 10, 10);
        position.setX(position.getX() + direction.getX());
        position.setY(position.getY() + direction.getY());
    }


    public double getPositionX() {
        return position.getX();
    }

    public void setPositionX(double x) {
        this.position.setX(x);
    }

    public double getPositionY() {
        return position.getY();
    }

    public void setPositionY(double y) {
        this.position.setY(y);
    }
}
