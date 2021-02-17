package com.twilio.moviesbot.dtos.autopilot;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActionDto {

	private List<ActionCollectDto> actions;
}
