<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


		직원리스트
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th></th>
					<th>이름</th>
					<th>부서</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<td><input type="checkbox" class="member_id checkbox_save"
								data-department-name="${item.department.name}" data-member-name="${item.name}"
								data-referrer-id="${item.id}"></td>
						<td>${item.name}</td>
						<td>${item.department.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

<nav aria-label="Page navigation example">
	<ul class="pagination">
		<c:if test="${!pager.start}">
			<li class="page-item"><a class="page-link approval"
				data-page="${pager.startNum-1}" data-search="${pager.search}"
				data-kind="${pager.kind}" aria-label="Previous">
				 <span class="start_referrer approval" data-start="${pager.startNum-1}" aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>

		<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
			<li class="page-item"><a class="page-link approval"
				data-page="${i}" data-search="${pager.search}"
				data-kind="${pager.kind}">${i}</a></li>
		</c:forEach>

		<c:if test="${!pager.last}">
			<li class="page-item"><a class="page-link approval"
				data-page="${pager.lastNum+1}" data-search="${pager.search}"
				data-kind="${pager.kind}" aria-label="Next">
				<span class="last_referrer approval" data-last="${pager.lastNum+1}" aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
	</ul>
</nav>

		<div>
			<form class="row g-3" id="referrer_search">
				<div class="col-auto">
					<select name=kind id="approvla_kind" class="form-select" aria-label="Default select example">
						<option value="kind2">이름</option>
						<option value="kind1">부서</option>
					</select>
				</div>
				<div class="col-auto">
					<label for="search" class="visually-hidden">검색</label> <input type="text" name="search"
						class="form-control" id="approval_search" placeholder="search">
					<button type="button" class="btn btn-primary mb-3 ref-btn">Search</button>
				</div>
			</form>
		</div>