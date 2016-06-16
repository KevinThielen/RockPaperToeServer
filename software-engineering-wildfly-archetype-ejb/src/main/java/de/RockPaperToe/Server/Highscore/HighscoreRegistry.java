package de.RockPaperToe.Server.Highscore;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class HighscoreRegistry {

	private ArrayList<Highscore> highscores;
	
	@PostConstruct
	private void init() {
		highscores = new ArrayList<>();
	}
	
	@Lock(LockType.READ)
	public Highscore findHighscoreById(int id){
		for(int i = 0; i < highscores.size(); i++){
			if(this.highscores.get(i).getPlayerId() == id)
				return this.highscores.get(id);
		}
		return null;
	}
	
	@Lock(LockType.WRITE)
	public void addHighscore(Highscore highscore){
		this.highscores.add(highscore);
	}
	
	@Lock(LockType.READ)
	public ArrayList<Highscore> getHighscores(){		
		return highscores;
	}
}
