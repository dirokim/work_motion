<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<div class="container d-flex align-items-center h-100">
    <div class="card w-50 h-50 m-auto">
        <div class="card-header">상품안내</div>
        <div class="card-body">
            <div class="d-flex flex-column justify-content-between h-100">
                <p>${detail.info}</p>
                <form action="./payment" method="post" class="ms-auto me-auto">
                    <input type="hidden" name="price" value="${detail.price}" />
                    <input
                        type="hidden"
                        name="period"
                        value="${detail.period}"
                    />
                    <button type="submit" class="btn btn-primary">
                        결제하기
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
