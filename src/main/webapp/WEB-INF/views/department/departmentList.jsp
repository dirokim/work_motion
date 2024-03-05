<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/resources/js/board/boardStyle.css" rel="stylesheet" />

<div style="height: 100px; text-align: center;" >
<h1 style="height: 50px;"></h1>
<h2>부서관리</h2>
</div>
<table class="table table-hover mt-3" style="width: 100%; border: 1; margin-left: 70px; width: 90%;">
	<thead>
		<tr id="tr1">
			<th>편집</th>
			<th scope="col">부서번호</th>
			<th scope="col">부서이름</th>
			<th scope="col">부서전화번호</th>
			<th>수정</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="dto">
			<tr>
				<td class="tr"><input type="hidden" value="${dto.id}" name="id" class="id">
					<input type="checkbox" class="checks"></td>
				<th class="tr">${dto.id}</th>
				<td class="listTitle"><a class="hihello" href="./departmentDetail?id=${dto.id}&name=${dto.name}">${dto.name}</a></td>
				<td class="tr">${dto.phone_num}</td>
				<td class="tr"><a href="./departmentUpdate?id=${dto.id}"><button
							type="button" class="btn btn-primary">부서수정</button></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:if test="${pager.lastNum > 1}">
<nav aria-label="..." class="d-flex justify-content-center" style="width: 100%; border: 1; margin-left: 70px; width: 90%;">
	<ul class="pagination">

		<!-- 이전@@@@@@@@@@ -->
		<c:if test="${!pager.start} ">
			<li class="page-item"><a class="page-link"
				href="./departmentList?page=${pager.startNum-1}&search=${pager.search}">Previous</a></li>
		</c:if>

		<!-- 번호@@@@@@@@@@@@ -->
		<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li class="page-item"><a class="page-link"
					href="./departmentList?page=${i}&search=${pager.search}">${i}</a></li>
		</c:forEach>
		<!-- 다음@@@@@@@@@@@@@@@ -->

		<c:if test="${!pager.last}">
			<li class="page-item"><a class="page-link"
				href="./departmentList?page=${pager.lastNum+1}&search=${pager.search}">Next</a></li>
		</c:if>
	</ul>
</nav>
</c:if>

<!-- 검색부분@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
<form action="./departmentList" class="d-flex justify-content-center">
	<div class="input-group mb-3" style=" border: 1; margin-left: 70px; width:30vh">
		<input type="text" width="200px" name="search"
			aria-label="Text input with dropdown button">
		<button class="btn btn-outline-secondary" type="submit">찾기</button>
	</div>
</form>


<!-- 검색부분@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
<div class="d-flex justify-content-end">
<a href="./departmentCreate" ><button type="button"  style=" margin-left: 70px;"
		class="btn btn-success">부서 추가</button></a>
<button  type="button" id="deleteDepartment" class="btn btn-danger" >부서
	삭제</button>
	</div>
<script src="/resources/js/board/list.js"></script>
<script src="/resources/departmentjs/deletedepartment.js"></script>
