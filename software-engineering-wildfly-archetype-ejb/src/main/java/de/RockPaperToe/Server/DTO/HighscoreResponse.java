package de.RockPaperToe.Server.DTO;

public class HighscoreResponse extends DTO {
	
	private static final long serialVersionUID = 1L;
	
	private HighscoreTO highscore;
	
	public HighscoreTO getHighscore(){
		return this.highscore;
	}
	
	public void setHighscore(HighscoreTO highscore){
		this.highscore = highscore;
	}
}
