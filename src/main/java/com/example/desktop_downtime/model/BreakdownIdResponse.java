package com.example.desktop_downtime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class BreakdownIdResponse {

    private Long id;
    private String failureName;
    private String computerName;
    private String description;
    private boolean ongoing;

    @JsonIgnore
    private long counter;
    @JsonIgnore
    private LocalDateTime failureStartTime;


    private long waitingTime;

    @JsonIgnore
    private LocalDateTime failureEndTime;

    public BreakdownIdResponse() {
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(long waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFailureName() {
        return failureName;
    }

    public void setFailureName(String failureName) {
        this.failureName = failureName;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOngoing() {
        return ongoing;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public LocalDateTime getFailureStartTime() {
        return failureStartTime;
    }

    public void setFailureStartTime(LocalDateTime failureStartTime) {
        this.failureStartTime = failureStartTime;
    }

    public LocalDateTime getFailureEndTime() {
        return failureEndTime;
    }

    public void setFailureEndTime(LocalDateTime failureEndTime) {
        this.failureEndTime = failureEndTime;
    }
}
