package com.example.desktop_downtime;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Start {

    private static final String DIRECTORY_PATH = "C:\\Users\\Kamil\\Desktop\\Projects\\Desktop_downtime\\src\\main\\resources\\";
    private static final String PARTIAL_FILENAME = "text";
    private static final String START_WORD = "fail";

    public static List<String> readFilesWithPartialName() {
        List<String> results = new ArrayList<>();

        File directory = new File(DIRECTORY_PATH);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().startsWith(PARTIAL_FILENAME)) {
                    try {
                        results.add(readErrorNameFromFile(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                        results.add("Błąd odczytu pliku: " + file.getName());
                    }
                }
            }
        }

        return results;
    }

    private static String readErrorNameFromFile(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(START_WORD);
                if (index != -1) {
                    stringBuilder.append(line.substring(index)).append("\n");
                }
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        List<String> results = readFilesWithPartialName();
        for (String result : results) {
            System.out.println(result);
        }
    }
}