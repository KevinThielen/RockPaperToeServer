package de.RockPaperToe.Server.DTO;

import java.util.List;

public class HighscoreListResponse extends DTO {

	private static final long serialVersionUID = 1L;
	
	private List<HighscoreTO> highscores;
	
	public HighscoreListResponse(){
	}
	
	public List<HighscoreTO> getHighscoreList(){
		return highscores;
	}
	
	public void setHighscoreList(List<HighscoreTO> highscores){
		this.highscores = highscores;
	}
}
