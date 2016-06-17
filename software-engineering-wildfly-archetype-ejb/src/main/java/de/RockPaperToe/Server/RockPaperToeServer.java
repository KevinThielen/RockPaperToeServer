package de.RockPaperToe.Server;


import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.RockPaperToe.Server.DTO.DTO;
import de.RockPaperToe.Server.DTO.HighscoreListResponse;
import de.RockPaperToe.Server.DTO.HighscoreResponse;
import de.RockPaperToe.Server.DTO.UpdateScoreResponse;
import de.RockPaperToe.Server.Highscore.Highscore;
import de.RockPaperToe.Server.Highscore.HighscoreRegistry;
import de.RockPaperToe.Server.Util.DtoAssembler;
import de.RockPaperToe.Server.dao.PersistenceManagerLocal;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Session Bean implementation class RockPaperToeServer
 */
@WebService
@WebContext(contextRoot = "/UnoServer")
@Stateless
public class RockPaperToeServer {

	private static final Logger logger = Logger.getLogger(RockPaperToeServer.class);
	
	public DTO login(String name, String pw) {
		//name und pw prüfen
		//dto ändern für relevante logindaten
		return new DTO();
	}
	
	
	//@EJB
	//private HighscoreRegistry highscoreRegistry;
	
	@EJB
	private PersistenceManagerLocal dao;
	
	@EJB
	private DtoAssembler dtoAssembler;
	
	public UpdateScoreResponse updatePoints(int playerId, int newScore){
		logger.info("Fassade: updateScore aufgerufen mit PlayerId: "+playerId+" und newScore: "+newScore);
		UpdateScoreResponse response = new UpdateScoreResponse();
		try{
			Highscore highscore = this.dao.findHighscoreById(playerId);
			if(highscore != null){
				int oldscore = highscore.getScore();
				highscore.setScore(oldscore+newScore);
				this.dao.updateRanking();
				response.setNewScore(highscore.getScore());
			}
		}
		catch(Exception e){
			response.setReturnCode(1);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	public HighscoreListResponse getTop10(){
		HighscoreListResponse response = new HighscoreListResponse();
		try{
			List<Highscore> highscoreList = this.dao.getTop10Highscores();
			if(highscoreList != null){
				response.setHighscoreList(dtoAssembler.makeDTO(highscoreList));
				logger.info("Resultat der Highscoreliste: "+highscoreList);
			}
			else{
				logger.info("Keine Highscoreliste gefunden");
			}
		}
		catch(Exception e){
			response.setReturnCode(1);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	public HighscoreResponse getMyHighscore(int id){
		HighscoreResponse response = new HighscoreResponse();
		
		try{
			Highscore me = this.dao.findHighscoreById(id);
			if(me != null){
				logger.info("Meinen Highscore gefunden! :)");
				response.setHighscore(this.dtoAssembler.makeDTO(me));
			}
			else{
				logger.info("Mein Highscore konnte nicht gefunden werden");
			}
		}
		catch (Exception e){
			response.setReturnCode(1);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	/*
	public HighscoreResponse getMyHighscore(int id){
		HighscoreResponse response = new HighscoreResponse();
		
		try{
			Highscore me = this.highscoreRegistry.findHighscoreById(id);
			if(me != null){
				logger.info("Meinen Highscore gefunden! :)");
				response.setHighscore(this.dtoAssembler.makeDTO(me));
			}
			else{
				logger.info("Mein Highscore konnte nicht gefunden werden");
			}
		}
		catch (Exception e){
			response.setReturnCode(1);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	public HighscoreListResponse getTop10(){
		HighscoreListResponse response = new HighscoreListResponse();
		try{
			List<Highscore> highscoreList = highscoreRegistry.getHighscores();
			if(highscoreList != null){
				response.setHighscoreList(dtoAssembler.makeDTO(highscoreList));
				logger.info("Resultat der Highscoreliste: "+highscoreList);
			}
			else{
				logger.info("Keine Highscoreliste gefunden");
			}
		}
		catch(Exception e){
			response.setReturnCode(1);
			response.setMessage(e.getMessage());
		}
		return response;
	}*/
}
