package com.example.demo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.dto.Article;

public interface ArticleService {
	public List<Article> getList(Map<String, Object> args);

	public Article getOne(Map<String, Object> args);

	public long add(Map<String, Object> args, HttpServletRequest request);

	public void update(Map<String, Object> args);
	
	public void delete(Map<String, Object> args);
	
	public void deleteReply(Map<String, Object> args);


}