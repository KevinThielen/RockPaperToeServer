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
		Player johann = new Player("Johann");
		Player manfred = new Player("Manfred");
		Player andre = new Player("Andre");
		Player kevin = new Player("Kevin");
		Player jasmin = new Player("Jasmin");
		Player arnold = new Player("Arnold");
		Player julian = new Player("Julian");
		Player lena = new Player("Lena");
		Player janna = new Player("Janna");
		
		//Spieler in die Registry-ArrayList
		addPlayer(max);
		addPlayer(anna);
		addPlayer(johann);
		addPlayer(manfred);
		addPlayer(andre);
		addPlayer(kevin);
		addPlayer(jasmin);
		addPlayer(arnold);
		addPlayer(julian);
		addPlayer(lena);
		addPlayer(janna);
		logger.info("Player Max angelegt: "+max);
		logger.info("Player Anna angelegt: "+anna);
		logger.info("Player Johann angelegt: "+johann);
		logger.info("Player Manfred angelegt: "+manfred);
		logger.info("Player Andre angelegt: "+andre);
		logger.info("Player Kevin angelegt: "+kevin);
		logger.info("Player Jasmin angelegt: "+jasmin);
		logger.info("Player Arnold angelegt: "+arnold);
		logger.info("Player Julian angelegt: "+julian);
		logger.info("Player Lena angelegt: "+lena);
		logger.info("Player Janna angelegt: "+janna);
		
		
		//Highscore anlegen
		Highscore maxHighscore = new Highscore(max);
		maxHighscore.setScore(3250);
		maxHighscore.setRanking(1);
		Highscore annaHighscore = new Highscore(anna);
		annaHighscore.setScore(2000);
		annaHighscore.setRanking(2);
		Highscore johannHighscore = new Highscore(johann);
		johannHighscore.setScore(1500);
		johannHighscore.setRanking(3);
		Highscore manfredHighscore = new Highscore(manfred);
		manfredHighscore.setScore(1450);
		manfredHighscore.setRanking(4);
		Highscore andreHighscore = new Highscore(andre);
		andreHighscore.setScore(1300);
		andreHighscore.setRanking(5);
		Highscore kevinHighscore = new Highscore(kevin);
		kevinHighscore.setScore(1200);
		kevinHighscore.setRanking(6);
		Highscore jasminHighscore = new Highscore(jasmin);
		jasminHighscore.setScore(1100);
		jasminHighscore.setRanking(7);
		Highscore arnoldHighscore = new Highscore(arnold);
		arnoldHighscore.setScore(1000);
		arnoldHighscore.setRanking(8);
		Highscore julianHighscore = new Highscore(julian);
		julianHighscore.setScore(900);
		julianHighscore.setRanking(9);
		Highscore lenaHighscore = new Highscore(lena);
		lenaHighscore.setScore(800);
		lenaHighscore.setRanking(10);
		Highscore jannaHighscore = new Highscore(janna);
		jannaHighscore.setScore(500);
		jannaHighscore.setRanking(11);
		
		//Highscores in die Registry-ArrayList
		HighscoreRegistry highscoreRegistry = (HighscoreRegistry) context.lookup("ejb/HighscoreManager");
		highscoreRegistry.addHighscore(maxHighscore);
		highscoreRegistry.addHighscore(annaHighscore);
		highscoreRegistry.addHighscore(johannHighscore);
		highscoreRegistry.addHighscore(manfredHighscore);
		highscoreRegistry.addHighscore(andreHighscore);
		highscoreRegistry.addHighscore(kevinHighscore);
		highscoreRegistry.addHighscore(jasminHighscore);
		highscoreRegistry.addHighscore(arnoldHighscore);
		highscoreRegistry.addHighscore(julianHighscore);
		highscoreRegistry.addHighscore(lenaHighscore);
		highscoreRegistry.addHighscore(jannaHighscore);
		
		logger.info("Highscore angelegt: "+maxHighscore);
		logger.info("Highscore angelegt: "+annaHighscore);
		logger.info("Highscore angelegt: "+johannHighscore);
		logger.info("Highscore angelegt: "+manfredHighscore);
		logger.info("Highscore angelegt: "+andreHighscore);
		logger.info("Highscore angelegt: "+kevinHighscore);
		logger.info("Highscore angelegt: "+jasminHighscore);
		logger.info("Highscore angelegt: "+arnoldHighscore);
		logger.info("Highscore angelegt: "+julianHighscore);
		logger.info("Highscore angelegt: "+lenaHighscore);
		logger.info("Highscore angelegt: "+jannaHighscore);
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
