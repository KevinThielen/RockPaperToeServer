package de.RockPaperToe.Server.Player;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.RockPaperToe.Server.Highscore.*;

@Entity
public class Player implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String userName;
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="player")
	private Highscore highscore;
	
	public Player() {}
	
	public Player(int id, String userName){
		this.id = id;
		this.userName = userName;
		this.highscore = null;
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

	public void setId(int playerId) {
		this.id = playerId;
	}
	
	public String toString(){
		return "Player: "+this.getId()+": "+ this.getUserName();
	}
}
