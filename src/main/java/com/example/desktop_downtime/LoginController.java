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

import static com.example.desktop_downtime.service.TechnicianService.technicianName;

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

    public void submit(ActionEvent event) throws IOException {
        try {
            badgeNumber = Integer.parseInt(myTextField.getText());
        } catch (NumberFormatException e) {
            myLabel.setText("Wprowadź tylko liczby");
        } catch (Exception e) {
            myLabel.setText("error");
        }
        TechnicianService technicianService = new TechnicianService();
        technicianService.loginButtonClick(badgeNumber);
        if (technicianName != null) {
            assingBreakedownButton(event);
            StartController startController = new StartController();
            startController.swichToScene2(event);
            technicianName = null;
        } else {
            myLabel.setText("Niepoprawny BT");
        }

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
