package com.example.desktop_downtime.service;

import com.example.desktop_downtime.LoginController;
import com.example.desktop_downtime.StartController;
import com.example.desktop_downtime.model.Technician;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static Long TechnicianID;
    public static String TechnicianName;

    public void loginButtonClick() {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(REST_API_CHECK_BADGE_NUMBER);
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("badgeNumber", String.valueOf(LoginController.badgeNumberInt)));
            request.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpClient.execute(request);
// Obsługa odpowiedzi od serwera
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("Zalogowano");
                HttpEntity responseEntity = response.getEntity();
                String responseString = EntityUtils.toString(responseEntity);
                System.out.println("Odpowiedź z serwera: " + responseString);
                ObjectMapper objectMapper = new ObjectMapper();
                Technician technician = objectMapper.readValue(responseString, Technician.class);
                TechnicianID = technician.getId();
                TechnicianName = technician.getName();
                System.out.println("TechnicianIDtuuuu = " + TechnicianID);
                System.out.println("TechnicianName tuuuu= " + TechnicianName);
            } else {
                System.out.println("Brak BT w bazie: ");
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas wysyłania żądania.");
            e.printStackTrace();
        }
    }
}
