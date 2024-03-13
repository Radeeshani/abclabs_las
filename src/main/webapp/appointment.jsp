<%@page import="java.sql.SQLException"%>
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
<title>Make Appointment - ABC Laboratories</title>

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

<%
// Retrieve the BASE_URL attribute from the ServletContext
String baseUrl = (String) getServletContext().getAttribute("BASE_URL");
%>

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

	<!-- Make Appointment Form -->
	<div class="container mt-5">
		<h2 class="text-center mb-4">Make Appointment</h2>
		<form action="appointment_servlet" method="post"
			onsubmit="return validateForm()">
			<div class="form-row">
				<!-- Patient ID -->
				<div class="form-group col-md-6">
					<label for="patientId">Patient ID</label> <input type="text"
						class="form-control" id="patientId" name="patientId"
						maxlength="10" pattern="\d+"
						title="Please enter a valid integer value" required> <small
						class="form-text text-muted">If you haven't patient ID,
						please <a href="patient_register.jsp">register</a> first.
					</small>
				</div>

				<!-- Test Type -->
				<div class="form-group col-md-6">
					<label for="testType">Test Type</label> <select
						class="form-control" id="testType" name="testType" required>
						<option value="" selected disabled>Select</option>

						<%
						try {
							ResultSet rs = MySQL.search("SELECT * FROM `test_type`");

							while (rs.next()) {
						%>
						<option value="<%=rs.getInt("id")%>">
							<%=rs.getString("name")%>
						</option>
						<%
						}
						} catch (SQLException e) {
						e.printStackTrace();
						}
						%>
						<!-- Add more options as needed -->
					</select>
				</div>

				<!-- Appointment Date -->
				<div class="form-group col-md-6">
					<label for="appointmentDate">Appointment Date</label> <input
						type="date" class="form-control" id="appointmentDate"
						name="appointmentDate" required>
				</div>

				<!-- Nearest Branch -->
				<div class="form-group col-md-6">
					<label for="nearestBranch">Nearest Branch</label> <select
						class="form-control" id="nearestBranch" name="nearestBranch"
						required>
						<option value="" selected disabled>Select</option>

						<%
						try {
							ResultSet rs = MySQL.search("SELECT `id`,`city` FROM `branch`");

							while (rs.next()) {
						%>
						<option value="<%=rs.getInt("id")%>"><%=rs.getString("city")%></option>

						<%
						}
						} catch (SQLException e) {
						e.printStackTrace();
						}
						%>
						<!-- Add more options as needed -->
					</select>
				</div>
			</div>

			<!-- Submit Button -->
			<button type="submit" class="btn btn-primary">Make
				Appointment</button>
		</form>
	</div>

	<!-- Bootstrap JS and Popper.js (required for Bootstrap components) -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	<script>
		function validateForm() {
			var appointmentDate = document.getElementById("appointmentDate").value;
			var currentDate = new Date();
			var selectedDate = new Date(appointmentDate);

			// Set the time components in current date to midnight
			currentDate.setHours(0, 0, 0, 0);

			if (selectedDate < currentDate) {
				alert("Appointment date must be today or a date in the future.");
				return false;
			}

			return true;
		}
	</script>

<script src="<%=baseUrl%>/js/script.js"></script>
</body>
</html>
