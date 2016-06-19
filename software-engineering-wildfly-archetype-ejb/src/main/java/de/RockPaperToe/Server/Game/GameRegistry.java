package de.RockPaperToe.Server.Game;

import java.util.ArrayList;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import org.jboss.logging.Logger;

import de.RockPaperToe.Server.Player.Player;


/**
 * Session Bean implementation class GameRegistry
 */
@Singleton
public class GameRegistry {
	private static final Logger logger = Logger.getLogger(GameRegistry.class);
	ArrayList<Game> gameList;
	ArrayList<Game> emptyGames;
	
    /**
     * Default constructor. 
     */
    public GameRegistry() {
        gameList = new ArrayList<>();
        emptyGames = new ArrayList<>();
    }

    @Lock(LockType.WRITE)
	public void lookForNewGame(Player player) {
		for(int i = 0; i<emptyGames.size(); i++) {
			Game game = emptyGames.get(i);
			if(game.getPlayer().getId() != player.getId()) {
				game.setPlayer2(player);
				emptyGames.remove(i);
				return;
			}
		}
		
//no empty games, create new game for player
		Game newGame = new Game(player);
		emptyGames.add(newGame);
		gameList.add(newGame);
		logger.info("Created new game for player");
	}
    
    @Lock(LockType.READ)
	public ArrayList<Game> findGamesForPlayer(Player player) {
		ArrayList<Game> games = new ArrayList<>();
		
		for(int i = 0; i<gameList.size(); i++) {
			Game game = gameList.get(i);
			Player player2 = game.getPlayer2();
			if(game.getPlayer().getId() == player.getId() || (player2 != null && player2.getId() == player.getId())) {
				games.add(game);
			}
		}
		
		logger.info("Number of games found: "+games.size());
		return games;
	}
}
