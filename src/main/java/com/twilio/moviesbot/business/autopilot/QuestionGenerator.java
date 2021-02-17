package com.twilio.moviesbot.business.autopilot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.twilio.moviesbot.dtos.autopilot.QuestionDto;
import com.twilio.moviesbot.dtos.imdb.MovieDto;

public class QuestionGenerator {

	private final String RELEASE_YEAR = "What was the release year of %s??";
	private final String ACTOR = "What was the %s who played the role of %s??";
	private final String ROLE = " What was the role played by the %s %s??";

	private final String YEAR_QUESTION = "year_question_%s";
	private final String ACTOR_QUESTION = "actor_question_%s";
	private final String ROLE_QUESTION = "role_question_%s";

	public List<QuestionDto> generateQuestions(MovieDto movie) {
		List<QuestionDto> questions = new ArrayList<>();

		QuestionDto questionYear = createYearQuestion(movie.getTitle(), movie.getYear());
		questions.add(questionYear);

		AtomicInteger count = new AtomicInteger(0);
		movie.getPrincipals().forEach(principal -> {
			count.addAndGet(1);
			if (count.get() % 2 == 0) {
				questions.add(createActorQuestion(principal.getCategory(), principal.toStringCharacters(),
						principal.getName()));
			} else {
				questions.add(createRoleQuestion(principal.getCategory(), principal.getName(),
						principal.toStringCharacters()));
			}
		});

		return questions;
	}

	private QuestionDto createRoleQuestion(String category, String principalName, String id) {
		String question = String.format(ROLE, category, principalName);
		String name = String.format(ROLE_QUESTION, id);
		return createQuestion(question, name);
	}

	private QuestionDto createActorQuestion(String category, String role, String id) {
		String question = String.format(ACTOR, category, role);
		String name = String.format(ACTOR_QUESTION, id);
		return createQuestion(question, name);
	}

	private QuestionDto createYearQuestion(String movieName, String year) {
		String question = String.format(RELEASE_YEAR, movieName);
		String name = String.format(YEAR_QUESTION, year);
		return createQuestion(question, name);
	}

	private QuestionDto createQuestion(String question, String name) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setName(name);
		questionDto.setQuestion(question);
		questionDto.setType("");
		return questionDto;
	}

}
