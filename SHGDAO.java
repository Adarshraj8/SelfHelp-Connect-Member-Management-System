package max.reg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import max.utility.ConnUtility;

public class SHGDAO {
	
	public void insertSHGTemp()
	{
		
		List<ShgDTO> shgllist=new ArrayList<ShgDTO>();
		
		Connection con = ConnUtility.getConnn();
		try {
			 con.setAutoCommit(false);
			  PreparedStatement ps = con.prepareStatement("select shg.shgcode as shgcode, shg.name as name, shg.acc as acc, shg.ifsc as ifsc,s.state_name as stname,d.district_name as dname  from shg as shg inner join mst_state s on shg.stcode::int=s.state_code inner join mst_district as d on shg.distcode=d.district_code");
			   ResultSet rs = ps.executeQuery();
			     while(rs.next())
			     {
			    	 ShgDTO shgDTO=new ShgDTO();
			    	 shgDTO.setAcc(rs.getString("acc"));
			    	 shgDTO.setGname(rs.getString("name"));
			    	 shgDTO.setIfsc(rs.getString("ifsc"));
			    	 shgDTO.setShgCode(rs.getInt("shgcode"));
			    	 shgDTO.setStName(rs.getString("stname"));
			    	 shgDTO.setDistrictName(rs.getString("dname"));
			    	 shgllist.add(shgDTO);
			     }
			     
			     if(shgllist.size()>0)
			     {
			    	 
			    	ps = con.prepareStatement("delete from shg_temp");
			    	int i = ps.executeUpdate();
			    	 System.out.println("delete "+i);
			    	ps= con.prepareStatement("insert into shg_temp values(?,?,?,?,?,?)");
			    	for(ShgDTO p:shgllist)
			    	{
			    		ps.setInt(1, p.getShgCode());
			    		ps.setString(2, p.getGname());
			    		ps.setString(3, p.getAcc());
			    		ps.setString(4, p.getIfsc());
			    		ps.setString(5, p.getStName());
			    		ps.setString(6, p.getDistrictName());
			    		//ps.executeUpdate();
			    		ps.addBatch();
			    	}
			    	    int[] j = ps.executeBatch();
			    	    if(j.length>0)
			    	    {
			    	    	con.commit();
			    	    }
			    	    else {
			    	    	con.rollback();
			    	    }
			     }
			     
			     
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public SHGMemberDATA getSHGDetail()
	{
		SHGMemberDATA sHGMemberDATA=new SHGMemberDATA();
		List<ShgDTO> shgllist=new ArrayList<ShgDTO>();
		List<MemberDTO> memlist=new ArrayList<MemberDTO>();
		
		try {
			 Connection con = ConnUtility.getConnn();
			  PreparedStatement ps = con.prepareStatement("select * from shg_temp");
			   ResultSet rs = ps.executeQuery();
			     while(rs.next())
			     {
			    	 ShgDTO shgDTO=new ShgDTO();
			    	 shgDTO.setAcc(rs.getString("acc"));
			    	 shgDTO.setGname(rs.getString("name"));
			    	 shgDTO.setIfsc(rs.getString("ifsc"));
			    	 shgDTO.setShgCode(rs.getInt("shgcode"));
			    	 shgDTO.setStName(rs.getString("stname"));
			    	 shgDTO.setDistrictName(rs.getString("distname"));
			    	 shgllist.add(shgDTO);
			     }
			     
			     if(shgllist.size()>0)
			     {
			    	ps= con.prepareStatement("select * from member");
			    	rs=ps.executeQuery();
			    	while(rs.next())
			    	{
			    		MemberDTO m = new MemberDTO();
			    		 m.setAdhar(rs.getString("adhar"));
			    		 m.setAge(rs.getString("age"));
			    		 m.setName(rs.getString("name"));
			    		 m.setMid(rs.getInt("memcode"));
			    		 m.setCid(rs.getInt("shgcodein"));
			    		 memlist.add(m);
			    	}
			     }
			     
			     System.out.println("shgllist "+shgllist.size());
			     System.out.println("memlist "+memlist.size());
			     sHGMemberDATA.setMemlist(memlist);
			     sHGMemberDATA.setShgllist(shgllist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sHGMemberDATA;
	}
	
	
	
	
	public SHGMemberDATA getSHGDetail(String shgCode)
	{
		SHGMemberDATA sHGMemberDATA=new SHGMemberDATA();
		ShgDTO shgDTO=new ShgDTO();
		List<MemberDTO> memlist=new ArrayList<MemberDTO>();
		
		try {
			 Connection con = ConnUtility.getConnn();
			  PreparedStatement ps = con.prepareStatement("select shg.shgcode as shgcode, shg.name as name, shg.acc as acc, shg.ifsc as ifsc,s.state_name as stname,d.district_name as dname  from shg as shg inner join mst_state s on shg.stcode::int=s.state_code inner join mst_district as d on shg.distcode=d.district_code where shgcode = ?");
			  ps.setInt(1, Integer.parseInt(shgCode));
			  ResultSet rs = ps.executeQuery();
			     while(rs.next())
			     {
			    	 
			    	 shgDTO.setAcc(rs.getString("acc"));
			    	 shgDTO.setGname(rs.getString("name"));
			    	 shgDTO.setIfsc(rs.getString("ifsc"));
			    	 shgDTO.setShgCode(rs.getInt("shgcode"));
			    	 shgDTO.setStName(rs.getString("stname"));
			    	 shgDTO.setDistrictName(rs.getString("dname"));
			    	
			     }
			     
			     if(shgDTO.getShgCode()>0)
			     {
			    	ps= con.prepareStatement("select * from member where shgcodein = ?");
			    	 ps.setInt(1, Integer.parseInt(shgCode));
			    	rs=ps.executeQuery();
			    	while(rs.next())
			    	{
			    		MemberDTO m = new MemberDTO();
			    		 m.setAdhar(rs.getString("adhar"));
			    		 m.setAge(rs.getString("age"));
			    		 m.setName(rs.getString("name"));
			    		 m.setMid(rs.getInt("memcode"));
			    		 m.setCid(rs.getInt("shgcodein"));
			    		 memlist.add(m);
			    	}
			     }
			     
			    
			     sHGMemberDATA.setMemlist(memlist);
			     sHGMemberDATA.setShgDTO(shgDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sHGMemberDATA;
	}
	
	
	
	
	

	public boolean insertRegDetail(SHGDetail sHGDetail,SHGMember sHGMember)
	{  Connection con = ConnUtility.getConnn();
		try {
			  con.setAutoCommit(false);
PreparedStatement ps = con.prepareStatement("insert into shg values (?,?,?,?,?,?)");
  int mid = ConnUtility.getMemberCode(con);
   int cid= mid;
         ps.setInt(1, mid);
		 ps.setString(2, sHGDetail.getGname());
		 ps.setString(3, sHGDetail.getAcc());
		 ps.setString(4, sHGDetail.getIfsc());
		 ps.setString(5, sHGDetail.getStCode());
		 ps.setString(6, sHGDetail.getDistrictCode());
		 int i=ps.executeUpdate();
		    if(i>0)
		    {
		    	String[] a = sHGMember.getAge();
		    	  String[] ca = sHGMember.getCadhar();
		    	  String[] n = sHGMember.getCname();
		    	ps=con.prepareStatement("insert into member values(?,?,?,?,?)");
		        for(int j=0; j<a.length;j++)
		        {
		        	ps.setInt(1, ++cid);
		        	ps.setString(2, n[j]);
		        	ps.setString(3, a[j]);
		        	ps.setString(4, ca[j]);
		        	ps.setInt(5, mid);
		        	ps.addBatch();
		        }
		       int[] k = ps.executeBatch();
		       if(k.length>0)
		       {
		    	    con.commit();
		    	   return true;
		       }
		       else
		       {
		    	   con.rollback();
		       }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
		
	}
	
}
