package com.example.desktop_downtime.service;

import com.example.desktop_downtime.model.Breakdown;
import com.example.desktop_downtime.model.BreakdownIdResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class BreakdownService {

    private final static String REST_API_URL = "http://localhost:8080/breakdown";

    private static Long tempID;


    public void onSendButtonClick() throws IOException {
        Breakdown breakdown = new Breakdown();
        breakdown.setFailureName(ComputerInfoService.getErrorName());
        breakdown.setComputerName(ComputerInfoService.getComputerName());


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

                ObjectMapper objectMapper2 = new ObjectMapper();
                BreakdownIdResponse breakdownResponse = objectMapper2.readValue(responseString, BreakdownIdResponse.class);

                tempID = breakdownResponse.getId();
            } else {
                System.out.println("Błąd podczas dodawania awarii. Status: " + statusCode);
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas wysyłania żądania.");
            e.printStackTrace();
        }
    }


    public void endButtonClick() {
        Breakdown breakdown = new Breakdown();
        breakdown.setId(tempID);
        breakdown.setDescription("Zamknieta awaria z programu hardcode");

// Wysłanie żądania POST do REST API
        try {
            HttpClient httpClient = HttpClients.createDefault();
            org.apache.http.client.methods.HttpRequestBase request = new HttpPatch(REST_API_URL);
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(breakdown);
            StringEntity entity = new StringEntity(requestBody);

            ((HttpPatch) request).setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            HttpResponse response = httpClient.execute(request);

// Obsługa odpowiedzi od serwera
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("Awaria została zamknieta");
                HttpEntity responseEntity = response.getEntity();
                String responseString = EntityUtils.toString(responseEntity);
                System.out.println("Odpowiedź z serwera: " + responseString);
            } else {
                System.out.println("Błąd podczas zamykania awarii. Status: " + statusCode);
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas wysyłania żądania.");
            e.printStackTrace();
        }
    }
}