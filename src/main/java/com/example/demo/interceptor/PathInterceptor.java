package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("PathInterceptor")
public class PathInterceptor implements HandlerInterceptor {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 요청에 대한 문자열을 UTF-8로 읽겠습니다.
		request.setCharacterEncoding("UTF-8");

		// 응답에 대한 문자열을 UTF-8로 작성하겠습니다.
		response.setCharacterEncoding("UTF-8");
		// 응답하는 파일은 최종적으로 utf-8 html문서가 될 것입니다.
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginMemberId") != null) {
			
			String script = "<script>";
			script += "location.href='/error/no_auth';";
			script += "</script>";
			 
			response.getWriter().append(script);
			return false;
			
		} 
		
		return true;
		// return false; 하면 컨트롤러 진입하지 않음!
    }
	
}
