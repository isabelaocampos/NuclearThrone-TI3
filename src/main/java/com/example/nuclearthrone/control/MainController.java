package com.example.nuclearthrone.control;
import com.example.nuclearthrone.screens.BaseScreen;
import com.example.nuclearthrone.screens.ScreenA;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Canvas canvas;

    private boolean isRunning;

    public static int SCREEN = 0;

    private ArrayList<BaseScreen> screens;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isRunning = true;
        screens = new ArrayList<>();
        screens.add(new ScreenA(this.canvas));
        canvas.setFocusTraversable(true);

        new Thread( () -> {
            while (isRunning){
                Platform.runLater( () -> {
                    paint();
                });
                pause(100);
            }
        }).start();

        initEvents();
    }

    public void paint(){
        if (SCREEN <= screens.size())
            screens.get(SCREEN).paint();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void initEvents(){
        canvas.setOnKeyPressed(event -> {
            screens.get(SCREEN).onKeyPressed(event);
        });

        canvas.setOnKeyReleased(event -> {
            screens.get(SCREEN).onKeyReleased(event);
        });

        canvas.setOnMousePressed(event -> {
            screens.get(SCREEN).onMousePressed(event);
        });
        canvas.setOnMouseMoved(event -> {
            screens.get(SCREEN).onMouseMove(event);
        });
    }

    private void pause(int time){
        try {
            Thread.sleep(time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}