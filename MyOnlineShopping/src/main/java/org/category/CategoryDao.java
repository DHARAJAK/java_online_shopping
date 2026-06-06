package org.category;

import java.util.Iterator;

public interface CategoryDao {

	public Iterator<RegisteredCategory> getAllCategories() throws CategoryException;

	public boolean addCategory(RegisteredCategory reg) throws CategoryException;

}
