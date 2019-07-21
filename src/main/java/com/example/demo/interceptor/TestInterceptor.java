package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("TestInterceptor")
public class TestInterceptor implements HandlerInterceptor {

	 @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//log.info("Interceptor > preHandle");
		 
		System.out.println("Interceptor > preHandle");
		return true;
		// return false; 하면 컨트롤러 진입하지 않음!
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 컨트롤러 진입 후 화면단 진입 전에 사용 가능
    	//log.info("Interceptor > postHandle");
    	
    	System.out.println("Interceptor > postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
    	// 컨트롤러, 화면 진입 후 랜더링 마지막에 사용 가능
    	//log.info("Interceptor > afterCompletion" );
    	
    	System.out.println("Interceptor > afterCompletion");
    }
	
    /*
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) {
		// 비동기 요청 시 PostHandle와 afterCompletion메서드를 수행하지 않고 이 메서드를 수행
	}
	*/
}
