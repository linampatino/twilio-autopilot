package com.twilio.moviesbot.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Assertions;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.moviesbot.business.autopilot.CollectedDataParser;
import com.twilio.moviesbot.business.autopilot.MovieValidator;
import com.twilio.moviesbot.dtos.autopilot.ActionDto;
import com.twilio.moviesbot.dtos.autopilot.ActionSayDto;
import com.twilio.moviesbot.dtos.autopilot.AutopilotRequestDto;
import com.twilio.moviesbot.dtos.autopilot.ValidateResponseDto;
import com.twilio.moviesbot.exceptions.MovieBotException;
import com.twilio.moviesbot.util.ApiCaller;

@ExtendWith(SpringExtension.class)
public class AutopilotServiceTest {

	@Mock
	MovieValidator movieValidator;

	@Mock
	private ApiCaller apiCaller;

	@Mock
	private ObjectMapper mapper;

	@Mock
	private CollectedDataParser collectedDataParser;

	@InjectMocks
	private AutopilotService service;

	@Test
	public void when_validateMovie_andMovieExists_thenReturn_true()
			throws IOException, InterruptedException, MovieBotException {

		AutopilotRequestDto request = AutopilotServiceMocks.getAutopilotRequestDto();
		ValidateResponseDto expected = AutopilotServiceMocks.getValidateResponseDto(true);

		when(movieValidator.existMovieName(any(AutopilotRequestDto.class))).thenReturn(true);

		ValidateResponseDto response = service.validateMovie(request);

		Assertions.assertEquals(expected.isValid(), response.isValid());
	}

	@Test
	public void when_validateMovie_andMovieNotExists_thenReturn_true()
			throws IOException, InterruptedException, MovieBotException {

		AutopilotRequestDto request = AutopilotServiceMocks.getAutopilotRequestDto();
		ValidateResponseDto expected = AutopilotServiceMocks.getValidateResponseDto(true);

		when(movieValidator.existMovieName(any(AutopilotRequestDto.class))).thenReturn(false);
		when(apiCaller.callGet(anyString(), any(Class.class))).thenReturn(AutopilotServiceMocks.getResultDto());

		ValidateResponseDto response = service.validateMovie(request);

		Assertions.assertEquals(expected.isValid(), response.isValid());
	}

	@Test
	public void when_getQuestions() throws IOException, InterruptedException, MovieBotException {

		AutopilotRequestDto request = AutopilotServiceMocks.getAutopilotRequestDto();
		ActionDto expected = AutopilotServiceMocks.getActionDto();

		when(apiCaller.callGet(anyString(), any(Class.class))).thenReturn(AutopilotServiceMocks.getResultDto());

		ActionDto response = service.getQuestions(request);

		Assertions.assertEquals(expected.getActions().get(0).getCollect().getQuestions().size(),
				response.getActions().get(0).getCollect().getQuestions().size());
	}

	
	public void when_validateQuestions() throws JsonMappingException, JsonProcessingException{

		AutopilotRequestDto request = AutopilotServiceMocks.getAutopilotRequestDto();
		ActionSayDto expected = AutopilotServiceMocks.getActionSayDto();

		when(collectedDataParser.parseAnswers(anyString(), anyString())).thenReturn(AutopilotServiceMocks.getAnswers());

		ActionSayDto response = service.validateQuestions(request);

		Assertions.assertEquals(expected.getActions().get(0).getSay(), response.getActions().get(0).getSay());
	}
}
