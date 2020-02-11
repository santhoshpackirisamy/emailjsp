import java.io.IOException;  
import java.io.PrintWriter;   
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;   
  
public class message extends HttpServlet 
{  
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{  
		HttpSession session=request.getSession(false); 
	    String sender=(String)session.getAttribute("uname");  
		if(sender==null)
		{
			response.sendRedirect("index.jsp");
			return;
		}
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  	  
		String receiver=request.getParameter("Loginname");  
		String message=request.getParameter("Message"); 
		db.database.connection();
		if(!db.database.Signupvalidate(receiver))
		{
			out.print("<script>alert("+sender+");</script>");
			db.database.message(sender,receiver,message);
			out.print("<script>alert('Message send');</script>");
			response.sendRedirect("Welcomepage.jsp"); 
			
		}
			
		else
		{  
			out.print("<script>alert('Message not send');</script>");
			response.sendRedirect("Welcomepage.jsp"); 
		}  	
		db.database.close();		
		out.close();  
	}  
}  