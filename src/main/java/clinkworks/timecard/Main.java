package clinkworks.timecard;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.clinkworks.timecard.config.DevelopmentConfig;
import com.clinkworks.timecard.config.JpaConfig;
import com.clinkworks.timecard.config.RestConfig;
import com.clinkworks.timecard.web.InvalidRequestServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;

/**
 * This class starts jetty with the guice config required to run the rest api
 * @author AnthonyJCLink
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception{
		
		//TODO: provide a way to load configs based on environment
		
		Injector injector = Guice.createInjector(new DevelopmentConfig(), new RestConfig(), new JpaConfig());
		
        Server server = new Server(8080);
        
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");

        handler.addServlet(new ServletHolder(new InvalidRequestServlet()), "/*");

        FilterHolder guiceFilter = new FilterHolder(injector.getInstance(GuiceFilter.class));
        handler.addFilter(guiceFilter, "/*", EnumSet.allOf(DispatcherType.class));

        server.setHandler(handler);
        
	}
	
}
