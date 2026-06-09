<%@page import="java.util.Iterator"%>
<%@page import="org.category.CategoryDaoImpl"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page errorPage="ErrorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel='stylesheet' type='text/css' href='Colors.css'>
<body>

	<%
	Object connObject = application.getAttribute("dbConnection");
	if (connObject == null)
		throw new RuntimeException("Database connection on available");
	Connection connection = (Connection) connObject;
	CategoryDaoImpl categories = new CategoryDaoImpl(connection);
	Iterator<String> list;
	list = categories.getAllCategory();
	%>

	<form action='AddProduct' method='post'>
		Product Name: <input type="text" name="productName"><br>
		Product Description: <input type="text" name="description"><br>
		Product Price: <input type="number" name="price"><br>
		Product Image Url: <input type="text" name="imageUrl"><br>
		<label>Choose a Category: </label> <select name="category">

			<%
			while (list.hasNext()) {
				String val = list.next();
			%>

			<option value=<%=val.toString()%>><%=val.toString()%></option>

			<%
			}
			%>
		</select>
		<button type="submit"> Add product to the List</button>
		
	</form>



</body>
</html>