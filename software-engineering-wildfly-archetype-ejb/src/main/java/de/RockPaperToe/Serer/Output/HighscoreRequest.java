package de.RockPaperToe.Serer.Output;


import javax.jms.*;

import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
public class HighscoreRequest {
	
	@Resource(lookup = "java:/JmsXA")
	private ConnectionFactory connectionFactory;
	@Resource(lookup = "java:/jms/queue1")
	private Queue queue;
		
	private static final Logger logger = Logger.getLogger(HighscoreRequest.class);
	
	public void printLetter(String letter){
		logger.info("NEw request: "+letter);
		try(JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)){
			TextMessage message = context.createTextMessage();
			message.setStringProperty("DocType","Letter");
			message.setText(letter);
			context.createProducer().send(queue, message);
		}
		catch(JMSException e){
			throw new EJBException(e);
		}
	}
}