<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ABC Laboratories</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
body {
	padding-top: 56px; /* Adjusted for the fixed navbar */
}

/* Background Image Styling */
#home {
	color: black; /* Text color on top of the background image */
}

  /* Custom styles for carousel control buttons */
  .carousel-control-prev, .carousel-control-next {
    background-color: transparent;
    color: #000; /* Set the color to black or any desired color */
    border: none; /* Remove border if necessary */
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
				<li class="nav-item active"><a class="nav-link" href="#home">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#services">Services</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#branches">Branches</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#contact">Contact</a>
				</li>
			</ul>
		</div>
	</nav>

	<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleCaptions" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
			<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="<%=baseUrl%>images/slider-1.jpg" class="d-block w-100"
					alt="...">
				<div class="carousel-caption d-none d-md-block carousel-text">
					<h1 class="display-4">Welcome to ABC Laboratories</h1>
					<p class="lead">We care about your health. Schedule your health
						test today.</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="<%=baseUrl%>images/slider-2.jpg" class="d-block w-100"
					alt="...">
				<div class="carousel-caption d-none d-md-block carousel-text">
					<h1 class="display-4">Welcome to ABC Laboratories</h1>
					<p class="lead">We care about your health. Schedule your health
						test today.</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="<%=baseUrl%>images/slider-3.jpg" class="d-block w-100"
					alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h1 class="display-4">Welcome to ABC Laboratories</h1>
					<p class="lead">We care about your health. Schedule your health
						test today.</p>
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-target="#carouselExampleCaptions" data-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-target="#carouselExampleCaptions" data-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</button>
		</div>
		</div>

		<!-- Health Test Form -->
		<div class="container text-center py-5" id="appointment">
			<div class="row">
				<div class="col-md-6 offset-md-3">
					<p class="lead">Make an appointment for your health test today.</p>
					<a href="appointment.jsp" class="btn btn-primary">Make
						Appointment</a>
				</div>
			</div>
		</div>

		<!-- Services Section -->
		<section id="services" class="bg-light py-5">
			<div class="container">
				<h2 class="text-center mb-4">Our Services</h2>
				<div class="row">
					<div class="col-md-4">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">Blood Test</h5>
								<p class="card-text">Comprehensive blood tests to assess
									your overall health.</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">Urinalysis</h5>
								<p class="card-text">Analysis of urine samples for various
									health indicators.</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">Cholesterol Test</h5>
								<p class="card-text">Measurement of cholesterol levels for
									heart health assessment.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>



		<!-- Branches Section -->
		<section id="branches">
			<div class="container py-5">
				<h2 class="text-center mb-4">Our Branches</h2>
				<div class="row">
					<div class="col-md-6">
						<h5>Colombo Branch</h5>
						<p>Address: 123 Main Street, Colombo</p>
					</div>
					<div class="col-md-6">
						<h5>Kandy Branch</h5>
						<p>Address: 456 Central Road, Kandy</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<h5>Galle Branch</h5>
						<p>Address: 789 Seaside Avenue, Galle</p>
					</div>
					<div class="col-md-6">
						<h5>Jaffna Branch</h5>
						<p>Address: 101 Northern Street, Jaffna</p>
					</div>
				</div>
				<!-- Add more branches as needed -->
			</div>
		</section>

		<!-- Contact Section -->
		<section id="contact" class="bg-light py-5">
			<div class="container">
				<h2 class="text-center mb-4">Contact Us</h2>
				<div class="row">
					<div class="col-md-6">
						<p>For inquiries or appointments, please contact us:</p>
						<ul>
							<li>Email: info@abclaboratories.lk</li>
							<li>Phone: +94 (0)11 123 4567</li>
							<li>Address: 789 Health Avenue, Colombo, Sri Lanka</li>
						</ul>
					</div>
					<div class="col-md-6">
						<!-- You can add a contact form here if needed -->
					</div>
				</div>
			</div>
		</section>

		<!-- Bootstrap JS and Popper.js (required for Bootstrap components) -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
