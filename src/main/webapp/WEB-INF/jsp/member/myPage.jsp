<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.demo.dto.Article"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../part/head.jspf"%>
<link rel="stylesheet" href="/myPage/myPage.css" />
<script src="/myPage/myPage.js"></script>

	<form id="modify-form" action="./doModifyInfo" method="POST">
		<div class="con table-common member-info-table">
			<table>
				<colgroup>
					<col width="40%">
				</colgroup>
				<tbody>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="name" value="${loginMember.name}">
						</td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>${loginMember.loginId}</td>
					</tr>
					<tr class="password-form">
						<th>비밀번호</th>
						<td>
							<input type="password" name="loginPw" placeholder="기존 비밀번호">
							<input class="not-password-change-mode" type="button" value="비밀번호 변경" onclick="Member__chagePw();">
							<div class="password-change-form">
								<input type="hidden" name="passwordChange" value="no">
								<input type="password" name="newLoginPw" placeholder="새 비밀번호">
								<input type="button" value="취소" onclick="Member__notChagePw();">
							</div>
						</td>
					</tr>
					<tr>
						<td class="btn-box" colspan="2">
							<input type="submit" value="회원정보 수정">
							<input type="button" onclick="doRetirement();" value="회원 탈퇴">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	
	<form id="retirement-form" action="./doRetirement" method="POST">
		<input type="hidden" name="loginPw">
	</form>

</body>
</html>