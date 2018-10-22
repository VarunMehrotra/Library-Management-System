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

h3 {
	color: #47ADDA;
	font-size: 100%;
	margin: 10;
	padding: 0;
	margin-left: 95px;
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

.button{
	margin-left: 95px;
	}
</style>

<script>

function validateForm() {
	var x;
	
	x = document.forms["SubmitCheckOut"]["bID"].value;
	if (x == "") {
		alert("Enter ID !!");
		return false;
	}
}</script>
</head>
	<jsp:include page="header.jsp" />
</html>