package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;

public interface BookService {

	/**
	 * 获取所有图书的方法
	 * 
	 * @return
	 */
	public List<Book> getBooks();

	/**
	 * 保存图书的方法
	 * 
	 * @param book
	 */
	public void saveBook(Book book);

	/**
	 * 删除图书的方法
	 * 
	 * @param bookId
	 */
	public void deleteBookById(String bookId);

	/**
	 * 查询图书的方法
	 * 
	 * @param bookId
	 * @return
	 */
	public Book getBookById(String bookId);

	/**
	 * 更新图书的方法
	 * 
	 * @param book
	 */
	public void updateBook(Book book);

	/**
	 * 获取带分页的图书信息
	 * 
	 * @param pageNo
	 * @return
	 */
	public Page<Book> getPageBooks(String pageNo);

	/**
	 * 获取带分页及价格范围的图书信息
	 * 
	 * @param pageNo
	 * @return
	 */
	public Page<Book> getPageBooksByPrice(String pageNo, String minPrice, String maxPrice);
}
