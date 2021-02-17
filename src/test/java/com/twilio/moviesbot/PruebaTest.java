package com.twilio.moviesbot;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PruebaTest {

	@Test
	public void test() throws JsonMappingException, JsonProcessingException {

		String data = "{\"twilio\":{\"chat\":{\"ChannelSid\":\"CH768b0fac4baa438c9d87be4329cd586b\",\"AssistantName\":\"\",\"Attributes\":{},\"ServiceSid\":\"IS31a89606ce794f6ab14fd072038da66d\",\"Index\":0,\"From\":\"user\",\"MessageSid\":\"IM9b15d4c35c17496dace3fd515fb705c9\"},\"collected_data\":{\"favorite_movie\":{\"answers\":{\"movie_name\":{\"confirm_attempts\":0,\"answer\":\"Finding Nemo\",\"filled\":true,\"confirmed\":false,\"validate_attempts\":1,\"attempts\":1}},\"date_completed\":\"2021-02-16T19:44:26Z\",\"date_started\":\"2021-02-16T19:44:18Z\",\"status\":\"complete\"},\"collect_tt0266543\":{\"answers\":{\"actor_question_Ellen DeGeneres\":{\"confirm_attempts\":0,\"answer\":\"debie\",\"filled\":true,\"confirmed\":false,\"validate_attempts\":1,\"attempts\":1},\"role_question_Marlin\":{\"confirm_attempts\":0,\"answer\":\"nemo\",\"filled\":true,\"confirmed\":false,\"validate_attempts\":1,\"attempts\":1},\"year_question_%s\":{\"confirm_attempts\":0,\"answer\":\"2003\",\"filled\":true,\"confirmed\":false,\"validate_attempts\":1,\"attempts\":1},\"role_question_Nemo\":{\"answer\":\"martin\",\"filled\":true,\"attempts\":1,\"validate_attempts\":1,\"confirm_attempts\":0,\"confirmed\":false,\"media\":null}},\"date_completed\":\"2021-02-16T19:45:06Z\",\"date_started\":\"2021-02-16T19:44:29Z\",\"status\":\"complete\"}}}}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(data);

		JsonNode collect = root.findValue("collect_tt0266543");
		JsonNode list = collect.findValues("answers").get(0); // asi obtengo las respuestas

		Iterator<Entry<String, JsonNode>> fieldsmap = list.fields();

		while (fieldsmap.hasNext()) {
			Entry<String, JsonNode> entry = fieldsmap.next();
			String name = entry.getKey();
			String value = entry.getValue().findValue("answer").asText();
		}
	}

}
