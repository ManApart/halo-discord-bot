package rak.discord.haloCustom;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import rak.discord.haloCustom.commands.CommandParser;

public class BotListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event){
		if (event.getMessage().getContent().startsWith("~!") && !isBotMessage(event)){
			Main.getCore().handleCommand(CommandParser.parse(event.getMessage().getContent(), event));
		}
	}
	
	@Override
	public void onReady(ReadyEvent event){
//		Main.log("status", "Logged in as: " + event.getJDA().getSelfUser().getName());
	}
	
	private boolean isBotMessage(MessageReceivedEvent event){
		return (event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId()));
	}
}
