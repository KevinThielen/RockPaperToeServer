package de.RockPaperToe.Server.DTO;

public class RegistrationResponse extends DTO{

	private static final long serialVersionUID = 1L;
	private int sessionId;
	
	public RegistrationResponse(int sessionId) {
		if(sessionId < 0)
			this.returnCode = -1;
		

		this.sessionId = sessionId;
	}
	
	public int getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
}
