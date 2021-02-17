package com.twilio.moviesbot.business.autopilot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twilio.moviesbot.dtos.AutopilotRequestDto;
import com.twilio.moviesbot.models.QueriedMovie;
import com.twilio.moviesbot.repositories.QueriedMovieRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MovieValidator {

	@Autowired
	private QueriedMovieRepository repository;
	
	public boolean existMovieName(AutopilotRequestDto request) {
		List<QueriedMovie> movies = repository.findByMovieName(request.getCurrentInput());
		log.info("Query movies by : {}, result {}", request.getCurrentInput(), movies.size());
		return !movies.isEmpty();
	}
	
	public void saveQueriedMovie(String assistantSid, String dialogueSid, String movieName, String movieId,
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

}
