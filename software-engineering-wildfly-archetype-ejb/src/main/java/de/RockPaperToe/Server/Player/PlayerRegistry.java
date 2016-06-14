package de.RockPaperToe.Server.Player;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

import de.RockPaperToe.Server.Highscore.Highscore;
import de.RockPaperToe.Server.Highscore.HighscoreRegistry;

@Singleton
@Startup
@DependsOn("HighscoreRegistry")
@EJB(name="ejb/HighscoreManager", beanInterface=HighscoreRegistry.class, beanName="HighscoreRegistry")
public class PlayerRegistry {

	private static final Logger logger = Logger.getLogger(HighscoreRegistry.class);
	
	private ArrayList<Player> players;
	
	@Resource
	private String player1Name = "Max", player2Name = "Anna";
	
	@Resource
	private SessionContext context;
	
	@PostConstruct
	private void init(){
		players = new ArrayList<>();
		
		//Spieler anlegen
		Player max = new Player(player1Name);
		Player anna = new Player(player2Name);
		
		//Spieler in die Registry-ArrayList
		addPlayer(max);
		addPlayer(anna);
		logger.info("Player Max angelegt: "+max);
		logger.info("Player Anna angelegt: "+anna);
		
		//Highscore anlegen
		Highscore maxHighscore = new Highscore(max);
		maxHighscore.setScore(3250);
		maxHighscore.setRanking(1);
		Highscore annaHighscore = new Highscore(anna);
		annaHighscore.setScore(2000);
		annaHighscore.setRanking(2);
		
		//Highscores in die Registry-ArrayList
		HighscoreRegistry highscoreRegistry = (HighscoreRegistry) context.lookup("ejb/HighscoreManager");
		highscoreRegistry.addHighscore(maxHighscore);
		highscoreRegistry.addHighscore(annaHighscore);
		
		logger.info("Highscore angelegt: "+maxHighscore);
		logger.info("Highscore angelegt: "+annaHighscore);
	}
	
	
	@Lock(LockType.READ)
	public Player findPlayerById(int userId){
		return this.players.get(userId);
	}
	
	@Lock(LockType.WRITE)
	public void addPlayer(Player player){
		this.players.add(player);
	}
}
