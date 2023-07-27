package com.example.desktop_downtime;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class BreakdownService {

    private final static String REST_API_URL = "http://localhost:8080/breakdown";

    public void onSendButtonClick() throws IOException {
// Tworze obiekt Breakdown na podstawie danych UI użytkownika

        Breakdown breakdown = new Breakdown();
        breakdown.setFailureName(ComputerInfo.getErrorName());
        breakdown.setComputerName(ComputerInfo.getComputerName());


// Wysłanie żądania POST do REST API
        try {
            HttpClient httpClient = HttpClients.createDefault();
            org.apache.http.client.methods.HttpRequestBase request = new HttpPost(REST_API_URL);
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(breakdown);
            StringEntity entity = new StringEntity(requestBody);

            ((HttpPost) request).setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            HttpResponse response = httpClient.execute(request);

// Obsługa odpowiedzi od serwera
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 201) {
                System.out.println("Awaria została pomyślnie dodana na serwerze.");
                HttpEntity responseEntity = response.getEntity();
                String responseString = EntityUtils.toString(responseEntity);
                System.out.println("Odpowiedź od serwera: " + responseString);
            } else {
                System.out.println("Błąd podczas dodawania awarii. Status: " + statusCode);
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas wysyłania żądania.");
            e.printStackTrace();
        }
    }
}