package com.twilio.moviesbot.business.verification;

import org.springframework.stereotype.Component;

@Component
public interface PhoneVerifierAdapter {

	public String sendVerificationToken(String phoneNumber, String channel);

	public String checkVerificationToken(String phoneNumber, String code);

}
