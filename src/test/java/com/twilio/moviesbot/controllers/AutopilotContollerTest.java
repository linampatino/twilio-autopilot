package com.twilio.moviesbot.controllers;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.moviesbot.dtos.AutopilotRequestDto;
import com.twilio.moviesbot.dtos.ValidateResponseDto;
import com.twilio.moviesbot.dtos.autopilot.ActionDto;
import com.twilio.moviesbot.dtos.autopilot.ActionSayDto;
import com.twilio.moviesbot.services.AutopilotService;

@WebMvcTest(AutopilotController.class)
public class AutopilotContollerTest {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AutopilotService service;

	@InjectMocks
	private AutopilotController contoller;

	@Test
	public void when_getQuestions_thenReturn_200() throws Exception {
		AutopilotRequestDto request = new AutopilotRequestDto();
		ActionDto response = new ActionDto();

		when(service.getQuestions(any(AutopilotRequestDto.class))).thenReturn(response);

		mvc.perform(post("http://localhost:8080/v1/autopilot/questions")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.content(mapper.writeValueAsString(request)))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void when_getQuestions_thenReturn_500() throws Exception {
		AutopilotRequestDto request = new AutopilotRequestDto();

		when(service.getQuestions(any(AutopilotRequestDto.class))).thenThrow(IOException.class);

		mvc.perform(post("http://localhost:8080/v1/autopilot/questions")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.content(mapper.writeValueAsString(request)))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
	
	@Test
	public void when_validateMovie_thenReturn_200() throws Exception {
		AutopilotRequestDto request = new AutopilotRequestDto();
		ValidateResponseDto response = new ValidateResponseDto();

		when(service.validateMovie(any(AutopilotRequestDto.class))).thenReturn(response);

		mvc.perform(post("http://localhost:8080/v1/autopilot/validateMovie")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.content(mapper.writeValueAsString(request)))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void when_validateMovie_thenReturn_500() throws Exception {
		AutopilotRequestDto request = new AutopilotRequestDto();

		when(service.validateMovie(any(AutopilotRequestDto.class))).thenThrow(IOException.class);

		mvc.perform(post("http://localhost:8080/v1/autopilot/validateMovie")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.content(mapper.writeValueAsString(request)))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
	
	@Test
	public void when_validateQuestions_thenReturn_200() throws Exception {
		AutopilotRequestDto request = new AutopilotRequestDto();
		ActionSayDto response = new ActionSayDto();

		when(service.validateQuestions(any(AutopilotRequestDto.class))).thenReturn(response);

		mvc.perform(post("http://localhost:8080/v1/autopilot/validateCollect")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.content(mapper.writeValueAsString(request)))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void when_validateQuestions_thenReturn_500() throws Exception {
		AutopilotRequestDto request = new AutopilotRequestDto();

		when(service.validateQuestions(any(AutopilotRequestDto.class))).thenThrow(JsonMappingException.class);

		mvc.perform(post("http://localhost:8080/v1/autopilot/validateCollect")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.content(mapper.writeValueAsString(request)))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
}
