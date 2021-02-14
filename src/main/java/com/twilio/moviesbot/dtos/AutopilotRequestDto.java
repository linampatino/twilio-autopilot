package com.twilio.moviesbot.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
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
