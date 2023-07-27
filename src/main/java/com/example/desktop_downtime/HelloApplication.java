package com.example.desktop_downtime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.j

import java.io.IOException;

public class HelloApplication extends Application {

    private final static String REST_API_URL = "http://localhost:8080/breakdown"; // URL do Twojego REST API

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    // Metoda do obsługi zdarzenia kliknięcia przycisku
    public void sendBreakdown() {
        // Tutaj stworzysz obiekt reprezentujący awarię (Breakdown) na podstawie danych wprowadzonych przez użytkownika w interfejsie graficznym

        Breakdown breakdown = new Breakdown();
        breakdown.setFailureName("Przykładowa awaria");

        // Wysłanie żądania POST do REST API
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(breakdown);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(REST_API_URL))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Obsługa odpowiedzi od serwera
            if (response.statusCode() == 201) {
                System.out.println("Awaria została pomyślnie dodana na serwerze.");
            } else {
                System.out.println("Błąd podczas dodawania awarii. Status: " + response.statusCode());
                System.out.println("Wiadomość od serwera: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Wystąpił błąd podczas wysyłania żądania.");
            e.printStackTrace();
        }
    }
}