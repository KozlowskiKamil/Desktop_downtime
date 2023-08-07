package com.example.desktop_downtime;

import java.time.LocalDateTime;

public class Breakdown {

    private Long id;
    private String failureName;
    private String computerName;
    private String description;
    private boolean ongoing;
    private long counter;
    private LocalDateTime failureStartTime;
    private LocalDateTime failureEndTime;

    public Breakdown() {
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

    public LocalDateTime getFailureEndTime() {
        return failureEndTime;
    }

    public void setFailureEndTime(LocalDateTime failureEndTime) {
        this.failureEndTime = failureEndTime;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
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

    public LocalDateTime getFailureStartTime() {
        return failureStartTime;
    }

    public void setFailureStartTime(LocalDateTime failureStartTime) {
        this.failureStartTime = failureStartTime;
    }

}
