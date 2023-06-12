package com.example.nuclearthrone.model.entity.armery;


import com.example.nuclearthrone.MainApplication;


import com.example.nuclearthrone.model.entity.Entity;
import javafx.animation.Timeline;
import javafx.scene.image.Image;

public class SunBullet extends PlayerBullet {

    public static final double WIDTH = 20;
    public static final double HEIGHT = 20;
    public static final String SPRITE = "/animations/hero/Object/bulletSun";
    public static final int HITS = 1;

    public SunBullet(int level) {
        super(WIDTH, HEIGHT, HITS, level);
        speed = 7;
        stopAnimation();
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
        animation[0] = new Image(uri, 20, 10, false, true, false);
        sprite = animation[0];
    }

    @Override
    public void takeDamage(Entity other) {

    }
}

