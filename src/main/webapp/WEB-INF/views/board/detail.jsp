<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/resources/js/board/boardStyle.css" rel="stylesheet" />

<div class="container p-4 mt-4 mb-4">
	<div class="d-flex justify-content-between">
		<div class="mt-4">
			<h4>제목</h4>
			<h2>${detail.title}</h2>
		</div>
		<div class="d-flex align-items-end">
			<div class="d-flex align-items-center">

				<h6>작성자 :</h6>
				<h4>${detail.writer}</h4>
			</div>
		</div>
	</div>

	<div class="mb-3">
		<textarea class="w-100" style="heigth: 1000;" id="content" readonly="readonly"
			name="content" rows="25" cols="30">${detail.content}</textarea>
	</div>

<c:if test="${detail.writer eq member.name}">
	<a href="./update?id=${detail.id}">
		<button type="button" class="btn btn-primary mb-2">수정하기</button>
	</a>

	<form action="./delete" method="post">
		<input type="hidden" value="${category_ID}" name="category_ID">
		<button type="submit" class="btn btn-danger"  name="id"  value="${detail.id}">삭제하기</button>
	</form>
	</c:if>

</div>