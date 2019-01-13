package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.OrderItem;

public interface OrderItemDao {
	public void saveOrderItem(OrderItem orderItem);
	
	public void batchSaveOrderItem(Object[][] params);
}
