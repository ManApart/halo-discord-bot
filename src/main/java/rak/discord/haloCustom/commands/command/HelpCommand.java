package rak.discord.haloCustom.commands.command;

import java.util.ArrayList;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import rak.discord.haloCustom.Main;

public class HelpCommand implements ICommand{
	private static final String INVOKE_TEXT = "help";
	private static final String HELP = "USAGE: ~!help <optional_about>";
	
	@Override
	public boolean canBeCalled(String[] args, MessageReceivedEvent event) {
		boolean hasPermissions = event.getMember().getPermissions(event.getTextChannel()).contains(Permission.MANAGE_CHANNEL);
		return hasPermissions;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		String message = null;
		if (args.length == 0){
			message = createGeneralHelpMessage();
		} else{
			String param = args[0];
			if ("commands".equals(param)){
				message = createCommandsMessage();
			} else if ("topics".equals(param)){
				message = createTopicMessage();
			}
		}
		event.getTextChannel().sendMessage(message).queue();
		
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
	
	private String createGeneralHelpMessage() {
		String message;
		message = "help commands"
				+ "\n -List all available commands"
				+ "\n\nhelp topics"
				+ "\n -List all available topics";
		return message;
	}
	
	private String createCommandsMessage() {
		ArrayList<ICommand> commands = Main.getCore().getCommands();
		String message = "Commands:";
		for (ICommand command : commands){
			message += "\n\n" +command.getInvokeText();
			message += "\n -" +command.help();
		}
		
		return message;
	}
	
	private String createTopicMessage() {
		ArrayList<String> names = Main.getCore().getMessageCurator().getAllTopicNames();
		String message = "Topics:";
		for (String topic : names){
			message += "\n" +topic;
		}
		return message;
	}
	
	

}
