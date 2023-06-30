package max.utility;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cronshedlr.LoginSchduler;
import max.login.LoginDAO;

public class ListnerClass implements HttpSessionListener,ServletContextListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("session created");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sd) {
		HttpSession s = sd.getSession();
		String uid = (String) s.getAttribute("uid");
		LoginDAO loginDAO=new LoginDAO();
		loginDAO.logout(uid);
		System.out.println("user logout "+uid);
	}
	@Override
	public void contextDestroyed(ServletContextEvent cd) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent ci) {
		          
		    LoginSchduler d = new LoginSchduler();
		     System.out.println("contextInitialized");
	}

}
