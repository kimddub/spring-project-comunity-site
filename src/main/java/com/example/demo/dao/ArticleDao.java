package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Article;

@Mapper
public interface ArticleDao {
	public List<Article> getList(Map<String, Object> args);

	public Article getOne(Map<String, Object> args);

	public void add(Map<String, Object> args);
	
	public void update(Map<String, Object> args);
	
	public void delete(Map<String, Object> args);
	
	public void deleteReply(Map<String, Object> args);

}