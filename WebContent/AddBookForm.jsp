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
		
		x = document.forms["myaddBook"]["isbn"].value;
		if (x == "") {
			alert("Book ISBN must be filled out");
			return false;
		}
	    if (x.length != 10) {
	        alert("ISBN Invalid. Please enter a 10 character ISBN");
	        return false;
	    }
		
		x = document.forms["myaddBook"]["book_name"].value;
		if (x == "") {
			alert("Book Title must be filled out");
			return false;
		}

		x = document.forms["myaddBook"]["author_name"].value;
		if (x == "") {
			alert("Author Name must be filled out");
			return false;
		}
	}
</script>

</head>


<jsp:include page="header.jsp" />

<body style="background-color: #323131; margin-top: 40px;">

	<h3>
		Add Book Form<br>
		<br>
	</h3>
	<form name="myaddBook" action="AddBook" method="post"
		style="width: 300px" onsubmit="return validateForm()">

		<div class="form-group">
			<label for="title">Book ISBN</label> <input type="text"
				class="form-control" name="isbn" id="isbn"
				placeholder="Book ISBN" />
		</div>
		<div class="form-group">
			<label for="title">Book Title</label> <input type="text"
				class="form-control" name="book_name" id="book_name"
				placeholder="Book Title" />
		</div>
		<div class="form-group">
			<label for="author">Author Name</label> <input type="text"
				class="form-control" name="author_name" id="author_name"
				placeholder="Author Name" />
		</div>
		<button type="submit" class="btn btn-primary">Save Book</button>
	</form>

</body>

</html>