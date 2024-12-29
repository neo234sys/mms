package com.sbmtech.mms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


//@SpringBootApplication
//public class MMSApplication {
////https://www.bezkoder.com/spring-boot-jwt-authentication/
//	public static void main(String[] args) {
//    SpringApplication.run(MMSApplication.class, args);
//	}
//
//}


@SpringBootApplication
public class MMSApplication extends SpringBootServletInitializer {
 
	public static void main(String[] args) {
		SpringApplication.run(MMSApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MMSApplication.class);
	}

}
