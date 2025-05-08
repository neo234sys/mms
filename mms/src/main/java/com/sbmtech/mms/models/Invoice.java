package com.sbmtech.mms.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Invoice {

    @Id
    //@GeneratedValue(generator = "string-id")
    //@GenericGenerator(name = "string-id", strategy = "com.sbmtech.mms.models.StringIdGenerator")
    private String id;

    private String customerName;

    // Getters and setters
}