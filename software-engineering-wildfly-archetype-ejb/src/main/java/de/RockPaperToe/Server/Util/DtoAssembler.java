package de.RockPaperToe.Server.Util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.RockPaperToe.Server.DTO.HighscoreTO;
import de.RockPaperToe.Server.Highscore.Highscore;

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
}
