package com.twilio.moviesbot.dtos.imdb;

import java.util.ArrayList;
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
public class ResultDto {

	private List<MovieDto> results = new ArrayList<>();
	private List<String> types = new ArrayList<>();

}
