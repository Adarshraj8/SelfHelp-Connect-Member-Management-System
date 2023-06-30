package max.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtility {
public  static String currentDate()
{
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	   LocalDateTime now = LocalDateTime.now();  
	   String d = dtf.format(now); 
	   return d;
}
}
