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
	
	// Autoincrement the Id in the Database
	@Id @GeneratedValue
	private int id;
	
	private int score, ranking;
	
	// Highscore is the owner of the Relationship with Player
	@OneToOne
	private Player player;
	
	public Highscore() {}
	
	public Highscore(Player player){
		this.score = 0;
		this.ranking = 0;
		this.player = player;
	}
	
	// Getter of the HighscoreId
	public int getId(){
		return id;
	}
	
	// Getter of a Player-object
	public Player getPlayer(){
		return player;
	}
	
	// Getter for the Id of a Player
	public int getPlayerId(){
		return this.player.getId();
	}
	
	//  Getter for the userName
	public String getPlayerName(){
		return this.player.getUserName();
	}
	
	// Getter own Score
	public int getScore(){
		return score;
	}
	
	// Setter for score
	public void setScore(int score){
		this.score = score;
	}
	
	// Getter for ranking
	public int getRanking(){
		return ranking;
	}
	
	// Setter for ranking
	public void setRanking(int ranking){
		this.ranking = ranking;
	}
		
	// Setter for HighscoreId
	public void setId(int highscoreId){
		this.id = highscoreId;
	}
	
	// toString-Method for a Highscore-object
	public String toString(){
		return "HighscoreID: "+this.id+" Name: "+this.getPlayerName()+" PlayerID: "+this.getPlayerId()+" Score: "+this.score+" Ranking: "+this.ranking;
	}
}
