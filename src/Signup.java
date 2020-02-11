import java.io.IOException;  
import java.io.PrintWriter;    
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;   
  
public class Signup extends HttpServlet {  
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{  
	  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		String n=request.getParameter("signname");  
		String p=request.getParameter("signpass");
		db.database.connection();
		if(db.database.Signupvalidate(n))
		{
			db.database.Signup(n,p);
			HttpSession session=request.getSession();  
			session.setAttribute("uname",n);
			response.sendRedirect("Welcomepage.jsp");
		} 
		else
		{  
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
			rd.include(request,response);  
			out.print("</br><center>Username Already exist</center>");
		}  	
		db.database.close();		
		out.close();  
	}  
}  