package com.sbmtech.mms.service;

import java.util.Map;

import com.sbmtech.mms.models.ProductConfig;

public interface ProductConfigService {
    ProductConfig getProductConfigBySubscriberId(Integer subscriberId);
    ProductConfig saveOrUpdateProductConfig(Integer subscriberId, Map<String, String> config);
    void deleteProductConfig(Integer subscriberId);
}