package max.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnUtility {
	private ConnUtility(){}
	private static Connection con;
	static
	{
		try {
	Class.forName("org.postgresql.Driver");
 con = DriverManager.getConnection("jdbc:postgresql://localhost/june2023", "postgres", "123");
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	public static int getMemberCode(Connection con)
	{
		try {
			Connection cn = ConnUtility.getConnn();
			  PreparedStatement ps = cn.prepareStatement("select max(memcode) as mcode from member");
		  ResultSet rs = ps.executeQuery();
		  while(rs.next())
		  {
			 int mcode= rs.getInt("mcode");
			 return 1+mcode;
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
	public static Connection getConnn()
	{
		return con;
	}
	
	public static int logCount(Connection con,String uid)
	{
		try {
			PreparedStatement ps = con.prepareStatement("select logcount from lgn_mst where uid=?");
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			  while(rs.next())
			  {
				 int logcount=1+Integer.parseInt(rs.getString("logcount"));
				 return logcount;
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
