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


class dummy
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner("1,2,3,");
			scan.useDelimiter(",");
			while(scan.hasNext()){
				int id = Integer.parseInt(scan.next());
			System.out.println(id);
			}
			scan.close();
	}
}