package cronshedlr;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import max.login.LoginDAO;
import max.reg.SHGDAO;

public class LoginStatus implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("----start and  End shedular-----");
		LoginDAO loginDAO  =  new LoginDAO();
	    String t = loginDAO.getLoginDetailForActive();
	    System.out.println(t);
	    
	    SHGDAO sHGDAO= new SHGDAO();
	    sHGDAO.insertSHGTemp();
	    System.out.println("insert data in temp");
	}

}
