package com.example.desktop_downtime;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onSendButtonClick() {
        welcomeText.setText("Wysłano zgłoszenie awarii");
// Wywołaj metodę sendBreakdown() z klasy HelloApplication
        BreakdownService breakdownService = new BreakdownService();
        breakdownService.onSendButtonClick();
    }

}