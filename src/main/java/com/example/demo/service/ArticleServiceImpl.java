package com.example.demo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.Member;
import com.example.demo.util.CUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public List<Article> getList(Map<String, Object> args) {
		if (args.containsKey("extra__repliesCount") && (boolean) args.containsKey("extra__repliesCount") == true) {
			args.put("leftJoin__articleReply", true);
			args.put("groupBy__articleId", true);
		}

		return articleDao.getList(args);
	}

	public Article getOne(Map<String, Object> args) {
		return articleDao.getOne(args);
	}

	public long add(Map<String, Object> args, HttpServletRequest request) {
		
		long boardId = 0;
		long memberId = (long)request.getAttribute("loginMemberId");
		
		args.put("boardId",boardId);
		args.put("memberId",memberId);
		
		articleDao.add(args);

		return CUtil.getAsLong(args.get("id"));
	}

	public void update(Map<String, Object> args) {
		articleDao.update(args);
	}
	
	public void delete(Map<String, Object> args) {
		articleDao.delete(args);
	}
	
	public void deleteReply(Map<String, Object> args) {
		articleDao.deleteReply(args);
	}
}