<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>templete List</title>
</head>
<body>
	<h1>서류추가</h1>
	<form id="contactForm" action="./add" method="post"
		enctype="multipart/form-data">
		<div class="mb-3">
			<lable for="item" class="form-label">기안서A,연차신청서B,지출결의서C</lable>		
			<input type="text" class="form-control" id="item" name="item"> 	
			<input class="form-control" type="file" name="templeteFile"/>
			<button class="btn btn-primary" id="submitButton" type="submit">파일 서류 추가</button>
		</div>
	</form>

</body>
</html>