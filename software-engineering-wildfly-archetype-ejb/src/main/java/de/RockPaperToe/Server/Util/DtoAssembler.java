package de.RockPaperToe.Server.Util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.RockPaperToe.Server.DTO.CellTO;
import de.RockPaperToe.Server.DTO.HighscoreTO;
import de.RockPaperToe.Server.Game.Cell;
import de.RockPaperToe.Server.Highscore.Highscore;
import de.RockPaperToe.Server.Player.Player;

@Stateless
public class DtoAssembler {

	/**
	 * Create an extra DTO-Object of a Highscore-Object
	 * DTO-Object gets Information of the real Highscore-Object
	 * 
	 * @param highscore
	 * @return dto
	 * @author Antonios Kouklidis
	 */
	public HighscoreTO makeDTO(Highscore highscore){
		HighscoreTO dto = new HighscoreTO();
		dto.setId(highscore.getId());
		dto.setScore(highscore.getScore());
		dto.setPlayerId(highscore.getPlayerId());
		dto.setPlayerName(highscore.getPlayerName());
		dto.setRanking(highscore.getRanking());
		return dto;
	}
	
	/**
	 * Creating a List of HighscoreTO-Objects
	 * Adding Highscores-Object to the List
	 * after they convert to DTO-Objects
	 * 
	 * @param highscores
	 * @return dtoList
	 */
	public List<HighscoreTO> makeDTO(List<Highscore> highscores){
		ArrayList<HighscoreTO> dtoList = new ArrayList<>();
		for(Highscore h: highscores){
			dtoList.add(this.makeDTO(h));
		}
		return dtoList;
	}
	
	public CellTO[][] makeCellTO(Player player, Cell[][] board) {
		CellTO[][] playerBoard = new CellTO[3][3];
		
		for(int x = 0; x<3; x++) {
			for(int y = 0; y<3; y++) {
				boolean owner = (board[x][y].getOwner() != null  
						&& player.getId() == board[x][y].getOwner().getId());
				
				playerBoard[x][y] = new CellTO(owner, board[x][y].getValue());
			}
		}
		
		return playerBoard;
		
		
	}
}
