<%@page import="org.products.ProductsDaoImpl"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.products.RegisteredProduct"%>
<%@page import="java.sql.Connection"%>
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

	<%
	Connection connection = (Connection) application.getAttribute("dbConnection");
	if (connection == null)
		throw new RuntimeException("database connection not found inside Products");
	Iterator<RegisteredProduct> prod = null;

	int categoryId = Integer.parseInt(request.getParameter("categoryId"));

	ProductsDaoImpl prodImpl = new ProductsDaoImpl(connection);

	prod = prodImpl.getAllProducts(categoryId);
	%>

	<a href='Category.jsp'> Go Back </a>

	<h3>
		Welcome:
		<%=session.getAttribute("username")%>>
	</h3>
	<br>

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
			<a href="ListCart.jsp">View Cart</a>
		</div>
	</div>


	<%
	String addedStatus = request.getParameter("added");
	if ("true".equals(addedStatus)) {
	%>
	<div
		style='background-color: #d4edda; color: #155724; padding: 10px; margin: 10px 0; border: 1px solid #c3e6cb; border-radius: 5px;'>
		<strong>Success!</strong> Item has been successfully added to your
		cart.
	</div>

	<%
	}
	%>

	<table border='1'>
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Image</th>
			<th>Name</th>
			<th>Cart</th>
		</tr>
		<%
		while (prod.hasNext()) {
			RegisteredProduct product = prod.next();
		%>
		<tr>
			<td><%=product.getProductName()%></td>
			<td><%=product.getProductDescription()%></td>
			<td><img
				src="Image/<%=product.getProductImageUrl()%>"  height='80px' width='80px'></td>
			<td><%=product.getProductPrice()%></td>
			<td><a href='AddToCart?productId=<%=product.getProductId()%>'>Add
					to Cart</a></td>
		</tr>

		<%
		}
		%>

	</table>
	<form action='AddProduct.jsp' method='get'>
		<button type="submit">Add Product</button>
	</form>


</body>
</html>