package swara.fun.plivo.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.plivo.helper.exception.PlivoException;
import com.plivo.helper.xml.elements.PlivoResponse;
import com.plivo.helper.xml.elements.Speak;

	public class Server extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	    	 try {
	    	// Generate a Speak XML with the details of the text to play on the call.
	        PlivoResponse response = new PlivoResponse();

	        // Add Speak XML Tag with English text
	        Speak spk1 = new Speak("Congratulations! You have successfully made a call with Pleevo.");
	        spk1.setLanguage("en-US"); // Language used to read out the text.
	        spk1.setVoice("MAN"); // The tone to be used for reading out the text.
	       
				response.append(spk1);
		

	        // Add Speak XML Tag with French Text
	        Speak spk2 = new Speak("Félicitations! Vous avez effectué avec succès un appel avec Pleevo.");
	        spk2.setLanguage("fr-FR"); // Language used to read out the text.
	        spk1.setVoice("WOMAN"); // The tone to be used for reading out the text.
	        response.append(spk2);

	        // Add Speak XML Tag with Spanish Text
	        Speak spk3 = new Speak("¡Enhorabuena! Usted ha realizado con éxito una llamada con Pleevo.");
	        spk3.setLanguage("es-US"); // Language used to read out the text.
	        spk1.setVoice("MAN"); // The tone to be used for reading out the text.
	        response.append(spk3);

	       
	            System.out.println(response.toXML());
	            resp.addHeader("Content-Type", "text/xml");
	            resp.getWriter().print(response.toXML());
	        } catch (PlivoException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void main(String[] args) throws Exception {
	     
	      
	        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(8080);
	        ServerConnector connector = new ServerConnector(server);
	        connector.setHost("0.0.0.0");
	        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	        context.setContextPath("/");
	        server.setHandler(context);
	        context.addServlet(new ServletHolder(new Server()),"/text-to-speech/");
	        server.start();
	        server.join();
	    
	}

}
