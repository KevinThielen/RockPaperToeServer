package de.RockPaperToe.Server.DTO;

import de.RockPaperToe.Server.Game.ECell;

public class CellTO  extends DTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean ownedByPlayer;
	ECell value;
	
    /**
     * Konstruktor. 
     * @author Kevin Thielen
     */
	
	public CellTO(boolean ownedByPlayer, ECell value) {
		this.ownedByPlayer = ownedByPlayer;
		this.value = value;
	}

	public boolean isOwnedByPlayer() {
		return ownedByPlayer;
	}

	public void setOwnedByPlayer(boolean ownedByPlayer) {
		this.ownedByPlayer = ownedByPlayer;
	}

	public ECell getValue() {
		return value;
	}

	public void setValue(ECell value) {
		this.value = value;
	}
	
	
}
