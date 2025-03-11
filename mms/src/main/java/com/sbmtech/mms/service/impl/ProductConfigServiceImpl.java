package com.sbmtech.mms.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.models.ProductConfig;
import com.sbmtech.mms.repository.ProductConfigRepository;
import com.sbmtech.mms.service.ProductConfigService;

@Service
public class ProductConfigServiceImpl implements ProductConfigService {

    @Autowired
    private ProductConfigRepository productConfigRepository;

    @Override
    public ProductConfig getProductConfigBySubscriberId(Integer subscriberId) {
        return productConfigRepository.findBySubscriberId(subscriberId);
    }

    @Override
    public ProductConfig saveOrUpdateProductConfig(Integer subscriberId, Map<String, String> config) {
        ProductConfig productConfig = productConfigRepository.findBySubscriberId(subscriberId);
        if (productConfig == null) {
            productConfig = new ProductConfig();
            productConfig.setSubscriberId(subscriberId);
        }
        productConfig.setConfigjson(config);
        return productConfigRepository.save(productConfig);
    }

    @Override
    public void deleteProductConfig(Integer subscriberId) {
        productConfigRepository.deleteById(subscriberId);
    }
}