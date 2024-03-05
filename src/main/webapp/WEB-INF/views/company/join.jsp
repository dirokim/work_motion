<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Work Motion</title>
	
		<!-- Template -->
		<link href="/resources/css/styles.css" rel="stylesheet" />
		<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
	
		<!-- Bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	
		 <!-- Jquery -->
		 <script src="https://code.jquery.com/jquery-3.7.1.slim.js" integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc=" crossorigin="anonymous"></script>
		<style>
			.red{color: red;
			}
            .green{color:green;}
            .backGro{
             background: #ebecf0;
             }
		</style>
	  </head>
    <body class="backGro">
                      <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">회사 계정 가입</h3></div>
                                    <div class="card-body">
                                        <form action="/company/join" method="post" id="frm">
											<div class="row mb-3">
												<div class="col-md-6">
													<div class="form-floating mb-3 mb-md-0">
														<input class="form-control" id="name" type="text" name="name" placeholder="Enter your name" />
                                                        <label for="name">회사 이름</label>
                                                    </div>
                                                </div>
                                                
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating">
                                                        <input class="form-control" id="owner" type="text" name="owner" placeholder="가입자 이름" />
                                                        <label for="owner">가입자 이름</label>
                                                </div>
                                            </div>
                                           </div>
                                            <div class="row mb-3">
											<div class="col-md-6">
												<div class="form-floating mb-3">
													<input class="form-control" id="phone_num" type="text"
														name="phone_num" placeholder="회사번호" /> <label
														for="phone_num">회사 전화 번호</label>
													<div id="emailResult" class="red"></div>
												</div>
											</div>
										</div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-6">
                                                        <input class="form-control" id="info" type="text" name="info"  placeholder="@그룹명.com" />
                                                        <label for="info">그룹 명</label>
                                                        <br>
                                                    </div>
                                                    
                                                </div>
                                                        <p>회사에서 사용하는 이메일 주소가 abc@workscorp.com이라면 'workscorp'을 그룹명으로 입력합니다</p>
                                                
                                            </div>
                                            <div class="row mb-3">
                                      
                                            </div>
                                           
                                                
                                            <div class="mt-4 mb-0">
                                                
												<button class="d-grid btn btn-primary" id="joinBtn" >회사 계정 가입</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="/member/agree">Need an account? Sign up!</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">

            </div>
        </div>
        

     <!-- Bootstrap JS -->
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
     <script src="/resources/js/scripts.js"></script>
    </body>
</html>