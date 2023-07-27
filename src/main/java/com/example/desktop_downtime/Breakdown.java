package com.example.desktop_downtime;

import java.time.LocalDateTime;

public class Breakdown {

    Long id;
    String failureName;
    private LocalDateTime failureStartTime;

    public Breakdown() {
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

    public Breakdown(Long id, String failureName, LocalDateTime failureStartTime) {
        this.id = id;
        this.failureName = failureName;
        this.failureStartTime = failureStartTime;
    }
}
