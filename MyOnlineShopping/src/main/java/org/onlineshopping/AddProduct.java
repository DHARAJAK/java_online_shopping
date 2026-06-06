package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection connection = null;

		Object connObj = getServletContext().getAttribute("dbConnection");

		if (connObj == null) {
			response.sendRedirect("ErrorPage.html");

		}
		String productName = request.getParameter("productName");
		String productdescription = request.getParameter("description");
		Float productPrice = Float.parseFloat(request.getParameter("price"));
		String productCategory = request.getParameter("category");

	}

}
