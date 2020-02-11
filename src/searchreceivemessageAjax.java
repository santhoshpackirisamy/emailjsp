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


@WebServlet("/searchreceivemessageAjax")
public class searchreceivemessageAjax extends HttpServlet {
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
		//	arr = db.database.Searchreceivemsg(name,username);
			arr = db.database.Receivedmsg(name);
			}
			catch(Exception e){}
		}
		else
		{
			try
			{
//				arr = db.database.Searchreceivemsg(name,username);
			arr = db.database.Receivedmsg(name);
			}
			catch(Exception e){}
		}

			out.print("<div class='container'><table>");
			out.print("<tr><th>From</th><th>message<th><tr>");
			for(int i = 0; i < arr.length(); i++)
			{
				count++;
//				JSONObject jsonobject = arr.getJSONObject(i);
//				String sendby = jsonobject.getString("sendby");
//				String message = jsonobject.getString("message");
//				int id = jsonobject.getInt("id");
				JSONObject jsonobject = null;
				try {
					jsonobject = arr.getJSONObject(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String sendby = null;
				try {
					sendby = jsonobject.getString("sendby");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String message = null;
				try {
					message = jsonobject.getString("message");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				int id = 0;
				try {
					id = jsonobject.getInt("id");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if(db.database.check(id))
				{
					out.print("<tr><td><input value='"+id+"' class='ads_Checkbox' checked=true id='check"+id+"' onclick='validate(this.id)' type='checkbox'/>"+sendby+"</td><td>"+message+"<li style='float:right'><input type='button' id='"+id+"' style='color: white;border: none;' value='Delete' class='cancelbtn' onclick='myFunction(this.id)' ></input></li></td></tr>");
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

			db.database.close();
			out.close();
	}
}
