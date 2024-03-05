<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="./update" method="post">
	<input type="hidden" name="id" value="${detail.id}">
	<div class="mb-3">
		<label for="name" class="form-label">회사이름</label> <input
			value="${detail.name}" name="name" type="text" class="form-control"
			id="name">
	</div>

	<div class="mb-3">
		<label for="owner" class="form-label">대표이름</label> <input
			value="${detail.owner}" name="owner" type="text" class="form-control"
			id="owner">
	</div>

	<div class="mb-3">
		<label for="info" class="form-label">회사정보</label> <input
			value="${detail.info}" name="info" type="text" class="form-control"
			id="info">
	</div>

	<div class="mb-3">
		<label for="phone_num" class="form-label">회사전화번호</label> <input
			value="${detail.phone_num}" name="phone_num" type="text"
			class="form-control" id="phone_num">
	</div>
	<button type="submit" class="btn btn-primary">수정하기</button>
</form>

