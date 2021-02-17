package com.twilio.moviesbot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.moviesbot.dtos.AutopilotRequestDto;
import com.twilio.moviesbot.dtos.ValidateResponseDto;
import com.twilio.moviesbot.dtos.autopilot.ActionDto;
import com.twilio.moviesbot.dtos.autopilot.ActionSayDto;
import com.twilio.moviesbot.services.AutopilotService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/autopilot/")
@Slf4j
public class AutopilotController {

	@Autowired
	AutopilotService autopilotService;

	@PostMapping(path = "questions", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })

	public ResponseEntity<ActionDto> getQuestions(AutopilotRequestDto request) {
		ActionDto action = null;
		try {

			log.info("Request :: {}", request);
			action = autopilotService.getQuestions(request);
			log.info("Response :: {} ", action);

		} catch (Exception e) {
			log.error("Error in validateCollect {}", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(action);
	}

	@PostMapping(path = "validateMovie", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public ResponseEntity<ValidateResponseDto> validateMovie(AutopilotRequestDto request) {
		ValidateResponseDto action = null;
		try {

			log.info("Request :: {}", request);
			action = autopilotService.validateMovie(request);
			log.info("Response :: {} ", action);

		} catch (Exception e) {
			log.error("Error in validateCollect {}", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(action);
	}

	@PostMapping(path = "validateCollect", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public ResponseEntity<ActionSayDto> validateQuestions(AutopilotRequestDto request) {
		ActionSayDto action = null;
		try {

			log.info("Request :: {}", request);
			action = autopilotService.validateQuestions(request);
			log.info("Response :: {} ", action);

		} catch (Exception e) {
			log.error("Error in validateCollect {}", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(action);
	}

}
