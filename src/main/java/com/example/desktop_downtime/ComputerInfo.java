package com.example.desktop_downtime;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ComputerInfo {


    protected static String getComputerName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Unknown name";
        }
    }
}