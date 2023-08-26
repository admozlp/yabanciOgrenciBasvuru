package com.ahievran.yabanciOgrenciBasvuru;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class YabanciOgrenciBasvuruApplication extends SpringBootServletInitializer{

	  @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    return builder.sources(YabanciOgrenciBasvuruApplication.class);
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(YabanciOgrenciBasvuruApplication.class, args);
	}
	

	
	  
	  
	  
	  
	  
	  
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean 
	public Random getRandom() {
		return new Random();
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
