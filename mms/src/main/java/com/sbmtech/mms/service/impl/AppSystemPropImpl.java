package com.sbmtech.mms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sbmtech.mms.models.AppProperties;
import com.sbmtech.mms.repository.AppSysPropRepository;

import lombok.Getter;

@Component("AppSystemProp")
@Order(1)
@Getter
public class AppSystemPropImpl  {

	public static Map<String, String> props = new HashMap<String, String>();

	@Autowired
	AppSysPropRepository dao;

	@PostConstruct
	public void loadProperties() {
		System.out.println("<<<<<<<<<<<<<DB prop Loaded >>>>>>>>>>>>");
		List<AppProperties> list = dao.findAll();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				AppProperties entity = list.get(i);

				if (entity != null) {

					String propName = entity.getName();
					String propVal = entity.getValue();

					if ((propName != null && !propName.isEmpty()) && (propVal != null && !propVal.isEmpty())) {

						props.put(propName.trim(), propVal.trim());
					}
				}
			}
		}
	}
}
