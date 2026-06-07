package org.onlineshopping;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;

import org.category.CategoryDaoImpl;
import org.category.CategoryException;
import org.products.ProductsDaoImpl;
import org.products.RegisteredProduct;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection connection = null;

		Object connObj = getServletContext().getAttribute("dbConnection");

		if (connObj == null) {
			response.sendRedirect("ErrorPage.html");

		}
//		String productName = request.getParameter("productName");
//		String productdescription = request.getParameter("description");
//		Float productPrice = Float.parseFloat(request.getParameter("price"));
//		String productCategory = request.getParameter("category");

		connection = (Connection) connObj;

		PrintWriter out = response.getWriter();
		CategoryDaoImpl categories = new CategoryDaoImpl(connection);
		Iterator<String> list;
		try {
			list = categories.getAllCategory();

			out.println("<html><body>");
			out.println("<link rel='stylesheet' type='text/css' href='Colors.css'>");
			out.println("<form action='AddProduct' method='post'>");
			out.println("ProductName : <input type='text' name='productName'> </br>");
			out.println("ProductDescription: <input type='text' name='description' > </br>");
			out.println("Product Price: <input type='number' name='price'></br>");
			out.println("Product Image URL: <input type='text' name='imageUrl'></br>");
			out.println("<label>Choose a Category: </br>");
			out.println("<select name='category'>");
			while (list.hasNext()) {
				String val = list.next().toString();
				out.println("<option value='" + val + "'>" + val + "</option>");
			}
			out.println("</select>");
			out.println("<button type='submit'>Add Product to the List</button>");
			out.println("</form>");
			out.println("</body></html>");
		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection connection = null;

		Object connObj = getServletContext().getAttribute("dbConnection");

		if (connObj == null) {
			resp.sendRedirect("ErrorPage.html");

		}
		String productName = req.getParameter("productName");
		String productDescription = req.getParameter("description");
		Float productPrice = Float.parseFloat(req.getParameter("price"));
		String productCategory = req.getParameter("category");
		String productUrl = req.getParameter("imageUrl");

		System.out.println(productName + "--" + productDescription + "--" + productPrice + "--" + productCategory + "--"
				+ productUrl);

		connection = (Connection) connObj;

		ProductsDaoImpl newproduct = new ProductsDaoImpl(connection);
		RegisteredProduct regProd = new RegisteredProduct(productName, productDescription, productPrice,
				productCategory.toString(), productUrl);

		newproduct.addProduct(regProd);

		ProductsDaoImpl prod = new ProductsDaoImpl(connection);
		Integer categoryId = prod.getCategoryId(productCategory);

		resp.sendRedirect("Products?categoryId=" + categoryId);

	}

}
