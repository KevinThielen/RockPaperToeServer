package de.RockPaperToe.Server.DTO;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "LoginResponse2")
public class LoginResponse extends DTO{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int sessionId;
 
 public LoginResponse(int sessionId) {
	 this.sessionId = sessionId;

	 if(sessionId < 0)
		 returnCode = -1;
 }
 
	public int getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
}
