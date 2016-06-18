package de.RockPaperToe.Server.Session;

import de.RockPaperToe.Server.Player.Player;

public class Session {
	private static int sessionCounter = 0;
	private int id;

	Player player;
	
	public Session(Player player) {
		id = sessionCounter++;

		this.player = player;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public int getId() {
		return id;
	}
}
