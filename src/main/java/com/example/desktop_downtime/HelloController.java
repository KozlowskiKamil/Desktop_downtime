package com.example.desktop_downtime;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private Button endButton;

    private boolean isEndButtonVisible = false;

    private Timeline hideTextTimeline;


    @FXML
    public void swichButtonsCreateClose() throws IOException {
        if (isEndButtonVisible) {
            closeButton();
            welcomeText.setText("Zamknięto awarię");
            endButton.setText("Zgłoś awarię");
            isEndButtonVisible = false;
        } else {
            createButton();
            welcomeText.setText("Zgłoszono awarię!");
            endButton.setText("Zamknij awarię");
            isEndButtonVisible = true;
        }

        hideWelcomeText(Duration.seconds(5));

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

    private void closeButton() {
        welcomeText.setText("Awaria została zamknięta");
        BreakdownService breakdownService = new BreakdownService();
        breakdownService.endButtonClick();
    }

}