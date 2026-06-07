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
import org.category.CategoryDaoImpl;
import org.category.CategoryException;
import org.category.RegisteredCategory;

/**
 * Servlet implementation class Category
 */

@WebServlet("/Category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection connection = null;
		Object connObj = getServletContext().getAttribute("dbConnection");

		if (connObj != null) {
			connection = (Connection) connObj;
		} else {
			response.sendRedirect("ErrorPage.html");
			return;
		}

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect("login.html");
			return;
		}

		CategoryDaoImpl catD = new CategoryDaoImpl(connection);
		try {
			Iterator<RegisteredCategory> regCat = catD.getAllCategoriesData();

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<link rel='stylesheet' type='text/css' href='Colors.css'>");
			out.println("<h3> Welcome " + session.getAttribute("username"));
			out.println("<table border='1'");
			out.println("<tr>");
			out.println("<th>Name </th>");
			out.println("<th>Description</th>");
			out.println("<th>Image</th>");
			out.println("<th>Name</th></tr>");
			while (regCat.hasNext()) {

				RegisteredCategory category = regCat.next();

				out.println("<tr>");
				out.println("<td> <a href='Products?categoryId=" + category.getCategoryId() + "' > "
						+ category.getCategoryName() + "</a></td>");
				out.println("<td>" + category.getCategoryDesc() + "</td>");
				out.println(
						"<td><img src='Image/" + category.getCategoryImgUrl() + "' height='80px' width='80px' /></td>");
				out.println("<td>" + category.getCategoryName() + "</td> </tr>");
			}

			out.println("</table>");
			out.println("<form action='AddCategory.html'  method='get'>");
			out.println("<button type='submit'> Add Category </button>");
			out.println("</form></body></html>");
//		out.println("Welcome to catgory page");

		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
