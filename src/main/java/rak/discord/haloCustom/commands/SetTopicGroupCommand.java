package rak.discord.haloCustom.commands;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import rak.discord.haloCustom.Main;

public class SetTopicGroupCommand implements ICommand{
	private static final String INVOKE_TEXT = "setTopic";
	private static final String HELP = "USAGE: ~!setTopic <topicKey>";
	
	@Override
	public boolean canBeCalled(String[] args, MessageReceivedEvent event) {
		boolean hasPermissions = event.getMember().getPermissions(event.getTextChannel()).contains(Permission.MANAGE_CHANNEL);
		return hasPermissions;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Main.getCore().getMessageCurator().setCurrentMessages(args[0]);
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
