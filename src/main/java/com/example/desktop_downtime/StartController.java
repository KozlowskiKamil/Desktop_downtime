package com.example.desktop_downtime;

import com.example.desktop_downtime.service.BreakdownService;
import com.example.desktop_downtime.service.ComputerInfoService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class StartController {

    public static String description;
    public static long waitingTime;
    public static LocalTime startTime;
    @FXML
    private Label welcomeText;
    @FXML
    private Label breakdownDescription;
    @FXML
    private Button endButton;
    @FXML
    private Label timerLabel;
    @FXML
    private TextArea myTextArea;
    @FXML
    private TextField myTextField;
    private final boolean isEndButtonVisible = false;
    private Timeline hideTextTimeline;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void endBreakedownButton(ActionEvent event) throws IOException {
        submitText(event);
        swichToScene1(event);
        BreakdownService breakdownService = new BreakdownService();
        breakdownService.endButtonClick();
    }

    @FXML
    public void swichButtonsCreateClose(ActionEvent event) throws IOException {
        endButton.setText("ZGŁOŚ\nAWARIĘ");
        welcomeText.setText("Awaria\nzgłoszona");
        startTime = LocalTime.now();
        createButton();
        swichToScene3(event);
        hideWelcomeText(Duration.seconds(5));
    }

    public void swichToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
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
        minimizeItem.setOnAction(event2 -> {
            stage.setIconified(true);
        });
        contextMenu.getItems().add(minimizeItem);
        root.setOnMousePressed(event2 -> {
            if (event2.isSecondaryButtonDown()) {
                contextMenu.show(root, event2.getScreenX(), event2.getScreenY());
            }
        });
        stage.show();
    }

    public void swichToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("description.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("/style.css");
        stage.setTitle(ComputerInfoService.getComputerName());
        double screenWidthInPixels = Screen.getPrimary().getBounds().getWidth();
        double oneCmInPixels = 0;
        double sceneWidth = 220;
        double sceneHeight = 900;
        double posX = screenWidthInPixels - sceneWidth - oneCmInPixels;
        double posY = (screenWidthInPixels - sceneHeight) / 2;
        stage.setX(posX);
        stage.setY(posY);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.alwaysOnTopProperty();
        ContextMenu contextMenu = new ContextMenu();
        MenuItem minimizeItem = new MenuItem("Minimalizuj");
        minimizeItem.setOnAction(event2 -> {
            stage.setIconified(true);
        });
        contextMenu.getItems().add(minimizeItem);
        root.setOnMousePressed(event2 -> {
            if (event2.isSecondaryButtonDown()) {
                contextMenu.show(root, event2.getScreenX(), event2.getScreenY());
            }
        });
        stage.show();
    }


    public void swichToScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("/style.css");
        stage.setTitle(ComputerInfoService.getComputerName());
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.alwaysOnTopProperty();
        double screenWidthInPixels = Screen.getPrimary().getBounds().getWidth();
        double oneCmInPixels = 210;
        double sceneHeight = 700;
        double posX = screenWidthInPixels - oneCmInPixels;
        double posY = (screenWidthInPixels - sceneHeight) / 2;
        stage.setX(posX);
        stage.setY(posY);
        ContextMenu contextMenu = new ContextMenu();
        MenuItem minimizeItem = new MenuItem("Minimalizuj");
        minimizeItem.setOnAction(event2 -> {
            stage.setIconified(true);
        });
        contextMenu.getItems().add(minimizeItem);
        root.setOnMousePressed(event2 -> {
            if (event2.isSecondaryButtonDown()) {
                contextMenu.show(root, event2.getScreenX(), event2.getScreenY());
            }
        });
        stage.show();
    }


    private void hideWelcomeText(Duration delay) {
        if (hideTextTimeline != null) {
            hideTextTimeline.stop();
        }
        welcomeText.setVisible(true);
        hideTextTimeline = new Timeline(new KeyFrame(delay, event -> welcomeText.setVisible(false)));
        hideTextTimeline.play();
    }


    private void createButton() throws IOException {
        welcomeText.setText("Wysłano zgłoszenie awarii");
        BreakdownService breakdownService = new BreakdownService();
        breakdownService.onSendButtonClick();
    }

    public void closeButton(ActionEvent event) throws IOException {
        LocalTime endTime = LocalTime.now();
        long minutes = startTime.until(endTime, ChronoUnit.MINUTES);
        long seconds = startTime.until(endTime, ChronoUnit.SECONDS);
        waitingTime = (minutes * 60) + seconds;
        System.out.println("waitingTime = " + waitingTime);
    }

    public void submitText(ActionEvent event) {
        description = myTextArea.getText();
        System.out.println("description = " + description);
    }
}