package com.twilio.moviesbot.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.twilio.moviesbot.dtos.AutopilotRequestDto;
import com.twilio.moviesbot.dtos.ValidateResponseDto;
import com.twilio.moviesbot.dtos.autopilot.ActionCollectDto;
import com.twilio.moviesbot.dtos.autopilot.ActionDto;
import com.twilio.moviesbot.dtos.autopilot.ActionSayDto;
import com.twilio.moviesbot.dtos.autopilot.CollectDto;
import com.twilio.moviesbot.dtos.autopilot.QuestionDto;
import com.twilio.moviesbot.dtos.autopilot.RedirectDto;
import com.twilio.moviesbot.dtos.autopilot.SayDto;
import com.twilio.moviesbot.dtos.autopilot.memory.collected.AnswerDto;
import com.twilio.moviesbot.dtos.imdb.MovieDto;
import com.twilio.moviesbot.dtos.imdb.PrincipalDto;
import com.twilio.moviesbot.dtos.imdb.ResultDto;

public class AutopilotServiceMocks {

	public static String movieName = "Finding Nemo";
	public static String movieId = "tt0266543";

	public static ValidateResponseDto getValidateResponseDto(boolean isValid) {
		ValidateResponseDto response = new ValidateResponseDto();
		response.setValid(isValid);
		return response;
	}

	public static AutopilotRequestDto getAutopilotRequestDto() {
		AutopilotRequestDto request = new AutopilotRequestDto();
		request.setAccountSid("accountSid");
		request.setAssistantSid("assistantSid");
		request.setDialogueSid("dialogueSid");
		request.setCurrentInput(movieName);
		request.setMemory(
				"{\"twilio\":{\"chat\":{\"ChannelSid\":\"CH768b0fac4baa438c9d87be4329cd586b\",\"AssistantName\":\"\",\"Attributes\":{},\"ServiceSid\":\"IS31a89606ce794f6ab14fd072038da66d\",\"Index\":0,\"From\":\"user\",\"MessageSid\":\"IM9b15d4c35c17496dace3fd515fb705c9\"},\"collected_data\":{\"favorite_movie\":{\"answers\":{\"movie_name\":{\"confirm_attempts\":0,\"answer\":\"Finding Nemo\",\"filled\":true,\"confirmed\":false,\"validate_attempts\":1,\"attempts\":1}},\"date_completed\":\"2021-02-16T19:44:26Z\",\"date_started\":\"2021-02-16T19:44:18Z\",\"status\":\"complete\"},\"collect_tt0266543\":{\"answers\":{\"actor_question_Ellen DeGeneres\":{\"confirm_attempts\":0,\"answer\":\"debie\",\"filled\":true,\"confirmed\":false,\"validate_attempts\":1,\"attempts\":1},\"role_question_Marlin\":{\"confirm_attempts\":0,\"answer\":\"nemo\",\"filled\":true,\"confirmed\":false,\"validate_attempts\":1,\"attempts\":1},\"year_question_2003\":{\"confirm_attempts\":0,\"answer\":\"2003\",\"filled\":true,\"confirmed\":false,\"validate_attempts\":1,\"attempts\":1},\"role_question_Nemo\":{\"answer\":\"martin\",\"filled\":true,\"attempts\":1,\"validate_attempts\":1,\"confirm_attempts\":0,\"confirmed\":false,\"media\":null}},\"date_completed\":\"2021-02-16T19:45:06Z\",\"date_started\":\"2021-02-16T19:44:29Z\",\"status\":\"complete\"}}}}");
		return request;
	}

	public static MovieDto getMovieDto() {
		PrincipalDto principal = new PrincipalDto();
		principal.setCategory("actor");
		principal.setId("tt");
		principal.setName("Albert Brooks");
		principal.setCharacters(Collections.singletonList("Marlin"));

		PrincipalDto principal1 = new PrincipalDto();
		principal1.setCategory("actor");
		principal1.setId("tt");
		principal1.setName("Ellen DeGeneres");
		principal1.setCharacters(Collections.singletonList("Dory"));

		PrincipalDto principal2 = new PrincipalDto();
		principal2.setCategory("actor");
		principal2.setId("tt");
		principal2.setName("Alexander Gould");
		principal2.setCharacters(Collections.singletonList("Nemo"));

		List<PrincipalDto> principals = new ArrayList<>();
		principals.add(principal);
		principals.add(principal1);
		principals.add(principal2);

		MovieDto movieDto = new MovieDto();
		movieDto.setId(movieId);
		movieDto.setTitle(movieName);
		movieDto.setTitleType("movie");
		movieDto.setYear("2003");
		movieDto.setPrincipals(principals);
		return movieDto;
	}

	public static ResultDto getResultDto() {
		ResultDto resultDto = new ResultDto();
		resultDto.setResults(Collections.singletonList(getMovieDto()));
		return resultDto;
	}

	public static List<QuestionDto> getQuestions() {
		List<QuestionDto> questions = new ArrayList<>();

		QuestionDto question1 = new QuestionDto();
		question1.setName("actor_question_Ellen DeGeneres");
		question1.setQuestion("question1");
		question1.setType(StringUtils.EMPTY);

		QuestionDto question2 = new QuestionDto();
		question2.setName("role_question_Marlin");
		question2.setQuestion("question2");
		question2.setType(StringUtils.EMPTY);

		QuestionDto question3 = new QuestionDto();
		question3.setName("year_question_2003");
		question3.setQuestion("question3");
		question3.setType(StringUtils.EMPTY);

		QuestionDto question4 = new QuestionDto();
		question4.setName("role_question_Nemo");
		question4.setQuestion("question4");
		question4.setType(StringUtils.EMPTY);

		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		questions.add(question4);

		return questions;
	}

	public static ActionDto getActionDto() {
		ActionDto action = new ActionDto();

		CollectDto collect = new CollectDto();
		RedirectDto redirect = new RedirectDto();
		redirect.setRedirect("/validateCollect");

		collect.setName(String.format("collect_%s", movieId));
		collect.setQuestions(getQuestions());
		collect.setOnComplete(redirect);

		ActionCollectDto actionCollect = new ActionCollectDto(collect);
		action.setActions(Collections.singletonList(actionCollect));
		return action;
	}

	public static List<AnswerDto> getAnswers(){
		List<AnswerDto> answers = new ArrayList<>();
		
		AnswerDto answer1 = new AnswerDto();
		answer1.setAnswer("debie");
		answer1.setCorrectAnswer("Ellen DeGeneres");
		answer1.setQuestion("question");
		
		AnswerDto answer2 = new AnswerDto();
		answer2.setAnswer("nemo");
		answer2.setCorrectAnswer("Marlin");
		answer2.setQuestion("question");
		
		AnswerDto answer3 = new AnswerDto();
		answer3.setAnswer("2003");
		answer3.setCorrectAnswer("2003");
		answer3.setQuestion("question");
		
		AnswerDto answer4 = new AnswerDto();
		answer4.setAnswer("martin");
		answer4.setCorrectAnswer("Nemo");
		answer4.setQuestion("question");
		
		answers.add(answer1);
		answers.add(answer2);
		answers.add(answer3);
		answers.add(answer4);
		
		return answers;
	}

	public static ActionSayDto getActionSayDto() {
		ActionSayDto actionSayDto = new ActionSayDto();
		SayDto say = new SayDto("You have 1 correct answers. Keep trying /n Say Hi to play again.");
		actionSayDto.setActions(Collections.singletonList(say));
		return actionSayDto;
	}
}
