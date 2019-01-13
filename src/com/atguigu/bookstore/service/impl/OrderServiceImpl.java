package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.CartItem;
import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.beans.ShoppingCart;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderDao orderdao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private BookDao bookdao  = new BookDaoImpl(); 

	@Override
	public String createOrder(User user, ShoppingCart cart) {
		// 生成订单号
		String orderId = System.currentTimeMillis() + "" + user.getId();
		//保存订单
		Order order = new Order(orderId, new Date(), cart.getTotalCount(), cart.getTotalAmount(), 0, user.getId());
		orderdao.saveOrder(order);

		//保存订单项
		List<CartItem> cartItemList = cart.getCartItemList();
		
		int size = cartItemList.size();
		Object[][] params = new Object[size][];
		Object[][] params2 = new Object[size][];
		for (int i = 0; i < size; i++) {
			CartItem cartItem = cartItemList.get(i);
			Book book = cartItem.getBook();
			
			params2[i] = new Object[] {cartItem.getCount(), cartItem.getAmount(), book.getTitle(), book.getAuthor(),
					book.getPrice(), book.getImgPath(), orderId};
//			OrderItem orderItem = new OrderItem(null, cartItem.getCount(), cartItem.getAmount(), book.getTitle(), book.getAuthor(),
//					book.getPrice(), book.getImgPath(), orderId);
//			orderItemDao.saveOrderItem(orderItem);
			
			
			
			//更新图书销量和库存
//			book.setSales(book.getSales()+cartItem.getCount());
//			book.setStock(book.getStock()-cartItem.getCount());
//			bookdao.updateBook(book);
			
			
			params[i] = new Object[] {book.getSales()+cartItem.getCount(),book.getStock()-cartItem.getCount(),book.getId()};
		}
		bookdao.batchUpdateSalesAndStock(params);
		orderItemDao.batchSaveOrderItem(params2);
		cart.clear();
		return orderId;
	}

}
