<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.demo.dto.Article"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="pageTitle" value="게시물 작성"/>
<%@ include file="../part/head.jspf"%>

<script src="/add/add.js"></script>

<form action="./doAdd" onsubmit="formCheck(this); return false;" methfo="POST">
	<div class="table-common con">
		<table>
			<colgroup>
				<col width="80">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td>
						<input autocomplete="off" type="text" name="title" placeholder="제목을 입력하세요">
					</td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td>
						<textarea name="body" placeholder="내용을 입력하세요"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" class="btn-box">
						<input type="submit" value="작성">
						<input type="button" onclick="history.back();" value="취소">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</form>
<%@ include file="../part/foot.jspf"%>