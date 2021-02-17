package com.twilio.moviesbot.dtos.imdb;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultDto {

	private List<MovieDto> results = new ArrayList<>();
	private List<String> types = new ArrayList<>();

}
