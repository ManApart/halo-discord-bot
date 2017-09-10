package rak.discord.haloCustom.botMessages;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageCurator {
	private HashMap<String, TopicGroup> messageGroups;
	private TopicGroup currentGroup;
	private ArrayList<String> currentMessages;
	int index = -1;
	
	
	public MessageCurator(){
		messageGroups = populateMessageGroups();
	}
	
	private HashMap<String, TopicGroup> populateMessageGroups() {
		MessageParser parser = new MessageParser();
		return parser.parseAllTopicGroups();
	}


	public String getNextMessage() {
		String message = null;
		
		if (currentMessages != null){
			if (index < currentMessages.size()){
				message = currentMessages.get(index);
			}
			index++;
		}
		
		return message;
	}
	
	public void setCurrentMessages(String topicGroupKey){
		if (messageGroups.containsKey(topicGroupKey)){
			currentGroup = messageGroups.get(topicGroupKey);
			currentMessages = currentGroup.getAllStatements();
			index = 0;
		}
	}
	
	public ArrayList<String> getAllTopicNames(){
		return new ArrayList<String>(messageGroups.keySet());
	}
	
	public boolean getSpeakAloud(){
		boolean speakAloud = false;
		if (currentGroup != null){
			speakAloud = currentGroup.isSpeakAloud();
		}
		return speakAloud;
	}
	

}
