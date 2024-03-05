package com.workmotion.app.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.workmotion.app.member.MemberDTO;

@Component
public class HrInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			HttpSession session = request.getSession();
			MemberDTO m = (MemberDTO)session.getAttribute("member");
			if(m.getRole_id()==30L ||m.getRole_id()==40L||m.getRole_id()==50L) {
				return true;
			}else {
				request.setAttribute("msg","권한이 필요합니다.");
				request.setAttribute("path","/");
				request.setAttribute("page","commons/result");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
				rd.forward(request, response);
				return false;
			}
			
	}
	
	
}
