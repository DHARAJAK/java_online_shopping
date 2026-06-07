package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import org.category.CategoryDao;
import org.category.CategoryDaoImpl;
import org.category.CategoryException;
import org.category.RegisteredCategory;

/**
 * Servlet implementation class AddCategory
 */
@WebServlet("/AddCategory")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection = null;

		String categoryName = request.getParameter("categoryName");
		String categoryImageUrl = request.getParameter("categoryImageUrl");
		String categoryDescription = request.getParameter("categoryDescription");

		System.out.println(categoryName + categoryImageUrl + categoryDescription);

		RegisteredCategory regCategory = new RegisteredCategory(categoryName, categoryImageUrl, categoryDescription);

		Object connObj = getServletContext().getAttribute("dbConnection");

		if (connObj == null) {
			System.out.println("add category eror redirection");
			response.sendRedirect("ErrorPage.html");
		} else {
			connection = (Connection) connObj;

			CategoryDao category = new CategoryDaoImpl(connection);

			try {
				if (category.addCategory(regCategory)) {
					response.sendRedirect("Category");
				} else {
					response.sendRedirect("ErrorPage.html");
				}

			} catch (CategoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
