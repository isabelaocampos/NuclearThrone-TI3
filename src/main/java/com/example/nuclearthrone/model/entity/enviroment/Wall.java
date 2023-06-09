package com.example.nuclearthrone.model.entity.enviroment;

import com.example.nuclearthrone.MainApplication;
import com.example.nuclearthrone.model.entity.armery.EnemyBullet;
import com.example.nuclearthrone.model.entity.important.Entity;
import com.example.nuclearthrone.model.entity.level.Level;
import javafx.scene.image.Image;

import java.util.Objects;

import static com.example.nuclearthrone.MainApplication.getHeight;
import static com.example.nuclearthrone.MainApplication.getWidth;

public class Wall extends Entity {

    int level;
    public static final double WIDTH = 50;
    public static final double HEIGHT = 50;

    public Wall(double x, double y, int health, int level, String spriteName) {
        super(x, y, WIDTH, HEIGHT, health, true);
        sprite = getSprite(spriteName);
        this.level = level;
    }

    public Wall(double x, double y, double width, double height, int health, int level, String spriteName) {
        super(x, y, width, height, health, true);
        sprite = getSprite(spriteName);
        this.level = level;
    }


    public Image getSprite(String name) {
        String uri = "file:" + MainApplication.getFile("environment/wall/" + name + ".png").getPath();
        return new Image(uri, getWidth(), getHeight(), true, false, false);
    }

    @Override
    public void takeDamage(Entity other) {

    }
}
