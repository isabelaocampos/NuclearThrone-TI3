package com.example.nuclearthrone.model.entity;

import com.example.nuclearthrone.model.entity.enviroment.Wall;
import com.example.nuclearthrone.model.entity.util.Direction;
import com.example.nuclearthrone.model.entity.util.Vector;
import com.example.nuclearthrone.model.level.Level;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;

import java.util.Objects;
public abstract class MovableEntity extends Entity implements IAnimate {

    public int speed;
    public int level;
    public Timeline timeline;
    public AnimateType animation;
    public Direction lookingAt;
    public int spriteStage = 0;
    public Thread thread = new Thread(this::running);
    public int stuck = 0;
    @Override
    public void takeDamage(Entity other) {
    }
    public abstract void movement();
    public abstract void resetTheMovement();

    public void checkCollisions(double oldX, double oldY) {
        if (isOutOfScreen(this)) {
            setX(oldX);
            setY(oldY);
            stuckAtBorder();
            resetTheMovement();
        } else {
            for (Wall wall : Objects.requireNonNull(Level.getLevel(level)).walls) {
                if (intersects(wall) && !checkStuck(oldX, oldY, wall)) {
                    setX(oldX);
                    setY(oldY);
                    resetTheMovement();
                    break;
                }
            }
        }
    }

    public void stuckAtBorder(){
    }

    public void running(){
        while (isAlive){
            try {
                Thread.sleep(50);
                Platform.runLater(() -> {
                    if(level == Level.getSelected()){
                        double oldX= getX();
                        double oldY = getY();
                        movement();
                        checkCollisions(oldX, oldY);
                    }
                });
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void startAnimation() {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void stopAnimation() {
        timeline.stop();
    }
    public void stop(){
        thread.interrupt();
    }

    @Override
    public Timeline getAnimation() {
        return null;
    }

    public void moveTowards(Entity entity) {
        Vector movement = Vector.getMovementVector(entity.getX() - getX(), entity.getY() - getY(), speed);
        setX(getX() + movement.x);
        setY(getY() + movement.y);
    }

    public void moveAwayFrom(Entity entity) {
        Vector movement = Vector.getMovementVector(entity.getX() - getX(), entity.getY() - getY(), speed);
        setX(getX() - movement.x);
        setY(getY() - movement.y);
    }

    public boolean checkStuck(double oldX, double oldY, Entity entity) {
        if (stuck >= 5) {
            if (entity.getX() < getX()) {
                setX(oldX + speed * 2);
            } else {
                setX( oldX - speed * 2);
            }
            if (entity.getY() < getY()) {
                setY(oldY + speed * 2);
            } else {
                setY(oldY - speed * 2);
            }
            stuck = 0;
            return true;
        }
        stuck++;
        return false;
    }
}
