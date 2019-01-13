package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

	@Override
	public void saveOrderItem(OrderItem orderItem) {
		String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
		update(sql, orderItem.getCount(), orderItem.getAmount(), orderItem.getTitle(), orderItem.getAuthor(),
				orderItem.getPrice(), orderItem.getImgPath(), orderItem.getOrderId());
	}

	@Override
	public void batchSaveOrderItem(Object[][] params) {
		String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
		batchUpdate(sql, params);
	}

}
