<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/resources/js/board/boardStyle.css" rel="stylesheet" />
<table class="table">
	<thead>
		<tr>
			<th scope="col">#</th>
			<th scope="col">사원번호</th>
			<th scope="col">사원이름</th>
			<th scope="col">사원이메일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${detail}" var="dto" varStatus="status">
			<tr>
				<th scope="row">${status.count}</th>
				<td>${dto.id}</td>
				<td>${dto.name}</td>
				<td>${dto.email}</td>
			</tr>
		</c:forEach>
		
	</tbody>
</table>
<div>
<a href="./create"><button type="button" class="btn btn-success">사원추가하기</button></a>
</div>