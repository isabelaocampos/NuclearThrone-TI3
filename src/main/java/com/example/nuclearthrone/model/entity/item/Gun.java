package com.example.nuclearthrone.model.entity.item;

import com.example.nuclearthrone.model.entity.AnimateType;

public abstract class Gun extends Item {

    public double delay;
    public Gun (double x, double y,double width, double height,double delay) {
        super(x,y,width,height);
        this.delay = delay;
    }

    public abstract AnimateType attack(double x, double y);
}
