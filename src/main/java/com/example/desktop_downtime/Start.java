package com.example.desktop_downtime;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        String wordToSearch = "fail";

        try {
            String filePath = "C:\\Users\\Kamil\\Desktop\\Projects\\Desktop_downtime\\src\\main\\resources\\test.txt";
            String sentence = findSentenceAfterWord(filePath, wordToSearch);
            textArea.setText("Zdanie po słowie \"" + wordToSearch + "\":\n" + sentence);
        } catch (IOException e) {
            textArea.setText("Błąd odczytu pliku: " + e.getMessage());
        }

        StackPane root = new StackPane(textArea);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Zdanie po słowie w pliku txt");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String findSentenceAfterWord(String filePath, String wordToSearch) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(wordToSearch);
                if (index != -1) {
                    result.append(line.substring(index)).append("\n");
                }
            }
        }
        return result.toString();
    }
}
