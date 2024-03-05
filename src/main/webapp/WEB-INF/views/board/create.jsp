<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="./create" method="post">
	<div class="mb-3">
		<label for="title" class="form-label">게시판제목</label> <input name="title"
			type="text" class="form-control" id="title">
	</div>
	<div class="mb-3">
		<label for="content" class="form-label">게시판내용</label>
		<textarea id="summernote" class="form-control" id="content" name="content"
			rows="3"></textarea>
	</div>
	<input type="hidden" name="category_id" value="${category.id}">
	<input type="hidden" name="writer" value="${member.name}">
	<button type="submit" class="btn btn-primary">등록하기</button>
</form>

<script>
$('#summernote').summernote({
	  height: 300,                 // set editor height
	  minHeight: null,             // set minimum height of editor
	  maxHeight: null,             // set maximum height of editor
	  focus: true                  // set focus to editable area after initializing summernote
	});
</script>