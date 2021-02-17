package com.twilio.moviesbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class TwilioVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwilioVerificationApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper;
	}
}
