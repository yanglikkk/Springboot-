package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.util.HttpUtil;

public class SessionInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("login").equals("login")) {
			return true;
		}
		String token = request.getParameter("token");
		if(token != null) {
			String requestUrl = "http://www.sso.com:8090/checkToken";
			String content = "token=" + token;
			String result = HttpUtil.sendGet(requestUrl, content);
			if("correct".equals(result)) {
				request.getSession().setAttribute("login", "login");
				return true;
			}
		}
		response.sendRedirect("http://www.sso.com:8090/prelogin?url=www.user.com:8081/user/wel");
		
		
		return false;
	}

}
