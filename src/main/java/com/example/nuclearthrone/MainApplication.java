package com.example.nuclearthrone;

import com.example.nuclearthrone.control.MainController;
import com.example.nuclearthrone.model.entity.menus.*;



import com.example.nuclearthrone.model.entity.Player;
import com.example.nuclearthrone.model.level.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MainApplication extends Application {

    static Stage gameStage;

    @FXML
    private Button playButton;

    @FXML
    private Button quitButton;

    static MainController controller;
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("hello view");

        FXMLLoader fxmlLoader = new FXMLLoader(getView("hello-view"));
        AnchorPane root = fxmlLoader.load();


        // Buttons references
        playButton = (Button) fxmlLoader.getNamespace().get("playButton");
        quitButton = (Button) fxmlLoader.getNamespace().get("quitButton");

        playButton.setOnAction(e -> {
            showScreen();
        });



        quitButton.setOnAction(e -> {
            stage.close();
        });

        VBox vBox = new VBox(20, playButton, quitButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setTranslateX(260);
        vBox.setTranslateY(200);

        root.getChildren().addAll(vBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.onCloseRequestProperty().set(event->{
        });

    }
    public static void showScreen() {
        Stage loadingStage = new Stage();
        loadingStage.initModality(Modality.APPLICATION_MODAL);
        loadingStage.initStyle(StageStyle.UNDECORATED);

        Pane root = new Pane();

        // Establecer fondo de color
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);

        Scene scene = new Scene(root);
        loadingStage.setScene(scene);
        loadingStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            loadingStage.close();
            initGame();


        }));
        timeline.setCycleCount(1);
        timeline.play();
    }


  /*  public static void showScreen() {
        Stage loadingStage = new Stage();
        loadingStage.initModality(Modality.APPLICATION_MODAL);
        loadingStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("windows/loading.fxml"));
        try{
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            loadingStage.setScene(scene);
            loadingStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event ->{
            loadingStage.close();
            initGame();
        }));
        timeline.setCycleCount(1);
        timeline.play();


    }
*/

    public final void show(){}

    public static void openWindow(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getView(fxml));
            AnchorPane root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Nuclear Throne");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void initGame() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getView("game-view"));
            Scene scene = new Scene(fxmlLoader.load(), getWidth(), getHeight());



            if(gameStage == null){
                gameStage = new Stage();
                gameStage.setWidth(1280);
                gameStage.setHeight(720);
                gameStage.setTitle("Nuclear Throne");
                gameStage.resizableProperty().set(false);
            }

            gameStage.setScene(scene);
            gameStage.show();
            gameStage.setOnCloseRequest(event->{
                Player.resetAvatar();
                Level.resetLevels();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static URL getView(String name) {
        return MainApplication.class.getResource("windows/" + name + ".fxml");
    }

    public static File getFile(String fileName) {
        return new File(Objects.requireNonNull(MainApplication.class.getResource(fileName)).getPath());
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

    public static Image getImage(String fileName) {
        return new Image(getFile(fileName).getPath());
    }

}