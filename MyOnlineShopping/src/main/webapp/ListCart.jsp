<%@page import="java.util.Iterator"%>
<%@page import="org.products.RegisteredProduct"%>
<%@page import="org.cart.ProductCart"%>
<%@page import="org.cart.Cart"%>
<%@page import="org.cart.CartFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	Cart objCart = (ProductCart) session.getAttribute("cart");

	if (objCart == null) {
		String cartClassName = application.getInitParameter("cartClassName");
		objCart = CartFactory.getInstance(cartClassName);

		session.setAttribute("cart", objCart);
	}

	Iterator<RegisteredProduct> iter = null;
	iter = objCart.listCart();
	%>
	<h3>
		Welcome
		<%=session.getAttribute("username")%>
	</h3>
	<br>
	<h3>
		<a href='Category.jsp'> Home </a>
	</h3>

	<%
	if (iter != null && iter.hasNext()) {
	%>



	<table border='1'>
		<tr>
			<th>Product Name</th>
			<th>Product Image</th>
			<th>Product Price</th>
		</tr>

		<%
		while (iter.hasNext()) {

			RegisteredProduct prod = iter.next();
		%>


		<%
		String productName = prod.getProductName();
		String productImage = prod.getProductImageUrl();
		Float productPrice = prod.getProductPrice();
		%>
		<tr>
			<td><%=productName%></td>
			<td><img src='Image/<%=productImage%>' height='100px'
				width='200px'></td>
			<td><%=productPrice%></td>
		</tr>
		<%
		}
		%>

		<tr>
			<td colspan='2' style='font-weight: bold'><h5>Total</h5></td>
			<td style='font-weight: bold'><%=objCart.cartTotal()%></td>
		</tr>

	</table>

	<h3 style='margin-top: 100px'>
		<a href='payment.html'>Checkout</a>
	</h3>

	<%
	} else {
	%>

	<h4>Cart is Empty !!</h4>

	<%
	}
	%>
</body>
</html>