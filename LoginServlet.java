package max.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String uid = request.getParameter("uid");
		String mobile = request.getParameter("mobile");
		String pass = request.getParameter("pass");
		String otpp = request.getParameter("otpp");
		LoginDAO loginDAO=new LoginDAO();
		String st = loginDAO.login(uid, pass, mobile, otpp);
		if(st.equalsIgnoreCase("success"))
		{
			HttpSession s = request.getSession();
			s.setAttribute("uid", uid);
			request.getRequestDispatcher("reg.jsp").forward(request, response);
		}
		else
		{
			out.print(st);
			request.getRequestDispatcher("login.jsp").include(request, response);
		}
	}

}
