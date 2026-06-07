package org.products;

import java.sql.Connection;
import java.util.Iterator;

public interface ProductsDao {

	public Iterator<RegisteredProducts> getAllProductsByCategory(String productId) throws ProductsException;

	public Iterator<RegisteredProducts> getAllProducts(Integer categoryId) throws ProductsException;
	
	public Boolean addProduct(RegisteredProducts regProd);

}
