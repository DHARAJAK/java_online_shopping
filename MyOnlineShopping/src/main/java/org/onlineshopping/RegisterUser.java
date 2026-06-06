package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

		UsersDao user = new UsersDaoImpl();

		try {
			if (!user.RegisterUser(r)) {
				response.sendRedirect("login.html");
			} else {
				out.println("<h2> Registering Failed.. Redirecting to Login");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("register.html");
			}

		} catch (UsersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
