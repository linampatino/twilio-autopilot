package com.twilio.moviesbot.services;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.moviesbot.business.autopilot.CollectedDataParser;
import com.twilio.moviesbot.business.autopilot.QuestionGenerator;
import com.twilio.moviesbot.dtos.AutopilotRequestDto;
import com.twilio.moviesbot.dtos.ValidateResponseDto;
import com.twilio.moviesbot.dtos.autopilot.ActionCollectDto;
import com.twilio.moviesbot.dtos.autopilot.ActionDto;
import com.twilio.moviesbot.dtos.autopilot.ActionSayDto;
import com.twilio.moviesbot.dtos.autopilot.CollectDto;
import com.twilio.moviesbot.dtos.autopilot.QuestionDto;
import com.twilio.moviesbot.dtos.autopilot.RedirectDto;
import com.twilio.moviesbot.dtos.autopilot.SayDto;
import com.twilio.moviesbot.dtos.autopilot.memory.MemoryDto;
import com.twilio.moviesbot.dtos.autopilot.memory.collected.AnswerDto;
import com.twilio.moviesbot.dtos.imdb.MovieDto;
import com.twilio.moviesbot.dtos.imdb.ResultDto;
import com.twilio.moviesbot.exceptions.MovieBotException;
import com.twilio.moviesbot.models.QueriedMovie;
import com.twilio.moviesbot.repositories.QueriedMovieRepository;
import com.twilio.moviesbot.util.ApiCaller;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AutopilotService {

	@Value("${imdb.x.rapidapi.find}")
	private String FIND_MOVIE_URL;
	
	@Value("${autopilot.endpoint.validate.collect}")
	private String VALIDATE_COLLECT_URL;
	
	@Autowired
	private QueriedMovieRepository repository;

	@Autowired
	private ApiCaller apiCaller;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private CollectedDataParser collectedDataParser;

	public ValidateResponseDto validateMovie(AutopilotRequestDto request)
			throws IOException, InterruptedException, MovieBotException {

		MemoryDto memory = mapper.readValue(request.getMemory(), MemoryDto.class);
		request.setMemoryDto(memory);

		ValidateResponseDto valid = new ValidateResponseDto(true);
		validateCurrentInput(request);

		if (existMovieName(request)) {
			return valid;
		}

		List<MovieDto> movies = getMoviesFromImdb(request.getCurrentInput());

		if (movies.isEmpty()) {
			valid.setValid(false);
			return valid;
		}

		MovieDto movieDto = movies.stream().findFirst().get();
		saveQueriedMovie(request.getAssistantSid(), request.getDialogueSid(), movieDto.getTitle(),
				getMovieId(movieDto.getId()), memory.getTwilio().getChat().getChannelSid());

		return valid;
	}

	private boolean existMovieName(AutopilotRequestDto request) {
		List<QueriedMovie> movies = repository.findByMovieName(request.getCurrentInput());
		log.info("Query movies by : {}, result {}", request.getCurrentInput(), movies.size());

		if (!movies.isEmpty()) {
			extracted(request, movies.stream().findFirst().get());
			return true;
		}
		return false;
	}

	private String getMovieId(String movieId) {
		return movieId.substring(7, movieId.length() - 1);
	}

	private void extracted(AutopilotRequestDto request, QueriedMovie movie) {
		String channelSid = request.getMemoryDto().getTwilio().getChat().getChannelSid();
		if (!movie.getChannelSid().equals(channelSid)) {
			saveQueriedMovie(request.getAssistantSid(), request.getDialogueSid(), movie.getMovieName(),
					movie.getMovieId(), channelSid);
		}
	}

	private void validateCurrentInput(AutopilotRequestDto request) throws MovieBotException {
		if (request.getCurrentInput() == null || request.getCurrentInput().equals(StringUtils.EMPTY)) {
			log.error("The current input is empty or null : \"{}\"", request.getCurrentInput());
			throw new MovieBotException("The current input is empty or null");
		}
	}

	private List<MovieDto> getMoviesFromImdb(String movieName) throws IOException, InterruptedException {
		String uri = String.format("%s?q=%s", FIND_MOVIE_URL, movieName.replace(" ", "%20"));
		ResultDto result = apiCaller.callGet(uri, ResultDto.class);
		List<MovieDto> movies = result.getResults().stream()
				.filter(movie -> movie.getTitleType() != null && movie.getTitleType().equals("movie"))
				.collect(Collectors.toList());
		return movies;
	}

	private void saveQueriedMovie(String assistantSid, String dialogueSid, String movieName, String movieId,
			String channelSid) {
		QueriedMovie newMovie = new QueriedMovie();

		newMovie.setAssistantSid(assistantSid);
		newMovie.setDialogueSid(dialogueSid);
		newMovie.setMovieName(movieName);
		newMovie.setMovieId(movieId);
		newMovie.setChannelSid(channelSid);

		log.info("Saving in DB :: {}", newMovie);
		repository.save(newMovie);
	}

	public ActionDto getQuestions(AutopilotRequestDto request) throws IOException, InterruptedException {

		ActionDto action = new ActionDto();
		List<MovieDto> movies = getMoviesFromImdb(request.getCurrentInput());

		if (!movies.isEmpty()) {

			MovieDto movieDto = movies.stream().findFirst().get();
			movieDto.setId(getMovieId(movieDto.getId()));

			QuestionGenerator generator = new QuestionGenerator();
			List<QuestionDto> questions = generator.generateQuestions(movieDto);

			CollectDto collect = new CollectDto();
			RedirectDto redirect = new RedirectDto();
			redirect.setRedirect(VALIDATE_COLLECT_URL);

			collect.setName(String.format("collect_%s", movieDto.getId()));
			collect.setQuestions(questions);
			collect.setOnComplete(redirect);

			ActionCollectDto actionCollect = new ActionCollectDto(collect);
			action.setActions(Collections.singletonList(actionCollect));
		}

		return action;
	}

	public ActionSayDto validateQuestions(AutopilotRequestDto request)
			throws JsonMappingException, JsonProcessingException {

		List<AnswerDto> answers = collectedDataParser.parseAnswers(request.getMemory(), collectedDataParser.getCollectName(request.getMemory()));
		int correctAnswers = getNumberOfCorrectAnswers(answers);

		String message = getResultMessage(answers.size(), correctAnswers);

		ActionSayDto action = new ActionSayDto();
		SayDto say = new SayDto(message);
		action.setActions(Collections.singletonList(say));
		return action;

	}

	private String getResultMessage(int answers, int correctAnswers) {
		String message = "You have %d correct answers. %s";
		if (correctAnswers == answers) {
			message = "Congrats!!";
		} else {
			message = "Keep trying";
		}
		return message;
	}

	private int getNumberOfCorrectAnswers(List<AnswerDto> answers) {
		return answers.stream().filter(answer -> answer.getCorrectAnswer().contains(answer.getAnswer()))
				.collect(Collectors.toList()).size();
	}

	
}
