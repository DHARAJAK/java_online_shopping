package org.cart;

import java.util.ArrayList;
import java.util.Iterator;

import org.products.RegisteredProduct;

public class ProductCart implements Cart {
	ArrayList<RegisteredProduct> cart = null;

	public ProductCart() {
		this.cart = new ArrayList<RegisteredProduct>();
	}

	@Override
	public void addToCart(RegisteredProduct objProduct) throws CartException {
		cart.add(objProduct);

	}

	@Override
	public Iterator<RegisteredProduct> listCart() throws CartException {

		return cart.iterator();
	}

	public Float cartTotal() {
		Float total = 0.0f;

		Iterator<RegisteredProduct> iter = cart.iterator();

		while (iter.hasNext()) {
			total += iter.next().getProductPrice();
		}

		return total;

	}
}
