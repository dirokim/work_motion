<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/resources/js/board/boardStyle.css" rel="stylesheet" />

<%-- <c:if test="${empty member}">
<script>
alert("로그인 하셔야 이용 가능합니다")
location.href="/member/login"
</script>
</c:if>
<c:if test="${empty check}">
<script>
alert("구독 하셔야 이용가능합니다")
location.href="/product/list"
</script>
</c:if> --%>
<div style="height: 100px; text-align: center;" >
<h1 style="height: 50px;"></h1>
<h2>회사 관리</h2>
</div>
<table class="table table-hover mt-3" style="width: 100%; border: 1; margin-left: 70px; width: 90%;">
	<thead>
		<tr id="tr1">
			<th scope="col">회사번호</th>
			<th scope="col">회사이름</th>
			<th scope="col">회사정보</th>
			<th scope="col">회사대표이름</th>
			<th scope="col">회사전화번호</th>
			<th scope="col">회사개설일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="dto">
			<tr>
				<td class="tr">${dto.id}</td>
				<td class="listTitle"><a class="hihello" href="./detail?id=${dto.id}">${dto.name}</a></td>
				<td class="tr">${dto.info}</td>
				<td class="tr">${dto.owner}</td>
				<td class="tr">${dto.phone_num}</td>
				<td class="tr">${dto.create_dt}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:if test="${pager.lastNum > 1}">
	<nav aria-label="..." class="d-flex justify-content-center" style="width: 100%; border: 1; margin-left: 70px; width: 90%;">
		<ul class="pagination">

			<!-- 이전@@@@@@@@@@ -->
			<c:if test="${!pager.start}">
				<li class="page-item"><a class="page-link"
					href="./list?search=${pager.search}&page=${pager.lastNum+1}">Previous</a>
				</li>
			</c:if>


			<!-- 번호@@@@@@@@@@@@ -->
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li class="page-item"><a class="page-link"
					href="./list?page=${i}&search=${pager.search}">${i}</a></li>
			</c:forEach>

			<!-- 다음@@@@@@@@@@@@@@@ -->
			<c:if test="${!pager.last}">
				<li class="page-item"><a class="page-link"
					href="./list?page=${pager.lastNum+1}&search=${pager.search}">Next</a></li>
			</c:if>
		</ul>
	</nav>
</c:if>


<!-- 검색@@@@@@@@@@@@@@@@ -->
<form action="./list" class="d-flex justify-content-center">
	<div class="input-group mb-3" style=" border: 1; margin-left: 70px; width:30vh">
		<input type="text" width="200px" name="search"
			aria-label="Text input with dropdown button">
		<button class="btn btn-outline-secondary" type="submit">찾기</button>
	</div>
</form>

<a href="./updateList" class="d-flex justify-content-end"><button type="button" class="btn btn-primary" style=" margin-left: 70px;">수정하기</button></a>
<script src="/resources/js/board/list.js"></script>

