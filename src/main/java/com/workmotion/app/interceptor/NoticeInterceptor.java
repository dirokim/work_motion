package com.workmotion.app.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.workmotion.app.member.MemberDTO;

public class NoticeInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	 HttpSession session = request.getSession();
	 MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
	 
	 
	 if(memberDTO.getRole_id() == 40 || memberDTO.getRole_id() == 30) {
		 
		 return true;
	 }else {
		 request.setAttribute("msg", "권한이 없습니다");
		 request.setAttribute("path", "/board/list");
		 request.setAttribute("page", "commons/result");
		 
		 RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		 requestDispatcher.forward(request, response);
		 return false;
	 }

	}
}
