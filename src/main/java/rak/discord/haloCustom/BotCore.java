package rak.discord.haloCustom;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import rak.discord.haloCustom.botMessages.MessageCurator;
import rak.discord.haloCustom.commands.CommandContainer;
import rak.discord.haloCustom.commands.CommandManager;
import rak.discord.haloCustom.commands.command.ICommand;

public class BotCore {
	private JDA jda;
	private String channelId;
	private int loopTime = 10;
	private MessageCurator messageCurator;
	private CommandManager commandManager;
	
	public BotCore(JDA jda, String channelId) {
		this.jda = jda;
		this.channelId = channelId;
		messageCurator = new MessageCurator();
		commandManager = new CommandManager();
	}
	
	public void startLoop(){
		loop();
	}

	private void loop(){
		while (true) {
			wait(loopTime);
			String nextMessage = messageCurator.getNextMessage();
			boolean speakAloud = messageCurator.getSpeakAloud();
			if (nextMessage != null){
				broadcast(nextMessage, speakAloud);
			}
		}
		
	}

	private void wait(int seconds){
		 try {
            Thread.sleep(seconds * 1000);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
	}
	
	private void broadcast(String message, boolean speakAloud) {
		TextChannel channel = jda.getTextChannelById(channelId);
		MessageBuilder builder = new MessageBuilder();
		builder.append(message);
		builder.setTTS(speakAloud);
		channel.sendMessage(builder.build()).queue();
		
	}
	
	public void cleanBotPosts(){
		TextChannel channel = jda.getTextChannelById(channelId);
		String userID = jda.getSelfUser().getId();
		List<Message> messages = new ArrayList<>();
		try {
			messages = channel.getHistory().retrievePast(100).block();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		}
		
		List<Message> messagesToDelete = new ArrayList<>();
		for (Message message : messages){
			if (message.getAuthor().getId().equals(userID) && !message.isPinned()){
				messagesToDelete.add(message);
			}
		}
		if (messagesToDelete.size() > 1){
			channel.deleteMessages(messagesToDelete).queue();
			System.out.println("Deleted " + messagesToDelete.size() + " messages");
		}
	}
	
	
	public void handleCommand(CommandContainer cmd){
		commandManager.handleCommand(cmd);
	}
	
	public ArrayList<ICommand> getCommands(){
		return commandManager.getAllCommands();
	}
	
	public MessageCurator getMessageCurator(){
		return messageCurator;
	}
}
