package rak.discord.haloCustom.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandContainer {
	private String raw, beheaded, invoke;
	private String[] splitBeheaded, args;
	private MessageReceivedEvent event;
	
	public CommandContainer(String raw, String beheaded, String invoke, String[] splitBeheaded, String[] args, MessageReceivedEvent event) {
		this.raw = raw;
		this.beheaded = beheaded;
		this.invoke = invoke;
		this.splitBeheaded = splitBeheaded;
		this.args = args;
		this.event = event;
	}

	public String getRaw() {
		return raw;
	}

	public String getBeheaded() {
		return beheaded;
	}

	public String getInvoke() {
		return invoke;
	}

	public String[] getSplitBeheaded() {
		return splitBeheaded;
	}

	public String[] getArgs() {
		return args;
	}

	public MessageReceivedEvent getEvent() {
		return event;
	}

}
