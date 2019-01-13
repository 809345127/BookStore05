package com.atguigu.bookstore.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class BookDaoTest {

	BookDao bookDao = new BookDaoImpl();
	
	@Test
	public void testGetBooks() {
		List<Book> books = bookDao.getBooks();
		for (Book book : books) {
			System.out.println(book);
		}
	}
	
	@Test
	public void testSaveBook(){
		Book book = new Book(null, "西游记", "吴承恩", 88.00, 100, 100);
		bookDao.saveBook(book);
	}

	@Test
	public void testDeleteBookById(){
		bookDao.deleteBookById("32");
	}
	@Test
	public void testgetBookById(){
		Book bookById = bookDao.getBookById("1");
		System.out.println(bookById);
	}
	@Test
	public void testupdateBook(){
		Book book = new Book(38, "不要胡叫", "韩总", 66.00, 1000000, 1);
		bookDao.updateBook(book);
	}
	
	@Test
	public void testGetPageBooks(){
		Page<Book> page = new Page<>();
		//给page对象设置一个页码
		page.setPageNo(1);
		Page<Book> pageBooks = bookDao.getPageBooks(page);
		System.out.println("当前页是："+pageBooks.getPageNo());
		System.out.println("总页数是："+pageBooks.getTotalPageNo());
		System.out.println("总记录数是："+pageBooks.getTotalRecord());
		System.out.println("集合中的图书有：");
		List<Book> list = pageBooks.getList();
		for (Book book : list) {
			System.out.println(book);
		}
	}
	@Test
	public void testGetPageBooksByPrice(){
		Page<Book> page = new Page<>();
		//给page对象设置一个页码
		page.setPageNo(2);
		Page<Book> pageBooks = bookDao.getPageBooksByPrice(page, 10.00, 30.00);
		System.out.println("当前页是："+pageBooks.getPageNo());
		System.out.println("总页数是："+pageBooks.getTotalPageNo());
		System.out.println("总记录数是："+pageBooks.getTotalRecord());
		System.out.println("集合中的图书有：");
		List<Book> list = pageBooks.getList();
		for (Book book : list) {
			System.out.println(book);
		}
	}
	
	@Test
	public void testBatchUpdateSalesAndStock() {
		Object[][] params = new Object[3][];
		params[0] = new Object[] {150,100,2};
		params[1] = new Object[] {150,100,3};
		params[2] = new Object[] {150,100,4};
		bookDao.batchUpdateSalesAndStock(params );
	}
}
