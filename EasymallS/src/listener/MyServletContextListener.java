package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

public class MyServletContextListener extends ContextLoaderListener implements
		ServletContextListener {
	
	private ContextLoader contextLoader;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext context = event.getServletContext();
		context.setAttribute("app", context.getContextPath());
	}


}
