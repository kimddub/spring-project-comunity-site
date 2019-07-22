package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("AuthInterceptor")
	private HandlerInterceptor authInterceptor;

	@Autowired
	@Qualifier("BaseInterceptor")
	private HandlerInterceptor baseInterceptor;

	@Autowired
	@Qualifier("PathInterceptor")
	private HandlerInterceptor pathInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor).addPathPatterns("/article/**").addPathPatterns("/member/myPage")
				.excludePathPatterns("/article/list");
		registry.addInterceptor(pathInterceptor).addPathPatterns("/member/**").excludePathPatterns("/member/doLogout")
				.excludePathPatterns("/member/myPage").excludePathPatterns("/member/doModifyInfo")
				.excludePathPatterns("/member/doRetirement");
		registry.addInterceptor(baseInterceptor); // 이렇게만 해도 아래처럼 작동됨
		// registry.addInterceptor(specialInterceptor).addPathPatterns("/**");
	}

}
