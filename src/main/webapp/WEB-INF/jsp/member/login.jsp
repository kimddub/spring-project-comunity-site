<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.demo.dto.Article"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="pageTitle" value="로그인"/>
<%@ include file="../part/head.jspf"%>
<script src="/login/login.js"></script>

<section class="member-form-box">
	<form action="./doLogin" method="POST" onsubmit="formCheck(this); return false;" class="con">
		<div class="member-form">
			<div>
				<input type="text" name="loginId" placeholder="로그인 ID">
			</div>
		
			<div>
				<input type="password" name="loginPw" placeholder="로그인 PW">
			</div>
			
			<div class="member-form-btn">
				<input type="submit" value="로그인">
				<input type="button" onclick="history.back();" value="취소">
			</div>
		</div>
	</form>
</section>

<%@ include file="../part/foot.jspf"%>