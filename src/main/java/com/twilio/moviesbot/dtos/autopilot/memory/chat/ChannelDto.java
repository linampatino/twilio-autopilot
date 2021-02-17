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
public class ChannelDto {

	@JsonAlias(value = "ChannelSid")
	private String channelSid;

	@JsonAlias(value = "AssistantName")
	private String assistantName;

	@JsonAlias(value = "ServiceSid")
	private String serviceSid;

	@JsonAlias(value = "Attributes")
	private AttributeDto attributes;

	@JsonAlias(value = "Index")
	private String index;

	@JsonAlias(value = "From")
	private String from;

	@JsonAlias(value = "MessageSid")
	private String messageSid;
}
