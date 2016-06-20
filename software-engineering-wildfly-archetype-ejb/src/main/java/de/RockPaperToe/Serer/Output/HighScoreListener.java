package de.RockPaperToe.Serer.Output;

import java.util.regex.Pattern;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import de.RockPaperToe.Server.RockPaperToeServer;
import de.RockPaperToe.Server.Highscore.Highscore;

/**
 * Message-Driven Bean implementation class for: HighScoreListener
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(
				propertyName = "destination",
				propertyValue = "queue/Queue1")
		})
public class HighScoreListener implements MessageListener {

	@PersistenceContext
	EntityManager em;


	
    public HighScoreListener() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	
        // TODO Auto-generated method stub
    	TextMessage msg = (TextMessage) message;
    	Pattern pattern = Pattern.compile(Pattern.quote("\t"));
    	String[] data;
		try {
			data = pattern.split(msg.getText());
	        int highscore = Integer.parseInt(data[0]);
	        String player = data[1];
	        
	        TypedQuery<Highscore> query = em.createQuery("SELECT h from Highscore h where player_id = ?1", Highscore.class);
			Highscore me =  (Highscore) query.setParameter(1, Integer.parseInt(player)).getSingleResult();
			me.addScore(highscore);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        

        

    }

}
