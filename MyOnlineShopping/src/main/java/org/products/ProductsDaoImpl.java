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
	public Iterator<RegisteredProduct> getAllProductsByCategory(String categoryId) throws ProductsException {
		ArrayList<RegisteredProduct> list = new ArrayList<>();
		// TODO Auto-generated method stub
		try {

			PreparedStatement psAllCategories = connection
					.prepareStatement("select * from products where categoryId = ?");

			psAllCategories.setNString(1, categoryId);

			ResultSet result = psAllCategories.executeQuery();

			while (result.next()) {
				RegisteredProduct regProd = new RegisteredProduct();

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

	public Iterator<RegisteredProduct> getAllProducts(Integer categoryId) {
		ArrayList<RegisteredProduct> list = new ArrayList<>();
		try {
			// Look closely at your SELECT query:
			PreparedStatement psAllProducts = connection.prepareStatement(
					"Select productId, productDescription, productImageUrl, productName, productPrice from products where categoryId = ?");
			psAllProducts.setInt(1, categoryId);

			ResultSet result = psAllProducts.executeQuery();
			while (result.next()) {
				RegisteredProduct regprod = new RegisteredProduct();
				regprod.setProductId(result.getString(1));
				regprod.setProductDescription(result.getString(2));
				regprod.setProductImageUrl(result.getString(3));
				regprod.setProductName(result.getString(4));
				regprod.setProductPrice(result.getFloat(5));
				list.add(regprod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list.iterator();
	}

	public Boolean addProduct(RegisteredProduct regProd) {
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

	public RegisteredProduct getProductByProductId(String productId) {
		try {
			// FIXED: Explicitly select all 5 required columns in matching order
			PreparedStatement psProdDetails = connection.prepareStatement(
					"select categoryId, productName, productDescription, productImageUrl, productPrice from products where productId=?");

			psProdDetails.setString(1, productId);
			ResultSet result = psProdDetails.executeQuery();

			if (result.next()) {
				RegisteredProduct prod = new RegisteredProduct();
				prod.setProductId(productId);
				prod.setCategoryId(result.getString(1)); // matches categoryId
				prod.setProductName(result.getString(2)); // matches productName
				prod.setProductDescription(result.getString(3)); // matches productDescription
				prod.setProductImageUrl(result.getString(4)); // matches productImageUrl
				prod.setProductPrice(result.getFloat(5)); // matches productPrice
				return prod;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
