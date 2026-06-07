package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;

import org.category.RegisteredCategory;
import org.products.ProductsDaoImpl;
import org.products.RegisteredProduct;

/**
 * Servlet implementation class Products
 */
@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = null;

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect("login.html");
			return;
		}

		Object connObj = getServletContext().getAttribute("dbConnection");
		if (connObj != null) {
			connection = (Connection) connObj;
		} else {
			response.sendRedirect("ErrorPage.html");
		}

		Iterator<RegisteredProduct> prod;

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

		ProductsDaoImpl prodimpl = new ProductsDaoImpl(connection);

		prod = prodimpl.getAllProducts(categoryId);

		PrintWriter out = response.getWriter();

		out.println("<a href='Category'> Go Back </a>");

		out.println("<html>");
		out.println("<body>");
		out.println("<link rel='stylesheet' type='text/css' href='Colors.css'>");
		out.println("<h3> Welcome " + session.getAttribute("username") + "</h3></br>");
		out.println("<div style='display: flex; justify-content: space-between; align-items: center;'>");
		out.println("   <h3><span>Welcome to Online Shopping</span></h3>");
		out.println("   <a href='Logout'>logout !!</a>");
		out.println("</div>");
		out.println("<div style=\"display: flex; justify-content: space-between;\">" + "  <div></div"
				+ "  <div><a href='ListCart'>View Cart</a></div>" + "</div>");

		String addedStatus = request.getParameter("added");
		if ("true".equals(addedStatus)) {
			out.println(
					"<div style='background-color: #d4edda; color: #155724; padding: 10px; margin: 10px 0; border: 1px solid #c3e6cb; border-radius: 5px;'>");
			out.println("   <strong>Success!</strong> Item has been successfully added to your cart.");
			out.println("</div>");
		}

		out.println("<table border='1'");
		out.println("<tr>");
		out.println("<th>Name </th>");
		out.println("<th>Description</th>");
		out.println("<th>Image</th>");
		out.println("<th>Name</th>");
		out.println("<th>Cart</th>");
		out.println("</tr>");
		while (prod.hasNext()) {

			RegisteredProduct products = prod.next();
			System.out.println(products.getProductId());

			out.println("<tr>");
			out.println("<td>" + products.getProductDescription() + "</td>");
			out.println("<td>" + products.getProductName() + "</td>");
			out.println(
					"<td><img src='Image/" + products.getProductImageUrl() + "' height='80px' width='80px' /></td>");
			out.println("<td>" + products.getProductPrice() + "</td>");
			out.println("<td><a href='AddToCart?productId=" + products.getProductId() + "'>Add to Cart</a></td>");

		}
		out.println("</tr> </table>");
		out.println("<form action='AddProduct'  method='get'>");
		out.println("<button type='submit'> Add Product </button>");
		out.println("</form></body></html>");

	}

}
