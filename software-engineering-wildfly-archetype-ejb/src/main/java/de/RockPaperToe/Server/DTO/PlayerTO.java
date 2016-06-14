package de.RockPaperToe.Server.DTO;

import java.io.Serializable;

import de.RockPaperToe.Server.Highscore.Highscore;

public class PlayerTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String userName;
	private Highscore highscore;
	
	public PlayerTO(int id, String userName,Highscore highscore){
		this.id = id;
		this.userName = userName;
		this.highscore = highscore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Highscore getHighscore() {
		return highscore;
	}

	public void setHighscore(Highscore highscore) {
		this.highscore = highscore;
	}
}
