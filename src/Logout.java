import java.io.IOException;  
import java.io.PrintWriter;   
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;   
  
public class Logout extends HttpServlet 
{  
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{  
		response.setContentType("text/html");    
		HttpSession session=request.getSession(false);  
		session.invalidate();
		response.sendRedirect("index.jsp");
		return; 
	}  
}  