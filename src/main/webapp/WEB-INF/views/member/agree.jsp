<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
	<head>
		<base href="/" />
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
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">약관 동의</h3></div>
                                    <div class="card-body">
                                       <form id="form" action="/member/join" method="post">
                            
                                            <input type="checkbox"  id="all" class="checks">
                                            <label for="all">전체동의</label><br> <br> <br>
                
                                            <input type="checkbox" class="checks abc" id="fil1">
                                            <label for="fil1">필수동의</label><br>
                                            <div class="input-group">
                                              <span class="input-group-text">Lorem Ipsum</span>
                                              <textarea class="form-control" aria-label="With textarea">
                                                is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                              </textarea>
                                            </div>
                
                                            <input type="checkbox"  class="checks abc" id="fil2">
                                            <label for="fil2">필수동의</label><br>
                                            <div class="input-group">
                                              <span class="input-group-text">Lorem Ipsum</span>
                                              <textarea class="form-control" aria-label="With textarea">
                                                is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                              </textarea>
                                            </div>
                
                                            <input type="checkbox"  class="checks abc" id="fil3">
                                            <label for="fil3">필수동의</label><br>
                                            <div class="input-group">
                                              <span class="input-group-text">Lorem Ipsum</span>
                                              <textarea class="form-control" aria-label="With textarea">
                                                is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                              </textarea>
                                            </div>
                
                                            <input type="checkbox"   class="checks" id="fil4">
                                            <label for="fil4">선택동의</label><br>
                                            <div class="input-group">
                                              <span class="input-group-text">Lorem Ipsum</span>
                                              <textarea class="form-control" aria-label="With textarea">
                                                is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                              </textarea>
                                            </div>
                
                                            <input type="checkbox"   class="checks" id="fil5">
                                            <label for="fil5">선택동의</label><br>
                                            <div class="input-group">
                                              <span class="input-group-text">Lorem Ipsum</span>
                                              <textarea class="form-control" aria-label="With textarea">
                                                is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                                              </textarea>
                                            </div>
                							<div>
                								<button id="btn" type="button" class="btn btn-primary">동의</button>
                							</div>
                
                                      </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
      
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>

        </div>
        
		
     <!-- Bootstrap JS -->
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="/resources/js/member/agreeCheck.js"></script>
    </body>
</html>