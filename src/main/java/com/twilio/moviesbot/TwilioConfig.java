package com.twilio.moviesbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioConfig {


	@Value("${twilio.account.sid}")
	private String ACCOUNT_SID;
	
	@Value("${twilio.auth.token}")
	private String AUTH_TOKEN;
	
	@Bean
	public void init(){
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}
}
