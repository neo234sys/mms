package com.sbmtech.mms.models;

public enum PaymentPurposeEnum {
    SECURITY_DEPOSIT(1, "Security Deposit"),
    RENT(2, "Rent"), 
    MAINTENANCE(3, "Maintenance"), 
    RESERVATION(4, "Reservation");
    
    private final int value;
    private final String displayName;
    
    private PaymentPurposeEnum(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }
    
    public int getValue() {
        return value;
    }
    
    // Returns the formatted name (e.g., "Security Deposit")
    public String getName() {
        return displayName;
    }
    
    // Override toString() if needed
    @Override
    public String toString() {
        return displayName;
    }
}