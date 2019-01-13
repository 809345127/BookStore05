package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

	@Override
	public void saveOrder(Order order) {
		String sql = "insert into orders values(?,?,?,?,?,?)";
		update(sql, order.getId(), order.getOrderTime(), order.getTotalCount(), order.getTotalAmount(),
				order.getState(), order.getUserId());
	}

}
