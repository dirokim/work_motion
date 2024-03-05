<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div class="container mt-4 p-4">
        <div class="row">
            <div class="col">
                <div class="card shadow-lg border-0 rounded-lg ">
                    <div class="card-header"><h3 class="text-center font-weight-light my-4">${dto.name}</h3></div>
                    <c:if test="${not empty dto.avatar}">
                            <div class="m-auto mt-3">
                                <img  src="${dto.avatar.name}" class="img-thumbnail" width="200px">
                            </div>
                        
                    </c:if>
                    <div class="card-body">

            			


                        <form action="/hr/update" method="post" id="hrFrm">
          
                            <div class="form-floating mb-3">
                                <input class="form-control" id="memberid" type="text" name="id" value="${dto.id}" placeholder="id" />
                                <label for="id">사원 번호</label>
                            </div>
     
          						<div class="form-floating mb-3">
                                <input class="form-control" id="name" type="text" name="name" value="${dto.name}" placeholder="name" />
                                <label for="name">Name</label>
                            </div>
     
     						<div class="form-floating mb-3">
                                <input class="form-control" id="email" type="text" name="email" value="${dto.email}" placeholder="name@example.com" />
                                <label for="email">Email</label>
                            </div>
						
						<c:if test="${not empty department}">
						<div class="form-floating mb-3">
								<select class="form-select"  name="department_id" aria-label="Default select example">
								 <option selected>부서 변경</option>
								<c:forEach items="${department}" var="d"> 
								<option value="${d.id}">${d.name}</option>		
								</c:forEach>
							</select>
						</div>
						</c:if>
						<c:if test="${not empty position}">
						<div class="form-floating mb-3">
								<select class="form-select"  name="position_id" aria-label="Default select example">
								 <option selected>직위 변경</option>
								<c:forEach items="${position}" var="p"> 
								<option value="${p.id}">${p.name}</option>		
								</c:forEach>
							</select>
						</div>
						</c:if>

						<div class="form-floating mb-3">
							<input class="form-control" id="phone" type="text" name="phone"
								value="${dto.phone}" placeholder="name@example.com" /> <label
								for="phone">전화번호</label>
						</div>

                            <div class="d-flex align-items-center justify-content-between ml-5 mt-2 mb-3">
                                <button class="btn btn-primary">사원 정보 수정</button>
                            </div>
                        </form>
                        		<button class="btn btn-danger" id="deleteBtn" type="button">사원 정보 삭제</button>
                    </div>
                  
                </div>
            </div>
        </div>
    </div>
	<script src="/resources/js/hr/delete.js"></script>