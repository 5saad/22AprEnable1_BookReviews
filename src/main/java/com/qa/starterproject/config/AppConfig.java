package com.qa.starterproject.config;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public String serverStart() {
		return "Server started at: " + LocalTime.now();
	}
	
	@Bean
	public ModelMapper mapToDTO() {
		return new ModelMapper();
	}
}
