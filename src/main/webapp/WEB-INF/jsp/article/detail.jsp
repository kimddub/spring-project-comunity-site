<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.demo.dto.Article"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="pageTitle" value="게시물 상세" />
<%@ include file="../part/head.jspf"%>

<link rel="stylesheet" href="/detail/detail.css" />
 
<script>
	var articleId = parseInt('${article.id}');
	var articleMemberId = parseInt('${article.memberId}');
	var sessionId = parseInt('${loginMemberId}');
</script>

<script src="/detail/detail.js"></script>

<div class="article-detail table-common con">
	<table>
		<colgroup>
			<col width="80">
		</colgroup>
		<tbody>
			<tr>
				<th>ID</th>
				<td><c:out value="${article.id}" /></td>
			</tr>
			<tr>
				<th>날짜</th>
				<td><c:out value="${article.regDate}" /></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><c:out value="${article.title}" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><c:out value="${article.body}" /></td>
			</tr>
		</tbody>
	</table>
</div>

<section class="reply con">
	<h2>
		댓글 작성
		</h1>

		<div class="reply-form">
			<form onsubmit="Article__addReply(this); return false;" method="POST">
				<div>
					<c:if test="${!isLogined}">
						<textarea readonly="readonly" placeholder="로그인 후 이용 가능합니다."></textarea>
					</c:if>
					<c:if test="${isLogined}">
						<textarea name="body" placeholder="욕설은 자제해주세요."></textarea>
						<input type="submit" value="작성">
					</c:if>
				</div>
			</form>
		</div>

		<div class="reply-list">
			<div class="table-common con">

				<table>
					<colgroup>
						<col width="80">
						<col width="180">
						<col>
						<col width="100">
					</colgroup>
					<thead>
						<tr>
							<th>ID</th>
							<th>등록날짜</th>
							<th>내용</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>

						<%--
						<c:forEach var="reply" items="${replies}">
							<tr>
								<td><c:out value="${reply.id}" /></td>
								<td><c:out value="${reply.regDate}" /></td>
								<td><c:out value="${reply.body}" /></td>
								<td></td>
							</tr>
						</c:forEach>
						 --%>
					</tbody>
				</table>
			</div>
		</div>
</section>
<%@ include file="../part/foot.jspf"%>