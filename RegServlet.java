package max.reg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		SHGDetail sHGDetail=new SHGDetail();
		String gname=request.getParameter("gname");
		String acc=request.getParameter("acc");
		String ifsc=request.getParameter("ifsc");
		String stCode=request.getParameter("stCode");
		String districtCode=request.getParameter("districtCode");
		sHGDetail.setAcc(acc);
		sHGDetail.setDistrictCode(districtCode);
		sHGDetail.setGname(gname);
		sHGDetail.setIfsc(ifsc);
		sHGDetail.setStCode(stCode);
		
		SHGMember sHGMember=new SHGMember();
		
		String[] cname = request.getParameterValues("cname");
		String[] age = request.getParameterValues("age");
		String[] cadhar = request.getParameterValues("cadhar");
		
		sHGMember.setAge(age);
		sHGMember.setCadhar(cadhar);
		sHGMember.setCname(cname);
		
		SHGDAO sHGDAO=new SHGDAO();
		
		if(sHGDAO.insertRegDetail(sHGDetail, sHGMember))
		{
			out.println("Successss");
		}
		else
		{
			out.println("un Successss");
		}
	}

}
