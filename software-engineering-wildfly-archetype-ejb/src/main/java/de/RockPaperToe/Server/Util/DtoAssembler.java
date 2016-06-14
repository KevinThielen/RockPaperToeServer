package de.RockPaperToe.Server.Util;

import javax.ejb.Stateless;

import de.RockPaperToe.Server.DTO.HighscoreTO;
import de.RockPaperToe.Server.Highscore.Highscore;

@Stateless
public class DtoAssembler {
	
	public HighscoreTO makeDTO(Highscore highscore){
		HighscoreTO dto = new HighscoreTO();
		dto.setId(highscore.getId());
		dto.setScore(highscore.getScore());
		dto.setRanking(highscore.getRanking());
		return dto;
	}
}
