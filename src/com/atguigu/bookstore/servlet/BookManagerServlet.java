package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

/**
 * 后台管理图书的Servlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

	// 获取所有图书
//	protected void getBooks(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// 调用bookService中获取所有图书的方法
//		List<Book> books = bookService.getBooks();
//		// 将books放到request域中
//		request.setAttribute("books", books);
//		// 转发到显示所有图书的页面
//		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
//	}

	// 添加图书
	// protected void saveBook(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// // 获取图书信息
	// String title = request.getParameter("title");
	// String author = request.getParameter("author");
	// String price = request.getParameter("price");
	// String sales = request.getParameter("sales");
	// String stock = request.getParameter("stock");
	// // 封装Book对象
	// Book book = new Book(null, title, author, Double.parseDouble(price),
	// Integer.parseInt(sales),
	// Integer.parseInt(stock));
	// // 调用bookService中保存图书的方法
	// bookService.saveBook(book);
	// // 方式一：重定向到BookManagerServlet中的getBooks方法
	// response.sendRedirect(request.getContextPath() +
	// "/BookManagerServlet?method=getBooks");
	// // 方式二：直接调用getBooks方法重新查一次数据库
	// // getBooks(request, response);
	// }

	// 删除图书
	protected void deleteBookById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 调用bookService的删除图书的方法
		bookService.deleteBookById(bookId);
		// 重定向到BookManagerServlet中的getBooks方法
		response.sendRedirect(request.getContextPath() + "/BookManagerServlet?method=getPageBooks");
	}

	// 获取图书
	protected void getBookById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 调用bookService的获取图书的方法
		Book book = bookService.getBookById(bookId);
		// 将book放到request域中
		request.setAttribute("book", book);
		// 转发到修改图书的页面
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}

	// 添加或更新图书
	protected void addOrUpdateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 获取修改之后的图书的信息
		// 获取图书信息
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		String sales = request.getParameter("sales");
		String stock = request.getParameter("stock");
		// 根据图书的id是否是空串开判断是在添加还是在更新
		if ("".equals(bookId)) {
			// 证明在添加图书
			// 封装Book对象
			Book book = new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales),
					Integer.parseInt(stock));
			// 调用bookService中保存图书的方法
			bookService.saveBook(book);
		} else {
			// 证明在更新图书
			// 封装Book对象
			Book book = new Book(Integer.parseInt(bookId), title, author, Double.parseDouble(price),
					Integer.parseInt(sales), Integer.parseInt(stock));
			// 调用bookService的更新图书的方法
			bookService.updateBook(book);
		}
		// 重定向到BookManagerServlet中的getBooks方法
		response.sendRedirect(request.getContextPath() + "/BookManagerServlet?method=getPageBooks");
	}

	// 获取带分页的图书
	protected void getPageBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取页码
		String pageNo = request.getParameter("pageNo");
		// 调用bookService中获取带分页图书的方法
		Page<Book> pageBooks = bookService.getPageBooks(pageNo);
		// 将pageBooks放到request域中
		request.setAttribute("page", pageBooks);
		// 转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
}
