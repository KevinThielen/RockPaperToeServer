package de.RockPaperToe.Server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.RockPaperToe.Server.Highscore.Highscore;
import de.RockPaperToe.Server.Player.Player;

@Stateless
public class PersistenceManager implements PersistenceManagerLocal {
	
	@PersistenceContext
	EntityManager em;
	
	/**
	 * After a game the winner get Points.
	 * The rank has to be updated
	 * 1. Step => get all Highscore-Objects ordered by score
	 * 2. Step => Set new Rank with a counter
	 * 3. Step => Save update changes in the database
	 * 
	 * @author Antonios Kouklidis
	 */
	public void updateRanking(){
		ArrayList<Highscore> highscores = new ArrayList<>();
		highscores = (ArrayList<Highscore>) em.createQuery("SELECT h from Highscore h ORDER BY score DESC", Highscore.class).getResultList();
		int rank = 1;
		for(int i = 0; i < highscores.size(); i++){
			highscores.get(i).setRanking(rank);
			em.merge(highscores.get(i));
			rank++;
		}
	}
	
	/**
	 * This function is responsible for finding the right Highscore-Object
	 * of a Player. The Highscore will be found with the PlayerId
	 * 
	 * @param id => the id of a Player
	 * @return me => is a Highscore-Object
	 * @author Antonios Kouklidis
	 */
	public Highscore findHighscoreById(int playerId){
		updateRanking();
		TypedQuery<Highscore> query = em.createQuery("SELECT h from Highscore h where player_id = ?1", Highscore.class);
		Highscore me =  (Highscore) query.setParameter(1, playerId).getSingleResult();
		return me;
	}
	
	/**
	 * 1. Step: Query for getting all Highscores-Objects ordered by score in an ArrayList
	 * 2. Step: Saving the first 10 Highscore-Objects in a new ArrayList
	 * Intention => return an ArrayList with only Top10 Player
	 * 
	 * @return top10 => An Array with Highscore-Objects
	 * @author Antonios Kouklidis
	 */
	public ArrayList<Highscore> getTop10Highscores(){
		ArrayList<Highscore> highscores = new ArrayList<>();
		highscores = (ArrayList<Highscore>) em.createQuery("SELECT h from Highscore h ORDER BY score DESC", Highscore.class).getResultList();
		
		int size = highscores.size();
		
		if(size > 10){
			size = 10;
		}
		
		ArrayList<Highscore> top10 = new ArrayList<>();
		
		for(int i = 0; i < size; i++){
			top10.add(highscores.get(i));
		}
		
		return top10;
	}

	/**
	 * Find the Player with the right id
	 * 
	 * @param id => param is the Id of a Player
	 * @author Antonios Kouklidis
	 */
	@Override
	public Player findPlayerById(int id) {
		Player player = em.find(Player.class, id);
		return player;
	}

	@Override
	public void addPlayer(String googleId, String userName) {
		Player player = new Player(googleId, userName);
		em.persist(player);
	}
	
	@Override
	public Player findPlayerByGoogleId(String googleId) {
		TypedQuery<Player> query = em.createQuery("SELECT p from Player p where googleId = ?1", Player.class);
		List results = query.setParameter(1, googleId).getResultList();
		
		if(results.isEmpty())
			return null;
		else 
			return (Player)results.get(0);
		
	}
}
