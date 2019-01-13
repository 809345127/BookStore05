package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;

public interface BookDao {

	/**
	 * 从数据库中查询出所有的图书
	 * 
	 * @return
	 */
	public List<Book> getBooks();

	/**
	 * 将图书插入到数据库中
	 * 
	 * @param book
	 */
	public void saveBook(Book book);

	/**
	 * 根据图书的id从数据库中将该图书删除
	 * 
	 * @param bookId
	 */
	public void deleteBookById(String bookId);

	/**
	 * 根据图书的id从数据库中将图书的信息查询出来
	 * 
	 * @param bookId
	 * @return
	 */
	public Book getBookById(String bookId);

	/**
	 * 根据传入的Book对象中的id来更新图书的信息
	 * 
	 * @param book
	 */
	public void updateBook(Book book);

	/**
	 * 获取带分页的图书信息
	 * 
	 * @param page
	 *            传入的page对象是只包含pageNo属性的page对象
	 * @return 返回的page对象中所有的属性都有
	 */
	public Page<Book> getPageBooks(Page<Book> page);

	/**
	 * 获取带分页及价格范围的图书信息
	 * 
	 * @param page
	 *            传入的page对象是只包含pageNo属性的page对象
	 * @return 返回的page对象中所有的属性都有
	 */
	public Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice, double maxPrice);
	
	/**
	 * 批量更新图书销量和库存
	 * @param params
	 */
	public void batchUpdateSalesAndStock(Object[][] params);
}
