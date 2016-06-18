package de.RockPaperToe.Server;


import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;



import de.RockPaperToe.Server.DTO.DTO;
import de.RockPaperToe.Server.DTO.HighscoreListResponse;
import de.RockPaperToe.Server.DTO.HighscoreResponse;
import de.RockPaperToe.Server.DTO.LoginResponse;
import de.RockPaperToe.Server.DTO.RegistrationResponse;
import de.RockPaperToe.Server.Highscore.Highscore;
import de.RockPaperToe.Server.Highscore.HighscoreRegistry;
import de.RockPaperToe.Server.Player.PlayerRegistry;
import de.RockPaperToe.Server.Session.Session;
import de.RockPaperToe.Server.Session.SessionManager;
import de.RockPaperToe.Server.Util.DtoAssembler;
import de.RockPaperToe.Server.Player.Player;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Session Bean implementation class RockPaperToeServer
 */
@WebService
@WebContext(contextRoot = "/RockPaperToeServer")
@Stateless
public class RockPaperToeServer {

	private static final Logger logger = Logger.getLogger(RockPaperToeServer.class);
	
    @EJB
    private SessionManager sessionManager;
    
    @EJB
    private PlayerRegistry playerRegistry;
    
	@EJB
	private HighscoreRegistry highscoreRegistry;
	@EJB
	private DtoAssembler dtoAssembler;
	
	
	
	public LoginResponse login(String userId) {
		logger.info("Received ID: "+userId);
		Player player = playerRegistry.getPlayerByGoogleId(userId);
	
		
		if(player != null) {
			//sessionhandling 
			logger.info("User exists. Logged in");
			return new LoginResponse(0);
		}
		else {
			logger.info("User doesn't exist. Request new Registration");
			return new LoginResponse(-1);
		}
	}
	
    public RegistrationResponse register(String userId, String name)  {
    	logger.info("Received ID: "+userId);
    	logger.info("Received Name: "+name);
    	
    	playerRegistry.addPlayer(new Player(name, userId));
    	
    	Player player = playerRegistry.getPlayerByGoogleId(userId);
    	
    	Session session = sessionManager.login(player);
    	
    	if(player != null) {
    		logger.info("Created session with id: "+session.getId());
    		return new RegistrationResponse(session.getId());
    	}
    	else {
    		return new RegistrationResponse(-1);
    	}
    }
	
	
	
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
	}
}
