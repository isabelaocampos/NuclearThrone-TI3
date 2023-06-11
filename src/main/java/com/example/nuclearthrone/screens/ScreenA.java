package com.example.nuclearthrone.screens;

import com.example.nuclearthrone.model.entity.Player;
import com.example.nuclearthrone.model.entity.enemy.Enemy;
import com.example.nuclearthrone.model.entity.armery.Bullet;
import com.example.nuclearthrone.model.entity.util.Vector;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.lang.Math;

public class ScreenA extends BaseScreen{

    private Player player;

    private int playerLives = 3;
    private ArrayList<Enemy> enemies;

    private ArrayList<Bullet> bullets;

    private ArrayList<Bullet> bulletsEnemy;

    public ScreenA(Canvas canvas) {
        super(canvas);
        player = new Player(canvas);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        bulletsEnemy = new ArrayList<>();
        //Create enemies
        int upperX = (int) canvas.getWidth()-50;
        int lowerX = 200;
        int rangeX = (upperX - lowerX) + 1;
        int upperY = (int) canvas.getHeight()-50;
        int lowerY = 200;
        int rangeY = (upperY - lowerY) + 1;

        for (int i=0; i<4; i++){
            double rX = (Math.random() * rangeX) + lowerX;
            double rY = (Math.random() * rangeY) + lowerY;

            Enemy enemy = new Enemy(canvas, new Vector(rX, rY), new Vector(0,0));
            enemies.add(enemy);
        }
    }

    @Override
    public void paint() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

        player.paint();

        //Paint enemies
        for (int i=0; i<enemies.size(); i++){
            enemies.get(i).paint();
            enemies.get(i).followPlayer(player.getPosition());
            enemies.get(i).attackPlayer(player.getPosition());
            enemyShoot(enemies.get(i).getAttacking(), i);
        }

        //Paint bullets of Player
        for (int i = 0; i< bullets.size(); i++){
            bullets.get(i).paint();

            if(bullets.get(i).getPositionX() > canvas.getWidth()){
                bullets.remove(i);
                i--;
            }
        }

        //Paint bullets of Enemy
        for (int i = 0; i< bulletsEnemy.size(); i++){
            bulletsEnemy.get(i).paint();

            if(bulletsEnemy.get(i).getPositionX() > canvas.getWidth()){
                bulletsEnemy.remove(i);
                i--;
            }
        }

        //Delete enemy
        for (int i = 0; i< enemies.size(); i++){
            for (int j = 0; j < bullets.size(); j++){

                Enemy actualEnemy = enemies.get(i);
                Bullet actualBullet = bullets.get(j);

                double distance = Math.sqrt(
                        Math.pow(actualEnemy.getPosition().getX() - actualBullet.getPositionX(), 2) +
                                Math.pow(actualEnemy.getPosition().getY() - actualBullet.getPositionY(), 2));

                if (distance <= 10){
                    actualEnemy.setDamage(actualEnemy.getDamage()+1);
                    bullets.remove(j);

                    if (actualEnemy.getDamage() == 3){
                        Enemy deletedEnemy = enemies.remove(i);
                        deletedEnemy.setAlive(false);
                        return;
                    }
                }
            }
        }

        // Delete player
        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < bulletsEnemy.size(); j++) {

                Enemy actualEnemy = enemies.get(i);
                Bullet actualBullet = bulletsEnemy.get(j);

                double distance = Math.sqrt(
                        Math.pow(player.getPosition().getX() - actualBullet.getPositionX(), 2) +
                                Math.pow(player.getPosition().getY() - actualBullet.getPositionY(), 2));

                if (distance <= 10) {
                    playerLives--;
                    bulletsEnemy.remove(j);

                    if (playerLives <= 0) {
                        player.remove();

                        return;
                    }
                }
            }
        }


    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        player.onKeyPressed(event);
    }

    @Override
    public void onKeyReleased(KeyEvent event) {
        player.onKeyReleased(event);
    }

    @Override
    public void onMousePressed(MouseEvent event) {

        double diffX = event.getX() - player.getPosition().getX(); //destino - origen
        double diffY = event.getY() - player.getPosition().getY();
        Vector diff = new Vector(diffX, diffY);
        diff.normalize();
        diff.setSpeed(7);

        bullets.add(
                new Bullet(canvas, new Vector( player.getPosition().getX(), player.getPosition().getY()), diff)
        );

    }

    //Cambiar cursor por una imagen - MODIFICAR
    @Override
    public void onMouseMove(MouseEvent e){
        double relativePosition = e.getX()-player.getPosition().getX();
        player.setFacingRight(
                relativePosition > 0
        );
    }

    //Enemies shoot to player
    public void enemyShoot(boolean isAttacking, int index){
        if (isAttacking){
            double diffXE = player.getPosition().getX() - enemies.get(index).getPosition().getX();
            double diffYE = player.getPosition().getY() - enemies.get(index).getPosition().getY();
            Vector diffE = new Vector(diffXE, diffYE);
            diffE.normalize();
            diffE.setSpeed(4);

            bulletsEnemy.add((new Bullet(canvas,
                    new Vector(enemies.get(index).getPosition().getX(), enemies.get(index).getPosition().getY()),
                    diffE)));
        }
    }

}