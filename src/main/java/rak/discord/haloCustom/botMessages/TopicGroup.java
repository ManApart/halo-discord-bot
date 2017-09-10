package rak.discord.haloCustom.botMessages;

import java.util.ArrayList;

public class TopicGroup {

	private ArrayList<Topic> topics;
	private ArrayList<String> topicNames;
	private boolean speakAloud;
	
	public TopicGroup(){
		topics = new ArrayList<>();
		topicNames = new ArrayList<>();
	}
	
	public void addTopicName(String topicName){
		topicNames.add(topicName);
	}
	public void addTopic(Topic topic){
		topics.add(topic);
	}
	
	public ArrayList<String> getTopicNames(){
		return topicNames;
	}
	
	public ArrayList<String> getAllStatements(){
		ArrayList<String> statements = new ArrayList<String>();
		for (Topic topic : topics){
			statements.addAll(topic.getStatements());
		}
		return statements;
	}

	public boolean isSpeakAloud() {
		return speakAloud;
	}

	public void setSpeakAloud(boolean speakAloud) {
		this.speakAloud = speakAloud;
	}
}
