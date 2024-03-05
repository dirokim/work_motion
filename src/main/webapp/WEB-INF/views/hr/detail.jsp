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

            			


                        <form action="/hr/update" method="get">
          
                            <div class="form-floating mb-3">
                                <input class="form-control" id="id" readonly="readonly" type="text" name="id" value="${dto.id}" placeholder="id" />
                                <label for="id">사원 번호</label>
                            </div>
     
      						<div class="form-floating mb-3">
                                <input class="form-control" id="name" readonly="readonly" type="text" name="name" value="${dto.name}" placeholder="name" />
                                <label for="name">Name</label>
                            </div>
     
     						<div class="form-floating mb-3">
                                <input class="form-control" id="email" readonly="readonly" type="text" name="email" value="${dto.email}" placeholder="name@example.com" />
                                <label for="email">Email</label>
                            </div>

                            <div class="form-floating mb-3">
                                <input class="form-control" id="departmentName" readonly="readonly" type="text" name="departmentName"
                                    value="${dto.department.name}" placeholder="name@example.com" /> <label
                                    for="email">부서</label>
                            </div>

                            <div class="form-floating mb-3">
                                <input class="form-control" id="positionName" readonly="readonly" type="text" name="positionName"
                                    value="${dto.position.name}" placeholder="name@example.com" /> <label
                                    for="email">직급</label>
                            </div>

                            <div class="form-floating mb-3">
                                <input class="form-control" id="phone" readonly="readonly" type="text" name="phone"
                                    value="${dto.phone}" placeholder="name@example.com" /> <label
                                    for="phone">전화번호</label>
                            </div>
                            <div>
                                <input type="hidden" name="id" value="${dto.id}">
                            </div>
                        
                            <div class="d-flex align-items-center justify-content-between ml-5 mt-2 mb-3">
                   		     <button class="btn btn-primary">사원 정보 수정</button>
                            </div>
                        </form>
                        		
                    </div>
                  
                </div>
            </div>
        </div>
    </div>