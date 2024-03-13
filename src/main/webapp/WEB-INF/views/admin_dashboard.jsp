<%@page import="com.healthsoft.abclabs.abclabs_las_web.dao.MySQL"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVfCSyM+8IgMxHsr8cFWOMIHsW+tGCzKS1UOOz7cS08v4tlfCgA"
	crossorigin="anonymous"></script>
</head>
<body>
	<%
// Retrieve the BASE_URL attribute from the ServletContext
String baseUrl = (String) getServletContext().getAttribute("BASE_URL");
%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2 bg-light border-right">
				<ul class="nav flex-column mt-3">
					<li class="nav-item"><a class="nav-link active"
						href="#appointments" data-bs-toggle="collapse" role="button"
						aria-expanded="true" aria-controls="appointments">Appointments</a>
					</li>
					<li class="nav-item"><a class="nav-link collapsed"
						href="#patients" data-bs-toggle="collapse" role="button"
						aria-expanded="false" aria-controls="patients">Patients</a></li>
					<li class="nav-item"><a class="nav-link collapsed"
						href="#doctors" data-bs-toggle="collapse" role="button"
						aria-expanded="false" aria-controls="doctors">Doctors</a></li>
					<li class="nav-item"><a class="nav-link collapsed"
						href="#technicians" data-bs-toggle="collapse" role="button"
						aria-expanded="false" aria-controls="technicians">Technicians</a>
					</li>
					<li class="nav-item"><a class="nav-link collapsed"
						href="#branches" data-bs-toggle="collapse" role="button"
						aria-expanded="false" aria-controls="branches">Branches</a></li>

					<li class="nav-item ms-2"><a
						class="btn btn-primary text-light collapsed" href="#"
						role="button" id="btn-logout">Sign out</a></li>
				</ul>
			</div>

			<div class="col-md-10">
				<div class="row mt-2">
					<div class="col-10"></div>
					<div class="col-2">
						<h6>
							Logged in as
							<%=request.getSession().getAttribute("username")%><h6>
					</div>
				</div>
				<div class="collapse show" id="appointments">
					<h2>Appointments</h2>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Date time</th>
								<th>Test Type</th>
								<th>Report</th>
								<th>Branch</th>
								<th>Patient</th>
								<th>Action</th>
								<th>Upload Report</th>
							</tr>
						</thead>
						<tbody>
							<%
							try {
								ResultSet rs = MySQL.search(
								"SELECT a.`id`,a.`datetime`,t.`name` AS `type`,r.`url`,b.`city`,p.`first_name`,p.`last_name`,p.`mobile`,p.`id` AS `pid`"
										+ " FROM appointment a" + " JOIN test_type t ON a.test_type_id = t.id"
										+ " LEFT JOIN report r ON a.report_id = r.id" + " JOIN branch b ON a.branch_id = b.id"
										+ " JOIN patient p ON a.patient_id = p.id");

								while (rs.next()) {
							%>

							<tr>
								<td><%=rs.getInt("id")%></td>
								<td><%=rs.getString("datetime")%></td>
								<td><%=rs.getString("type")%></td>

								<%
								String url = rs.getString("url");
								if (url != null) {
								%>
								<td><a class="btn btn-primary"
									href="<%=baseUrl%>report?url=<%=rs.getString("url")%>">View</a></td>
								<%
								} else {
								%>
								<td>Not Available</td>
								<%
								}
								%>

								<td><%=rs.getString("city")%></td>
								<td><%=rs.getString("first_name") + " " + rs.getString("last_name")%></td>
								<td><a href="#" class="btn btn-primary btn-sm">Update</a></td>
								<td><label for="reportFile<%=rs.getInt("id")%>"
									class="btn btn-success">Choose file</label> <input
									class="form-control d-none" type="file" accept=".pdf"
									id="reportFile<%=rs.getInt("id")%>">
									<button class="btn btn-primary"
										onclick="uploadFile(<%=rs.getInt("id")%>)">
										<%
										if (rs.getString("url") == null) {
											out.print("Upload");
										} else {
											out.print("Re-upload");
										}
										%>
									</button></td>
							</tr>

							<%
							}

							} catch (Exception e) {
							e.printStackTrace();
							}
							%>

						</tbody>
					</table>
					<div class="d-flex justify-content-end mb-3">
						<button class="btn btn-success">New Appointment</button>
					</div>
				</div>
				<div class="collapse" id="patients">
					<h2>Patients</h2>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Mobile</th>
								<th>Email</th>
								<th>Gender</th>
								<th>Address</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							try {
								ResultSet rs = MySQL.search("SELECT * FROM `patient` p INNER JOIN `gender` g ON p.`gender_id`=g.`id`");

								while (rs.next()) {
							%>
							<tr>
								<td><%=rs.getString("id")%></td>
								<td><%=rs.getString("first_name") + " " + rs.getString("last_name")%></td>
								<td><%=rs.getString("mobile")%></td>
								<td><%=rs.getString("email")%></td>
								<td><%=rs.getString("g.name")%></td>
								<td><%=rs.getString("address_line_1") + " " + rs.getString("address_line_2")%></td>
								<td><a href="#" class="btn btn-primary btn-sm">Update</a></td>
							</tr>
							<%
							}
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>

						</tbody>
					</table>
					<div class="d-flex justify-content-end mb-3">
						<button class="btn btn-success">New Patient</button>
					</div>
				</div>
				<div class="collapse" id="doctors">
					<h2>Doctors</h2>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Mobile</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							try {
								ResultSet rs = MySQL.search("SELECT * FROM `doctor`");

								while (rs.next()) {
							%>
							<tr>
								<td><%=rs.getString("id")%></td>
								<td><%=rs.getString("name")%></td>
								<td><%=rs.getString("mobile")%></td>
								<td><a href="#" class="btn btn-primary btn-sm">Update</a></td>
							</tr>
							<%
							}
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>

						</tbody>
					</table>
					<div class="d-flex justify-content-end mb-3">
						<button class="btn btn-success">New Doctor</button>
					</div>
				</div>
				<div class="collapse" id="technicians">
					<h2>Technicians</h2>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Mobile</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							try {
								ResultSet rs = MySQL.search("SELECT * FROM `technician`");

								while (rs.next()) {
							%>
							<tr>
								<td><%=rs.getString("id")%></td>
								<td><%=rs.getString("name")%></td>
								<td><%=rs.getString("mobile")%></td>
								<td><a href="#" href="#" class="btn btn-primary btn-sm">Update</a>
								</td>
							</tr>
							<%
							}
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>
						</tbody>
					</table>
					<div class="d-flex justify-content-end mb-3">
						<button class="btn btn-success">New Technician</button>
					</div>
				</div>
				<div class="collapse" id="branches">
					<h2>Branches</h2>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>City</th>
								<th>Address</th>
								<th>Telephone</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							try {
								ResultSet rs = MySQL.search("SELECT * FROM `branch`");

								while (rs.next()) {
							%>
							<tr>
								<td><%=rs.getString("id")%></td>
								<td><%=rs.getString("city")%></td>
								<td><%=rs.getString("address")%></td>
								<td><%=rs.getString("telephone")%></td>
								<td><a href="#" class="btn btn-primary btn-sm">Update</a></td>
							</tr>
							<%
							}
							} catch (Exception e) {
							e.printStackTrace();
							}
							%>
						</tbody>
					</table>
					<div class="d-flex justify-content-end mb-3">
						<button class="btn btn-success">New Branch</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	const sideNavLinks = document.querySelectorAll('.nav-link');

	sideNavLinks.forEach(link => {
	  link.addEventListener('click', function () {
	    const href = this.getAttribute('href');
	    const targetPanel = document.querySelector(href);

	    // Hide all other panels
	    sideNavLinks.forEach(otherLink => {
	      if (otherLink !== this) {
	        const otherTarget = document.querySelector(otherLink.getAttribute('href'));
	        otherTarget.classList.remove('show');
	      }
	    });

	    // Toggle visibility of the clicked panel
	    targetPanel.classList.toggle('show');
	  });
	});
	
	function uploadFile(appointmentId) {
	    var fileInput = document.getElementById('reportFile' + appointmentId);
	    var file = fileInput.files[0];

	    if (file) {
	        // Check if the selected file is a PDF
	        if (file.type !== 'application/pdf') {
	            console.error('Invalid file type. Please select a PDF file.');
	            alert('Invalid file type. Please select a PDF file.');
	            return;
	        }

	        var formData = new FormData();
	        formData.append('file', file);

	        fetch(<%=baseUrl%> + 'FileUploadServlet?id=' + appointmentId, {
	            method: 'POST',
	            body: formData
	        })
	        .then(response => response.text())
	        .then(data => {
	            console.log('File uploaded successfully:', data);
	            alert(data);
	            location.reload();
	            // Handle success
	        })
	        .catch(error => {
	            console.error('Error uploading file:', error);
	            alert('Error uploading file: ' + error);
	            // Handle error
	        });
	    } else {
	        console.error('No file selected');
	        alert('Please select a file first.');
	    }
	}

	// Assume this code is triggered when a logout button is clicked

	document.getElementById('btn-logout').addEventListener('click', function() {
	    fetch(<%=baseUrl%>+'logout', {
	        method: 'GET'
	        })
	    .then(response => {
	        if (!response.ok) {
	            throw new Error('Network response was not ok');
	        }
	        return response.text();  // or response.json() if expecting JSON
	    })
	    .then(data => {
	        // Handle success, you can redirect to a new page if needed
	        window.location.replace(<%=baseUrl%>+'admin_login');
	        alert('Logout successful:', data);
	    })
	    .catch(error => {
	        // Handle errors, e.g., show an alert
	        console.error('There was a problem with the fetch operation:', error);
	        alert('Logout failed. Please try again.');
	    });
	});

	</script>

</body>
</html>
