package de.RockPaperToe.Server.Session;

import java.util.HashMap;

import javax.ejb.Singleton;
import javax.ejb.Lock;
import javax.ejb.LockType;

import de.RockPaperToe.Server.Player.Player;


@Singleton
public class SessionManager {
	HashMap<Integer, Session> sessions;

	
	
	public SessionManager() {
		sessions = new HashMap<>();

	}
	
	@Lock(LockType.READ)
	public Session getSessionById(int sessionId) {
		return sessions.get(sessionId);
	}
	
	@Lock(LockType.WRITE)
	public Session login(Player player)
	{

		Session newSession = new Session(player);
	    sessions.put(newSession.getId(), newSession);

	    return newSession;
	}
}
