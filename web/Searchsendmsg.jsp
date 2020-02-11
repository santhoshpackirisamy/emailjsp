<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-1.10.2.js"
type="text/javascript"></script>
<script src="js/app-ajax.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
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
			
			
			/* ===========================
			====== Search Box ====== 
			=========================== */

			.search
			{
			border: 2px solid #CF5C3F;
			overflow: auto;
			border-radius: 5px;
			-moz-border-radius: 5px;
			-webkit-border-radius: 5px;
			}

			.search input[type="text"]
			{
			border: 0px;
			width: 67%;
			padding: 10px 10px;
			}

			.search input[type="text"]:focus
			{
			outline: 0;
			}

			.search input[type="submit"]
			{
			border: 0px;
			background: none;
			background-color: #CF5C3F;
			color: #fff;
			float: right;
			padding: 10px;
			border-radius-top-right: 5px;
			-moz-border-radius-top-right: 5px;
			-webkit-border-radius-top-right: 5px;
			border-radius-bottom-right: 5px;
			-moz-border-radius-bottom-right: 5px;
			-webkit-border-radius-bottom-right: 5px;
			cursor:pointer;
			}

			/* ===========================
			====== Medua Query for Search Box ====== 
			=========================== */

			@media only screen and (min-width : 150px) and (max-width : 780px)
			{
			{}
			.search
			{
			width: 95%;
			margin: 0 auto;
			}

			}

			
	</style>
</head>
<body>
<%@ page import = "java.io.*,java.util.*,org.json.*,java.sql.*" %>
<%
	response.setHeader("Cache-control","no-cache, no-store, must-revalidate");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","0");
%>
<ul>
	<li><a href="Welcomepage.jsp" class="login" title="Login"><img style="width: 30px;height: 30px;" src="images/back.png" /></a></li>
	<form  action="Logout"  method="post">
	  <li style="float:right"><p> </p></li>
	  <li style="float:right"><button class="cancelbtn" style="width:auto">Logout</button></li>
	</form>
</ul>


<div class="container">
	<div class="search">
			<input name="searchbar" type="text" onkeyup="searchreceivemsg()" id="searchsendmessage" placeholder="Search for username..">
			<input style="height:52px;" onclick="searchreceivemsg()" type="submit" value="Submit">
		
	</div>
</div>

<script>

	searchreceivemsg();

	function searchreceivemsg(){
	var email = $("#searchsendmessage").val();
		$.ajax({
		type: "POST",
		url: "http://localhost:8080/emailjsp/searchsendmessageAjax",
		data: {Loginname:email},
		dataType: "text",
		cache: false,
		success: function(msg) {

		$('#msg').show();
		$("#msg").html(msg);
		},
		error: function(jqXHR, textStatus, errorThrown) {
		$('#msg').show();
		$("#msg").html(textStatus + "error" + errorThrown);
		}
		});
	}
</script>
<div id="msg"></div>
</body>
</html>

