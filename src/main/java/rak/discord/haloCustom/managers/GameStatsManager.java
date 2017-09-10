package rak.discord.haloCustom.managers;

import rak.halo.stats.haloStats.HaloStatsManager;
import rak.halo.stats.haloStats.model.enums.Platform;
import rak.halo.stats.haloStats.model.matches.CarnageReport;
import rak.halo.stats.haloStats.model.player.PlayerStats;

public class GameStatsManager {
	public static final String DEFAULT_GAMERTAG = "Iceburg 33308";
	public static final String DEFAULT_ERROR_MESSAGE = "Unable to fetch data from server!";
	public static final String PADDING = "                                           ";
	
	private HaloStatsManager manager = new HaloStatsManager();
	
	public String getLastGameStatsMessage(String gamertag) {
		String message = DEFAULT_ERROR_MESSAGE;
		
		//default a gamertag
		gamertag = (gamertag == null) ? DEFAULT_GAMERTAG : gamertag;
		
		CarnageReport report = manager.getLatestMatchResult(gamertag, Platform.PC);
		message = generateMessage(report);
		return message;
	}

	private String generateMessage(CarnageReport report) {
		String results = " Player "+ PADDING + " Kills "+ PADDING + " Assists "+ PADDING + " Deaths ";
		for (PlayerStats player : report.getPlayerStats()){
			results += "\n" +getPlayerLine(player);
		}
		return results;
	}

	private String getPlayerLine(PlayerStats player) {
		String gamerTag = player.getPlayerInfo().getGamertag();
		String line = gamerTag + ":  " + PADDING.substring(gamerTag.length(), PADDING.length()) 
		+ player.getTotalKills() + PADDING + player.getTotalAssists() + PADDING +  player.getTotalDeaths() + "  ";
		
		return line;
	}
	
	
}
