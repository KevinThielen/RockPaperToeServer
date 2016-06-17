package de.RockPaperToe.Server.dao;

import java.util.ArrayList;

import de.RockPaperToe.Server.Highscore.Highscore;
import de.RockPaperToe.Server.Player.Player;

public interface PersistenceManagerLocal {

	public Highscore findHighscoreById(int id);
	public ArrayList<Highscore> getTop10Highscores();
	public Player findPlayerById(int id);
	public void updateRanking();
}
