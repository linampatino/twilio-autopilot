package com.twilio.moviesbot.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.moviesbot.dtos.ActionDto;
import com.twilio.moviesbot.dtos.AutopilotRequestDto;
import com.twilio.moviesbot.services.AutopilotService;

@RestController
public class AutopilotController {
	
	@Autowired
	AutopilotService autopilotService;
	
	@PostMapping(path = "/autopilot/webhook", 
	  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})

	public ResponseEntity<ActionDto> sendVerificationToken(AutopilotRequestDto request){
		ActionDto action = null;
		try {
			action = autopilotService.webhook(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(action);
	}


}
