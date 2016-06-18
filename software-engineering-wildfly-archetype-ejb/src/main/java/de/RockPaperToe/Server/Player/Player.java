package de.RockPaperToe.Server.Player;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.RockPaperToe.Server.Highscore.*;

@Entity
public class Player implements Serializable{

	private static final long serialVersionUID = 1L;

	// ID of a Player
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	// Username of a Player
	private String userName;
	private String googleId;
	
	// Inverse Objekt of the Relationships
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="player")
	private Highscore highscore;
	
	public Player() {
	
	}
	public Player(String googleId, String userName){
		this.userName = userName;
		this.highscore = null;
		this.googleId = googleId;
		highscore = new Highscore(this);
	}
	
	public Player(String userName){
		this.userName = userName;
		this.highscore = null;
		this.googleId = "";
		highscore = new Highscore(this);
	}
	/**
	 * When a new Instance of a Player will be created then
	 * he will get one highscores object as a reference
	 * 
	 * @param highscore
	 * @author Antonios Kouklidis
	 */
	public void addNewHighscore(Highscore highscore){
		this.highscore = highscore;
	}
	
	// Getter for username of a Player
	public String getUserName(){
		return userName;
	}
	
	// Getter for a Highscore-object of a Player
	public Highscore getHighscore(){
		return highscore;
	}

	// Getter for the Id of a Player
	public int getId() {
		return id;
	}

	public String getGoogleId() {
		return googleId;
	}
	
	// toString-Method of a Player
	public String toString(){
		return "Player: "+this.getId()+": "+ this.getUserName();
	}
}
