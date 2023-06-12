package com.example.nuclearthrone.model.entity.armery;

import com.example.nuclearthrone.model.entity.util.Vector;
import com.example.nuclearthrone.model.entity.Player;

public abstract class PlayerBullet extends Bullet {

    public PlayerBullet(double width, double height, int health, int level) {
        super(Player.getInstance().getPosition(), new Vector(0, 0), width, height, health, level);
    }
}
