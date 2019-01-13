package com.atguigu.bookstore.test;

import org.junit.Test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.ShoppingCart;

public class CartTest {

	@Test
	public void test() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.addBookCart(new Book(1, "西游记", "吴承恩", 88.03, 100, 100));
		shoppingCart.addBookCart(new Book(1, "西游记", "吴承恩", 88.03, 100, 100));
		shoppingCart.addBookCart(new Book(3, "西游记3", "吴承恩3", 88.03, 100, 100));
		shoppingCart.addBookCart(new Book(4, "西游记4", "吴承恩4", 88.02, 100, 100));
		shoppingCart.addBookCart(new Book(8, "西游记8", "吴承恩8", 88.00, 100, 100));
		shoppingCart.addBookCart(new Book(9, "西游记9", "吴承恩9", 88.00, 100, 100));
		shoppingCart.addBookCart(new Book(2, "西游记2", "吴承恩2", 88.00, 100, 100));
		System.out.println(shoppingCart);
	}
	
	@Test
	public void testDouble() {
		Double a = 0.2333333333;
		String bString = a.toString();
		System.out.println(bString);
	}

}
