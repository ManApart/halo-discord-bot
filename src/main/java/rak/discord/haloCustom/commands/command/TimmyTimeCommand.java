package rak.discord.haloCustom.commands.command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import rak.discord.haloCustom.managers.GameStatsManager;

public class TimmyTimeCommand  implements ICommand {

	private static final String INVOKE_TEXT = "timmyTime";
	private static final String HELP = "USAGE: ~!timmyTime <gamertag>";
	
	@Override
	public boolean canBeCalled(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		GameStatsManager manager = new GameStatsManager();
		String gamertag = args.length > 0 ? args[0] : null;
		
		String message = manager.getTimmyTimeMessage(gamertag);
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
		event.getTextChannel().deleteMessageById(event.getMessage().getId()).queue();
	}

}
