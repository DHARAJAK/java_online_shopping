package org.cart;

import java.util.Iterator;

import org.products.RegisteredProduct;

public interface Cart {

	public void addToCart(RegisteredProduct objProduct) throws CartException;

	public Iterator<RegisteredProduct> listCart() throws CartException;

	public Float cartTotal();
}
