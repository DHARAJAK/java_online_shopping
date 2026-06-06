package org.category;

import java.sql.Connection;
import java.util.Iterator;

public interface CategoryDao {

	public Iterator<RegisteredCategory> getAllCategories(Connection connection) throws CategoryException;

}
