package com.twilio.moviesbot.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApiCaller {

	@Value("${imdb.x.rapidapi.key}")
	private String RAPIDAPI_KEY;

	@Value("${imdb.x.rapidapi.host}")
	private String RAPIDAPI_HOST;

	@Autowired
	private ObjectMapper mapper;

	private final String X_RAPIDAPI_KEY = "x-rapidapi-key";
	private final String X_RAPIDAPI_HOST = "x-rapidapi-host";
	private final String HTTPS = "https://%s%s";

	public <T> T callGet(String uri, Class<T> clazz) throws IOException, InterruptedException {
		String url = String.format(HTTPS, RAPIDAPI_HOST, uri);
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header(X_RAPIDAPI_KEY, RAPIDAPI_KEY)
				.header(X_RAPIDAPI_HOST, RAPIDAPI_HOST)
				.GET()
				.build();

		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		log.info("Imdb request : {}", url);
		log.info("Imdb response : {}", response.body());

		return (T) mapper.readValue(response.body(), clazz);
	}
}
