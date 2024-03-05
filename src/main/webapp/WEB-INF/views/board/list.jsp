<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<link href="/resources/js/board/boardStyle.css" rel="stylesheet" />

<div class="container mt-4 p-4 text-center">
    <div class="mb-3">
        <h2>${name}</h2>
    </div>

    <table class="table table-hover mt-3">
        <thead>
            <tr id="tr1">
                <th scope="col">No</th>
                <th scope="col">Title</th>
                <c:if test="${category_id ne 2}">
                    <th scope="col">writer</th>
                </c:if>
                <th scope="col">Views</th>
                <th scope="col">Date</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${list}" var="dto">
                <tr>
                    <td class="tr">${dto.ID}</td>
                    <td class="listTitle">
                        <a
                            id="${dto.ID}"
                            class="hihello"
                            href="./detail?id=${dto.ID}&category_ID=${dto.CATEGORY_ID}"
                            >${dto.TITLE}</a
                        >
                    </td>
                    <c:if test="${category_id ne 2}">
                        <td class="tr">${dto.WRITER}</td>
                    </c:if>
                    <td class="tr">
                        <i class="fa-solid fa-eye"></i> ${dto.VIEWS}
                    </td>
                    <td class="tr">${dto.CREATE_DT}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${pager.lastNum >= 1}">
        <nav
            aria-label="..."
            class="d-flex justify-content-center"
            style="width: 100%; border: 1; margin-left: 70px; width: 90%"
        >
            <ul class="pagination">
                <!-- 이전@@@@@@@@@@ -->
                <c:if test="${!pager.start} ">
                    <li class="page-item">
                        <a
                            class="page-link"
                            href="./list?page=${pager.startNum-1}&search=${pager.search}&id=${category_id}"
                            >Previous</a
                        >
                    </li>
                </c:if>

                <!-- 번호@@@@@@@@@@@@ -->
                <c:forEach
                    begin="${pager.startNum}"
                    end="${pager.lastNum}"
                    var="i"
                >
                    <li class="page-item">
                        <a
                            class="page-link"
                            href="./list?page=${i}&search=${pager.search}&id=${category_id}"
                            >${i}</a
                        >
                    </li>
                </c:forEach>
                <!-- 다음@@@@@@@@@@@@@@@ -->

                <c:if test="${!pager.last}">
                    <li class="page-item">
                        <a
                            class="page-link"
                            href="./list?page=${pager.lastNum+1}&search=${pager.search}&id=${category_id}"
                            >Next</a
                        >
                    </li>
                </c:if>
                &nbsp;
            </ul>
        </nav>
    </c:if>

    <!-- 검색부분@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
    <form action="./list" class="d-flex justify-content-center">
        <div
            class="input-group mb-3"
            style="border: 1; margin-left: 70px; width: 30vh"
        >
            <input type="text" width="200px" name="search" />
            <input
                type="hidden"
                width="200px"
                value="${category_id}"
                name="id"
            />
            <button class="btn btn-outline-secondary" type="submit">
                찾기
            </button>
        </div>
    </form>

    <a href="./create?id=${category_id}" class="d-flex justify-content-end"
        ><button class="btn btn-success" style="margin-left: 70px">
            글쓰기
        </button></a
    >
</div>
<script
    src="https://kit.fontawesome.com/3a9643202c.js"
    crossorigin="anonymous"
></script>
<script src="/resources/js/board/list.js"></script>
