package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ArticleDao;
import com.example.demo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("member/join")
	public String showJoin(@RequestParam Map<String, Object> param, HttpSession session) {
		return "member/join";
	}

	@RequestMapping("member/doJoin")
	@ResponseBody
	public String doJoin(@RequestParam Map<String, Object> param) {

		String html = "";
		html += "<script>";
		html += memberService.join(param);
		html += "</script>";

		return html;
	}

	@RequestMapping("member/login")
	public String showLogin(@RequestParam Map<String, Object> param, HttpSession session) {
		return "member/login";
	}

	@RequestMapping("member/doLogin")
	@ResponseBody
	public String doLogin(@RequestParam Map<String, Object> param, HttpSession session) {

		String html = "";
		html += "<script>";
		html += memberService.login(param, session);
		html += "</script>";

		return html;
	}

	@RequestMapping("member/doLogout")
	@ResponseBody
	public String doLogout(@RequestParam Map<String, Object> param, HttpSession session) {

		memberService.logout(session);

		String html = "";
		html += "<script>";
		html += "alert('로그아웃 되었습니다.');";
		html += "location.href = '../article/list';";
		html += "</script>";

		return html;
	}
	
	@RequestMapping("member/myPage")
	public String myPage(HttpSession session) {
		return "member/myPage";
	}
	
	@RequestMapping("member/doModifyInfo")
	@ResponseBody
	public String doModifyInfo(@RequestParam Map<String, Object> param, HttpServletRequest request) {
		String html = "";
		
		html = memberService.modify(param,request);
		
		return html;
	}
	
	@RequestMapping("member/doRetirement")
	@ResponseBody
	public String doRetirement(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpSession session) {
		String html = "";
		
		html = memberService.delete(param,request,session);
		
		return html;
	}
}
