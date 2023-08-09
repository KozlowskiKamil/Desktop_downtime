package com.example.desktop_downtime.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ComputerInfoService {

    private static final String FILE_PATH = "C:\\Users\\Kamil\\Desktop\\Projects\\Desktop_downtime\\src\\main\\resources\\test.txt";
    private static final String startWord = "fail";


    public static String getComputerName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Brak nazwy PC";
        }
    }

    public static String getErrorName() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try
                (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(startWord);
                if (index != -1) {
                    stringBuilder.append(line.substring(index)).append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }
}