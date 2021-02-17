package com.twilio.moviesbot.business.autopilot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.moviesbot.dtos.autopilot.memory.collected.AnswerDto;

@Component
public class CollectedDataParser {
	
	private final String COLLECT_TT = "collect_tt";
	private final String ANSWER = "answer";
	private final String ANSWERS = "answers";
	
	@Autowired
	private ObjectMapper mapper;
	
	public List<AnswerDto> parseAnswers(String data, String collectName)
			throws JsonMappingException, JsonProcessingException {
		List<AnswerDto> answers = new ArrayList<>();

		JsonNode root = mapper.readTree(data);
		JsonNode collect = root.findValue(collectName);
		JsonNode list = collect.findValues(ANSWERS).get(0);

		Iterator<Entry<String, JsonNode>> fieldsmap = list.fields();

		while (fieldsmap.hasNext()) {
			Entry<String, JsonNode> entry = fieldsmap.next();
			AnswerDto answer = createAnswer(entry);
			answers.add(answer);
		}

		return answers;
	}

	private AnswerDto createAnswer(Entry<String, JsonNode> entry) {
		AnswerDto answer = new AnswerDto();
		answer.setQuestion(entry.getKey());
		answer.setAnswer(entry.getValue().findValue(ANSWER).asText().toLowerCase());
		answer.setCorrectAnswer(getCorrectAnswer(entry.getKey()));
		return answer;
	}

	private String getCorrectAnswer(String question) {
		return question.substring(question.lastIndexOf("_") + 1).toLowerCase();
	}

	public String getCollectName(String memory) {
		if (memory.contains(COLLECT_TT)) {
			return memory.substring(memory.indexOf(COLLECT_TT), memory.indexOf(COLLECT_TT) + 17);
		}
		return StringUtils.EMPTY;
	}


}
