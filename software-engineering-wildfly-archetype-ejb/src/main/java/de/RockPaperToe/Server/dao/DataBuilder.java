package de.RockPaperToe.Server.dao;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import de.RockPaperToe.Server.Highscore.Highscore;
import de.RockPaperToe.Server.Player.Player;

@Singleton
@Startup
public class DataBuilder {

	private static final Logger logger = Logger.getLogger(DataBuilder.class);
	
	@PersistenceContext
	EntityManager em;
	
	@EJB
	PersistenceManagerLocal dao;
	
	/**
	 * Test-szenario
	 * Creating 11 Player with 11 Highscore
	 * Using all Setter-Method for the objects
	 * Persisting all Objects for testing
	 * Show Top10 Highscore
	 * Add new score to Player with id 77077 => set score from 20 to 10020. So he will change his Ranking from 20 to 1
	 * Show Top10 Highscore => new Player in List (PlayerId 77077 is the first one)
	 * 
	 * @author Antonios Kouklidis
	 */
	@PostConstruct
	private void createTestData(){
		/*
		Player player1 = dao.findPlayerById(1);
		if(player1 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player1 = new Player("1", "Max");
			em.persist(player1);
			Highscore highscore1 = new Highscore(player1);
			highscore1.setScore(450);
			highscore1.setRanking(1);
			em.persist(highscore1);
			logger.info("DATENBANK == Player angelegt:" +player1);
			logger.info("DATENBANK == Highscore angelegt: "+highscore1);
		}
		
		if(player1 != null){
			Highscore me = dao.findHighscoreByGoogleId(1);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 1== " +me.toString());
		}
		
		Player player2 = dao.findPlayerById(50);
		if(player2 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player2 = new Player("50", "Rocky");
			em.persist(player2);
			Highscore highscore2 = new Highscore(player2);
			highscore2.setScore(350);
			highscore2.setRanking(2);
			em.persist(highscore2);
			logger.info("DATENBANK == Player angelegt:" +player2);
			logger.info("DATENBANK == Highscore angelegt: "+highscore2);
		}
		
		if(player2 != null){
			Highscore me = dao.findHighscoreById(50);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 50 === " +me.toString());
		}
		
		Player player3 = dao.findPlayerById(20);
		if(player3 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player3 = new Player("20", "Tommy");
			em.persist(player3);
			Highscore highscore3 = new Highscore(player3);
			highscore3.setScore(300);
			highscore3.setRanking(3);
			em.persist(highscore3);
			logger.info("DATENBANK == Player angelegt:" +player3);
			logger.info("DATENBANK == Highscore angelegt: "+highscore3);
		}
		
		if(player3 != null){
			Highscore me = dao.findHighscoreById(20);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 20 === " +me.toString());
		}
		
		Player player4 = dao.findPlayerById(3333);
		if(player4 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player4 = new Player("3333", "Ace");
			em.persist(player4);
			Highscore highscore4 = new Highscore(player4);
			highscore4.setScore(290);
			highscore4.setRanking(4);
			em.persist(highscore4);
			logger.info("DATENBANK == Player angelegt:" +player4);
			logger.info("DATENBANK == Highscore angelegt: "+highscore4);
		}
		
		if(player4 != null){
			Highscore me = dao.findHighscoreById(3333);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 3333 === " +me.toString());
		}
		
		Player player5 = dao.findPlayerById(334);
		if(player5 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player5 = new Player("334", "Janna");
			em.persist(player5);
			Highscore highscore5 = new Highscore(player5);
			highscore5.setScore(280);
			highscore5.setRanking(5);
			em.persist(highscore5);
			logger.info("DATENBANK == Player angelegt:" +player5);
			logger.info("DATENBANK == Highscore angelegt: "+highscore5);
		}
		
		if(player5 != null){
			Highscore me = dao.findHighscoreById(334);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 334 === " +me.toString());
		}
		
		Player player6 = dao.findPlayerById(111);
		if(player6 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player6 = new Player("111", "Bud");
			em.persist(player6);
			Highscore highscore6 = new Highscore(player6);
			highscore6.setScore(250);
			highscore6.setRanking(6);
			em.persist(highscore6);
			logger.info("DATENBANK == Player angelegt:" +player6);
			logger.info("DATENBANK == Highscore angelegt: "+highscore6);
		}
		
		if(player6 != null){
			Highscore me = dao.findHighscoreById(111);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 111 === " +me.toString());
		}
		
		Player player7 = dao.findPlayerById(67);
		if(player7 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player7 = new Player("67", "Benni");
			em.persist(player7);
			Highscore highscore7 = new Highscore(player7);
			highscore7.setScore(230);
			highscore7.setRanking(7);
			em.persist(highscore7);
			logger.info("DATENBANK == Player angelegt:" +player7);
			logger.info("DATENBANK == Highscore angelegt: "+highscore7);
		}
		
		if(player7 != null){
			Highscore me = dao.findHighscoreById(67);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 67 === " +me.toString());
		}
		
		Player player8 = dao.findPlayerById(55);
		if(player8 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player8 = new Player("55", "Jack");
			em.persist(player8);
			Highscore highscore8 = new Highscore(player8);
			highscore8.setScore(155);
			highscore8.setRanking(8);
			em.persist(highscore8);
			logger.info("DATENBANK == Player angelegt:" +player8);
			logger.info("DATENBANK == Highscore angelegt: "+highscore8);
		}
		
		if(player8 != null){
			Highscore me = dao.findHighscoreById(55);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 55 === " +me.toString());
		}
		
		Player player9 = dao.findPlayerById(15);
		if(player9 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player9 = new Player("15", "Sebi");
			em.persist(player9);
			Highscore highscore9 = new Highscore(player9);
			highscore9.setScore(140);
			highscore9.setRanking(9);
			em.persist(highscore9);
			logger.info("DATENBANK == Player angelegt:" +player9);
			logger.info("DATENBANK == Highscore angelegt: "+highscore9);
		}
		
		if(player9 != null){
			Highscore me = dao.findHighscoreById(15);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 15 === " +me.toString());
		}
		
		Player player10 = dao.findPlayerById(15000);
		if(player10 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player10 = new Player("15000", "Angela");
			em.persist(player10);
			Highscore highscore10 = new Highscore(player10);
			highscore10.setScore(50);
			highscore10.setRanking(10);
			em.persist(highscore10);
			logger.info("DATENBANK == Player angelegt:" +player10);
			logger.info("DATENBANK == Highscore angelegt: "+highscore10);
		}
		
		if(player10 != null){
			Highscore me = dao.findHighscoreById(15000);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 15000 === " +me.toString());
		}
		
		Player player11 = dao.findPlayerById(77077);
		if(player11 == null){
			logger.info("DATENBANK: Player gibt es nicht");
			player11 = new Player("77077", "Bodo");
			em.persist(player11);
			Highscore highscore11 = new Highscore(player11);
			highscore11.setScore(20);
			highscore11.setRanking(30);
			em.persist(highscore11);
			logger.info("DATENBANK == Player angelegt:" +player11);
			logger.info("DATENBANK == Highscore angelegt: "+highscore11);
		}
		
		if(player11 != null){
			Highscore me = dao.findHighscoreById(dao.findPlayerById);
			logger.info("DATENBANK == er hat den Highscore anhand der PlayerId gefunden id war 77077 === " +me.toString());
		}
		
		logger.info("DATENBANK gib mir die top 10");
		ArrayList<Highscore> test = new ArrayList<>();
		test = dao.getTop10Highscores();
		int size = test.size();
		for(int i = 0; i < size; i++){
		logger.info("DATENBANK Objekte in der Liste MYSQL: "+test.get(i));	
		}
		
		Highscore h = this.dao.findHighscoreById(77077);
		if(h != null){
			int oldscore = h.getScore();
			h.setScore(oldscore+10000);
			em.merge(h);
			this.dao.updateRanking();
		}
		
		logger.info("DATENBANK gib mir die top 10");
		ArrayList<Highscore> test2 = new ArrayList<>();
		test2 = dao.getTop10Highscores();
		int size2 = test2.size();
		for(int i = 0; i < size2; i++){
		logger.info("DATENBANK Objekte in der Liste MYSQL: "+test.get(i));	
		}*/
	}
}
