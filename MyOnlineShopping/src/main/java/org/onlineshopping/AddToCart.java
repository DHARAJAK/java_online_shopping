package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import org.cart.Cart;
import org.cart.CartException;
import org.cart.CartFactory;
import org.products.ProductsDaoImpl;
import org.products.RegisteredProduct;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Session verification
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.html");
			return;
		}

		Connection connection = (Connection) getServletContext().getAttribute("dbConnection");

		if (connection == null) {
			response.sendRedirect("ErrorPage.html");
			return;
		}

		String productId = request.getParameter("productId");
		System.out.println("DEBUG: Incoming Product ID is -> " + productId);

		ProductsDaoImpl prodDetails = new ProductsDaoImpl(connection);
		RegisteredProduct prod = prodDetails.getProductByProductId(productId);

		if (prod == null) {
			response.sendRedirect("ErrorPage.html");
			return;
		}

		Cart objCart = (Cart) session.getAttribute("cart");
		if (objCart == null) {
			String cartClassName = getServletContext().getInitParameter("cartClassName");
			objCart = CartFactory.getInstance(cartClassName);
			session.setAttribute("cart", objCart);
		}

		try {
			objCart.addToCart(prod);
			System.out.println("Product added to cart");
			response.sendRedirect("Products.jsp?categoryId=" + prod.getCategoryId() + "&added=true");
			return;
		} catch (CartException e) {
			e.printStackTrace();
			response.sendRedirect("ErrorPage.html");
		}
	}
}