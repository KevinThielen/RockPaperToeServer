package de.RockPaperToe.Server;


import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;



import de.RockPaperToe.Server.DTO.HighscoreListResponse;
import de.RockPaperToe.Server.DTO.HighscoreResponse;
import de.RockPaperToe.Server.DTO.LoginResponse;
import de.RockPaperToe.Server.DTO.RegistrationResponse;
import de.RockPaperToe.Server.Highscore.Highscore;
import de.RockPaperToe.Server.Session.Session;
import de.RockPaperToe.Server.Session.SessionManager;
import de.RockPaperToe.Server.Util.DtoAssembler;
import de.RockPaperToe.Server.Player.Player;
import de.RockPaperToe.Server.DTO.UpdateScoreResponse;
import de.RockPaperToe.Server.dao.PersistenceManagerLocal;


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
	private PersistenceManagerLocal dao;
	
	@EJB
	private DtoAssembler dtoAssembler;
	
    @EJB
    private SessionManager sessionManager;
    
	
	public LoginResponse login(String googleId) {
		logger.info("Received ID: "+googleId);
		Player player = dao.findPlayerByGoogleId(googleId);
	
		
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
    	
    	dao.addPlayer(userId, name);
    	
    	Player player = dao.findPlayerByGoogleId(userId);
    	
    	Session session = sessionManager.login(player);
    	
    	if(player != null) {
    		logger.info("Created session with id: "+session.getId());
    		return new RegistrationResponse(session.getId());
    	}
    	else {
    		return new RegistrationResponse(-1);
    	}
    }
	
	
	

	/**
	 * Method for updating the score of a player
	 * Persistence-Manager will find the Highscore by the playerId
	 * When Highscore-Object found => update information (score)
	 * Then Highscore-Objects have to be ordered because of getting the latest Top10
	 * 
	 * @param playerId => id of the Player
	 * @param newScore => Points after a winning game
	 * @return response => UpdateScoreResponse if success or not
	 * @author Antonios Kouklidis
	 */
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
	
	/**
	 * Method for getting an ArrayList with Top10 Players
	 * Persistence-Manager return an ordered List with Highscore-Objects
	 * 
	 * @return response => HighscoreListResponse if success or not
	 * @author Antonios Kouklidis
	 */
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
	
	/**
	 * Method for finding a Highscore-Object of a Player
	 * Persistence-Manager finds the Highscore-Object by the playerId
	 * 
	 * @param id => id of a Player
	 * @return response => if success or not
	 * @author Antonios Kouklidis
	 */
	public HighscoreResponse getMyHighscore(int id){
		HighscoreResponse response = new HighscoreResponse();
		
		try{
			Highscore me = this.dao.findHighscoreById(id);
			if(me != null){
				logger.info("Meinen Highscore gefunden!");
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
}
