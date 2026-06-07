package org.products;

import java.sql.Connection;
import java.util.Iterator;

public interface ProductsDao {

	public Iterator<RegisteredProduct> getAllProductsByCategory(String productId) throws ProductsException;

	public Iterator<RegisteredProduct> getAllProducts(Integer categoryId) throws ProductsException;
	
	public Boolean addProduct(RegisteredProduct regProd);

}
