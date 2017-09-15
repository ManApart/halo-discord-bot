package rak.discord.haloCustom.managers;

import rak.halo.stats.haloStats.HaloStatsManager;
import rak.halo.stats.haloStats.model.enums.GameMode;
import rak.halo.stats.haloStats.model.enums.Platform;
import rak.halo.stats.haloStats.model.matches.CarnageReport;
import rak.halo.stats.haloStats.model.other.FriendlyDuration;
import rak.halo.stats.haloStats.model.player.PlayerStats;
import rak.halo.stats.haloStats.model.serviceRecord.ServiceRecordArray;

public class GameStatsManager {
	public static final String DEFAULT_GAMERTAG = "Iceburg 33308";
	public static final String DEFAULT_ERROR_MESSAGE = "Unable to fetch data from server!";
	public static final String PADDING = "                                           ";
	
	private HaloStatsManager manager = new HaloStatsManager();
	
	public String getLastGameStatsMessage(String gamertag, Platform platform) {
		String message = DEFAULT_ERROR_MESSAGE;
		
		gamertag = (gamertag == null) ? DEFAULT_GAMERTAG : gamertag;
		platform = (platform == null) ? Platform.PC : platform;
		
		
		CarnageReport report = manager.getLatestMatchResult(gamertag, platform);
		message = generateMessage(report);
		return message;
	}

	private String generateMessage(CarnageReport report) {
		if (report == null){
			return "No results";
		}
		
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
	
	public String getTimmyTimeMessage(String gamertag) {
		String message = DEFAULT_ERROR_MESSAGE;
		
		gamertag = (gamertag == null) ? DEFAULT_GAMERTAG : gamertag;
		
		ServiceRecordArray pcRecord = manager.getServiceRecord(gamertag, Platform.PC, GameMode.CUSTOM);
		ServiceRecordArray xboxRecord = manager.getServiceRecord(gamertag, Platform.XBOX, GameMode.CUSTOM);
		
		FriendlyDuration pcDuration = pcRecord.getResults()[0].getResult().getCustomStats().getTotalTimePlayed();
		FriendlyDuration xboxDuration = xboxRecord.getResults()[0].getResult().getCustomStats().getTotalTimePlayed();
		FriendlyDuration totalDuration = pcDuration.plus(xboxDuration);
		
		message = "Timmy Time! Unlock a helmet after playing 50 hours of custom games. "
				+ "\nYou're at:"
				+ "\nPC: " + pcDuration 
				+"\nXbox: " + xboxDuration
				+"\nTotal: " + totalDuration;
		
		return message;
	}
	
	
}
