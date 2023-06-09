package com.example.nuclearthrone.model.entity.item;

import com.example.nuclearthrone.MainApplication;
import com.example.nuclearthrone.control.MainController;
import com.example.nuclearthrone.model.entity.important.Entity;


import com.example.nuclearthrone.model.entity.enemy.Enemy;


public class Item extends Entity {

    public Item(double x, double y, double width, double height) {
        super(x, y, width, height, -1, true);
    }

    @Override
    public void takeDamage(Entity other) {}

    public static Item generateItem(int level) {
        int type = (int) (Math.random() * 1);
        if (type == 0) {
            return new Healing(Math.random() * (MainApplication.getWidth() - Healing.WIDTH),
                    Math.random() * (MainApplication.getHeight() - Healing.HEIGHT));
        }
        return null;
    }


}

