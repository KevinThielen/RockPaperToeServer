package de.RockPaperToe.Server.DTO;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "LoginResponse2")
public class LoginResponse extends DTO{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 private int sessionId;
 private String userName;
 
	 public LoginResponse(int sessionId, String userName) {
		 this.sessionId = sessionId;
		 this.userName = userName;
		 
		 if(sessionId < 0)
			 returnCode = -1;
	 }
 
	 public LoginResponse(int code) {
		 this.sessionId = code;
	
		 if(sessionId < 0)
			 returnCode = -1;
	 }
 
	public int getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
}
