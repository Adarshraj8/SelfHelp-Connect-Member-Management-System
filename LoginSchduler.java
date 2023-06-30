package cronshedlr;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class LoginSchduler {

	public LoginSchduler(){
		try {
	       SchedulerFactory sf = new StdSchedulerFactory();
	      Scheduler s = sf.getScheduler();
	      s.start();
	       JobDetail jb = new JobDetail("modi","yogi",LoginStatus.class);
	        CronTrigger ct = new CronTrigger("bjp","yogi","0/50 * * * * ?");
	        s.scheduleJob(jb, ct);
	       
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
