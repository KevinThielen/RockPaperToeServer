package de.RockPaperToe.Server.Session;

import java.util.HashMap;

import javax.ejb.Singleton;

import org.jboss.logging.Logger;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import de.RockPaperToe.Server.Player.Player;


@Singleton
public class SessionManager {
	private static final Logger logger = Logger.getLogger(SessionManager.class);
	
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
