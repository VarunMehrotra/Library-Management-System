<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
h3 {
	color: #47ADDA;
	font-size: 150%;
	margin: 0;
	padding: 0;
}

label {
	color: #47ADDA;
	font-size: 100%;
	margin: 0;
	padding: 0;
}
h2 {
	color:#FF0000;
	font-size: 150%;
	margin: 0;
	padding: 0;
	}
	
body 
{
 margin-left:20px;
}
</style>

<script>
function validateForm() {
	var x;
	
    x = document.forms["borrowerID"]["bID"].value;
    if (x == "") {
        alert("First Name must be filled out");
        return false;
    }
}
</script>

</head>


<jsp:include page="header.jsp" />

<body style="background-color: #323131; margin-top: 40px;">

	<h3>
		Enter Borrower ID
		<br>
		<br>
	</h3>
	<form name="borrowerID" action="AddBorrower" method="post" style="width: 300px" onsubmit="return validateForm()">

		<div class="form-group">
			<label for="title">Borrower ID</label> <input type="text"
				class="form-control" name="bID" id="bID"
				placeholder="First Name" />
		</div>
		<button type="submit" class="btn btn-primary">Enter</button>
	</form>

</body>

</html>