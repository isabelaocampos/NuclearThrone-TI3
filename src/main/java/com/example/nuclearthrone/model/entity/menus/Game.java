package com.example.nuclearthrone.model.entity.menus;

import com.example.nuclearthrone.model.KeyboardControl;
import com.example.nuclearthrone.model.entity.enviroment.Background;
import com.example.nuclearthrone.model.entity.enviroment.Wall;
import com.example.nuclearthrone.model.entity.important.Entity;
import com.example.nuclearthrone.model.entity.important.Player;
import com.example.nuclearthrone.model.level.Level;
import com.example.nuclearthrone.screens.ScreenA;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.util.ArrayList;

public class Game {
    @FXML
    AnchorPane gameOver;

    @FXML
    private Button menuBtn;

    @FXML
    private ImageView playAgainBtn;

    @FXML
    private AnchorPane winner;
    @FXML
    Canvas canvas;

    @FXML
    private ImageView heartOne;

    @FXML
    private ImageView heartThree;

    @FXML
    private ImageView heartTwo;

    @FXML
    private ProgressBar reloadBar;

    @FXML
    private ImageView weaponImage;

    @FXML
    void goToMenu(ActionEvent event) {
        canvas.getScene().getWindow().hide();
    }

    @FXML
    void replay(MouseEvent event) {
        System.out.println("BBB");
        menuBtn.setDisable(true);
        playAgainBtn.setDisable(true);
        gameOver.setVisible(false);
        Player.resetAvatar();
        Level.resetLevels();
    }

    @FXML
    public void initialize() {
        bindHUD();
        initKeyBoard();
        initBounds();
        Thread gameThread = new Thread(() -> {
            while (0 != 1) {
                Platform.runLater(() -> {
                    Level currentLevel = Level.currentLevel();
                    graphicsContext.setFill(Color.BLACK);
                    graphicsContext.drawImage(currentLevel.background, 0, 0);
                    paintEntities(currentLevel);
                    checkAvatarAlive();
                });
                try {
                    Thread.sleep(msRate());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }

    GraphicsContext graphicsContext;

    public void bindHUD(){
       // Ball.winner = winner;
        Player.hearts = new ImageView[3];
        Player.hearts[0] = heartOne;
        Player.hearts[1] = heartTwo;
        Player.hearts[2] = heartThree;
        Player.hand = weaponImage;
        Player.reloadBar = reloadBar;
    }

    public void initKeyBoard() {
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(KeyboardControl:onKeyPressed);
        canvas.setOnKeyReleased(KeyboardControl:onKeyReleased);
        canvas.setOnMousePressed(KeyboardControl::onMousePressed);
    }

    public void initBounds() {
        graphicsContext = canvas.getGraphicsContext2D();
        Rectangle2D rect = Screen.getPrimary().getBounds();
        canvas.setWidth(rect.getWidth());
        canvas.setHeight(rect.getHeight());
    }

    public void paintEntities(Level current) {
        for (Wall wall : current.walls) {
            wall.draw(graphicsContext);
        }
        for (Background decoration : current.background) {
            decoration.draw(graphicsContext);
        }
        bulletsInteraction(current);
        Player.getInstanceOf().draw(graphicsContext);
        for (MovableEntity entity : current.entities) {
            entity.draw(graphicsContext);
        }
    }

    @SuppressWarnings("unchecked")
    public void bulletsInteraction(Level currentLevel) {
        for (int i = 0; i < currentLevel.bullets.size(); i++) {
            Bullet currentB = currentLevel.bullets.get(i);
            currentB.draw(graphicsContext);
            if (currentB instanceof EnemyBullet) {
                if (currentB.intersects(Avatar.getInstance())) {
                    Player.getInstance().takeDamage(currentB);
                    currentLevel.bullets.remove(i);
                    i--;
                    continue;
                }
            }
            ArrayList<Entity> intersected = currentB.intersectsAny(currentLevel.walls, currentLevel.entities);
            for (Entity entity : intersected) {
                entity.takeDamage(currentB);
                break;
            }
            if (!intersected.isEmpty()) {
                currentB.health--;
                if (currentB.health <= 0) {
                    currentLevel.bullets.remove(i);
                    i--;
                }
            }
        }
    }
*/
    public void checkAvatarAlive(){
        if(!Player.getInstance().isAlive) {
            gameOver.setVisible(true);
            playAgainBtn.setDisable(false);
            menuBtn.setDisable(false);
        }
    }

    public static int msRate() {
        return 16;
    }
}
