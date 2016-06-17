package de.RockPaperToe.Server.Highscore;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.RockPaperToe.Server.Player.Player;

@Entity
public class Highscore implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int id;
	private int score, ranking;
	
	@OneToOne
	private Player player;
	
	public Highscore() {}
	
	public Highscore(Player player){
		this.score = 0;
		this.ranking = 0;
		this.player = player;
	}
	
	// Nur die HighscoreID
	public int getId(){
		return id;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public int getPlayerId(){
		return this.player.getId();
	}
	
	public String getPlayerName(){
		return this.player.getUserName();
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
