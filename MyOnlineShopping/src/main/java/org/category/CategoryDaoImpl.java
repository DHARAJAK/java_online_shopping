package org.category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dbConfig.DBConfig;

public class CategoryDaoImpl implements CategoryDao {
	Connection connection = null;

	public CategoryDaoImpl(Connection connection) {
		this.connection = connection;

	}

	@Override
	public Iterator<RegisteredCategory> getAllCategoriesData() throws CategoryException {

		ArrayList<RegisteredCategory> list = new ArrayList<>();

		try {
			PreparedStatement psGetAllcategory = connection.prepareStatement("select * from category");

			ResultSet result = psGetAllcategory.executeQuery();

			while (result.next()) {
				RegisteredCategory regCat = new RegisteredCategory();

				regCat.setCategoryDesc(result.getString(1));
				regCat.setCategoryId(result.getInt(2));
				regCat.setCategoryImgUrl(result.getString(3));
				regCat.setCategoryName(result.getString(4));

				list.add(regCat);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list.iterator();
	}

	private boolean categoryPresentOrNot(String category) {

		try {
			PreparedStatement psAddCategory = connection
					.prepareStatement("select distinct categoryName category from category");

			ResultSet result = psAddCategory.executeQuery();

			while (result.next()) {
				if (category.equals(result.getString(1))) {
					;
					return false; // category already present;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public boolean addCategory(RegisteredCategory regcat) throws CategoryException {

		if (!categoryPresentOrNot(regcat.getCategoryName())) {
			return false;
		}

		try {
			PreparedStatement psAddCategory = connection.prepareStatement(
					"INSERT INTO category (categoryName, categoryImageUrl, categoryDescription) VALUES (?, ?, ?)");
			psAddCategory.setString(1, regcat.getCategoryName());
			psAddCategory.setString(2, regcat.getCategoryImgUrl());
			psAddCategory.setString(3, regcat.getCategoryDesc());

			psAddCategory.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public Iterator<String> getAllCategory() throws CategoryException {
		List<String> list = new ArrayList<>();

		try {
			PreparedStatement psAllCategory = connection.prepareStatement("select distinct categoryName from category");

			ResultSet result = psAllCategory.executeQuery();

			while (result.next()) {
				System.out.println(result.getString(1) + "\n");
				list.add(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list.iterator();

	}

}
