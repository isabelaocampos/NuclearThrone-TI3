package com.example.nuclearthrone.model.entity.armery;

import com.example.nuclearthrone.MainApplication;
import com.example.nuclearthrone.model.entity.Entity;
import com.example.nuclearthrone.model.entity.IAnimate;
import com.example.nuclearthrone.model.entity.Player;
import com.example.nuclearthrone.model.entity.util.Vector;
import com.example.nuclearthrone.model.level.Level;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public abstract class Bullet extends Entity implements IAnimate {

    // Elementos graficos

    private Canvas canvas;
    Vector movement;
    private GraphicsContext graphicsContext;

    public static final int DAMAGE = 20;

    // referencias espaciales
    double speed;
    Vector position;

    Image[] animation;


    double initialX;

    double initialY;
    boolean alive;
    Vector direction;
    private int size;
    Timeline animationPlayer;
    int spriteStage;
    private Image bulletImage;

    //** */
    int level;

    public Bullet(Vector position, Vector direction, double width, double height, int health, int level) {
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

    public abstract void initAnimation();

    public boolean uniqueAliveConstraint(){
        return alive;
    }

    public void shootTo(double x, double y, double delay) {
        movement = new Vector(x - getX(), y - getY());
        movement.normalize();

        Thread t = new Thread(() -> {
            try {
                Thread.sleep((long) delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()->{
                if(this instanceof PlayerBullet){
                    Player.reloadBar.setProgress(1);
                }
            });

            setVisible(true);
            while (alive) {
                setX(getX() + movement.x);
                setY(getY() + movement.y);
                alive = uniqueAliveConstraint();
                alive = alive && !isOutOfScreen(this);
                try {
                    Thread.sleep(MainApplication.msRate());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Objects.requireNonNull(Level.getLevel(level)).bullets.remove(this);
        });
        t.start();
    }
}
