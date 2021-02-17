package com.twilio.moviesbot.exceptions;

public class MovieBotException extends Exception {

	public MovieBotException(Exception e) {
		super(e);
	}

	public MovieBotException(String message) {
		super(message);
	}

}
