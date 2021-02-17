package com.twilio.moviesbot.dtos.autopilot;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CollectDto {

	private String name;
	private List<QuestionDto> questions;

	@JsonProperty("on_complete")
	private RedirectDto onComplete;

}
