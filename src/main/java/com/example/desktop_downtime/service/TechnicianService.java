package com.example.desktop_downtime.service;

import com.example.desktop_downtime.StartController;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.desktop_downtime.service.BreakdownService.tempID;

public class TechnicianService {

    private final static String REST_API_CHECK_BADGE_NUMBER = "http://localhost:8080/checkbadgenumber";



    public void loginButtonClick() {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(REST_API_CHECK_BADGE_NUMBER);
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("badgeNumber", String.valueOf(1)));
//            params.add(new BasicNameValuePair("breakdownId", String.valueOf(tempID)));
//            params.add(new BasicNameValuePair("waitingTime", String.valueOf(StartController.waitingTime)));
            request.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpClient.execute(request);
// Obsługa odpowiedzi od serwera
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("Wysłano czas oczekiwania i przypisano technika");
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
