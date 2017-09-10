package rak.discord.haloCustom.haloAPIs.model.stats;

public enum GameMode {
	ERROR(0),
	ARENA(1), 
	CAMPAIGN(2), 
	CUSTOM(3), 
	WARZONE(4);
	
	private int number;
	
	private GameMode(int number){
		this.number = number;
	}
	
	public static GameMode findByNumber(int number){
		for (GameMode gameType : values()){
			if (gameType.number == number){
				return gameType;
			}
		}
		return GameMode.ERROR;
	}
}
