package com.workmotion.app.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.workmotion.app.member.MemberDTO;

@Component
public class MemberInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			HttpSession session = request.getSession();
			MemberDTO m = (MemberDTO)session.getAttribute("member");
			if(m != null) {  //로그인 여부
				if(m.getRole_id()== 20L||m.getRole_id()==30L||m.getRole_id()==40L||m.getRole_id()==50L) {
					return true; 				
				}else {				//권한 승인 받지 못한 회원	
					request.setAttribute("msg","회원 승인이 안되었습니다.");
					request.setAttribute("path","/member/login");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
					rd.forward(request, response);
					return false;
				}
			}else {
				request.setAttribute("msg","로그인이 필요한 서비스 입니다");
				request.setAttribute("path","/member/login");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
				rd.forward(request, response);
				return false;
			}
	}

}
