package de.RockPaperToe.Server;


import org.jboss.ws.api.annotation.WebContext;

import de.RockPaperToe.Server.DTO.DTO;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Session Bean implementation class RockPaperToeServer
 */
@WebService
@WebContext(contextRoot = "/UnoServer")
@Stateless
public class RockPaperToeServer {

	public DTO login(String name, String pw) {
		//name und pw prüfen
		//dto ändern für relevante logindaten
		return new DTO();
	}
	
}
