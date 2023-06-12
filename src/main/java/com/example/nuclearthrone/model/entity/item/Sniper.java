package com.example.nuclearthrone.model.entity.item;

import com.example.nuclearthrone.MainApplication;
import com.example.nuclearthrone.model.entity.AnimateType;
import com.example.nuclearthrone.model.entity.armery.Bullet;
import com.example.nuclearthrone.model.entity.armery.SunBullet;
import com.example.nuclearthrone.model.level.Level;
import javafx.scene.image.Image;

public class Sniper extends Gun {

    public static final double DELAY = 1200;
    public static final double WIDTH = 10;
    public static final double HEIGHT = 20;
    public static final String SPRITE = "entities/weapon/slingshot.png";

    public Sniper(double x, double y) {
        super(x, y, WIDTH, HEIGHT,DELAY);
        String uri = "file:" + MainApplication.getFile(SPRITE).getPath();
        this.sprite = new Image(uri, 20, 40, false, true, false);
    }


    public AnimateType attack(double x, double y) {
        Bullet bullet = new SunBullet(Level.getSelected());
        bullet.setVisible(false);
        bullet.shootTo(x, y, DELAY);
        Level.currentLevel().bullets.add(bullet);

        return AnimateType.SHOOT;
    }
}
