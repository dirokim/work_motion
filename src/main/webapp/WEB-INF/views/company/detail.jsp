<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/resources/js/board/boardStyle.css" rel="stylesheet" />

<div style="height: 100px; text-align: center;">
	<h1 style="height: 50px;"></h1>
	<h2>회사 관리</h2>
</div>
<table class="table table-hover mt-3" style="width: 100%; border: 1; margin-left: 70px; width: 90%;">
	<thead>
		<tr id="tr1">
			<th scope="col">회사이름</th>
			<th scope="col">회사정보</th>
			<th scope="col">회사전화번호</th>
			<th scope="col">회사대표</th>
			<th scope="col">회사사원의수</th>
			<th scope="col">회사개설일</th>

		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="tr">${detail.name}</td>
			<td class="tr">${detail.info}</td>
			<td class="tr">${detail.phone_num}</td>
			<td class="tr">${detail.owner}</td>
			<td class="tr">${member}</td>
			<td class="tr">${detail.create_dt}</td>
		</tr>
	</tbody>
</table>

<script src="/resources/js/board/list.js"></script>
