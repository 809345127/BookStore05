package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 专门用来被继承的Servlet
 * @author HanYanBing
 *
 */
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置字符集必须在第一次获取请求参数之前
//		request.setCharacterEncoding("UTF-8");
		// 获取method请求参数
		//1.获取要调用的方法的方法名
		String methodName = request.getParameter("method");
//		if ("login".equals(methodName)) {
//			// 证明在登录，调用login方法处理用户的请求
//			login(request, response);
//		} else if ("regist".equals(methodName)) {
//			// 证明在注册，调用regist方法处理用户的请求
//			regist(request, response);
//		}
		try {
			/*
			 * 2.获取方法对象
			 * getDeclaredMethod()方法中需要传入两个参数
			 * 	第一个参数是要调用的方法的方法名
			 * 	第二个是要调用的方法中需要传入的参数的类型
			 */
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			/*
			 * 3.调用方法
			 * invoke()方法中也需要传入两个参数
			 * 	第一个参数是要调用那个对象的方法
			 * 	第二个参数是要调用的方法需要传入的参数
			 */
			
			method.invoke(this, request,response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
