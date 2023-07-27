package com.example.desktop_downtime;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onSendButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

        // Wywołaj metodę sendBreakdown() z klasy HelloApplication
        HelloApplication helloApp = new HelloApplication();
        helloApp.onSendButtonClick();
    }
}