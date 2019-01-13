package com.atguigu.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

/**
 * 前台管理图书的Servlet
 */
public class BookClientServlet extends BaseServlet {

	private BookService bookService = new BookServiceImpl();

/*	//获取带分页的图书信息
	protected void getPageBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取页码
		String pageNo = request.getParameter("pageNo");
		// 调用bookService中获取带分页图书的方法
		Page<Book> pageBooks = bookService.getPageBooks(pageNo);
		// 将pageBooks放到request域中
		request.setAttribute("page", pageBooks);
		// 转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);
	}*/
	//获取带分页和价格范围的图书信息
	protected void getPageBooksByPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取页码
		String pageNo = request.getParameter("pageNo");
		//获取价格范围
		String minPrice = request.getParameter("min");
		String maxPrice = request.getParameter("max");
		// 调用bookService中获取带分页及价格范围的图书的方法
		Page<Book> pageBooks = bookService.getPageBooksByPrice(pageNo, minPrice, maxPrice);
		// 将pageBooks放到request域中
		request.setAttribute("page", pageBooks);
		//将价格范围放到request域中
		request.setAttribute("min", minPrice);
		request.setAttribute("max", maxPrice);
		// 转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);
	}

}
