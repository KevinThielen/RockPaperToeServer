package de.RockPaperToe;

import org.jboss.arquillian.container.test.api.Deployment
;
import org.jboss.arquillian.junit.Arquillian
;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap
;
import org.jboss.shrinkwrap.api.asset.EmptyAsset
;
import org.jboss.shrinkwrap.api.spec.JavaArchive
;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert
;
import org.junit.Test
;
import org.junit.runner.RunWith
;

import static org.junit.Assert.*;

import javax.ejb.EJB
;

import de.RockPaperToe.Server.RockPaperToeServer;
import de.RockPaperToe.Server.DTO.LoginResponse;

@RunWith(Arquillian.class)
public class LoginTest {
	
	@EJB
	RockPaperToeServer bean;
	
	@Deployment
    public static WebArchive createDeployment() {
    	WebArchive archive = ShrinkWrap.create(WebArchive.class, "test.war")
               .addPackages(true,"de/RockPaperToe")
               .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")               
               .addAsWebInfResource("META-INF/ejb-jar.xml", "META-INF/ejb-jar.xml");
		archive.addClass(RockPaperToeServer.class);
		archive.addClass(RockPaperToeServer.class);
		archive.addClass(LoginResponse.class);
		return archive;
    }
	
	
	@Test
	/**
	 * Create a new Account via googleId
	 */
	public void loginTest() throws Exception{
			
		    LoginResponse response = bean.register("googleid", "Tommy Tester");     
		    int sessionId = response.getSessionId();
		    assertEquals(sessionId, 0);
		}

	}