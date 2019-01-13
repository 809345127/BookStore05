package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.ShoppingCart;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

	protected void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book bookById = bookService.getBookById(bookId);

		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		cart.addBookCart(bookById);
		session.setAttribute("recentBook", bookById);
		response.sendRedirect(request.getHeader("referer"));
	}
	
	protected void clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		cart.clear();
		
		response.sendRedirect(request.getHeader("referer"));
	}
	
	protected void delCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		cart.delCartItem(bookId);
		
		response.sendRedirect(request.getHeader("referer"));
	}
	
	protected void updateCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String countStr = request.getParameter("countStr");
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		cart.updateCartItem(bookId, countStr);
		
		//response.sendRedirect(request.getHeader("referer"));
		
		int totalCount = cart.getTotalCount();
		double totalAmount = cart.getTotalAmount();
		double amount = cart.getMap().get(bookId).getAmount();
		
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount+"");
		map.put("totalAmount", totalAmount+"");
		map.put("amount", amount+"");
		Gson gson = new Gson();
		String json = gson.toJson(map);
		System.out.println(json);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
}
