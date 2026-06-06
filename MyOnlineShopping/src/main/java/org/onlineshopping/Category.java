package org.onlineshopping;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

import org.category.CategoryDaoImpl;
import org.category.CategoryException;
import org.category.RegisteredCategory;

/**
 * Servlet implementation class Category
 */

//@WebServlet("/Category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		Object connObj = getServletContext().getAttribute("dbConnection");
		
		if(connObj != null) {
			this.connection =  (Connection) connObj;
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		CategoryDaoImpl catD = new CategoryDaoImpl();
		try {
			Iterator<RegisteredCategory> regCat = catD.getAllCategories(connection);

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
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

			out.println("</table></body></html>");
//		out.println("Welcome to catgory page");

		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
