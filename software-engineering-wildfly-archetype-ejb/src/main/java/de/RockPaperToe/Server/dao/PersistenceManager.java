package de.RockPaperToe.Server.dao;

import java.util.ArrayList;

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
	
	public Highscore findHighscoreById(int id){
		TypedQuery<Highscore> query = em.createQuery("SELECT h from Highscore h where player_id = ?1", Highscore.class);
		Highscore me =  (Highscore) query.setParameter(1, id).getSingleResult();
		return me;
	}
	
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

	@Override
	public Player findPlayerById(int id) {
		Player player = em.find(Player.class, id);
		return player;
	}
}
