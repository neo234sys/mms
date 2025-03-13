package com.sbmtech.mms.models;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class ProductConfigJson {
    private String key1;
    private Integer key2;
    private Map<String, String> key3; // Nested object example
}