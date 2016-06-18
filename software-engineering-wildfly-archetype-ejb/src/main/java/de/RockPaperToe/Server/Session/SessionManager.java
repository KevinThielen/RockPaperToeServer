package de.RockPaperToe.Server.Session;

import java.util.HashMap;

import javax.ejb.Singleton;


import de.RockPaperToe.Server.Player.Player;


@Singleton
public class SessionManager {
	HashMap<Integer, Session> sessions;

	
	
	public SessionManager() {
		sessions = new HashMap<>();

	}
	

	
	public Session login(Player player)
	{
		//verify tokenId
		Session newSession = new Session(player);
	    sessions.put(newSession.getId(), newSession);

	    return newSession;
	}
	
	
}
