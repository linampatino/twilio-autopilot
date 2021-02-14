package com.twilio.moviesbot.dtos.imdb;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDto {

	public String id;
	public String runningTimeInMinutes;
	public String title;
	public String titleType;
	public String year;
	
}
