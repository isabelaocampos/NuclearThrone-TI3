package com.example.nuclearthrone.model.entity.armery;

import com.example.nuclearthrone.model.entity.Player;

public abstract class PlayerBullet extends Bullet {

    public PlayerBullet(double width, double height, int health, int level) {
        super(Player.getInstance(), width, height, health, level);
    }
}
