<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container mt-4 p-4" >
	<div class="d-flex justify-content-center mb-3">
        <h2 class="">전자결제</h2>
    </div>
  <c:if test="${member.role_id eq 30 or member.role_id eq 40}">
	<div  class="justify-content-end d-flex">
		<form class="row g-3">
			<div class="col-auto">
				<select name=kind class="form-select"
					aria-label="Default select example">
					<option value="kind2">서류이름</option>
					<option value="kind1">서류번호</option>					
					<option value="kind3">서류번호+서류이름</option>
				</select>
			</div>
			<div class="col-auto">
				<label for="search" class="visually-hidden">Search</label>
				<input type="text" name="search" class="form-control" id="search" placeholder="search">
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary mb-3">Search</button>
				<a href="./add" class="btn btn-primary mb-3">서류추가</a>				
			</div>			
		</form>
	</div>
	</c:if>

<table class="table table-dark table-hover">
  <thead>
  	<tr>
  		<td>서류번호</td>
  		<td>서류이름</td>
  	</tr>
  </thead>
  <c:forEach items="${templeteList}" var="dto">
  	<tr>
  		<td>${dto.id}</td>
  		<td><a href="./detail?id=${dto.id}">${dto.file_nm}</a></td>
  	</tr>
  </c:forEach>  
</table>

	<nav aria-label="Page navigation example"  class="justify-content-center d-flex">
	  <ul class="pagination">
	  	<c:if test="${!pager.start}">
	    <li class="page-item">
	      <a class="page-link" href="./list?page=${pager.startNum-1}&search=${pager.search}&kind=${pager.kind}" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    </c:if>
	    
	    <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
	    		<li class="page-item"><a class="page-link" href="./list?page=${i}&search=${pager.search}&kind=${pager.kind}">${i}</a></li>
	    </c:forEach>
	    
	    <c:if test="${!pager.last}">
	    <li class="page-item">
	      <a class="page-link" href="./list?page=${pager.lastNum+1}&search=${pager.search}&kind=${pager.kind}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	    </c:if>
	  </ul>
	</nav>
	
</div>