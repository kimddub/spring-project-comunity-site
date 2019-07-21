package com.example.demo.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.groovy.util.Maps;

import com.example.demo.dto.Member;

public interface MemberService {
	
	public Member getMember(long id) ;

	public Member getMember(Map <String,Object> args) ;
	
	public String login(Map<String, Object> param,HttpSession session);

	public boolean sessionCheck(HttpServletRequest request);

	public String join(Map<String, Object> param);

	public void logout(HttpSession session);

	public String modify(Map<String, Object> args,HttpServletRequest request);

	public String delete(Map<String, Object> args,HttpServletRequest request, HttpSession session);
	
}
