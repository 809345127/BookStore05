package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.ShoppingCart;
import com.atguigu.bookstore.beans.User;

public interface OrderService {
	public String createOrder(User user,ShoppingCart cart);
}
