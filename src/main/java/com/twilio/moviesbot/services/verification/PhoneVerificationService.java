package com.twilio.moviesbot.services.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneVerificationService {

	@Autowired
	private TwilioVerifierAdaptee phoneVerification;
	
	public String sendVerificationToken(String phoneNumber, String channel) {
		return phoneVerification.sendVerificationToken(phoneNumber, channel);
	}
	
	public String checkVerificationToken(String phoneNumber, String code) {
		return phoneVerification.checkVerificationToken(phoneNumber, code);
	}

}
