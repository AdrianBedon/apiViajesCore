package com.arbc.development.mvc.models.entities;

import java.time.LocalDate;

public class PeakReport {

    LocalDate startDate;
    LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
