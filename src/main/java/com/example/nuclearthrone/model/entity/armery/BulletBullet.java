package com.example.nuclearthrone.model.entity.armery;

import com.example.nuclearthrone.model.entity.Entity;
import com.example.nuclearthrone.model.entity.util.Vector;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;

public class BulletBullet extends Bullet {
    // Implementa los métodos abstractos de la clase Bullet aquí

    // Constructor de ConcreteBullet
    public BulletBullet(Canvas canvas, Vector position, DiffE diffE) {
        super(canvas, position, diffE);
    }

    @Override
    public void takeDamage(Entity other) {

    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public Timeline getAnimation() {
        return null;
    }

    @Override
    public void initAnimation() {

    }
}

