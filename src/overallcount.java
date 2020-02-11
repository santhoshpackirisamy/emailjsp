import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import javax.servlet.http.*; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*; 

@WebServlet("/overallcount")
public class overallcount extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			HttpSession session=request.getSession(false); 
			String name=(String)session.getAttribute("uname");  
			int count=-1;
			PrintWriter out = response.getWriter();
			db.database.connection();
			
			count = db.database.getoverallcount(name);
			
			out.print(count);
			
			db.database.close();
			out.close();
		}
		catch(Exception e){}	  			
	}
}