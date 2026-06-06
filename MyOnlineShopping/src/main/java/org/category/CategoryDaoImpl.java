package org.category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.dbConfig.DBConfig;

public class CategoryDaoImpl implements CategoryDao {

	public CategoryDaoImpl() {

	}

	@Override
	public Iterator<RegisteredCategory> getAllCategories(Connection connection) throws CategoryException {

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

}
