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
import java.io.*; 
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*; 


@WebServlet("/Deletealltrash")
public class Deletealltrash extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			db.database.connection();
			HttpSession session=request.getSession(false); 
			String name=(String)session.getAttribute("uname");  
			if(name==null)
			{
				response.sendRedirect("index.jsp");
			}
			String deleteall = request.getParameter("deleteall");
			
			db.database.deletealltrash(name);
			
			db.database.close();
		}
		catch(Exception e){}
	}
}