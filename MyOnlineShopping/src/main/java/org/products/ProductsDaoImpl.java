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
				regProd.setProductPrice(result.getFloat("productPrice"));
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
				regprod.setProductPrice(result.getFloat(4));
				list.add(regprod);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list.iterator();
	}

	public Boolean addProduct(RegisteredProducts regProd) {
		Integer catId = 0;
		try {
			PreparedStatement psAddProduct = connection.prepareStatement(
					"insert into products(categoryId, productDescription, productImageUrl, productName, productPrice) values (?,?,?,?,?)");
			catId = getCategoryId(regProd.getCategoryId());
			if (catId == 0) {
				System.out.println("Catid can't be zero -> productsDaoImpl"); // console test for catid
			}
			psAddProduct.setInt(1, catId);
			psAddProduct.setString(2, regProd.getProductDescription());
			psAddProduct.setString(3, regProd.getProductImageUrl());
			psAddProduct.setString(4, regProd.getProductName());
			psAddProduct.setFloat(5, regProd.getProductPrice());

			psAddProduct.execute();

			if (psAddProduct.getUpdateCount() == 1) {
				System.out.println("Product added");
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public Integer getCategoryId(String categoryName) {
		Integer var = 0;
		try {
			PreparedStatement psCatId = connection
					.prepareStatement("select categoryId from category where categoryName = ?");
			psCatId.setString(1, categoryName);

			ResultSet result = psCatId.executeQuery();

			if (result.next()) {
				var = result.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return var;
	}

}
