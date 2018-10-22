<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
body 
{
 margin-left:20px;
}
</style>
<script>
	function validateForm() {
		var x;
		
		x = document.forms["myBorrow"]["borrowerID"].value;
		if (x == "") {
			alert("Please enter borrower SSN");
			return false;
		}
	}
</script>

</head>

<body style="background-color: #323131; margin-top: 10px;">
	<h2>Enter borrower SSN</h2>
	<br>
	<form name="myBorrow" action="BorrowBook" method="post" style="width: 100px" onsubmit="return validateForm()">

		<div class="form-group">
			<input type="text" class="form-control" name="borrowerID"
				id="borrowerID" placeholder="Search ..." />
		</div>

		<button type="submit" class="btn btn-primary">Enter</button>
	</form>

</body>

</html>