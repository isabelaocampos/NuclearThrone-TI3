package com.example.nuclearthrone;

import com.example.nuclearthrone.model.level.Level;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MainApplication extends Application {

    static Stage gameStage;

    @FXML
    private Button playButton;

    @FXML
    private Button manualButton;

    @FXML
    private Button creditsButton;

    @FXML
    private Button quitButton;

    public static void main(String[] args) {
        launch();
    }

    private Canvas canvas;




    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Main Menu");

        FXMLLoader fxmlLoader = new FXMLLoader(getView("main-menu"));
        AnchorPane root = fxmlLoader.load();

        Rectangle background = new Rectangle(800, 600);
        background.setFill(Color.BLUE);

        // Buttons references
        playButton = (Button) fxmlLoader.getNamespace().get("playButton");
        manualButton = (Button) fxmlLoader.getNamespace().get("manualButton");
        creditsButton = (Button) fxmlLoader.getNamespace().get("creditsButton");
        quitButton = (Button) fxmlLoader.getNamespace().get("quitButton");

        playButton.setOnAction(e -> {
            showScreen();
        });

        manualButton.setOnAction( e -> {
            openWindow("manual");
        });

        creditsButton.setOnAction(e -> {
            openWindow("credits");
        });

        quitButton.setOnAction(e -> {
            stage.close();
        });

        VBox vBox = new VBox(20, playButton, manualButton, creditsButton, quitButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setTranslateX(260);
        vBox.setTranslateY(200);

        root.getChildren().addAll(background, vBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.onCloseRequestProperty().set(event->{
        });

    }

    public static void showScreen() {
        Stage loadingStage = new Stage();

            initGame();

    }

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
            FXMLLoader fxmlLoader = new FXMLLoader(getView("game"));
            Scene scene = new Scene(fxmlLoader.load(), getWidth(), getHeight());

            ImageCursor cursor = new ImageCursor(new Image(getFile("cursor.png").getPath()),30,30);
            scene.setCursor(cursor);

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
                //Player.resetAvatar();
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