import java.io.IOException;  
import java.io.PrintWriter;   
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;   
  
public class Login extends HttpServlet 
{  
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  	  
		String n=request.getParameter("Loginname");  
		String p=request.getParameter("Loginpass"); 
		db.database.connection();
		if(db.database.Loginvalidate(n, p))
		{  
			HttpSession session=request.getSession();  
			session.setAttribute("uname",n);  
		    response.sendRedirect("Welcomepage.jsp");
		}  
		else
		{  
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
			rd.include(request,response);  
			out.print("</br><center>Wrong username and password combination</center>");
		} 
		db.database.close();
		out.close();  
	}  
}  