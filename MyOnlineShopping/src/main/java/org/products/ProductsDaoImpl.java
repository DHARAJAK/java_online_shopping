package org.products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.dbConfig.DBConfig;

public class ProductsDaoImpl implements ProductsDao {
	Connection connection;

	public ProductsDaoImpl(Connection connection) {
		this.connection = connection;
	}

	public ProductsDaoImpl() {
	}

	@Override
	public Iterator<RegisteredProducts> getAllProductsByCategory(String categoryId) throws ProductsException {
		ArrayList<RegisteredProducts> list = new ArrayList<>();
		// TODO Auto-generated method stub
		try {

			PreparedStatement psAllCategories = connection
					.prepareStatement("select * from products where categoryId = ?");

			psAllCategories.setNString(1, categoryId);

			ResultSet result = psAllCategories.executeQuery();

			while (result.next()) {
				RegisteredProducts regProd = new RegisteredProducts();

				regProd.setProductDescription(result.getString("productDescription"));
				regProd.setProductImageUrl(result.getString("productImageUrl"));
				regProd.setProductName(result.getString("productName"));
				regProd.setProductPrice(result.getString("productPrice"));
				System.out.println(regProd);

				list.add(regProd);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list.iterator();
	}

	public Iterator<RegisteredProducts> getAllProducts(Integer categoryId) {

		ArrayList<RegisteredProducts> list = new ArrayList<>();

		try {
			PreparedStatement psAllProducts = connection.prepareStatement(
					"Select productDescription, productImageUrl, productName, productPrice from products where categoryId = ?");
			psAllProducts.setInt(1, categoryId);

			ResultSet result = psAllProducts.executeQuery();
			while (result.next()) {
				RegisteredProducts regprod = new RegisteredProducts();
				regprod.setProductDescription(result.getString(1));
				regprod.setProductImageUrl(result.getString(2));
				regprod.setProductName(result.getString(3));
				regprod.setProductPrice(result.getString(4));
				list.add(regprod);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list.iterator();
	}
}
