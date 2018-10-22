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
	
    x = document.forms["myForm"]["bfname"].value;
    if (x == "") {
        alert("First Name must be filled out");
        return false;
    }
    
    x = document.forms["myForm"]["ssn"].value;
    if (x == "") {
        alert("SSN must be filled out");
        return false;
    }
    if (x.length != 9) {
        alert("SSN Invalid. Please enter correct SSN");
        return false;
    }
    
    x = document.forms["myForm"]["phone"].value;
    if (x == "") {
        alert("Phone Number must be filled out");
        return false;
    }
    if (x.length != 10) {
        alert("Phone Number Invalid. Please enter correct Phone Number");
        return false;
    }
    
    x = document.forms["myForm"]["address"].value;
    if (x == "") {
        alert("Address must be filled out");
        return false;
    }
    
    x = document.forms["myForm"]["city"].value;
    if (x == "") {
        alert("City must be filled out");
        return false;
    }
    
    x = document.forms["myForm"]["state"].value;
    if (x == "") {
        alert("State must be filled out");
        return false;
    }
}
</script>

</head>


<jsp:include page="header.jsp" />

<body style="background-color: #323131; margin-top: 40px;">

	<h3>
		Add Borrower Form
		<br>
		<br>
	</h3>
	<form name="myForm" action="AddBorrower" method="post" style="width: 300px" onsubmit="return validateForm()">

		<div class="form-group">
			<label for="title">Borrower First Name</label> <input type="text"
				class="form-control" name="bfname" id="bfname"
				placeholder="First Name" />
		</div>
		<div class="form-group">
			<label for="title">Borrower Last Name</label> <input type="text"
				class="form-control" name="blname" id="blname"
				placeholder="Last Name" />
		</div>
		<div class="form-group">
			<label for="title">SSN</label> <input type="text"
				class="form-control" name="ssn" id="ssn" placeholder="SSN" />
		</div>
		<div class="form-group">
			<label for="title">Phone Number</label> <input type="text"
				class="form-control" name="phone" id="phone"
				placeholder="Phone Number" />
		</div>
		<div class="form-group">
			<label for="title">Address</label> <input type="text"
				class="form-control" name="address" id="address"
				placeholder="Address" />
		</div>
		<div class="form-group">
			<label for="title">City</label> <input type="text"
				class="form-control" name="city" id="city" placeholder="City" />
		</div>
		<div class="form-group">
			<label for="title">State</label> <input type="text"
				class="form-control" name="state" id="state" placeholder="State" />
		</div>
		<button type="submit" class="btn btn-primary">Save Form</button>
	</form>

</body>

</html>