package rak.discord.haloCustom.commands;

import java.util.ArrayList;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandParser {
	public static CommandContainer parse(String rawInput, MessageReceivedEvent event){
		String raw = rawInput.toLowerCase();
		
		ArrayList<String> split = new ArrayList<>();
		String beheaded = raw.replaceFirst("~!", ""); 
		String[] splitBeheaded = beheaded.split(" ");
		for (String s : splitBeheaded){
			split.add(s);
		}
		String invoke = split.get(0);
		String[] args = split.subList(1, split.size()).toArray(new String[0]);
		
		return new CommandContainer(raw, beheaded, invoke, splitBeheaded, args, event);
	}
}
