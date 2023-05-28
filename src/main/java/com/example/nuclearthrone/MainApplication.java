package com.example.nuclearthrone;

import com.example.nuclearthrone.control.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            MainController controller = fxmlLoader.getController();
            controller.setRunning(false);

        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}