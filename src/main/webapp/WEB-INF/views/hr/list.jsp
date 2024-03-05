<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<div class="container-fluid px-4">
    <h1 class="mt-4">Hr List</h1>
    <div class="card mb-4">
        <div class="card-header">
            <i class="fas fa-table me-1"></i>
            Hr List
        </div>

        <div class="card-body">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Email</th>
                        <th>Department</th>
                        <th>PhoneNumber</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="li">
                        <tr>
                            <td>${li.ID}</td>
                            <td>
                                <a href="/hr/detail?email=${li.EMAIL}"
                                    >${li.NAME}</a
                                >
                            </td>
                            <td>${li.PNAME}</td>
                            <td>${li.EMAIL}</td>
                            <td>${li.DNAME}</td>
                            <td>${li.PHONE}</td>

                            <c:if test="${li.ROLE_ID eq 10}">
                                <td>
                                    <button
                                        class="btn btn-primary createBtn"
                                        data-id="${li.ID}"
                                        data-company="${li.COMPANY_ID}"
                                        type="button"
                                    >
                                        사원 추가
                                    </button>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <nav
            aria-label="Page navigation example"
            class="d-flex flex-column align-items-center"
        >
            <ul class="pagination ml-3">
                <c:if test="${!pager.start}">
                    <li class="page-item">
                        <a
                            class="page-link"
                            href="/hr/list?page=${pager.startNum-1}&search=${pager.search}"
                            >Previous</a
                        >
                    </li>
                </c:if>
                <c:forEach
                    begin="${pager.startNum}"
                    end="${pager.lastNum}"
                    var="p"
                >
                    <li class="page-item">
                        <a
                            class="page-link"
                            href="/hr/list?page=${p}&search=${pager.search}"
                            >${p}</a
                        >
                    </li>
                </c:forEach>

                <c:if test="${!pager.last}">
                    <li class="page-item">
                        <a
                            class="page-link"
                            href="/hr/list?page=${pager.lastNum+1}&search=${pager.search}"
                            >Next</a
                        >
                    </li>
                </c:if>
            </ul>
            <form action="/hr/list" method="get" class="row g-3 mb-3">
                <div class="col-auto">
                    <select
                        class="form-select"
                        name="kind"
                        aria-label="Default select example"
                    >
                        <option value="kind1">이름으로 검색</option>
                        <option value="kind2">직위로 검색</option>
                        <option value="kind3">이메일로 검색</option>
                        <option value="kind4">부서로 검색</option>
                    </select>
                </div>
                <div class="col-auto">
                    <div class="input-group">
                        <input
                            type="text"
                            class="form-control"
                            name="search"
                            aria-label="Text input with dropdown button"
                        />
                        <button class="btn btn-primary">찾기</button>
                    </div>
                </div>
            </form>
        </nav>
    </div>
</div>
<script src="/resources/js/hr/list.js"></script>
