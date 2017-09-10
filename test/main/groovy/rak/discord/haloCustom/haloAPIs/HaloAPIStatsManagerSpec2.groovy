package rak.discord.haloCustom.haloAPIs


import rak.discord.haloCustom.haloAPIs.model.stats.carnageReport.CarnageReport
import rak.discord.haloCustom.haloAPIs.model.stats.playerMatches.PlayerStatResults
import spock.lang.Specification

class HaloAPIStatsManagerSpec2 extends Specification {
	def "Get Results "(){
		given:
			HaloAPIStatsManager manager = new HaloAPIStatsManager()
			String gamerTag = "Danceparty17"
		when:
			String result = manager.getMatchesForPlayer(gamerTag)
			System.println(result);
		then:
			result.isEmpty() == false
		
	}
	
	
	def "Parse sample player matches Json"(){
		given:
			StatsParser parser = new StatsParser()
			JsonHelper helper = new JsonHelper()
			String jsonData = helper.getJsonData("SampleStatsPlayerMatches.Json")
		when:
			PlayerStatResults results = parser.parseStats(jsonData)
		then:
			"Iceburg 33308" == results.getResults()[0].getPlayers()[0].getPlayer().getGamertag()
	}
	
	
	def "Parse sample carnage report Json"(){
		given:
			StatsParser parser = new StatsParser()
			JsonHelper helper = new JsonHelper()
			String jsonData = helper.getJsonData("SampleStatsCarnageReport.Json")
		when:
			CarnageReport results = parser.parseReport(jsonData)
		then:
			"ALegionOfAngels" == results.getPlayerStats()[0].getPlayerInfo().getGamertag()
	}
	
}
