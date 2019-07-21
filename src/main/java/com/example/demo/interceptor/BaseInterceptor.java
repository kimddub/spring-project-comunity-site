package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("BaseInterceptor")
public class BaseInterceptor implements HandlerInterceptor {
	@Autowired
	MemberService memberService;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginMemberId") == null) {
			
			request.setAttribute("isLogined", false);
			request.setAttribute("loginMemberId", 0);
			request.setAttribute("loginMemberLoginId", "");
			request.setAttribute("loginMember", null);
			
		} else {
			long loginMemverId = (long)session.getAttribute("loginMemberId");
			Member member = memberService.getMember(loginMemverId);
			
			request.setAttribute("isLogined", true);
			request.setAttribute("loginMemberId", loginMemverId);
			request.setAttribute("loginMemberLoginId", member.getLoginId());
			request.setAttribute("loginMember", member);
			
		}
		
		return true;
		// return false; 하면 컨트롤러 진입하지 않음!
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 컨트롤러 진입 후 화면단 진입 전에 사용 가능
    	//log.info("Interceptor > postHandle");
    	
    	//System.out.println("Interceptor > postHandle");
    }
    
}
