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

@WebServlet("/AjaxTable")
public class AjaxTable extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			HttpSession session=request.getSession(false); 
			String name=(String)session.getAttribute("uname");  
			int count=0;
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");  
			db.database.connection();
			
			JSONArray arr = new JSONArray();
			arr = db.database.Receivedmsg(name);
			
			
			
			out.print("<div class='container'><table>");
			out.print("<tr><th>From</th><th>message<th><tr>");	
			for(int i = 0; i < arr.length(); i++)
			{
				count++;
				JSONObject jsonobject = arr.getJSONObject(i);
				String sendby = jsonobject.getString("sendby");
				String message = jsonobject.getString("message");
				int id = jsonobject.getInt("id");
				if(db.database.check(id))
				{
					out.print("<tr><td><input value='"+id+"' class='ads_Checkbox' checked=true id='check"+id+"' onclick='validate(this.id)' type='checkbox'/>"+sendby+"</td><td>"+message+"<li style='float:right'><button id='"+id+"' class='cancelbtn' onclick='myFunction(this.id)' >Delete</button></li></td></tr>");
				}
				else
				{
					out.print("<tr><td><input id='check"+id+"' onclick='validate(this.id)' type='checkbox'/>"+sendby+"</td><td>"+message+"<li style='float:right'><button id='"+id+"' class='cancelbtn' onclick='myFunction(this.id)' >Delete</button></li></td></tr>");
				}
			}			
			if(count==0)
			{
				out.print("<tr><td><center>no messages</center></td></tr>");
				out.print("<tr><td></td><td></td></tr>");
			}
			out.print("</table></div>");
			
			db.database.updateoverallcount(name,count);
			
			db.database.close();
			out.close();
		}
		catch(Exception e){}	  			
	}
}