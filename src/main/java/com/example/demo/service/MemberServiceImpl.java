package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memberDao;
	
	public Member getMember(long id) {
		Map<String,Object> args = new HashMap<>();
		args.put("id", id);
		args.put("where__id",true);
		return memberDao.getMember(args);
	}

	public Member getMember(Map <String,Object> args) {
		return memberDao.getMember(args);
	}
	
	public boolean sessionCheck(HttpServletRequest request) {
		return request.getAttribute("loginMemberId") == null ? false : true;
	}
	
	public String join(Map<String, Object> param) {
		String script = "";

		param.put("where__loginId", true);
		Member loginMember = getMember(param);

		if (loginMember != null) {
			script += "alert('이미 있는 로그인 ID입니다.');";
			script += "history.back();";
		} else {

			memberDao.join(param);

			script += "alert('회원가입을 축하드립니다. 로그인 후 이용해주세요!');";
			script += "location.replace('../member/login');"; // 로그인 전 페이지로 돌아가기
		}

		return script;
	}

	public String login(Map<String, Object> param, HttpSession session) {
		String script = "";

		param.put("where__loginInfo", true);
		Member loginMember = getMember(param);

		if (loginMember == null) {
			script += "alert('로그인 정보를 다시 확인하세요');";
			script += "history.back();";
		} else {
			session.setAttribute("loginMemberId", loginMember.getId());
			session.setMaxInactiveInterval(30 * 60);

			script += "alert('" + loginMember.getName() + "님 환영합니다.');";
			script += "location.replace('../article/list');"; // 로그인 전 페이지로 돌아가기
		}

		return script;
	}

	public void logout(HttpSession session) {
		session.removeAttribute("loginMemberId");
	}
	
	public String modify(Map<String, Object> args,HttpServletRequest request) {
		String html = "<script>";
		
		args.put("id", request.getAttribute("loginMemberId"));
		
		// 비밀번호 불일치시 진행 X
		args.put("loginId", request.getAttribute("loginMemberLoginId"));
		args.put("where__loginInfo", true);
		Member loginMember = getMember(args);
		
		if (loginMember == null) {
			html += "alert('기존 비밀번호를 확인해주세요.');";
			html += "history.back();";
			html += "</script>";
			
			return html;
		}
		
		if (((String)args.get("passwordChange")).equals("no")) {
			// 비밀번호 변경 안할시 다른 정보만 수정
			args.put("newLoginPw", null);
			memberDao.update(args);
		} else {
			// 비밀번호까지 변경
			memberDao.update(args);
		}
		
		html += "alert('회원 정보가 성공적으로 수정되었습니다.');";
		html += "location.replace('./myPage');";
		html += "</script>";
		
		return html;
	}

	public String delete(Map<String, Object> args,HttpServletRequest request, HttpSession session) {
		String html = "<script>";
		
		args.put("id", request.getAttribute("loginMemberId"));
		args.put("loginId", request.getAttribute("loginMemberLoginId"));
		
		// 비밀번호 불일치시 진행 X
		args.put("where__loginInfo", true);
		Member loginMember = getMember(args);
		
		if (loginMember == null) {
			html += "alert('기존 비밀번호를 확인해주세요.');";
			html += "history.back();";
			html += "</script>";
			
			return html;
		}
		
		memberDao.delete(args);
		html += "alert('탈퇴되셨습니다.');";
		html += "location.replace('../article/list');";
		html += "</script>";
		
		session.removeAttribute("loginMemberId");
		
		return html;
	}
}
