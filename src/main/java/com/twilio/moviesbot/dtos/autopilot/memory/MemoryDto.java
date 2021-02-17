package com.twilio.moviesbot.dtos.autopilot.memory;

import com.twilio.moviesbot.dtos.autopilot.memory.chat.ChatDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemoryDto {

	private ChatDto twilio;

}
