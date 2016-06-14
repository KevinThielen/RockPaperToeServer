package de.RockPaperToe.Server;


import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.RockPaperToe.Server.DTO.DTO;
import de.RockPaperToe.Server.DTO.HighscoreResponse;
import de.RockPaperToe.Server.Highscore.Highscore;
import de.RockPaperToe.Server.Highscore.HighscoreRegistry;
import de.RockPaperToe.Server.Util.DtoAssembler;

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
	
	
	@EJB
	private HighscoreRegistry highscoreRegistry;
	
	@EJB
	private DtoAssembler dtoAssembler;
	
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
			
		}
		
		return response;
	}
}
