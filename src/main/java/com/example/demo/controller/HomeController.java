package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@RequestMapping("/")
	public String showMain() {
		return "redirect:article/list";
	}
	
	@RequestMapping("/error/no_auth")
	public String showError() {
		return "error/no_auth";
	}
	
}
