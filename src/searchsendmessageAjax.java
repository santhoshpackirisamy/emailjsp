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


@WebServlet("/searchsendmessageAjax")
public class searchsendmessageAjax extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("Loginname");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);
		response.setContentType("text/html");  
		db.database.connection();
		String name = "";
		JSONArray arr = new JSONArray();
		int count=0;

		try
		{
			name=(String)session.getAttribute("uname");  
			if(name==null)
			{
				response.sendRedirect("index.jsp");
			}
		}
		catch(Exception e){} 
		
		if (username != null && username.trim().length() > 0) {
			try
			{
			arr = db.database.Searchsendmsg(name,username);
			}
			catch(Exception e){} 
		}
		else 
		{
			try
			{
			arr = db.database.Sendmsg(name);
			}
			catch(Exception e){} 
		}
			out.print("<div class='container'><table>");
			out.print("<tr><th>To</th><th>message<th><tr>");	
			for(int i = 0; i < arr.length(); i++)
			{
				count++;
//				JSONObject jsonobject = arr.getJSONObject(i);
//				String receivedby = jsonobject.getString("receivedby");
//				String message = jsonobject.getString("message");

				JSONObject jsonobject = null;
				try {
					jsonobject = arr.getJSONObject(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String receivedby = null;
				try {
					receivedby = jsonobject.getString("receivedby");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String message = null;
				try {
					message = jsonobject.getString("message");
				} catch (JSONException e) {
					e.printStackTrace();
				}

				out.print("<tr><td>"+receivedby+"</td><td>"+message+"</td></tr>");
			}
			if(count==0)
			{
				out.print("<tr><td><center>no messages</center></td></tr>");
				out.print("<tr><td></td><td></td></tr>");
			}
			out.print("</table></div>");
			db.database.close();
			out.close(); 		
	}
}