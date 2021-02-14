package com.twilio.moviesbot.services.verification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

@Component
public class TwilioVerifierAdaptee implements PhoneVerifierAdapter{

    
	@Value("${twilio.service.id}")
	public String SERVICE_SID;
    
	@Override
	public String sendVerificationToken(String phoneNumber, String channel) {
		Verification verification = Verification.creator(SERVICE_SID, phoneNumber, channel).create();
		return verification.getStatus();
	}

	@Override
	public String checkVerificationToken(String phoneNumber, String code) {
		VerificationCheck verificationCheck = VerificationCheck.creator(SERVICE_SID, code).setTo(phoneNumber).create();
		return verificationCheck.getStatus();
	}

}
