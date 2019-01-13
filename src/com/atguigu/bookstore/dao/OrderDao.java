package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.Order;

public interface OrderDao {
	/**
	 * 向数据库保存订单对象的方法
	 * @param order
	 */
	public void saveOrder(Order order);
	
	
}
