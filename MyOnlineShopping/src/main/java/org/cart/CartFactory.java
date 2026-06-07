package org.cart;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CartFactory {

	public static ProductCart getInstance(String cartClass) {

		try {
			Class<?> refCart = Class.forName(cartClass);

			Constructor<?> ctor = refCart.getConstructor();

			return (ProductCart) ctor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
