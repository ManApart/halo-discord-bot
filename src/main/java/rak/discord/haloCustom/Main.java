package rak.discord.haloCustom;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Main {
	
	private static JDA jda;
	private static final String TOKEN = "MjYxNjEzNTg3MDI0MzE0MzY4.Cz3fCg.unqvl9Elt2RIMK56BCWVmXd-Cpo";
	private static final String CHANNEL = "261603219451543552";
	private static BotCore core;
	
	
	public static void main(String[] args){
		try {
			JDABuilder builder = new JDABuilder(AccountType.BOT);
			builder.addListener(new BotListener());
			builder.setToken(TOKEN);
			jda = builder.buildBlocking();
			jda.setAutoReconnect(true);
			
			core = new BotCore(jda, CHANNEL);
			core.cleanBotPosts();
			core.startLoop();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		} catch (LoginException e) {
			System.out.println("The provided Login information is incorrect. Please provide valid details.");
		} catch (IllegalArgumentException e) {
			System.out.println("The config was not populated. Please enter an email and password.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static BotCore getCore(){
		return core;
	}

}
