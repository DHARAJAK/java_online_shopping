package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import javax.smartcardio.CardException;

import org.cart.Cart;
import org.cart.ProductCart;
import org.payment.CardsDaoImpl;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.html");
			return;

		}

		Cart objCart = (ProductCart) session.getAttribute("cart");

		if (objCart == null || objCart.cartTotal() <= 0) {
			response.sendRedirect("ListCart");
			return;
		}
		Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
		Integer cardCVV = Integer.parseInt(request.getParameter("cardCVV"));
		Integer password = Integer.parseInt(request.getParameter("cardPassword"));

		Float billAmount = objCart.cartTotal();

		Connection connection = (Connection) getServletContext().getAttribute("dbConnection");

		if (connection != null) {
			CardsDaoImpl card = new CardsDaoImpl(connection);

			try {
				if (card.verifyCard(cardNo, cardCVV, password)) {

					Boolean paymentSuccess = card.deductBalance(cardNo, billAmount);

					if (paymentSuccess) {
						session.removeAttribute("cart");
						response.sendRedirect("success.html");
						return;
					} else {
						response.sendRedirect("PaymentFailed.html?reason=insufficient_funds");
					}

				} else {
					response.sendRedirect("PaymentFailed.html?reason=invalid_credentials");
				}

			} catch (CardException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}