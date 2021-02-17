package com.twilio.moviesbot.dtos.autopilot.memory.chat;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatDto {

	@JsonAlias({ "chat", "slack" })
	private ChannelDto chat;

}
