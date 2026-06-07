package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import org.users.RegUser;
import org.users.UsersDao;
import org.users.UsersDaoImpl;
import org.users.UsersException;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		RegUser r = new RegUser(name, password, username, email);
		Object connObj = getServletContext().getAttribute("dbConnection");

		if (connObj == null) {
			response.sendRedirect("login.html");
		}

		Connection connection = (Connection) connObj;

		UsersDao user = new UsersDaoImpl(connection);

		try {
			if (user.UserExists(username)) {
				response.sendRedirect("login.html");
				return;

			}
		} catch (UsersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			user.RegisterUser(r);
			response.sendRedirect("login.html");
			return;
		} catch (UsersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
			return;
		}

	}

}
