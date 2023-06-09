package com.example.nuclearthrone.control;
<<<<<<< HEAD
//*import com.example.nuclearthrone.model.entity.level.Level;
=======

import com.example.nuclearthrone.model.level.Level;
>>>>>>> 91d21398dfaf5ca6faf5396f098731f46d92ffe8
import com.example.nuclearthrone.model.entity.important.Player;
import com.example.nuclearthrone.screens.BaseScreen;
import com.example.nuclearthrone.screens.ScreenA;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public static final String Main_Resources_Path = System.getProperty("user.dir") + "\\src\\main\\resources";
    public static final String Main_Resources_Images = Main_Resources_Path + "";

    public static final String Main_Resources_Audio_Path = Main_Resources_Path + "";
    public static final String Collision_Pack = Main_Resources_Path + "";
    public static int level = 0;
    //*private ArrayList<Level> levels;
    private Player players;
    @FXML
    private Canvas canvas;

    @FXML
    private GraphicsContext graphics;

    private boolean isRunning;

    public static int SCREEN = 0;

    private ArrayList<BaseScreen> screens;
    public static boolean reset;

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

    private void start(){
        graphics = canvas.getGraphicsContext2D();
        reset = false;
        isRunning = true;
        //levels = new ArrayList<>();
        //players = new Player();
        canvas.setFocusTraversable(true);
        //printInCanvas();
        //levels.add(new Level(44,30,canvas,"\\", players, 1));
        //levels.add(new Level(44,30,canvas,"\\", players, 2));
        //levels.add(new Level(44,30,canvas,"\\", players, 3));
        initEvents();

    }

    public static double getWidth() {
        return 1280;
    }

    public static double getHeight() {
        return 720;
    }

    public static int msRate() {
        return 16;
    }
}