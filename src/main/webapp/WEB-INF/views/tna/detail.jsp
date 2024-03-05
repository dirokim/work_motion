<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<div class="container-fluid px-4">
    <h1 class="mt-4">${dto.name}</h1>
    <div class="card mb-4">
        <div class="card-header">
            <i class="fas fa-table me-1"></i>
            출근부
        </div>

        <div class="card-body">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>출근</th>
                        <th>퇴근</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="li">
                        <tr>
                            <td>${li.ID}</td>
                            <td>${li.START_TIME}</td>
                            <td>${li.FINISH_TIME}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <nav
            aria-label="Page navigation example"
            class="d-flex flex-column align-items-center"
        >
            <form action="/tna/detail" method="get" class="row g-3 mb-3">
                <div class="col-auto">
                    <select
                        class="form-select"
                        name="kind"
                        aria-label="Default select example"
                    >
                        <option value="2024-01-01,2024-01-31">1월</option>
                        <option value="2024-02-01,2024-02-29">2월</option>
                        <option value="2024-03-01,2024-03-31">3월</option>
                        <option value="2024-04-01,2024-04-30">4월</option>
                        <option value="2024-05-01,2024-05-31">5월</option>
                        <option value="2024-06-01,2024-06-30">6월</option>
                        <option value="2024-07-01,2024-07-31">7월</option>
                        <option value="2024-08-01,2024-08-31">8월</option>
                        <option value="2024-09-01,2024-09-30">9월</option>
                        <option value="2024-10-01,2024-10-31">10월</option>
                        <option value="2024-11-01,2024-11-30">11월</option>
                        <option value="2024-12-01,2024-12-31">12월</option>
                    </select>
                </div>
                <div class="col-auto">
                    <div class="input-group mb-3">
                        <input
                            type="hidden"
                            name="email"
                            value="${dto.email}"
                        />
                        <input type="hidden" name="id" value="${dto.id}" />
                        <input
                            type="text"
                            class="form-control"
                            name="search"
                            aria-label="Text input with dropdown button"
                        />
                        <button class="btn btn-primary">검색</button>
                    </div>
                </div>
            </form>
        </nav>
    </div>
</div>
<script src="/resources/js/tna/detail.js"></script>
