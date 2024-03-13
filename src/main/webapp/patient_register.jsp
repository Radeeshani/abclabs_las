<%@page import="java.sql.ResultSet"%>
<%@page import="com.healthsoft.abclabs.abclabs_las_web.dao.MySQL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Patient Registration - ABC Laboratories</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
body {
	padding-top: 56px; /* Adjusted for the fixed navbar */
}
</style>
</head>
<body>

	<!-- Navigation Bar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
		<a class="navbar-brand" href="#">ABC Laboratories</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a>
				</li>
			</ul>
		</div>
	</nav>

	<!-- Patient Registration Form -->
	<div class="container mt-5">
		<h2 class="text-center mb-4">Patient Registration</h2>
		<form action="registerPatient" method="post">
			<div class="row">
				<!-- First Name -->
				<div class="col-md-6 form-group">
					<label for="firstName">First Name</label> <input type="text"
						class="form-control" id="firstName" name="firstName"
						maxlength="45" required>
				</div>

				<!-- Last Name -->
				<div class="col-md-6 form-group">
					<label for="lastName">Last Name</label> <input type="text"
						class="form-control" id="lastName" name="lastName" maxlength="45"
						required>
				</div>
			</div>



			<div class="row">
				<!-- Mobile -->
				<div class="col-md-6 form-group">
					<label for="mobile">Mobile</label> <input type="tel"
						class="form-control" id="mobile" name="mobile" maxlength="10"
						pattern="[0-9]{10}"
						title="Please enter a valid 10-digit mobile number" required>
				</div>

				<!-- Gender -->
				<div class="form-group col-md-6">
					<label for="gender">Gender</label> <select class="form-control"
						id="gender" name="gender" required>
						<option value="" selected disabled>Select</option>
						
						<%
						try{
							ResultSet rs=MySQL.search("SELECT * FROM `gender`");
							
							while(rs.next()){
								%>
								<option value="<%=rs.getString("id")%>"><%=rs.getString("name")%></option>
								<%
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						%>
					</select>
				</div>
			</div>

			<div class="row">
				<!-- Email -->
				<div class="col-md-12 form-group">
					<label for="email">Email</label> <input type="email"
						class="form-control" id="email" name="email" maxlength="45"
						required>
				</div>
			</div>

			<div class="row">
				<!-- Address Line 1 -->
				<div class="col-md-6 form-group">
					<label for="addressLine1">Address Line 1</label> <input type="text"
						class="form-control" id="addressLine1" name="addressLine1"
						maxlength="45" required>
				</div>

				<!-- Address Line 2 -->
				<div class="col-md-6 form-group">
					<label for="addressLine2">Address Line 2</label> <input type="text"
						class="form-control" id="addressLine2" name="addressLine2"
						maxlength="45" required>
				</div>
			</div>

			<!-- Submit Button -->
			<button type="submit" class="btn btn-primary">Register</button>
		</form>
	</div>

	<!-- Bootstrap JS and Popper.js (required for Bootstrap components) -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
