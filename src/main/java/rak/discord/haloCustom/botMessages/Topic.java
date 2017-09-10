package rak.discord.haloCustom.botMessages;

import java.util.ArrayList;
import java.util.Collection;

public class Topic {
	private ArrayList<String> statements;
	
	public Topic (ArrayList<String> statements){
		this.statements = statements;
	}

	public Collection<String> getStatements() {
		return statements;
	}

}
