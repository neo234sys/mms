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
@Table(name = "subscription_plan")
@Setter
@Getter
@TypeDef(name = "json", typeClass = JsonType.class)
public class SubscriptionPlan {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "duration_in_months", nullable = false)
    private Integer durationInMonths;

    @Column(name = "description")
    private String description;

   
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<String> features;
    
    
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, String> metadata;
}
