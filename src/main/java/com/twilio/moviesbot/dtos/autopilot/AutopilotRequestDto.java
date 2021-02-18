package com.twilio.moviesbot.dtos.autopilot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AutopilotRequestDto {

	public String accountSid;
	public String assistantSid;
	public String dialogueSid;
	public String userIdentifier;
	public String currentInput;
	public String currentTask;
	public String dialoguePayloadUrl;
	public String memory;
	public String channel;
	public String currentTaskConfidence;
	public String NextBestTask;

	public String callbackSource;
	public String querySid;

}
