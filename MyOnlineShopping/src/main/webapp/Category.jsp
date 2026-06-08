<%@page import="org.category.RegisteredCategory"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.category.CategoryDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel='stylesheet' type='text/css' href='Colors.css'>
<body>
	<h3>
		Welcome
		<%=session.getAttribute("username")%></h3>
	<div
		style='display: flex; justify-content: space-between; align-items: center;'>
		<h3>
			<span>Welcome to Online Shopping</span>
		</h3>
		<a href='Logout'>logout !!</a>
	</div>
	<div style="display: flex; justify-content: space-between;">
		<div></div>
		<div>
			<a href='ListCart'>View Cart</a>
		</div>
		<div></div>
	</div>
	<table border='1'>
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Image</th>
			<th>Name</th>
		</tr>
		<%
		Connection connection = (Connection) application.getAttribute("dbConnection");
		if (connection == null)
			System.out.println("connection is null");
		CategoryDaoImpl catD = new CategoryDaoImpl(connection);
		Iterator<RegisteredCategory> regCat = catD.getAllCategoriesData();
		while (regCat.hasNext()) {
		%>
		<tr>
			<td>
				<%
				RegisteredCategory category = regCat.next();
				%> <a href="Products?categoryId=<%=category.getCategoryId()%>">
					<%=category.getCategoryName()%>
			</a>
			</td>
			<td><%=category.getCategoryDesc()%></td>

			<td><img src="Image/<%=category.getCategoryImgUrl()%>"
				height='80px' width='80px'></td>

			<td><%=category.getCategoryName()%></td>


			<%
			}
			%>
		</tr>

	</table>
	<form action='AddCategory.html' method='get'>
		<button type='submit'>Add Category</button>
	</form>
</body>
</html>