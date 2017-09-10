package rak.discord.haloCustom.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface ICommand {
	
	public boolean canBeCalled(String[] args, MessageReceivedEvent event);
	public void action(String[] args, MessageReceivedEvent event);
	public String help();
	public String getInvokeText();
	public void executed(boolean success, MessageReceivedEvent event);
}
