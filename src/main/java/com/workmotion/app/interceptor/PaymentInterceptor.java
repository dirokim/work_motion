package com.workmotion.app.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class PaymentInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object ob = session.getAttribute("check");
		if(ob != null) {
			return true;
		}else {
			request.setAttribute("msg", "구독이 필요한 서비스입니다");
			request.setAttribute("path", "/product/list");
			request.setAttribute("page", "commons/result");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			requestDispatcher.forward(request, response);
			return false;
		}
	}

}
