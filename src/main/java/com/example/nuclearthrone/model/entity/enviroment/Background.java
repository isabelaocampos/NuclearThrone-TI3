package com.example.nuclearthrone.model.entity.enviroment;


import com.example.nuclearthrone.MainApplication;
import com.example.nuclearthrone.model.entity.Entity;
import com.example.nuclearthrone.model.entity.util.Images;
import javafx.scene.image.Image;

public class Background extends Entity  {

    public static final double WIDTH = 50;
    public static final double HEIGHT = 50;

     public Background(double x, double y, String spriteName, double angle) {
        super(x, y,WIDTH,HEIGHT, 0,false);
        sprite = Images.rotateImage(getSprite(spriteName),angle);
    }

   @Override
    public void takeDamage(Entity other) {}
    public Image getSprite(String name){
        String uri = "file:" + MainApplication.getFile("environment/decoration/" +name+".png").getPath();
        return new Image(uri,getWidth(),getHeight(),true,false,false);
    }
}

