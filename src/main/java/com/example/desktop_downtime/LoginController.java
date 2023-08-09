package com.example.desktop_downtime;

import com.example.desktop_downtime.service.BreakdownService;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LoginController {


    @FXML
    private Label welcomeText;
    @FXML
    private TextField myTextField;
    @FXML
    private Label timerLabel;


    @FXML
    private void assingBreakedownButton(ActionEvent event) throws IOException {
        StartController startController = new StartController();
        startController.closeButton(event);
        BreakdownService breakdownService = new BreakdownService();
        breakdownService.assingButtonClick();

    }

    @FXML
    private void loginTechnicanButton(ActionEvent event) throws IOException {


    }




    public void initialize() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (StartController.startTime != null) {
                    LocalTime currentTime = LocalTime.now();
                    long minutes = StartController.startTime.until(currentTime, ChronoUnit.MINUTES);
                    long seconds = StartController.startTime.until(currentTime, ChronoUnit.SECONDS) % 60;
                    timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
                }
            }
        };
        timer.start();
    }
}
