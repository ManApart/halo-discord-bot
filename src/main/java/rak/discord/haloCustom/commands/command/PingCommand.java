package rak.discord.haloCustom.commands.command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PingCommand implements ICommand{
	private static final String INVOKE_TEXT = "ping";
	private static final String HELP = "USAGE: ~!ping";

	@Override
	public boolean canBeCalled(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		event.getTextChannel().sendMessage("PONG").queue();
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
		event.getTextChannel().deleteMessageById(event.getMessage().getId()).queue();
	}

}
