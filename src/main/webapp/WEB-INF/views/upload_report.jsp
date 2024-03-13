<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>File Upload Form</title>
</head>
<body>
    <h2>File Upload Form</h2>
    <form action="FileUploadServlet" method="post" enctype="multipart/form-data">
        Select File: <input type="file" name="file" required><br><br>
        <input type="submit" value="Upload">
    </form>
</body>
</html>
