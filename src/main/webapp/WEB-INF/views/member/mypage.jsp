<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div class="container mt-4 p-4">
        <div class="row">
            <div class="col">
                <div class="card shadow-lg border-0 rounded-lg">
                    <div class="card-header"><h3 class="text-center font-weight-light my-4">My page</h3></div>
                    
                    <c:if test="${not empty dto.avatar}">
                        <div class="m-auto mt-3">
                            <img  src="${dto.avatar.name}" class="img-thumbnail" width="200px">
                        </div>
                        
                    </c:if>
                        
                    
                        <div class="card-body">


                           
                                <div class="form-floating mb-3">
                                    <input class="form-control" id="pw" type="password" name="pass" placeholder="현재비밀번호" />
                                    <label for="pw">비밀번호 확인</label>
                                    <div id="pwResult"></div>
                                </div>
                                <div class="form-floating mb-3">
                                    <button  id="pwCheckBtn" type="button" class="btn btn-primary">비밀번호 확인</button>
                                </div>
                          
                        </div>
                            
                        
                        
                        
                        
                        
                        <div class="card-body">


                        <form action="/member/update" method="post"  id="updateFrm" enctype="multipart/form-data">
							 <div class="form-floating mb-3">
                                <input class="form-control" id="picture" type="file" name="picture" placeholder="name@example.com" />
                                <label for="picture">사진추가</label>
                            </div>	
		
                            <div class="form-floating mb-3">
                                <input class="form-control" id="email" type="email" name="email" value="${dto.email}" placeholder="name@example.com" />
                                <label for="email">Email address</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input  class="form-control" id="updatePw"  type="password" name="password" placeholder="비밀번호 변경" />
                                <label for="updatePw">비밀번호 변경</label>
                            </div>

                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" name="name" type="text" value="${dto.name}" placeholder="이름" />
                                <label for="name">이름</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input class="form-control" id="phone" name="phone" type="text" value="${dto.phone}" placeholder="번호" />
                                <label for="phone">핸드폰번호</label>
                            </div>
                            <div>
                            <input type="hidden" name="id" value="${dto.id}">
                            </div>
                            <div class="d-flex align-items-center justify-content-between ml-5 mt-2 mb-3">
                               
                                <button class="btn btn-primary" id="updateBtn" type="button">정보 수정</button>
                            </div>
                        </form>
                    </div>
                  
                </div>
            </div>
        </div>
    </div>
<script src="/resources/js/member/mypagePwCheck.js"></script>


      
      