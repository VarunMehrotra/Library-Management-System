<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->

<title>Library Management System</title>
<link rel="stylesheet" href="bootstrap.min.css" />

<style>
h1 {
	color: #47ADDA;
	font-size: 200%;
	margin: 0;
	padding: 0;
}

p {
	color: #47ADDA;
	font-size: 100%;
	margin: 0;
	padding: 0;
}

p1 {
	color: #47ADDA;
	font-size: 100%;
	margin: 0;
	padding: 0;
	padding-left: 20cm;
}

a {
	color: #47ADDA;
	font-size: 100%;
	margin-right: 1cm;
	text-align: right;
}

h2 {
	color: #47ADDA;
	font-size: 150%;
	margin: 0;
	padding: 0;
}

table {
	border-collapse: collapse;
}

table, td, th {
	border: 1px solid black;
}

tr {
	background-color: #323131;
	color: #47ADDA;
}

th {
	background-color: #323131;
	color: #47ADDA;
	font-weight: bold;
	text-align: center;
	display: table-cell;
	vertical-align: inherit;
}

body {
	margin-left: 20px;
}
</style>
</head>
<body style="background-color: #323131; margin-top: 40px;">

	<div>
		<h1>Library Management System</h1>
		<p>A complete solution to manage school library</p>
		
	<form name="myFine" action="TriggerFine" method="post" style="width: 300px">
		<button type="submit" class="btn btn-info" value="Submit">Update Fine Table</button>
		<br> <br>
	</form>
	
	</div>
	<a href="home.jsp">Home</a>
	<a href="AddBookForm.jsp">Add Book</a>
	<a href="ViewBook.jsp">View Book</a>
	<a href="AddBorrower.jsp">Add Borrower</a>
	<a href="CheckoutBook.jsp">Check-out Book</a>
	<a href="CheckinBook.jsp">Check-in Book</a>
	<a href="UpdateFines">Show Fines</a>
	<br>
	<br>
	<br>
</body>
</html>