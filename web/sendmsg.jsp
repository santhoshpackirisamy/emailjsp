<%@ page import="db.database"%>
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<style type="text/css">
			
			
			ul {
			list-style-type: none;
			margin: 0;
			padding: 0;
			overflow: hidden;
			background-color: #333;
			}

			li {
			float: left;
			border-right:1px solid #bbb;
			}

			li:last-child {
			border-right: none;
			}

			li a {
			display: block;
			color: white;
			text-align: center;
			padding: 14px 16px;
			text-decoration: none;
			}

			li a:hover:not(.active) {
			background-color: #111;
			}

			.active {
			background-color: #4CAF50;
			}

			.cancelbtn {
			width: auto;
			padding: 10px 18px;
			background-color: #f46141;
			}

			body {font-family: Arial, Helvetica, sans-serif;}

			/* Full-width input fields */
			input[type=text], input[type=password] {
			width: 100%;
			padding: 12px 20px;
			margin: 8px 0;
			display: inline-block;
			border: 1px solid #ccc;
			box-sizing: border-box;
			}

			/* Set a style for all buttons */
			button {
			background-color: #4CAF50;
			color: white;
			padding: 14px 20px;
			margin: 8px 0;
			border: none;
			cursor: pointer;
			width: 100%;
			}

			button:hover {
			opacity: 0.8;
			}

			/* Extra styles for the cancel button */
			.cancelbtn {
			width: auto;
			padding: 10px 18px;
			background-color: #f46141;
			}

			/* Center the image and position the close button */
			.imgcontainer {
			text-align: center;
			margin: 24px 0 12px 0;
			position: relative;
			}

			img.avatar {
			width: 40%;
			border-radius: 50%;
			}

			.container {
			padding: 16px;
			}

			span.psw {
			float: right;
			padding-top: 16px;
			}

			/* The Modal (background) */
			.modal {
			display: none; /* Hidden by default */
			position: fixed; /* Stay in place */
			z-index: 1; /* Sit on top */
			left: 0;
			top: 0;
			width: 100%; /* Full width */
			height: 100%; /* Full height */
			overflow: auto; /* Enable scroll if needed */
			background-color: rgb(0,0,0); /* Fallback color */
			background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
			padding-top: 60px;
			}

			/* Modal Content/Box */
			.modal-content {
			background-color: #fefefe;
			margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
			border: 1px solid #888;
			width: 80%; /* Could be more or less, depending on screen size */
			}

			/* The Close Button (x) */
			.close {
			position: absolute;
			right: 25px;
			top: 0;
			color: #000;
			font-size: 35px;
			font-weight: bold;
			}

			.close:hover,
			.close:focus {
			color: red;
			cursor: pointer;
			}

			/* Add Zoom Animation */
			.animate {
			-webkit-animation: animatezoom 0.6s;
			animation: animatezoom 0.6s
			}

			@-webkit-keyframes animatezoom {
			from {-webkit-transform: scale(0)} 
			to {-webkit-transform: scale(1)}
			}

			@keyframes animatezoom {
			from {transform: scale(0)} 
			to {transform: scale(1)}
			}

			/* Change styles for span and cancel button on extra small screens */
			@media screen and (max-width: 300px) {
			span.psw {
			display: block;
			float: none;
			}
			.cancelbtn {
			width: 100%;
			}
			}

			.compose{
			background: url('images/compose.PNG') no-repeat;
			cursor:pointer;
			border: none;
			width:70px;
			height:50px;
			margin-top:15px;
			}

			table {
			float:right;
			border-collapse: collapse;
			width: 100%;
			}

			th, td {
			text-align: left;
			padding: 8px;
			}

			tr:nth-child(even){background-color: #f2f2f2}

			th {
			background-color: #4CAF50;
			color: white;
			}
			
			
	</style>
</head>
<body>
<%@ page import = "java.io.*,java.util.*,org.json.*,java.sql.*" %>
<%
		response.setHeader("Cache-control","no-cache, no-store, must-revalidate");
		response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","0");
		String n = (String)session.getAttribute("uname");
		if(n==null)
		{
			response.sendRedirect("index.jsp");
		}
		
%>

<ul>
	<li><a href="Welcomepage.jsp" class="login" title="Login"><img style="width: 30px;height: 30px;" src="images/back.png" /></a></li>
	<form  action="Logout"  method="post">
	  <li style="float:right"><p> </p></li>
	  <li style="float:right"><button class="cancelbtn" style="width:auto">Logout</button></li>
	</form>
</ul>

<%@ page import = "java.io.*,java.util.*,java.sql.*,javax.servlet.*,javax.servlet.http.*" %>
<%		try
		{
			String name=(String)session.getAttribute("uname");  
			if(name==null)
			{
				response.sendRedirect("index.jsp");
			}
			int count=0;
			response.setContentType("text/html");  
			db.database.connection();
			
			
			JSONArray arr = new JSONArray();
			arr = db.database.Sendmsg(name);
			
			
			out.print("<div class='container'><table>");
			out.print("<tr><th>To</th><th>message<th><tr>");	
			for(int i = 0; i < arr.length(); i++)
			{
				count++;
				JSONObject jsonobject = arr.getJSONObject(i);
				String receivedby = jsonobject.getString("receivedby");
				String message = jsonobject.getString("message");
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
		catch(Exception e){} 
%>

</body>
</html>
