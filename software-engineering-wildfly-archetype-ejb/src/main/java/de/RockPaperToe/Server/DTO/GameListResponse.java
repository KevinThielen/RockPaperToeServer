package de.RockPaperToe.Server.DTO;

import java.util.ArrayList;

import de.RockPaperToe.Server.Game.Game;
import de.RockPaperToe.Server.Game.GameState;
import de.RockPaperToe.Server.Player.Player;

public class GameListResponse extends DTO{

	ArrayList<GameState> gameStates;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public GameListResponse(ArrayList<Game> games, Player player) {
		gameStates = new ArrayList<>();
		for(int i = 0; i<games.size(); i++) {
			gameStates.add(games.get(i).getGameState(player));
		}
	}
	public void setGameStates(ArrayList<GameState> gameStates) {
		this.gameStates = gameStates;
	}
	public GameListResponse()  {
		
	}
	
	public ArrayList<GameState> getGameStates() {
		return gameStates;
	}
}
