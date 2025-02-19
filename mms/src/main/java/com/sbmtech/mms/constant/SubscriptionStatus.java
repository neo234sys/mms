package com.sbmtech.mms.constant;
public enum SubscriptionStatus 
{
    TRIAL("TRIAL"), 
    ACTIVE("ACTIVE"), 
    EXPIRED("EXPIRED");
 
    private String status;
 
    SubscriptionStatus(String status) {
        this.status = status;
    }
 
    public String getStatus() {
        return status;
    }
}