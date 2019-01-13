package com.atguigu.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	// 创建数据库连接池
	private static DataSource dataSource = new ComboPooledDataSource();
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

	// 获取连接
	public static Connection getConnection() {
		Connection connection = threadLocal.get();
		if (connection == null) {
			try {
				connection = dataSource.getConnection();
				threadLocal.set(connection); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return connection;
	}

	// 释放连接
/*	public static void releaseConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}*/
	public static void releaseConnection() {
		Connection connection = threadLocal.get();
		if (connection != null) {
			try {
				connection.close();
				threadLocal.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
