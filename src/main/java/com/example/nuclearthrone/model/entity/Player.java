package com.example.nuclearthrone.model.entity;
import com.example.nuclearthrone.MainApplication;

import javafx.animation.Animation;
import javafx.animation.Timeline;

import com.example.nuclearthrone.model.entity.util.Vector;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;


import java.util.ArrayList;
public class Player extends Entity{

    //** */
    public static final int HEALTH = 100;
    private static Player instance;
    private static Timeline animationPlayer;
    public boolean isAlive;
    //** */


    // Elementos graficos
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<Image> idleImages;
    private ArrayList<Image> runImages;
    private ArrayList<Image> attackImages;

    private ArrayList<Image> dieImages;

    // referencias espaciales
    private double posX;
    private double posY;

    private Vector position;
    private Vector direction;

    // estado actual del personaje
    private int state;
    private int frame;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean isAttacking;



    //
    private boolean isFacingRight = true;

    public static ImageView[] hearts;

    public static ImageView hand;
    public static ProgressBar reloadBar;

    public static final double WIDTH = 50;
    public static final double HEIGHT = 50;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player(200, 70, WIDTH, HEIGHT);

        }
        return instance;
    }

    public Player(double x, double y, double width, double height) {
        super();
        boolean isAlive = true;
    }

    public Player(Canvas canvas) {
        this.state = 0;
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.isAlive = true;

        this.position = new Vector(100, 100);

        idleImages = new ArrayList<>();
        runImages = new ArrayList<>();
        attackImages = new ArrayList<>();
        dieImages = new ArrayList<>();


        for (int i = 1; i <= 3; i++) {
            Image image = MainApplication.getImage("animations/hero/idle/idle_0" + i + ".png");
            idleImages.add(image);
        }

        for (int i = 1; i <= 5; i++) {
            Image image = MainApplication.getImage("animations/hero/run/run_0" + i + ".png");
            runImages.add(image);
        }

        for (int i = 1; i <= 4; i++) {
            Image image = MainApplication.getImage("animations/hero/shoot/shoot_0" + i + ".png");
            attackImages.add(image);
        }

        for (int i = 1; i <= 8; i++) {
            Image image = MainApplication.getImage("animations/hero/dead/dead_0" + i + ".png");
            dieImages.add(image);
        }

    }


    public void paint() {
        onMove();

        if (isAlive) {

            if (state == 0) {
                double width = isFacingRight ? 50 : -50;
                double xOffset = isFacingRight ? -25 : 25;

                graphicsContext.drawImage(idleImages.get(frame % 3), position.getX() + xOffset, position.getY() - 25, width, 50);
            } else if (state == 1) {
                double width = isFacingRight ? 60 : -60;
                double xOffset = isFacingRight ? -30 : 30;

                graphicsContext.drawImage(runImages.get(frame % 5), position.getX() + xOffset, position.getY() - 25, width, 50);
            } else if (state == 2) {
                double width = isFacingRight ? 70 : -70;
                double xOffset = isFacingRight ? -35 : 35;

                graphicsContext.drawImage(attackImages.get(frame % 4), position.getX() + xOffset, position.getY() - 25, width, 50);
            }

        } else {
            if (state == 3) {
                graphicsContext.drawImage(dieImages.get(frame % 8), position.getX(), position.getY());

            }
        }

    }


    public void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                state = 1;
                upPressed = true;
                break;
            case S:
                state = 1;
                downPressed = true;
                break;
            case D:
                state = 1;
                rightPressed = true;
                break;
            case A:
                state = 1;
                leftPressed = true;
                break;
        }
    }

    public void onKeyReleased(KeyEvent event) {
        state = 0;
        switch (event.getCode()) {
            case W:
                upPressed = false;
                break;
            case S:
                downPressed = false;
                break;
            case D:
                rightPressed = false;
                break;
            case A:
                leftPressed = false;
                break;
        }
    }

    public void onMove() {
        if (upPressed) {
            position.setY(position.getY() - 10);
        }
        if (downPressed) {
            position.setY(position.getY() + 10);
        }
        if (leftPressed) {
            position.setX(position.getX() - 10);
        }
        if (rightPressed) {
            position.setX(position.getX() + 10);
        }
    }

    public Vector getPosition() {
        return position;
    }


    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }


    public void setAlive(boolean alive) {
        isAlive = alive;
    }



    public static void resetAvatar() {
        instance.stopAnimation();
        instance = null;
    }

    public void stopAnimation() {
        animationPlayer.stop();
    }

    public void startAnimation() {
        animationPlayer.setCycleCount(Animation.INDEFINITE);
        animationPlayer.play();

    }

    public void remove(){
        setAlive(false);
        state = 3;  // Establecer el estado en 3 para mostrar la animación de muerte
        frame = 0;  // Reiniciar el contador de frames para la animación de muerte
        instance.stopAnimation();
        //aaaaa
    }


    @Override
    public void takeDamage(Entity other) {

    }
}

