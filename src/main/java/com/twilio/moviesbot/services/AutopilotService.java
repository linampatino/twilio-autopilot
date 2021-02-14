package com.twilio.moviesbot.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.moviesbot.dtos.ActionDto;
import com.twilio.moviesbot.dtos.AutopilotRequestDto;
import com.twilio.moviesbot.dtos.SayDto;
import com.twilio.moviesbot.models.QueriedMovie;
import com.twilio.moviesbot.repositories.QueriedMovieRepository;
import com.twilio.moviesbot.util.ApiCaller;
import com.twilio.rest.preview.bulkExports.Export;

import io.jsonwebtoken.lang.Collections;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

@Service
public class AutopilotService {
	
	@Autowired
	private QueriedMovieRepository repository;
	
	@Autowired
	private ApiCaller apiCaller;
	
	public void method() {
		 Map<String, Object> model = new HashMap<>();
		     new VelocityTemplateEngine().render(
		        new ModelAndView(model, "dynamicsay.json")
		    );
	}
	
	
	public ActionDto webhook(AutopilotRequestDto request) throws IOException, InterruptedException {
		System.out.println("autopilotRespones");
		SayDto say = new SayDto("Hola desde spring boot");
		
		QueriedMovie movie = new QueriedMovie();
		movie.setAssistantSid(request.getAccountSid());
		movie.setDialogueSid(request.getDialogueSid());
		
		repository.save(movie);
		
		QueriedMovie movie1 = repository.findByDialogueSid(request.getDialogueSid());
		
		apiCaller.call();
		
		return new ActionDto(Arrays.asList(say));
	}
	
	

}
