package rak.discord.haloCustom.haloAPIs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import rak.discord.haloCustom.haloAPIs.model.stats.playerMatches.MatchId;

public class HaloAPIStatsManager {
	public static final String PRIMARY_KEY = "170f6ad95dd740689eedc31707ccf2c2";
	private static final String BASE_URL = "https://www.haloapi.com/stats/h5/";
	
	public String getMatchesForPlayer(String gamerTag) {
		String url = BASE_URL + "players/" + gamerTag + "/matches";
		String results = baseCall(url);
	   
	    return results;
	}
	
	public String getCarnageReport(MatchId matchId) {
		String gameMode = matchId.getGameModeEnum().name().toLowerCase();
		String url = BASE_URL + gameMode +"/matches/" + matchId.getMatchId();
		String results = baseCall(url);
		
		return results;
	}


	private String baseCall(String url) {
		String results = null;
		url = url.replace(" ", "%20");
		try {
			URL apiUrl = new URL(url);
			 HttpURLConnection urlConn = (HttpURLConnection)apiUrl.openConnection();
		    urlConn.setRequestMethod("GET");
		    urlConn.setRequestProperty("Ocp-Apim-Subscription-Key", PRIMARY_KEY);
		    
		    StringBuilder output = new StringBuilder();
		    BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		    String inputLine;
		    while ((inputLine = in.readLine()) != null) {
		      output.append(inputLine);
		    }
		    in.close();
		    results = output.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}
	

}
