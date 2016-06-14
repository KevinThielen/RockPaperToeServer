package de.RockPaperToe.Server.Highscore;

import java.io.Serializable;

import de.RockPaperToe.Server.Player.Player;

public class Highscore implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id, score, ranking;
	
	private Player player;
	private int playerId;
	private String playerName;
	
	private static int lastID = 0;
	
	public Highscore(Player player){
		this.id = ++lastID;
		this.score = 0;
		this.ranking = 0;
		this.player = player;
		this.playerId = player.getId();
		this.playerName = player.getUserName();
	}
	
	// Nur die HighscoreID
	public int getId(){
		return id;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public int getPlayerId(){
		return playerId;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getRanking(){
		return ranking;
	}
	
	public void setRanking(int ranking){
		this.ranking = ranking;
	}
		
	public void setId(int highscoreId){
		this.id = highscoreId;
	}
	
	public String toString(){
		return "HighscoreID: "+this.id+" Name: "+this.getPlayerName()+" PlayerID: "+this.getPlayerId()+" Score: "+this.score+" Ranking: "+this.ranking;
	}
}
