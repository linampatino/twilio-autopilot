package com.twilio.moviesbot.dtos.autopilot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDto {

	private String answer;
	private String question;
	private String correctAnswer;
}
