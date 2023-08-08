package com.example.desktop_downtime;

import com.example.desktop_downtime.service.BreakdownService;
import com.example.desktop_downtime.service.ComputerInfoService;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class StartController {

    public static String description;
    public static long waitingTime;
    @FXML
    private Label welcomeText;
    @FXML
    private Button endButton;
    @FXML
    private Label timerLabel;
    @FXML
    private TextArea myTextArea;
    private boolean isEndButtonVisible = false;
    private Timeline hideTextTimeline;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private LocalTime startTime;


    @FXML
    private void endBreakedownButton(ActionEvent event) throws IOException {
        submitText(event);
        swichToScene1(event);
        BreakdownService breakdownService = new BreakdownService();
        breakdownService.endButtonClick();
    }


    @FXML
    public void swichButtonsCreateClose(ActionEvent event) throws IOException {
        if (isEndButtonVisible) {
            closeButton(event);
            welcomeText.setText("Zamknięto awarię");
            endButton.setText("ZGŁOŚ\nAWARIĘ");
            isEndButtonVisible = false;
        } else {
            createButton();
            welcomeText.setText("Zgłoszono awarię!");
            endButton.setText("ZAMKNIJ\nAWARIĘ");
            isEndButtonVisible = true;
            startTime = LocalTime.now();
            endButton.getStyleClass().add("buttonEnd");

        }
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
        stage.show();
    }

    public void swichToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("description.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("/style.css");
        stage.setTitle(ComputerInfoService.getComputerName());
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.alwaysOnTopProperty();
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

    private void closeButton(ActionEvent event) throws IOException {
        swichToScene2(event);
        LocalTime endTime = LocalTime.now();
//        long minutes = startTime.until(endTime, ChronoUnit.MINUTES);
        long seconds = startTime.until(endTime, ChronoUnit.SECONDS) % 60;
//        waitingTime = LocalTime.of(0, (int) minutes, (int) seconds);
        waitingTime = seconds;
        System.out.println("waitingTime = " + waitingTime);
    }

    public void submitText(ActionEvent event) {
        description = myTextArea.getText();
        System.out.println("description = " + description);
    }

    public void initialize() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (startTime != null) {
                    LocalTime currentTime = LocalTime.now();
                    long minutes = startTime.until(currentTime, ChronoUnit.MINUTES);
                    long seconds = startTime.until(currentTime, ChronoUnit.SECONDS) % 60;
                    timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
                }
            }
        };
        timer.start();
    }

}