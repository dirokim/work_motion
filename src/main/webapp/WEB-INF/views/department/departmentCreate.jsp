<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<div class="container mt-3 m-4 p-4">
    <div class="text-center mt-4 mb-4">
        <h1>부서 추가</h1>
    </div>
    <form action="./departmentCreate" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">부서이름</label>
            <input name="name" type="text" class="form-control" id="name" />
        </div>
        <div class="mb-3">
            <label for="phone_num" class="form-label">부서전화번호</label>
            <input
                name="phone_num"
                type="text"
                class="form-control"
                id="phone_num"
            />
        </div>
        <button type="submit" class="btn btn-primary">부서만들기</button>
    </form>
</div>
