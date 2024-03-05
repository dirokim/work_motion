<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/resources/js/board/boardStyle.css" rel="stylesheet" />

<div style="height: 100px; text-align: center;">
	<h1 style="height: 50px;"></h1>
	<h2>${department }</h2>
</div>
<table class="table table-hover mt-3"
	style="width: 100%; border: 1; margin-left: 70px; width: 90%;">
	<thead>
		<tr id="tr1">
			<th scope="col">편집</th>
			<th scope="col">사원번호</th>
			<th scope="col">사원이름</th>
			<th scope="col">사원이메일</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<input type="hidden" id="department_id"
				value="${member.department_id}">
		</tr>
		<c:forEach items="${detail}" var="dto" varStatus="status">
			<tr>
				<input type="hidden" value="${dto.id}" class="member_id">
				<td class="tr"><input type="checkbox" class="check"></td>
				<td class="tr">${dto.id}</td>
				<td class="tr">${dto.name}</td>
				<td class="tr">${dto.email}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- 페이징@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
<c:if test="${pager.lastNum > 1}">
	<nav aria-label="..." class="d-flex justify-content-center"
		style="width: 100%; border: 1; margin-left: 70px; width: 90%;">
		<ul class="pagination">

			<!-- 이전@@@@@@@@@@ -->
			<c:if test="${!pager.start}">
				<li class="page-item"><a class="page-link"
					href="./memberupdate?department_id=${member.department_id}&page=${pager.startNum-1}&search=${pager.search}">Previous</a>
				</li>
			</c:if>

			<!-- 번호@@@@@@@@@@@@ -->
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li class="page-item"><a class="page-link"
					href="./memberupdate?department_id=${member.department_id}&page=${i}&search=${pager.search}">${i}</a></li>
			</c:forEach>
			<!-- 다음@@@@@@@@@@@@@@@ -->

			<c:if test="${!pager.last}">
				<li class="page-item"><a class="page-link"
					href="./memberupdate?department_id=${member.department_id}&page=${pager.lastNum+1}&search=${pager.search}">Next</a></li>
			</c:if>
		</ul>

	</nav>
</c:if>

<!-- 검색@@@@@@@@@@@@@@@@ -->
<form action="./memberupdate" class="d-flex justify-content-center">
	<div class="input-group mb-3"
		style="border: 1; margin-left: 70px; width: 30vh">
		<input type="text" width="200px" name="search"
			aria-label="Text input with dropdown button"> <input
			type="hidden" value="${member.department_id}" name="department_id">
		<button class="btn btn-outline-secondary" type="submit">찾기</button>
	</div>
</form>

<div  class="d-flex justify-content-end">
	<a href="./memberList?department_id=${member.department_id}"><button
			type="button" class="btn btn-success" style="margin-left: 70px;">사원
			추가하기</button></a>
	<button type="button" id="deletebtn" class="btn btn-danger">사원
		삭제하기</button>
</div>
<script src="/resources/departmentjs/deletemember.js"></script>