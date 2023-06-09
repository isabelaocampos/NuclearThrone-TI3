package com.example.nuclearthrone.model.entity.armery;

import com.example.nuclearthrone.MainApplication;
import com.example.nuclearthrone.model.entity.Entity;
import com.example.nuclearthrone.model.entity.util.Vector;
import javafx.animation.Timeline;
import javafx.scene.image.Image;

public class EnemyBullet extends Bullet {

    public static final double WIDTH = 20;
    public static final double HEIGHT = 20;
    public static final String SPRITE = "entities/ammo/fireball.png";
    public static final int HITS = 1;

    public EnemyBullet(Vector position, Vector direction, int level) {
        super(position, direction, WIDTH, HEIGHT, HITS, level);
        speed = 3;
        stopAnimation();
        initAnimation();
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
        animation = new Image[1];
        String uri = "file:" + MainApplication.getFile(SPRITE).getPath();
        animation[0] = new Image(uri, WIDTH, HEIGHT, true, false, false);
        sprite = animation[0];
    }

    @Override
    public boolean uniqueAliveConstraint() {
        return false;
    }

    @Override
    public void takeDamage(Entity other) {
        // Implementation of takeDamage() method for EnemyBullet if needed
    }
}
