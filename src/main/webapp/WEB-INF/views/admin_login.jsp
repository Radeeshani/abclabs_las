<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login - ABC Laboratories</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
// Retrieve the BASE_URL attribute from the ServletContext
String baseUrl = (String) getServletContext().getAttribute("BASE_URL");
%>
    <div class="container-fluid vh-100 d-flex justify-content-center align-items-center">
        <div class="row">
            <div class="col-md-12 text-center mb-5 text-light">
                <h1 class="display-4">ABC Laboratories</h1>
            </div>
            <div class="col-md-6 mx-auto">
                <div class="card shadow-sm p-4">
                    <h3 class="card-title text-center mb-4">Admin Login</h3>
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" required>
                        </div>
                        <button onclick="login()" class="btn btn-primary d-block mx-auto">Login</button>
                </div>
            </div>
        </div>
    </div>
    
        <script>
        function login() {
            // Get username and password from the form
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;

            // Send a fetch request to the login servlet
            fetch(<%=baseUrl%>+'admin_login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password),
            })
            .then(response => response.text())
            .then(data => {
                // Check the response data
                if (data === 'success') {
                    // Redirect to admin_dashboard.jsp on success
                    window.location.href = <%=baseUrl%>+'dashboard';
                } else {
                    // Show an alert on failure
                    alert('Login failed. Response: ' + data);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
    </script>
</body>
</html>
