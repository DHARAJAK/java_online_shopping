<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Oops! Something Went Wrong</title>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f8f9fa;
	color: #343a40;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	text-align: center;
}

.error-container {
	max-width: 500px;
	padding: 40px;
	background: #ffffff;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.error-icon {
	font-size: 64px;
	color: #dc3545;
	margin-bottom: 20px;
}

h1 {
	font-size: 28px;
	margin-bottom: 15px;
	color: #212529;
}

p {
	font-size: 16px;
	color: #6c757d;
	margin-bottom: 30px;
	line-height: 1.5;
}

.btn-home {
	display: inline-block;
	padding: 12px 24px;
	font-size: 16px;
	font-weight: 600;
	color: #ffffff;
	background-color: #0d6efd;
	text-decoration: none;
	border-radius: 5px;
	transition: background-color 0.2s ease;
}

.btn-home:hover {
	background-color: #0b5ed7;
}
</style>
</head>
<body>

	<div class="error-container">
		<div class="error-icon">⚠️</div>

		<h1>An Error Occurred</h1>

		<p>We ran into an unexpected issue processing your request. This
			could be due to a temporary database disruption or a broken session
			link.</p>

		<a href="login.jsp" class="btn-home">Return to Login</a>
	</div>

</body>
</html>



