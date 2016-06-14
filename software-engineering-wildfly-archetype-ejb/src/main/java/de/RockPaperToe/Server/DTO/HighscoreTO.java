package de.RockPaperToe.Server.DTO;

import java.io.Serializable;

import de.RockPaperToe.Server.Player.Player;

public class HighscoreTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id, score, ranking;
	
	private int playerId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public HighscoreTO(){
	}
	
	public HighscoreTO(int id, int score, int ranking, int playerId){
		this.id = id;
		this.score = score;
		this.ranking = ranking;
		this.playerId = playerId;
	}
	
	public String toString(){
		return "HighscoreID: "+this.id+" Score: "+this.score+" Ranking: "+this.ranking+" PlayerID: "+this.getPlayerId();
	}
	
}
