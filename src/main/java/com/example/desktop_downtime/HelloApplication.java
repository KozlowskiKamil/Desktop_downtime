package com.example.desktop_downtime;

import com.example.desktop_downtime.service.ComputerInfoService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
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
        Image image = new Image(getClass().getResource("/ico/icoapp.png").toExternalForm());
        stage.getIcons().add(image);
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("/style.css");
        stage.setTitle(ComputerInfoService.getComputerName());
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.alwaysOnTopProperty();
        double screenWidthInPixels = Screen.getPrimary().getBounds().getWidth();
        double oneCmInPixels = 100;
        double sceneHeight = 700;
        double posX = screenWidthInPixels - oneCmInPixels;
        double posY = (screenWidthInPixels - sceneHeight) / 2;
        stage.setX(posX);
        stage.setY(posY);
        ContextMenu contextMenu = new ContextMenu();
        MenuItem minimizeItem = new MenuItem("Minimalizuj");
        minimizeItem.setOnAction(event -> {
            stage.setIconified(true);
        });
        contextMenu.getItems().add(minimizeItem);
        root.setOnMousePressed(event -> {
            if (event.isSecondaryButtonDown()) {
                contextMenu.show(root, event.getScreenX(), event.getScreenY());
            }
        });
        stage.show();
    }
}