package org.onlineshopping;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.users.UsersDaoImpl;
import org.users.UsersException;

public class AuthenticateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	Connection connection;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);
			String driverClass = config.getInitParameter("driverClass");
			String dbUrl = config.getInitParameter("dbUrl");
			String dbUser = config.getInitParameter("dbUser");
			String dbPassword = config.getInitParameter("dbPassword");
			Class.forName(driverClass);
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			getServletContext().setAttribute("dbConnection", connection);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.destroy();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (connection == null) {
			response.sendRedirect("ErrorPage.html");
			return;
		}

		UsersDaoImpl user = new UsersDaoImpl(connection);
//		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println(username);
		System.out.println(password);

		try {
			if (user.UserExists(username)) {

				if (user.VerifyPassword(username, password)) {
					System.out.println("User Verified");

					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					response.sendRedirect("Category.jsp");
					return;

				} else {
					System.out.println("User password incorrect");
					response.sendRedirect("ResetPassword");
				}

			} else {
				System.out.println("User does not exist");
				response.sendRedirect("register.html");
			}
		} catch (UsersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
