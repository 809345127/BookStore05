package com.atguigu.bookstore.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

public class BookServiceTest {

	@Test
	public void testGetPageBooks() {
		BookService bookService = new BookServiceImpl();
		Page<Book> pageBooks = bookService.getPageBooks("1");
		List<Book> list = pageBooks.getList();
		for (Book book : list) {
			System.out.println(book);
		}
	}

}
