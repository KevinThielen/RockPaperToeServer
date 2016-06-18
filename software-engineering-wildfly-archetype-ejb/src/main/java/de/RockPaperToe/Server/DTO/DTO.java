package de.RockPaperToe.Server.DTO;

import java.io.Serializable;

public class DTO implements Serializable{
	private static final long serialVersionUID = 1L;
    //no error code
	public static final int OK_CODE = 0;

	protected int returnCode;
	protected String message;

	public DTO() {
		setReturnCode(OK_CODE);
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
