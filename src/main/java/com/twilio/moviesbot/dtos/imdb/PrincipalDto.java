package com.twilio.moviesbot.dtos.imdb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrincipalDto {

	private String id;
	private String name;
	private String category;
	private List<String> characters;
	
	public String toStringCharacters() {
		return characters.stream().reduce(String::join).get();
	}

}
