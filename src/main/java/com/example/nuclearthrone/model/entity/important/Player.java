package com.example.nuclearthrone.model.entity.important;
import com.example.nuclearthrone.MainApplication;
import com.example.nuclearthrone.model.entity.util.Vector;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;


import java.util.ArrayList;
public class Player {

    // Elementos graficos
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<Image> idleImages;
    private ArrayList<Image> runImages;
    private ArrayList<Image> attackImages;

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
    private boolean isMoving;

    public Player(Canvas canvas){
        this.state = 0;
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();

        this.position = new Vector(100, 100);

        idleImages = new ArrayList<>();
        runImages = new ArrayList<>();
        attackImages = new ArrayList<>();

        for(int i = 1; i <= 3; i++){
            Image image = MainApplication.getImage("animations/hero/idle/idle_0"+i+".png");
            idleImages.add(image);
        }

        for(int i = 1; i <= 5; i++){
            Image image = MainApplication.getImage("animations/hero/run/run_0" +i+".png");
            runImages.add(image);
        }

        for(int i = 1; i <= 4; i++){
            Image image = MainApplication.getImage("animations/hero/shoot/shoot_0"+i+".png");
            attackImages.add(image);
        }

    }


    public void paint() {
        onMove();

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

        frame++;
    }







    public void onKeyPressed(KeyEvent event){
        switch (event.getCode()){
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

    public void onKeyReleased(KeyEvent event){
        state = 0;
        switch (event.getCode()){
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

    public void onMove(){
        if (upPressed){
            position.setY(position.getY() - 10);
        }
        if (downPressed){
            position.setY(position.getY() + 10);
        }
        if (leftPressed){
            position.setX(position.getX() - 10);
        }
        if (rightPressed){
            position.setX(position.getX() + 10);
        }
    }

    public Vector getPosition() {
        return position;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }

}
