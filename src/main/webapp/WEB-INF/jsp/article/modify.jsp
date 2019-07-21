<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.demo.dto.Article"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="pageTitle" value="게시물 수정"/>
<%@ include file="../part/head.jspf"%>

<form action="./doModify" method="POST">
	<div class="article-detail table-common con">
		<table>
			<colgroup>
				<col width="80">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th>ID</th>
					<td><c:out value="${article.id}" /></td>
					<input type="hidden" name="boardId" value="${article.id}">
				</tr>
				<tr>
					<th>날짜</th>
					<td><c:out value="${article.regDate}" /></td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input autocomplete="off" type="text" name="title" value="${article.title}">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="body">${article.body}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="btn-box">
						<input type="submit" value="수정">
						<input type="button" onclick="history.back();" value="취소">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</form>
<%@ include file="../part/foot.jspf"%>