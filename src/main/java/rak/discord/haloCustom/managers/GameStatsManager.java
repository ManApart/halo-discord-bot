package rak.discord.haloCustom.managers;

import rak.discord.haloCustom.haloAPIs.HaloAPIStatsManager;
import rak.discord.haloCustom.haloAPIs.StatsParser;
import rak.discord.haloCustom.haloAPIs.model.stats.carnageReport.CarnageReport;
import rak.discord.haloCustom.haloAPIs.model.stats.carnageReport.PlayerStats;
import rak.discord.haloCustom.haloAPIs.model.stats.playerMatches.MatchId;
import rak.discord.haloCustom.haloAPIs.model.stats.playerMatches.PlayerStatResults;

public class GameStatsManager {
	public static final String DEFAULT_GAMERTAG = "Iceburg 33308";
	public static final String DEFAULT_ERROR_MESSAGE = "Unable to fetch data from server!";
	public static final String PADDING = "                                           ";
	
	
	public String getLastGameStatsMessage(String gamertag) {
		HaloAPIStatsManager apiManager = new HaloAPIStatsManager();
		String message = DEFAULT_ERROR_MESSAGE;
		
		//default a gamertag
		gamertag = (gamertag == null) ? DEFAULT_GAMERTAG : gamertag;
		
		//Fetch the player results
		String playerJson = apiManager.getMatchesForPlayer(gamertag); 
		if (playerJson != null && !playerJson.isEmpty()){
			StatsParser parser = new StatsParser();
			PlayerStatResults results = parser.parseStats(playerJson);
			
			
			//Fetch the carnage report
			MatchId matchId = results.getResults()[0].getId();
			String matchJson = apiManager.getCarnageReport(matchId);
			if (matchJson != null && !matchJson.isEmpty()){
				CarnageReport report = parser.parseReport(matchJson);
				message = generateMessage(report);
			}
		}
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
