package rak.discord.haloCustom.botMessages;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;


public class MessageParser {
	
	
	public HashMap<String, TopicGroup> parseAllTopicGroups(){
		HashMap<String, TopicGroup> map = null;
			try {
				HashMap<String, TopicGroup> topicGroups = parseTopicGroups();
				HashMap<String, Topic> topics = parseTopics();
				map = connectTopics(topicGroups, topics);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return map;
	}

	private HashMap<String, TopicGroup> parseTopicGroups() throws IOException, JsonParseException {
		HashMap<String, TopicGroup> topicGroupMap = new HashMap<>();
		
		String name = "TopicGroups.json";
		InputStream in = getClass().getResourceAsStream(name);
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode root = (ArrayNode) mapper.readTree(in);
		
		for (JsonNode topicGroup : root){
			String groupName = topicGroup.get("TopicGroup").textValue();
			boolean speakAloud = topicGroup.get("SpeakAloud").asBoolean();
			ArrayNode topics = (ArrayNode) topicGroup.get("Topics");
			
			TopicGroup group = new TopicGroup();
			group.setSpeakAloud(speakAloud);
			for (JsonNode topic : topics){
				group.addTopicName(topic.textValue());
			}
			topicGroupMap.put(groupName, group);
		}
		
		return topicGroupMap;
	}
	
	private HashMap<String, Topic> parseTopics() throws IOException, JsonParseException {
		HashMap<String, Topic> topicGroupMap = new HashMap<>();
		
		String name = "Topics.json";
		InputStream in = getClass().getResourceAsStream(name);
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode root = (ArrayNode) mapper.readTree(in);
		
		for (JsonNode topic : root){
			String topicName = topic.get("Topic").textValue();
			ArrayNode statements = (ArrayNode) topic.get("Statements");
			ArrayList<String> topicIds = new ArrayList<String>();
			for (JsonNode statement : statements){
				topicIds.add(statement.textValue());
			}
			
			topicGroupMap.put(topicName, new Topic(topicIds));
		}
		
		return topicGroupMap;
	}
	
	
	private HashMap<String, TopicGroup> connectTopics(HashMap<String, TopicGroup> topicGroups, HashMap<String, Topic> topics) {
		HashMap<String, TopicGroup> map = new HashMap<>();
		//For each Group
		for (String topicGroupName : topicGroups.keySet()){
			TopicGroup group = topicGroups.get(topicGroupName);
		
			//Add all associated topics
			for (String topicId : group.getTopicNames()){
				group.addTopic(topics.get(topicId));
			}
			map.put(topicGroupName.toLowerCase(), group);
		}
		return map;
	}

}
