package com.example.desktop_downtime;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private Button endButton;

    private boolean isEndButtonVisible = false;

    @FXML
    public void onSendEndButtonClick() throws IOException {
        if (isEndButtonVisible) {
            // Wykonaj akcję przypisaną do przycisku "Zamknij awarię"
            endButtonClick();
            // Zmień etykietę i przypisaną akcję przycisku na "Zgłoś awarię"
            welcomeText.setText("Witaj w aplikacji Desktop Downtime!");
            endButton.setText("Zgłoś awarię");
            isEndButtonVisible = false;
        } else {
            // Wykonaj akcję przypisaną do przycisku "Zgłoś awarię"
            onSendButtonClick();
            // Zmień etykietę i przypisaną akcję przycisku na "Zamknij awarię"
            welcomeText.setText("Zgłoszono awarię!");
            endButton.setText("Zamknij awarię");
            isEndButtonVisible = true;
        }
    }


    private void onSendButtonClick() throws IOException {
        welcomeText.setText("Wysłano zgłoszenie awarii");
        BreakdownService breakdownService = new BreakdownService();
        breakdownService.onSendButtonClick();
    }

    private void endButtonClick() {
        welcomeText.setText("Awaria została zamknięta");
        BreakdownService breakdownService = new BreakdownService();
        breakdownService.endButtonClick();
    }

}