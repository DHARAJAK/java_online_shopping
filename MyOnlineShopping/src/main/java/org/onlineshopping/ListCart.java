package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import org.cart.Cart;
import org.cart.CartException;
import org.cart.CartFactory;
import org.cart.ProductCart;
import org.products.RegisteredProduct;

@WebServlet("/ListCart")
public class ListCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect("login.html");
			return;
		}

		Cart objCart = null;

		objCart = (ProductCart) session.getAttribute("cart");

		if (objCart == null) {

			String cartClassName = getServletContext().getInitParameter("cartClassName");

			objCart = CartFactory.getInstance(cartClassName);

			session.setAttribute("cart", objCart);

		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Iterator<RegisteredProduct> iter = null;
		out.println("<html><body>");
		out.println("<h3> Welcome  " + session.getAttribute("username") + "</h3><br>");
		out.println("<h3><a href='Category'> Home </a> </h3>");

		try {
			iter = objCart.listCart();
			if (iter != null && iter.hasNext()) {

				out.println("<table border='1'><tr>");
				out.println("<th> Product Name </th> <th> Product Image </th> <th>  Product Price</th></tr>");

				while (iter.hasNext()) {

					RegisteredProduct prod = iter.next();

					String productName = prod.getProductName();
					String productImageUrl = prod.getProductImageUrl();
					Float productPrice = prod.getProductPrice();

					out.println("<tr>");
					out.println("<td>" + productName + "</td>");
					out.println("<td><img src='Image/" + productImageUrl + "' height='80px' width='80px' /></td>");
					out.println("<td>" + productPrice + "</td>");
					out.println("</tr>");
				}

				out.println("<tr>");
				out.println("<td colspan='2' style='font-weight:bold'>Total</td>");
				out.println("<td style:'font-weight: bold;'>" + objCart.cartTotal() + "</td>");
				out.println("</tr>");

				out.println("</table>");

				out.println("<h3 style='margin-top:100px'> <a href ='payment.html'>Checkout</a></h3>");

			} else {
				out.println("Cart is empty");
			}
		} catch (CartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<p> Error loading Cart Details.</p>");
		}

	}

}
