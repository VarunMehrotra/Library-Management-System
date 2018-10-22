<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
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

body 
{
 margin-left:20px;
}
</style>

<script>
	function validateForm() {
		var x;
		
		x = document.forms["mysearch"]["checkout"].value;
		if (x == "") {
			alert("Search bar is empty !!");
			return false;
		}
	}
	
 	function myPopUp(){
 		
 		
		var x = prompt("Enter Borrower SSN");
		debugger;
		
		if (x == "") {
			alert("Please enter SSN !!");
			return false;
		}
	} 
</script>

</head>

<jsp:include page="header.jsp" />

<body style="background-color: #323131; margin-top: 40px;">
	<h2>Check-out Book</h2>
	<form name="mysearch" action="Checkout" method="get" style="width: 400px" onsubmit="return validateForm()">

		<div class="form-group">
			<input type="text" class="form-control" name="checkout"
				id="checkout" placeholder="Enter ISBN or Book Name or Author Name" />
		</div>

		<button type="submit" class="btn btn-primary">Enter</button>
	</form>

</body>

</html>