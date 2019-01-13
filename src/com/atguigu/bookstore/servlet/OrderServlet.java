package com.atguigu.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.beans.ShoppingCart;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 保存订单的servlet
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	OrderService orderService = new OrderServiceImpl();
	
	protected void checkOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		String orderId = orderService.createOrder(user,cart);
		session.setAttribute("orderId", orderId);
		response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
	}
}
