package de.RockPaperToe.Server.Util;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<HighscoreTO> makeDTO(List<Highscore> highscores){
		ArrayList<HighscoreTO> dtoList = new ArrayList<>();
		for(Highscore h: highscores){
			dtoList.add(this.makeDTO(h));
		}
		return dtoList;
	}
}
