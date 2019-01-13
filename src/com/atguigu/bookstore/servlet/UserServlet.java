package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

/**
 * 处理用户登录、注册的Servlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	

	// 处理注册的方法
	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户名、密码、邮箱
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		// 验证用户提交的验证码和session域存的是否一样
		HttpSession session = request.getSession();
		String kaptcha = (String) session.getAttribute("KAPTCHA_SESSION_KEY");
		session.removeAttribute("KAPTCHA_SESSION_KEY");
		String code = request.getParameter("code");

		if (code != null && code.equals(kaptcha)) {
			// 封装User对象
			User user = new User(null, username, password, email);
			// 调用userService的注册的方法
			boolean regist = userService.regist(user);
			if (regist) {
				// 用户名已存在，设置一个错误消息并放到request域中
				request.setAttribute("msg", "用户名已存在！");
				// 转发到注册页面
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			} else {
				// 用户名可用，将用户保存到数据库中
				userService.saveUser(user);
				// 重定向到注册成功页面
				response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
			}
		}else {
			request.setAttribute("msg", "验证码错误请重新输入");
			// 转发到注册页面
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}

	}

	// 处理登录的方法
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 封装User对象
		User user = new User(null, username, password, null);
		// 调用userService的登录的方法
		User login = userService.login(user);
		HttpSession session = request.getSession();
		if (login != null) {
			// 用户名和密码正确，重定向到登录成功页面,并保存到session域中
			session.setAttribute("user",login);
			response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
		} else {
			// 用户名或密码不正确，设置一个错误消息并放到request域中
			request.setAttribute("msg", "用户名或密码不正确！");
			// 转发到登录页面
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}
	}
	
	//处理注销的方法
	protected void loginOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	//处理ajax提交请求查询数据库用户名是否已经存在
	protected void checkUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		User user = new User(null, username, null, null);
		boolean regist = userService.regist(user);
		response.setContentType("text/html;charset=UTF-8");
		if (regist) {
			//用户名已存在
			response.getWriter().write("用户名已存在！");
		}else {
			//用户名可用
			response.getWriter().write("<font style='color:green'>用户名可用！</font>");
		}
	}

}
