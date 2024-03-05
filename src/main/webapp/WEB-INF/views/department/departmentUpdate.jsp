<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<div class="container mt-3 m-4 p-4">
    <div class="text-center mt-4 mb-4">
        <h1>부서 정보 수정</h1>
    </div>
    <form action="./departmentUpdate" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">부서이름</label>
            <input
                value="${update.name}"
                name="name"
                type="text"
                class="form-control"
                id="name"
            />
        </div>
        <input type="hidden" name="id" value="${update.id}" />
        <div class="mb-3">
            <label for="phone_num" class="form-label">부서전화번호</label>
            <input
                value="${update.phone_num}"
                name="phone_num"
                type="text"
                class="form-control"
                id="phone_num"
            />
        </div>
        <button type="submit" class="btn btn-primary">부서수정하기</button>
    </form>
</div>
