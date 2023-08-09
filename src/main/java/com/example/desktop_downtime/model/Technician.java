package com.example.desktop_downtime.model;

public class Technician {


    private Long id;
    private String name;
    private int badgeNumber;

    public Technician() {
    }

    public Technician(Long id, String name, int badgeNumber) {
        this.id = id;
        this.name = name;
        this.badgeNumber = badgeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(int badgeNumber) {
        this.badgeNumber = badgeNumber;
    }
}
