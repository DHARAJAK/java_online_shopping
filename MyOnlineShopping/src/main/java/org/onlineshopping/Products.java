package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;

import org.category.RegisteredCategory;
import org.products.ProductsDaoImpl;
import org.products.RegisteredProducts;

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
	Connection connection;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object connObj = getServletContext().getAttribute("dbConnection");
		if (connObj != null) {
			connection = (Connection) connObj;
		}else {
			response.sendRedirect("ErrorPage.html");
		}

		Iterator<RegisteredProducts> prod;

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

		ProductsDaoImpl prodimpl = new ProductsDaoImpl(connection);

		prod = prodimpl.getAllProducts(categoryId);

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<link rel='stylesheet' type='text/css' href='Colors.css'>");
		out.println("<table border='1'");
		out.println("<tr>");
		out.println("<th>Name </th>");
		out.println("<th>Description</th>");
		out.println("<th>Image</th>");
		out.println("<th>Name</th></tr>");
		while (prod.hasNext()) {

			RegisteredProducts products = prod.next();

			out.println("<tr>");
			out.println("<td>" + products.getProductDescription() + "</td>");
			out.println("<td>" + products.getProductName() + "</td>");
			out.println(
					"<td><img src='Image/" + products.getProductImageUrl() + "' height='80px' width='80px' /></td>");
			out.println("<td>" + products.getProductPrice() + "</td> </tr>");

		}
		out.println("</table>");
		out.println("<form action='AddProduct.html'  method='get'>");
		out.println("<button type='submit'> Add Product </button>");
		out.println("</form></body></html>");

	}

}
