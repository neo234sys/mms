package com.sbmtech.mms.models;


import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.hypersistence.utils.hibernate.type.json.JsonType; 
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "subscription_plan_master")
@Setter
@Getter
@TypeDef(name = "json", typeClass = JsonType.class)
public class SubscriptionPlanMaster {

    @Id
    @Column(name = "plan_id")
    private Integer planId;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(name = "price_month", nullable = false)
    private Double priceMonth;
    
    @Column(name = "price_year", nullable = false)
    private Double priceYear;
    
    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "duration_in_days", nullable = false)
    private Integer durationInDays;
    
    @Column(name = "trial_days", nullable = false)
    private Integer trialDays;

    
    @Column(name = "description")
    private String description;

   
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<String> features;
    
    
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, Object> metadata;
    
    
    @Column(name = "active", nullable = false)
    private Integer active;
    
    @Column(name = "plan_category")
    private String planCategory;
}
