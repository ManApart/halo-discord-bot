package rak.discord.haloCustom.haloAPIs;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rak.discord.haloCustom.haloAPIs.model.stats.carnageReport.CarnageReport;
import rak.discord.haloCustom.haloAPIs.model.stats.playerMatches.PlayerStatResults;

public class StatsParser {
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public PlayerStatResults parseStats(String jsonData){
		PlayerStatResults results = null;
		try {
			results = objectMapper.readValue(jsonData, PlayerStatResults.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

	public CarnageReport parseReport(String jsonData) {
		CarnageReport results = null;
		try {
			results = objectMapper.readValue(jsonData, CarnageReport.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}
	


}
