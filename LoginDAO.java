package max.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import max.DistBean;
import max.StateBean;
import max.utility.ConnUtility;
import max.utility.DateUtility;
import max.utility.EncriMD5;
//up_ram1122
public class LoginDAO {
	
	public List<DistBean> getDistList(String stCode)
	{
		List<DistBean> l=new ArrayList<DistBean>();
		try {
			Connection con = ConnUtility.getConnn();
			PreparedStatement ps = con.prepareStatement("select * from mst_district where state_code = ?");
		   ps.setString(1, (stCode).length()==1 ? "0"+stCode : stCode);
		   ResultSet rs = ps.executeQuery();
		   while(rs.next())
		   {
			  DistBean db = new DistBean();
			   db.setDistCode(rs.getString(1));
			   db.setDistName(rs.getString(2));
			   db.setStCode(rs.getString(3));
			   l.add(db);
		   }
		} catch (Exception e) {
		e.printStackTrace();
		}
		return l;
	}
	
	
	
	
	
	public StateBean getStateName(String uid)
	{    StateBean sb = new StateBean();
		try {
			Connection con = ConnUtility.getConnn();
PreparedStatement ps = con.prepareStatement("select * from mst_state where state_short_name =?");
		 ps.setString(1, uid.substring(0, 2).toUpperCase());
		  ResultSet rs = ps.executeQuery();
		     while(rs.next())
		     {
		 
		    	int stCode= rs.getInt(1);
		    	String stname= rs.getString(2);
		    	String stCstShortname= rs.getString(3);
		    	sb.setStCode(stCode);
		    	sb.setStname(stname);
		    	sb.setShortName(stCstShortname);
		    	
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb;
	}
	
	
	
	public void logout(String uid)
	{
		try {
			Connection con = ConnUtility.getConnn();
			PreparedStatement ps = con.prepareStatement("update lgn_mst set status = ? , logcount =? where uid=?");
		   ps.setString(1, "Y");
		   ps.setString(2, "0");
		   ps.setString(3, uid);
		   ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("resource")
	public String login(String uid,String pass,String mobile,String otp)
	{
		try {
			Connection con = ConnUtility.getConnn();
PreparedStatement ps = con.prepareStatement("select pass,otp,status from lgn_mst where uid=?");
  ps.setString(1, uid);			
   ResultSet rs = ps.executeQuery();
    while(rs.next())
    {
    	 String p = rs.getString("pass");
    	 String st = rs.getString("status");
    	 int otpp = rs.getInt("otp");
    	 if(otpp==Integer.parseInt(otp))
    	 {
    		 if(p.equals(EncriMD5.getMd5(pass)))
    		 {
    			   if(st.equalsIgnoreCase("Y"))
    			   {
    				  ps= con.prepareStatement("update lgn_mst set status =?, logcount=? where uid=?");
    				     ps.setString(1, "N");
    				     ps.setString(2, "0");
    				  ps.setString(3, uid);
    				  
    				    ps.executeUpdate();
    						  return "success";
    			   }
    			   if(st.equalsIgnoreCase("L"))
    			   {
    				   return "Cont To Admin";
    			   }
    			   else
    			   {
    				   return "User Allready Login";
    			   }
    		 }
    		 else
    		 {
    			int lcont= ConnUtility.logCount(con, uid);
    			if(lcont==1 || lcont==2)
    			{
    				ps=con.prepareStatement("update lgn_mst set logcount = ? where uid=?");
    			   ps.setString(1, String.valueOf(lcont));
    			   ps.setString(2, uid);
    			   ps.executeUpdate();
    			   return "wpass";
    			}
    			else
    			{
    				ps=con.prepareStatement("update lgn_mst set logcount=? , status =? where uid=?");
    			   ps.setString(1, DateUtility.currentDate());
    			   ps.setString(2, "L");
    			   ps.setString(3, uid);
    			   ps.executeUpdate();
    			   return "LUID";
    			}
    			
    			 
    		 }
    		 
    		 
    	 }
    	 else
    		 return "wotp";
    	 
    	 
    	 
    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return "wuid";
		
	}
	
	
	
	
	
	

	 public String getOTP(String mob)
	 {
		 try {
			
			 if(mob.length()==10)
			 {
				String otp = generateOTP();
				Connection con = ConnUtility.getConnn();
PreparedStatement ps = con.prepareStatement("update lgn_mst set otp=? where mobile =?");
				ps.setInt(1, Integer.parseInt(otp));
				ps.setString(2, mob);
				int i=ps.executeUpdate();
				if(i>0)
					return otp;
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	 }
	 
	 
	 private  String generateOTP()
	 {
		 StringBuffer sb = new StringBuffer();
		 for(int i=0; i<=9;i++)
		 {
			 sb.append(new Random().nextInt(10));
		 }
		 return sb.substring(6);
	 }
	
	 public String getLoginDetailForActive()
	 {
		 try {
			 Connection con = ConnUtility.getConnn();
			  PreparedStatement ps = con.prepareStatement("select logcount from lgn_mst where status=?");
			  ps.setString(1, "L");
			  ResultSet rs = ps.executeQuery();
			  while(rs.next())
			  {
				 String lc = rs.getString("logcount");
				 
				 if(lc.length()>10)
				 {
					ps = con.prepareStatement("update lgn_mst set logcount=?,status=? where status=?");
					ps.setString(1, "0");
					ps.setString(2, "Y");
					ps.setString(3, "L");
					int i = ps.executeUpdate();
					if(i>0)
					{
						return "Updated User For Login";
					}
				 }
			  }
		} catch (Exception e) {
			
		}
		return null;
	 }
	
}
