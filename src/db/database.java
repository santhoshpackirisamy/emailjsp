package db;

import java.sql.*; 
import org.json.*; 
  
public class database 
{
	
	public static Statement stmt = null;
	static Connection con = null;

	public static void connection()
	{
		try
		{
			Class.forName("org.postgresql.Driver");  
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/test","postgres", "root");  
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS LOGIN " + "(ID SERIAL PRIMARY KEY," + "username TEXT NOT NULL," + "password TEXT NOT NULL," + "overallcount INT)");
		//	stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MESSAGE " + "(ID SERIAL PRIMARY KEY," + "sendby TEXT NOT NULL," + "receivedby TEXT NOT NULL," + "message TEXT NOT NULL," + "status INT," + "checkbox INT)");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MESSAGE " + "(ID SERIAL PRIMARY KEY," + "sendby TEXT NOT NULL," + "receivedby TEXT NOT NULL," + "message TEXT NOT NULL," + "deletestatus INT," +  "checkbox INT," + "trashbin INT)");
		}
		catch(Exception e){}
	}

	public static boolean Loginvalidate(String name,String pass)
	{ 
		try
		{  
			ResultSet rs = stmt.executeQuery("SELECT id FROM LOGIN WHERE username ='" +name+ "' AND password ='" +pass+ "';");      
			if(rs.next())
			{
				return true;
			}
			rs.close();         
		}
		catch(Exception e){System.out.println(e);}  
		return false;  
	} 

	public static boolean Signupvalidate(String name)
	{
		try
		{  
			ResultSet rs = stmt.executeQuery("SELECT id FROM LOGIN WHERE username ='" +name+ "';");   
			if(rs.next())
			{
					return false;
			}
			rs.close();         
		}catch(Exception e){System.out.println(e);}  
		return true;  
	}



	public static void Signup(String name,String pass)
	{
		try
		{  
			stmt.executeUpdate("INSERT INTO LOGIN (username,password,overallcount) "+ "VALUES('"+name+"','"+pass+"',0);");   
		}
		catch(Exception e){System.out.println(e);}  
	}
	
	public static void message(String sender,String receiver,String message)
	{
		try
		{  
			stmt.executeUpdate("INSERT INTO MESSAGE (sendby,receivedby,message,deletestatus,checkbox,trashbin) "+ "VALUES('"
								+sender+"','"+receiver+"','"+message+"',0,0,0);");
		}
		catch(Exception e){System.out.println(e);}  
	}
	
	
	
	public static JSONArray Sendmsg(String name) throws Exception
	{
		ResultSet rs = stmt.executeQuery("SELECT * FROM MESSAGE WHERE sendby ='" +name+ "' ORDER BY id DESC;");
		
		JSONArray arr = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();

		while(rs.next()) {
		int numColumns = rsmd.getColumnCount();
		JSONObject obj = new JSONObject();

		for (int i=1; i<numColumns+1; i++) {
		String column_name = rsmd.getColumnName(i);

		if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
		obj.put(column_name, rs.getArray(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
		obj.put(column_name, rs.getBoolean(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
		obj.put(column_name, rs.getBlob(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
		obj.put(column_name, rs.getDouble(column_name)); 
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
		obj.put(column_name, rs.getFloat(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
		obj.put(column_name, rs.getNString(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
		obj.put(column_name, rs.getString(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
		obj.put(column_name, rs.getDate(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
		obj.put(column_name, rs.getTimestamp(column_name));   
		}
		else{
		obj.put(column_name, rs.getObject(column_name));
		}
		}
		arr.put(obj);
		}
		rs.close();
		return arr;	
	}
	
	
	
	public static JSONArray Receivedmsg(String name) throws Exception
	{
		ResultSet rs = stmt.executeQuery("SELECT * FROM MESSAGE WHERE receivedby ='" +name+ "' AND deletestatus=0 ORDER BY id DESC;");  
		
		JSONArray arr = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();
		
		while(rs.next()) {
		int numColumns = rsmd.getColumnCount();
		JSONObject obj = new JSONObject();

		for (int i=1; i<numColumns+1; i++) {
		String column_name = rsmd.getColumnName(i);

		if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
		obj.put(column_name, rs.getArray(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
		obj.put(column_name, rs.getBoolean(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
		obj.put(column_name, rs.getBlob(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
		obj.put(column_name, rs.getDouble(column_name)); 
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
		obj.put(column_name, rs.getFloat(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
		obj.put(column_name, rs.getNString(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
		obj.put(column_name, rs.getString(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
		obj.put(column_name, rs.getDate(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
		obj.put(column_name, rs.getTimestamp(column_name));   
		}
		else{
		obj.put(column_name, rs.getObject(column_name));
		}
		}
		arr.put(obj);
		}
		rs.close();
		return arr;	
	}
	
	public static JSONArray Searchsendmsg(String name,String username) throws Exception
	{
		ResultSet rs = stmt.executeQuery("SELECT * FROM MESSAGE WHERE sendby ='" +name+ "' AND (receivedby LIKE '%" +username+ "%' OR message LIKE '%" +username+ "%') ORDER BY id DESC;");
		
		JSONArray arr = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();

		while(rs.next()) {
		int numColumns = rsmd.getColumnCount();
		JSONObject obj = new JSONObject();

		for (int i=1; i<numColumns+1; i++) {
		String column_name = rsmd.getColumnName(i);

		if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
		obj.put(column_name, rs.getArray(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
		obj.put(column_name, rs.getBoolean(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
		obj.put(column_name, rs.getBlob(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
		obj.put(column_name, rs.getDouble(column_name)); 
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
		obj.put(column_name, rs.getFloat(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
		obj.put(column_name, rs.getNString(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
		obj.put(column_name, rs.getString(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
		obj.put(column_name, rs.getDate(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
		obj.put(column_name, rs.getTimestamp(column_name));   
		}
		else{
		obj.put(column_name, rs.getObject(column_name));
		}
		}
		arr.put(obj);
		}
		rs.close();
		return arr;	
	}
	
	public static JSONArray Searchreceivemsg(String name,String username) throws Exception
	{
		ResultSet rs = stmt.executeQuery("SELECT * FROM MESSAGE WHERE receivedby ='" +name+ "' AND deletestatus=0 AND (sendby LIKE '%" +username+ "%' OR message LIKE '%" +username+ "%') ORDER BY id DESC;");  
	//	ResultSet rs = stmt.executeQuery("SELECT * FROM MESSAGE WHERE sendby ='" +name+ "' AND receivedby LIKE '%" +username+ "%' ORDER BY id DESC;");
		
		JSONArray arr = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();

		while(rs.next()) {
		int numColumns = rsmd.getColumnCount();
		JSONObject obj = new JSONObject();

		for (int i=1; i<numColumns+1; i++) {
		String column_name = rsmd.getColumnName(i);

		if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
		obj.put(column_name, rs.getArray(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
		obj.put(column_name, rs.getBoolean(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
		obj.put(column_name, rs.getBlob(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
		obj.put(column_name, rs.getDouble(column_name)); 
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
		obj.put(column_name, rs.getFloat(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
		obj.put(column_name, rs.getNString(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
		obj.put(column_name, rs.getString(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
		obj.put(column_name, rs.getDate(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
		obj.put(column_name, rs.getTimestamp(column_name));   
		}
		else{
		obj.put(column_name, rs.getObject(column_name));
		}
		}
		arr.put(obj);
		}
		rs.close();
		return arr;	
	}

	
	public static void checkbox(String id)
	{
		try
		{  
			stmt.executeUpdate("UPDATE MESSAGE set checkbox=1 where ID=" +id+ ";");  
		}
		catch(Exception e){System.out.println(e);}  
	}
	
	public static void uncheckbox(String id)
	{
		try
		{  
			stmt.executeUpdate("UPDATE MESSAGE set checkbox=0 where ID=" +id+ ";");  
		}
		catch(Exception e){System.out.println(e);}  
	}
	
	public static boolean check(int id)
	{
		try
		{  
			ResultSet rs = stmt.executeQuery("SELECT * FROM MESSAGE WHERE checkbox = 1 AND id=" +id+ ";");     
			if(rs.next())
			{
					return true;
			}
			rs.close();         
		}catch(Exception e){System.out.println(e);}  
		return false;  
	}
	
	public static JSONArray Trashmsg(String name) throws Exception
	{
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM MESSAGE WHERE receivedby ='" +name+ "' AND deletestatus=1 AND trashbin=0 ORDER BY id DESC;");
		
		JSONArray arr = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();

		while(rs.next()) {
		int numColumns = rsmd.getColumnCount();
		JSONObject obj = new JSONObject();

		for (int i=1; i<numColumns+1; i++) {
		String column_name = rsmd.getColumnName(i);

		if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
		obj.put(column_name, rs.getArray(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
		obj.put(column_name, rs.getBoolean(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
		obj.put(column_name, rs.getBlob(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
		obj.put(column_name, rs.getDouble(column_name)); 
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
		obj.put(column_name, rs.getFloat(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
		obj.put(column_name, rs.getNString(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
		obj.put(column_name, rs.getString(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
		obj.put(column_name, rs.getInt(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
		obj.put(column_name, rs.getDate(column_name));
		}
		else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
		obj.put(column_name, rs.getTimestamp(column_name));   
		}
		else{
		obj.put(column_name, rs.getObject(column_name));
		}
		}
		arr.put(obj);
		}
		rs.close();
		return arr;	
	}
	
	public static void deletemsg(String id)
	{
		try
		{  
			stmt.executeUpdate("UPDATE MESSAGE set deletestatus=1,checkbox=0 where ID=" +id+ ";");  
		}
		catch(Exception e){System.out.println(e);}  
	}
	
	public static void deleteall(String name)
	{
		try
		{  
			stmt.executeUpdate("UPDATE MESSAGE set deletestatus=1,checkbox = 0 where checkbox=1 AND receivedby='"+name+"';");
		}
		catch(Exception e){System.out.println(e);}  
	}
	
	public static void deletetrash(String id)
	{
		try
		{  
			stmt.executeUpdate("UPDATE MESSAGE set deletestatus=1,checkbox = 0,trashbin=1 where ID=" +id+ ";");  
		}
		catch(Exception e){System.out.println(e);}  
	}
	
	public static void deletealltrash(String name)
	{
		try
		{  
			stmt.executeUpdate("UPDATE MESSAGE set deletestatus=1,checkbox=0,trashbin=1 where checkbox=1 AND receivedby='"+name+"';");
		}
		catch(Exception e){System.out.println(e);}  
	}
	
	public static void updateoverallcount(String username,int count)
	{
		try
		{  
			stmt.executeUpdate("UPDATE LOGIN set overallcount=" +count+ " where username='" +username+ "';");
		}
		catch(Exception e){System.out.println(e);}  
	}
	
	public static int getoverallcount(String name)
	{
		try
		{  
			ResultSet rs = stmt.executeQuery("SELECT * FROM LOGIN WHERE username ='" +name+ "';");
			if(rs.next())
			{
				int overcount = rs.getInt("overallcount");
				return overcount;
			}
			
		}
		catch(Exception e){System.out.println(e);} 
		return 1000;
	}
	
	public static void close()
	{
		try
		{
			stmt.close();
			con.close();
		}
		catch(Exception e){System.out.println(e);}  
	}
}  



