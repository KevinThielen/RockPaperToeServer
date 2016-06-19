package de.RockPaperToe.Server.Game;

import java.io.Serializable;

import de.RockPaperToe.Server.DTO.CellTO;
import de.RockPaperToe.Server.DTO.DTO;

public class GameState extends DTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String opponent;
	int currentTurn;
	boolean opponentsTurn;
	int gameId;
	boolean gameOver;
    CellTO board[][];
    String currentValue;
	boolean won;
    
	public GameState(int gameId, String opponent, int currentTurn, boolean opponentsTurn, ECell currentValue, boolean gameOver, boolean won, CellTO board[][]) {
		this.opponent = opponent;
		this.currentTurn = currentTurn;
		this.opponentsTurn = opponentsTurn;
		this.gameId = gameId;
		
		if(currentValue == ECell.PAPER)
			this.currentValue = "PAPER";
		else if(currentValue == ECell.ROCK)
			this.currentValue = "ROCK";
		else if(currentValue == ECell.SCISSOR)
			this.currentValue = "SCISSOR";
		else
			this.currentValue = "EMPTY";
		
		this.gameOver = gameOver; 
		this.board = board;
		this.won = won;
	}
	
	
	
	public boolean isGameOver() {
		return gameOver;
	}



	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}



	public CellTO[][] getBoard() {
		return board;
	}



	public void setBoard(CellTO[][] board) {
		this.board = board;
	}



	public String getCurrentValue() {
		return currentValue;
	}



	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}



	public boolean isWon() {
		return won;
	}



	public void setWon(boolean won) {
		this.won = won;
	}



	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public boolean isOpponentsTurn() {
		return opponentsTurn;
	}

	public void setOpponentsTurn(boolean opponentsTurn) {
		this.opponentsTurn = opponentsTurn;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

}
