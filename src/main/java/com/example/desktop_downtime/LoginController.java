package com.example.desktop_downtime;

import com.example.desktop_downtime.service.BreakdownService;
import com.example.desktop_downtime.service.TechnicianService;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;

public class LoginController {


    int badgeNumber;
    @FXML
    private Label myLabel;
    @FXML
    private TextField myTextField;
    @FXML
    private Label timerLabel;
    @FXML
    private Button loginButton;


    public static int badgeNumberInt;

    public void submit(ActionEvent event) {
        TechnicianService technicianService = new TechnicianService();
        try {
            badgeNumber = Integer.parseInt(myTextField.getText());
            badgeNumberInt = badgeNumber;
            System.out.println("badgeNumberInt = " + badgeNumberInt);

        } catch (NumberFormatException e) {
            myLabel.setText("Wprowadź tylko liczby");
        } catch (Exception e) {
            myLabel.setText("error");
        }
        technicianService.loginButtonClick();
    }


    private void assingBreakedownButton(ActionEvent event) throws IOException {
        StartController startController = new StartController();
        startController.closeButton(event);
        BreakdownService breakdownService = new BreakdownService();
        breakdownService.assingButtonClick();
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
