package de.RockPaperToe.Server.DTO;

public class DTO {
	private static final long serialVersionUID = 1L;
    //no error code
	public static final int OK_CODE = 0;

	private int returnCode;
	private String message;

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
