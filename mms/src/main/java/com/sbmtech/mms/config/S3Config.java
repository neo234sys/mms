package com.sbmtech.mms.config;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.sbmtech.mms.service.impl.AppSystemPropImpl;

@Configuration
@DependsOn("AppSystemProp")
public class S3Config {
	
	String ak="";
	String aks="";
	
	@PostConstruct
	public void initialize() {

		ak = AppSystemPropImpl.props.get("ak");
		aks = AppSystemPropImpl.props.get("aks");
	}

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.US_EAST_1) // Replace with your region
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ak, aks)))
                .build();
    }
    
    
    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ak, aks)
                ))
                .build();
    }
}