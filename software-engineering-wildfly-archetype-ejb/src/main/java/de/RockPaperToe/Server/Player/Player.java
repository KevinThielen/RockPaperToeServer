package de.RockPaperToe.Server.Player;

import java.io.Serializable;
import de.RockPaperToe.Server.Highscore.*;

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;
	private static int lastID = 0;
	
	private int id;
	private String userName;
	private String googleId;
	private Highscore highscore;
	
	public Player(String userName, String googleId){
		this.id = lastID++;
		this.userName = userName;
		this.highscore = null;
		this.googleId = googleId;
	}
		
	public void addNewHighscore(Highscore highscore){
		this.highscore = highscore;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public Highscore getHighscore(){
		return highscore;
	}

	public int getId() {
		return id;
	}

	public String getGoogleId() {
		return googleId;
	}
	public void setId(int playerId) {
		this.id = playerId;
	}
	
	public String toString(){
		return "Player: "+this.getId()+": "+ this.getUserName();
	}
}
