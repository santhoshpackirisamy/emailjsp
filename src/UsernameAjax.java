import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UsernameAjax")
public class UsernameAjax extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("Loginname");
		PrintWriter out = response.getWriter();
		if (username != null && username.trim().length() > 0) {
			try {
				db.database.connection();
				if(db.database.Signupvalidate(username))
				{
					out.print("<span style='color:red;'>Username unavailable</span>");
				}
				else
				{
					out.print("<span style='color:green;'>Username available</span>");
				}
				db.database.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else {
			out.print("<span style='color:red;'>Username is required field.</span>");
		}	
	}
}