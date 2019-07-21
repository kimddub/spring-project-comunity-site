package com.example.demo.dto;

import java.util.Map;

import org.springframework.web.util.HtmlUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Member {
	private long id;
	private String regDate;
	private String loginId;
	private String loginPw;
	private String name;
}