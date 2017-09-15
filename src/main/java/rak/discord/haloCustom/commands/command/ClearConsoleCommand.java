package rak.discord.haloCustom.commands.command;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class ClearConsoleCommand implements ICommand{
	private static final String INVOKE_TEXT = "clearConsole";
	private static final String HELP = "USAGE: ~!clearConsole";

	@Override
	public boolean canBeCalled(String[] args, MessageReceivedEvent event) {
		boolean hasPermissions = event.getMember().getPermissions(event.getTextChannel()).contains(Permission.MANAGE_CHANNEL);
		return hasPermissions;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		TextChannel channel = event.getTextChannel();
		List<Message> messages = new ArrayList<>();
		try {
			messages = channel.getHistory().retrievePast(100).block();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		}
		
		if (messages.size() > 1){
			channel.deleteMessages(messages).queue();
			System.out.println("Deleted " + messages.size() + " messages");
		}		
	}

	@Override
	public String help() {
		return HELP;
	}
	@Override
	public String getInvokeText() {
		return INVOKE_TEXT;
	}


	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		
	}

}
