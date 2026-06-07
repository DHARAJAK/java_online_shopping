package org.category;

import java.util.Iterator;

public interface CategoryDao {

	public Iterator<RegisteredCategory> getAllCategoriesData() throws CategoryException;

	public boolean addCategory(RegisteredCategory reg) throws CategoryException;

	public Iterator<String> getAllCategory() throws CategoryException;

}
