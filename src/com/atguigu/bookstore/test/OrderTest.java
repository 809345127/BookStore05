package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;

public class OrderTest {

	OrderDao orderDao = new OrderDaoImpl();
	OrderItemDao orderItemDao = new OrderItemDaoImpl();

	@Test
	public void test() {
		Order order = new Order("46465456", new Date(), 10, 666, 0, 1);
		orderDao.saveOrder(order);
	}

	@Test
	public void test2() {
		OrderItem orderItem = new OrderItem(null, 2, 77.7, "金瓶梅", "han", 99.00, "static/img/default.jpg","46465456");
		orderItemDao.saveOrderItem(orderItem);
	}

}
