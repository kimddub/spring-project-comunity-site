package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.Member;
import com.example.demo.dto.Reply;
import com.example.demo.service.ArticleService;
import com.example.demo.service.MemberService;
import com.example.demo.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ReplyService replyService;

	@RequestMapping("article/list")
	public String showList(Model model, @RequestParam Map<String, Object> param,HttpServletRequest request) {
		
		param.put("extra__repliesCount", true);
		
		List<Article> list = articleService.getList(param);
		
		if ((boolean)request.getAttribute("isLogined")) {
			model.addAttribute("loginMemberName", ((Member)request.getAttribute("loginMember")).getName());
		}
		
		model.addAttribute("list", list);
		
		return "article/list";
	}

	@RequestMapping("/article/detail")
	public String showDetail(@RequestParam(value = "id", defaultValue = "0") int id, Model model) {
		Article article = articleService.getOne(Maps.of("id", id));

		model.addAttribute("article", article);
		
		return "article/detail";	
	}
	
	@RequestMapping("/article/getReplies")
	@ResponseBody
	public List getReplies(@RequestParam Map<String,Object> param) {
		
		List<Reply> replies = replyService.getList(param);
		
		System.out.println("댓글 : " + replies.size());
		
		int from = Integer.parseInt((String)param.get("from"));
		
		return replies;	
	}
	
	
	
	@RequestMapping("/article/add")
	public String showAdd(HttpSession session,Model model) {
		return "/article/add";
	}
	
	@RequestMapping("/article/doAdd")
	@ResponseBody
	public String doAdd(@RequestParam Map<String, Object> param,HttpServletRequest request) {
		
		long newId = articleService.add(param, request);

		String html = "<script>";
		html += "alert('게시물이 추가되었습니다.');";
		html += "location.replace('./detail?id=" + newId + "');";
		html += "</script>";
		return html;
	}
	
	@RequestMapping("/article/modify")
	public String showModify(Model model,long id) {
		
		Article article = articleService.getOne(Maps.of("id", id));

		model.addAttribute("article", article);
		
		return "/article/modify";
	}
	
	@RequestMapping("/article/doModify")
	@ResponseBody
	public String doModify(long boardId, String title, String body) {

		Map<String,Object> args = new HashMap<>();
		
		//BigInteger boardId = BigInteger.valueOf(0);
		long memberId = 0;
		
		args.put("title",title);
		args.put("body",body);
		args.put("memberId",memberId);
		args.put("boardId",boardId);
		args.put("id",boardId);
		
		articleService.update(args);

		String html = "<script>";
		html += "alert('게시물이 수정되었습니다.');";
		html += "location.replace('./detail?id=" + boardId + "');";
		html += "</script>";
		return html;
	}
	
	@RequestMapping("/article/doDelete")
	@ResponseBody
	public String doDelete(long id, HttpServletRequest request) {

		String html = "<script>";
		
		long memberId = articleService.getOne(Maps.of("id",id)).getMemberId();
		
		if ( memberId != (long)request.getAttribute("loginMemberId") ) {
			html += "alert('권한이 없습니다.');";
			html += "history.back();";
			html += "</script>";
			
			return html;
		}

		Map<String,Object> args = new HashMap<>();
		
		args.put("id",id);
		
		articleService.delete(args);
		articleService.deleteReply(args);

		html += "alert('게시물이 삭제되었습니다.');";
		html += "location.replace('./list');";
		html += "</script>";
		return html;
	}
	
	@RequestMapping("/article/doAddReply")
	@ResponseBody
	public Map doAddReply(@RequestParam Map<String,Object> param, HttpServletRequest request) {

		long id = replyService.add(param,request);
		Reply reply = replyService.getOne(id);
		
		Map<String, Object> rs = new HashMap<>();
		
		rs.put("resultCode", "S-1");
		rs.put("msg", id + "번 댓글이 작성되었습니다.");
		rs.put("reply", reply);
		return rs;
	}
	
	@RequestMapping("/article/doDeleteReply")
	@ResponseBody
	public Map doDeleteReply(@RequestParam Map<String,Object> param) {

		replyService.delete(param);
		
		Map<String, Object> rs = new HashMap<>();
		
		rs.put("resultCode", "S-1");
		rs.put("msg", param.get("id") + "번 댓글이 삭제되었습니다.");
		return rs;
	}
	
	@RequestMapping("/article/doModifyReply")
	@ResponseBody
	public Map doModifyReply(@RequestParam Map<String,Object> param) {

		replyService.modify(param);
		
		Map<String, Object> rs = new HashMap<>();
		
		rs.put("resultCode", "S-1");
		rs.put("msg", param.get("id") + "번 댓글이 수정되었습니다.");
		return rs;
	}
}