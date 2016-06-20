package de.RockPaperToe.Server;


import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.RockPaperToe.Server.DTO.GameListResponse;
import de.RockPaperToe.Server.DTO.HighscoreListResponse;
import de.RockPaperToe.Server.DTO.HighscoreResponse;
import de.RockPaperToe.Server.DTO.LoginResponse;
import de.RockPaperToe.Server.Highscore.Highscore;
import de.RockPaperToe.Server.Session.Session;
import de.RockPaperToe.Server.Session.SessionManager;
import de.RockPaperToe.Server.Util.DtoAssembler;
import de.RockPaperToe.Server.Player.Player;
import de.RockPaperToe.Server.DTO.UpdateScoreResponse;
import de.RockPaperToe.Server.Game.Game;
import de.RockPaperToe.Server.Game.GameRegistry;
import de.RockPaperToe.Server.Game.GameState;
import de.RockPaperToe.Server.dao.PersistenceManagerLocal;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Session Bean implementation class RockPaperToeServer
 */
@WebService
@WebContext(contextRoot = "/RPTServer")
@Stateless
public class RockPaperToeServer {

	private static final Logger logger = Logger.getLogger(RockPaperToeServer.class);
	
	@EJB
	private PersistenceManagerLocal dao;
	
	@EJB
	private DtoAssembler dtoAssembler;
	
    @EJB
    private SessionManager sessionManager;
    
    @EJB
    GameRegistry gameManager;
    
    /**
     * Looks for a new game for the player.
     * If there are no empty games available, it will create a new one.
     * It returns a list of all available games for the player.
     * @author Kevin Thielen
     */
    public GameListResponse newGame(int sessionId) {
    	logger.info("Request for new Game by ID: "+sessionId);
    	Session session = sessionManager.getSessionById(sessionId);
    	
    	if(session != null) {
	    	Player player = session.getPlayer();
	    	
	    	gameManager.lookForNewGame(player);
	    	ArrayList<Game> games = gameManager.findGamesForPlayer(player);
	    	
	    	GameListResponse response = new GameListResponse(games, player);
	    	
	    	return response;
    	}
    	else {
    		GameListResponse response = new GameListResponse();
        	response.setReturnCode(-1);
        	response.setMessage("No valid Session active");
        	
        	return response;
    	}
    }
    /**
     * Make a play move. The move will be validated on the server. It return
     * the entire gamestate.
     * @author Kevin Thielen
     */
    public GameState makeMove(int sessionId, int gameId, int x, int y) {
    	logger.info("Request for GameList by ID: "+sessionId);
    	
    	Player player = sessionManager.getSessionById(sessionId).getPlayer();
    	
    	gameManager.makeMove(player, gameId, x, y);
    	
    	return gameManager.getGameState(player, gameId);
    }
    /**
     * Returns the gmestate for a specific game. It finds the game viw
     * the gameId from the gameManager
     * @author Kevin Thielen
     */
    public GameState getGameState(int sessionId, int gameId) {
    	logger.info("Request for GameList by ID: "+sessionId);
    	
    	Player player = sessionManager.getSessionById(sessionId).getPlayer();
    	
    	return gameManager.getGameState(player, gameId);
    }
    /**
     * Returns a list of all available games for the player.
     * @author Kevin Thielen
     */
    public GameListResponse getGames(int sessionId) {
    	logger.info("Request for GameList by ID: "+sessionId);
    	
    	Session session = sessionManager.getSessionById(sessionId);
    	if(session == null) {
        	GameListResponse response = new GameListResponse();
        	response.setReturnCode(-1);
        	response.setMessage("No valid Session active");
        	
        	return response;
    	}
    	else {
    	Player player = session.getPlayer();
    	ArrayList<Game> games = gameManager.findGamesForPlayer(player);
    	
    	GameListResponse response = new GameListResponse(games, player);
    	
    	return response;
    	}
    }
    /**
     * Creates a new session for the user. If the user 
     * doesnt exist, it will ask him to create a new accout.
     * @author Kevin Thielen
     */
	public LoginResponse login(String googleId) {
		logger.info("Received ID: "+googleId);
		Player player = dao.findPlayerByGoogleId(googleId);
	
		
		if(player != null) {
			logger.info("User exists. Logged in");
			Session session = sessionManager.login(player);
			
			logger.info("Created session with id: "+session.getId());
			return new LoginResponse(session.getId(), player.getUserName());
		}
		else {
			logger.info("User doesn't exist. Request new Registration");
			return new LoginResponse(-1);
		}
	}
	
    /**
     * Bind the googleId with the user account
     * and creates a new Session
     * @author Kevin Thielen
     */
    public LoginResponse register(String userId, String name)  {
    	logger.info("Received ID: "+userId);
    	logger.info("Received Name: "+name);
    	
    	dao.addPlayer(userId, name);
    	
    
	    Player player = dao.findPlayerByGoogleId(userId);
	    	
	    Session session = sessionManager.login(player);
	    	

	    logger.info("Created session with id: "+session.getId());
	    return new LoginResponse(session.getId(), player.getUserName());
	
    	
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
	public UpdateScoreResponse updatePoints(int sessionId, int newScore){
		Session session = sessionManager.getSessionById(sessionId);
		
		int playerId = session.getPlayer().getId();
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
	public HighscoreResponse getMyHighscore(int sessionId){
		Session session = sessionManager.getSessionById(sessionId);
		
		int playerId = session.getPlayer().getId();
		HighscoreResponse response = new HighscoreResponse();
		
		try{
			Highscore me = this.dao.findHighscoreById(playerId);
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
