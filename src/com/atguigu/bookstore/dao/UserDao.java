package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.User;

public interface UserDao {

	/**
	 * 根据User对象中的用户名和密码从数据库中查询记录
	 * 
	 * @param user
	 *            是根据前端用户输入的用户名和密码封装的一个User对象
	 * @return User 数据库中有对应的记录 null 数据库中无此记录
	 */
	public User checkUserNameAndPassword(User user);

	/**
	 * 根据User对象中的用户名从数据库中查询记录
	 * 
	 * @param user
	 *            是根据前端用户输入的用户名封装的一个User对象
	 * @return true 数据库中有对应的记录 false 数据库中无此记录
	 */
	public boolean checkUserName(User user);

	/**
	 * 将User中的信息插入到数据库中
	 * 
	 * @param user
	 *            根据前端用户输入的用户名、密码、邮箱封装的User对象
	 */
	public void saveUser(User user);
}
