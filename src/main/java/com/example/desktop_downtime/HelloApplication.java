package com.example.desktop_downtime;

import com.example.desktop_downtime.service.ComputerInfoService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("start.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.DECORATED); // TODO: 7/27/23 change to TRANSPARENT
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("/style.css");
        stage.setTitle(ComputerInfoService.getComputerName());
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.alwaysOnTopProperty();
        stage.show();
    }

}