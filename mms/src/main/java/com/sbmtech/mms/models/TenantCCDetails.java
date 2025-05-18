package com.sbmtech.mms.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenant_cc_details")
@Setter
@Getter
public class TenantCCDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tenantId;
    private Integer tenantUnitId;

    private String ccName;
    private String ccNo;
    private String ccCvcNo;
    private String ccExpiry;

    private Timestamp createdAt;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_due_id", referencedColumnName = "rent_due_id")
    private RentDueEntity rentDue;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}