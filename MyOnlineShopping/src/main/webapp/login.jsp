<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' type='text/css' href='Colors.css'>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Welcome to Online Shopping</h3>
	<form action="AuthenticateUser" method="post">
		User Name: <input value="rahul" type="text" name="username"><br />
		Password : <input value="r@123" type="password" name="password"><br />
		<input type="submit" value="Login"><br /> <a
			href="register.html">register New User?</a>
	</form>
</body>
</html>