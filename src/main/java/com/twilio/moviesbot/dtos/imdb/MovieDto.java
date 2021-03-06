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
public class MovieDto {

	private String id;
	private String runningTimeInMinutes;
	private String title;
	private String titleType;
	private String year;
	private List<PrincipalDto> principals;
}
