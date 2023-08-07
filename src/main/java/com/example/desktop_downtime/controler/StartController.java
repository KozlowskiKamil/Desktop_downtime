package com.example.desktop_downtime.controler;

import com.example.desktop_downtime.service.BreakdownService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class StartController {


    @FXML
    private TextField myTextField;

    @FXML
    private Label welcomeText;

    @FXML
    private Button endButton;

    private boolean isEndButtonVisible = false;

    private Timeline hideTextTimeline;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToStart(ActionEvent event){

    }
    public void switchToDescription(ActionEvent event){

    }

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