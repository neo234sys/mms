package com.sbmtech.mms.config;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class VelocityEngineConfig {

	@Value("${email.templatePath}")
	private String emailTemplatePath;
	
	@Autowired
    private ResourceLoader resourceLoader;
	
	@Bean
	VelocityEngine velocityEngine() throws VelocityException, IOException {
		Resource resource = resourceLoader.getResource("classpath:" + emailTemplatePath);
		File templateDir=resource.getFile();

		Properties props = new Properties();
		props.put(RuntimeConstants.RESOURCE_LOADER, "file");
		props.put(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templateDir.toPath().toString());
		VelocityEngine velocityEngine = new VelocityEngine(props);
		velocityEngine.init();
		return velocityEngine;
	}
}