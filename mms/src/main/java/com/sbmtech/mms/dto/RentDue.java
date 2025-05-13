package com.sbmtech.mms.dto;

import java.time.LocalDate;

public class RentDue {
    private LocalDate dueDate;
    private double amount;

    public RentDue(LocalDate dueDate, double amount) {
        this.dueDate = dueDate;
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "RentDue{dueDate=" + dueDate + ", amount=" + amount + '}';
    }
}