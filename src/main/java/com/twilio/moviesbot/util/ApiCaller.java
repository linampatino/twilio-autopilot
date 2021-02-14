package com.twilio.moviesbot.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.twilio.moviesbot.dtos.imdb.MovieDto;
import com.twilio.moviesbot.dtos.imdb.ResultDto;

@Component
public class ApiCaller {

	
	
	public void call() throws IOException, InterruptedException {
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://imdb8.p.rapidapi.com/title/get-reviews?tconst=tt0944947&currentCountry=US&purchaseCountry=US"))
				.header("x-rapidapi-key", "866cc1d4ccmsh30f3bf3786a9b90p125e49jsn13ae4dd98ab7")
				.header("x-rapidapi-host", "imdb8.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (response.body(), JsonElement.class);
		ResultDto movieDto = gson.fromJson(element, ResultDto.class);
		System.out.println(response.body());
		
	}
	
	public <T> T callGet(String url, Class<T> clazz, String... headers) throws IOException, InterruptedException {
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.headers(headers)
				.GET()
				.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		
		Gson gson = new Gson();
		JsonElement element = gson.fromJson (response.body(), JsonElement.class);
		return (T)gson.fromJson(element, clazz);	
		
	}
}
