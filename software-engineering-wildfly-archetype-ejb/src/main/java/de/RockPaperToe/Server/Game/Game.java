package de.RockPaperToe.Server.Game;

import java.util.Random;

import javax.ejb.EJB;

import de.RockPaperToe.Server.DTO.CellTO;
import de.RockPaperToe.Server.Player.Player;
import de.RockPaperToe.Server.Util.DtoAssembler;

public class Game {
	Player player;
	Player player2;
	
	Player currentPlayer;
	int currentTurn = 0;
	int gameId;
	boolean gameOver;
    Cell board[][];
    ECell currentValue;
    boolean terminate;
    DtoAssembler dtoAssembler;
    boolean resultCheckedByPlayer;
    boolean resultCheckedByPlayer2;
    
    static int GAME_COUNTER = 0;
	
//@EJB
//HighscoreRequest scorer;
    
	public Game(Player creator) {
		player = creator;
		currentPlayer = creator;
		currentTurn = 0;
		gameId = GAME_COUNTER++;
		board = new Cell[3][3];
		gameOver = false;
		terminate = false;
		dtoAssembler = new DtoAssembler();
		currentPlayer = creator;
		currentValue = ECell.ROCK;
		resultCheckedByPlayer = false;
		resultCheckedByPlayer2 = false;
		resetBoard();
	}
	public int getId() {
		return gameId;
	}
	boolean isOver() {
		return terminate;
	}
	
	private void resetBoard() {
		board = new Cell[3][3];
        for(int x = 0; x<3; x++) {
            for(int y = 0; y<3; y++) {
                board[x][y] = new Cell();
            }
        }
    }
	Player getPlayer() {
		return player;
	}
	
	Player getPlayer2() {
		return player2;
	}
	
	void start() {
        Random random = new Random();
        int randomIndex = random.nextInt(2);
        if(randomIndex == 0)
        	currentPlayer = player;
        else
        	currentPlayer = player2;
        
		resetBoard();
	}
	
	 public void makeMove(Player player, int column, int row) {
	        if(player.getId() == currentPlayer.getId() && !gameOver) {
	            if(ECell.EMPTY == board[column][row].value) {
	                changeCell(column, row);
	                endTurn();
	            }
	        }
	    }
	 void changeCell(int column, int row) {
	        board[column][row].setValue(currentValue);
	        board[column][row].setOwner(currentPlayer);

	        //check left neighbour
	        if(column > 0 && board[column-1][row].getValue() == loseAgainst(currentValue)) {
	            board[column-1][row].owner = currentPlayer;
	        }
	        //right neighbour
	        if(column < 2 && board[column+1][row].getValue() == loseAgainst(currentValue)) {
	            board[column+1][row].owner = currentPlayer;
	        }
	        //top neighbour
	        if(row > 0 && board[column][row-1].getValue() == loseAgainst(currentValue)) {
	            board[column][row-1].owner = currentPlayer;
	        }
	        //bottom neighbour
	        if(row < 2 && board[column][row+1].getValue() == loseAgainst(currentValue)) {
	            board[column][row+1].owner = currentPlayer;

	        }
	    }
	 boolean checkWin() {

	        for(int i = 0; i < 3; i++) {
	            //horizontal
	            Player owner = board[0][i].getOwner();
	            if(owner != null && owner == board[1][i].getOwner() && owner == board[2][i].getOwner()) {
	                return true;
	            }
	            //vertical
	            owner = board[i][0].getOwner();
	            if(owner != null && owner == board[i][1].getOwner() && owner == board[i][2].getOwner()) {
	                return true;
	            }
	        }

	        //diagonal
	        Player owner = board[1][1].getOwner();
	        if(owner != null && owner == board[0][0].getOwner() && owner == board[2][2].getOwner())
	            return true;
	        return owner != null && owner == board[2][0].getOwner() && owner == board[0][2].getOwner();


	    }
	 ECell winsAgainst(ECell cell) {
	        if(ECell.SCISSOR == cell) {
	            return ECell.ROCK;
	        }
	        if(ECell.ROCK == cell) {
	            return ECell.PAPER;
	        }
	        if(ECell.PAPER == cell) {
	            return ECell.SCISSOR;
	        }

	        return ECell.EMPTY;
	    }
	    ECell loseAgainst(ECell cell) {
	        if(ECell.SCISSOR == cell) {
	            return ECell.PAPER;
	        }
	        if(ECell.ROCK == cell) {
	            return ECell.SCISSOR;
	        }
	        if(ECell.PAPER == cell) {
	            return ECell.ROCK;
	        }

	        return ECell.EMPTY;
	    }
	 
	 
	    void endTurn() {

	        if(checkWin()) {
	            gameOver = true;
	            
	            int winner = currentPlayer.getId();
	            
	   //         scorer.printLetter("+\t"+winner);
	            return;
	        }

	        if(currentPlayer == player)
	            currentPlayer = player2;
	        else
	            currentPlayer = player;
	        
	        currentTurn++;

	        currentValue = winsAgainst(currentValue);
	        
	    }
	    
	 void setPlayer2(Player player) {
		player2 = player;
		
		//players complete, start the games
		start();
	}
	
	public GameState getGameState(Player player) {
		Player opponent;  
		if(this.player.getId() == player.getId())
			opponent = player2;
		else 
			opponent = this.player;
		
		boolean won = false;
		
		if(gameOver && player.getId() == currentPlayer.getId()) {
			won = true;
		}
		if(gameOver) {
			if(player.getId() == this.player.getId()) {
				resultCheckedByPlayer = true;
			}
			else {
				resultCheckedByPlayer2 = true;
			}
			
			if(resultCheckedByPlayer && resultCheckedByPlayer2) {
				terminate = true;
			}
		}
		boolean opponentsTurn = (currentPlayer.getId() != player.getId());
		String opponentsName = "";
		if(opponent != null)
			opponentsName = opponent.getUserName();
		
		CellTO[][] playerBoard = dtoAssembler.makeCellTO(player, board);
		
		return new GameState(gameId, opponentsName, currentTurn, opponentsTurn, currentValue, gameOver, won, playerBoard);
	}
}
