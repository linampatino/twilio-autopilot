package com.twilio.moviesbot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.moviesbot.services.PhoneVerificationService;

@RestController
public class PhoneVerificationController {

	@Autowired
	private PhoneVerificationService phoneVerificationService;

	@PostMapping("/phone-verification/send-token/{phoneNumber}/{channel}")
	public ResponseEntity<String> sendVerificationToken(@PathVariable String phoneNumber,
			@PathVariable String channel) {
		String status = phoneVerificationService.sendVerificationToken(phoneNumber, channel);
		return ResponseEntity.ok(status);
	}

	@PostMapping("/phone-verification/check-token/{phoneNumber}/{channel}")
	public ResponseEntity<String> checkVerificationToken(@PathVariable String phoneNumber, @PathVariable String code) {
		String status = phoneVerificationService.checkVerificationToken(phoneNumber, code);
		return ResponseEntity.ok(status);
	}

}
