
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
	<script src="js/app-ajax.js" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
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
			background-color: #f44336;
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
			background: url('images/img_avatar3.PNG') no-repeat;
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
			
			#save_value{
				margin-top:20px;
				margin-right:20px;
				width:300px;
			}
			
	</style>
</head>
<body>

<%@ page import = "java.io.*,java.util.*,java.sql.*" %>

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
	<form  action="Logout"  method="post">
	  <li style="float:right"><p> </p></li>
	  <li style="float:right"><button class="cancelbtn" style="width:auto">Logout</button></li>
	</form>
</ul>

<button onclick="document.getElementById('id03').style.display='block'" style="width:auto; background-color:#eaeaea;  border-color: orange; border-width: 1px;border-style: solid;"><img style="width:40px;" src='images/img_avatar3.PNG'/></button>

<form style="float: right;" action="trashbin.jsp"  method="post">
<button align="right" style="width:auto; background-color:#eaeaea;  border-color: orange; border-width: 1px;border-style: solid;"><img style="width:40px;" src='images/img_avatar4.jpg'/></button>
</form>

<!--<form action="trashbin.jsp"  method="post">
	<button  type="submit">Trash Bin</button>
	<input style='float:right' class='cancelbtn' type="button" onclick='deleteall()' id="save_value" name="save_value" value="Trash Bin" />
</form>-->

<div id="id03" class="modal">
<script>
	$(document).ready(function(){
	$("#Loginname").keyup(function(){
	var email = $(this).val();
	//email = $.trim(email);
		$.ajax({
		type: "POST",
		url: "http://localhost:8080/emailjsp/UsernameAjax",
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
	});
	});
</script>
		    
<form class="modal-content animate" action="message"  method="post">
	<div class="imgcontainer">
		<span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">&times;</span>
		<img src="images/img_avatar2.png" style="width:100px;" alt="Avatar" class="avatar">
	</div>
	
	<div class="container"> 
	<label>Username</label><br /> <input type="text" placeholder="Enter Username" name="Loginname" id="Loginname" required />
	<center><span id="msg"></span></center>
	<label for="psw"><b>Message</b></label>
	<input type="text" style="height:100px;" placeholder="Enter Message" name="Message" id="message" required>
	<button id="sendmsg" type="submit">Send</button>
	</div>
	<div class="container" style="background-color:#f1f1f1">
	<button type="button" onclick="document.getElementById('id03').style.display='none'" class="cancelbtn">Cancel</button>
	</div>
</form>
</div>



<div class="container">
	<form action="sendmsg.jsp"  method="post">
		<button  type="submit">Send Messages</button>
	</form>
</div>

	<form action="Searchsendmsg.jsp"  method="post">
		<button style="width:450px; background-color:#f4b942; float:left;" type="submit">Search Send Messages</button>
	</form>
	
	<form action="Searchreceivemsg.jsp"  method="post">
		<button style="width:450px; background-color:#f4b942; float:right;" type="submit">Search Received Messages</button>
	</form>


<script>
var modal1 = document.getElementById('id03');
window.onclick = function(event) {
    if (event.target == modal1) {
        modal1.style.display = "none";
    }
}




function myFunction(id) 
{
	var reference4 = document.getElementById(id);
	var reference2 = document.getElementById(id).id;
		$.ajax({
		url:"deletemsg",
		method:"POST",
		data:{reference2:reference2},
		dataType:"text",
		success: function(mes) {						
		$('#mes').show();
		$("#mes").html(mes);
		},
		error: function(jqXHR, textStatus, errorThrown) {
		$('#mes').show();
		$("#mes").html(textStatus + "error" + errorThrown);
		}
		});
}

var pre = "";
var post = "a";

setInterval(function() 
{
	$.ajax({
	url:"AjaxTable",
	method:"POST",
	success: function(mes) {
			$.ajax({
			url:"overallcount",
			method:"POST",
			//dataType : "int"
			success: function(mas) {	
				post = mas;
			}
			});
		if(pre.localeCompare(post))
		{
			pre = post;
			$('#mass').show();
			$("#mass").html(mes);
		}
	},
	error: function(jqXHR, textStatus, errorThrown) {
	$('#mass').show();
	$("#mass").html(textStatus + "error" + errorThrown);
	}
	});
}, 2000);

function validate(id) {
    var reference4 = document.getElementById(id);
	var reference2 = document.getElementById(id).id;
	reference2 = reference2.replace("check", "");
	if (document.getElementById(id).checked)
	{
		$.ajax({
		url:"Checkbox",
		method:"POST",
		data:{reference2:reference2},
		dataType:"text",
		success: function(sec) {						
		$('#sec').show();
		$("#sec").html(sec);
		},
		error: function(jqXHR, textStatus, errorThrown) {
		$('#sec').show();
		$("#sec").html(textStatus + "error" + errorThrown);
		}
		});	
	}
	else
	{
		$.ajax({
		url:"UnCheckbox",
		method:"POST",
		data:{reference2:reference2},
		dataType:"text",
	/*	success: function(sec) {						
		$('#sec').show();
		$("#sec").html(sec);
		},
		error: function(jqXHR, textStatus, errorThrown) {
		$('#sec').show();
		$("#sec").html(textStatus + "error" + errorThrown);
		}*/
		});
	}
}


function deleteall() 
{
  //  var deleteall = '';
  //  $('.ads_Checkbox:checked').each(function(){        
   //     var values = $('.ads_Checkbox:checked').val();
    //    deleteall += values+",";
   // });
   // alert(deleteall);
		$.ajax({
		url:"Deleteall",
		method:"POST",
	//	data:{deleteall:deleteall},
		dataType:"text",
		success: function(sec) {						
		$('#sec').show();
		$("#sec").html(sec);
		},
		error: function(jqXHR, textStatus, errorThrown) {
		$('#sec').show();
		$("#sec").html(textStatus + "error" + errorThrown);
		}
		});
}
</script>
<center><input class='cancelbtn' type="button" onclick='deleteall()' id="save_value" name="save_value" value="Delete Selected Messages" /></center>



<div id="sec"></div>
<div id="mes"></div>
<div id="mass1"></div>
<div id="mass"></div>

</body>
</html>
