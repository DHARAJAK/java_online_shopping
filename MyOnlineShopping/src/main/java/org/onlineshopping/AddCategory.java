package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import org.category.RegisteredCategory;

/**
 * Servlet implementation class AddCategory
 */
@WebServlet("/AddCategory")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String categoryName = request.getParameter("categoryName");
		String categoryImageUrl=request.getParameter("categoryImageUrl");
		String categoryDescription=request.getParameter("categoryDescription");
		
//		System.out.println(categoryName+ categoryImageUrl+categoryDescription);
		
		RegisteredCategory regCategory =new RegisteredCategory(categoryName,categoryImageUrl, categoryDescription);
		connection= (Connection)getServletContext().getAttribute("connection");
		if(connection==null) {
			response.sendRedirect("ErrorPage.html");
		}
		else {
			
		}
		
	}

}
