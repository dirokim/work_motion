<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %>
<style type="text/css">
    #container {
        display: flex;
        width: 100%;
        flex-direction: column;
        padding: 20px;
    }
    #container .header {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
    }
    #projectList,
    #comProjectList {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
    }
    .projectCard {
        margin: 10px;
        text-decoration: none;
    }
    .card {
        transition: 0.2s;
    }
    .card:hover {
        transform: scale(1.02);
    }
    .card-body {
        height: 10vh;
        width: 15vh;
    }
    .card-mlength {
        height: fit-content;
        margin: 0;
        margin-left: 2px;
    }
</style>

<div id="container" class="container p-3 mt-4">
    <div class="header mb-4">
        <h1>프로젝트</h1>
        <a type="button" class="btn btn-primary" href="/projects/create"
            >프로젝트 추가</a
        >
    </div>

    <h2 class="mb-3">나의 프로젝트</h2>
    <div id="projectList">
        <c:forEach items="${myProjects}" var="project">
            <a href="/projects/detail?id=${project.id}" class="projectCard">
                <div class="card shadow mb-1 bg-body-tertiary rounded">
                    <div class="card-body">
                        <h5
                            class="card-title d-flex justify-content-between align-items-center"
                        >
                            <span class="text-truncate">${project.name}</span>

                            <div
                                class="d-flex justify-contents-center align-items-center"
                            >
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    width="16"
                                    height="16"
                                    fill="currentColor"
                                    class="bi bi-people"
                                    viewBox="0 0 16 16"
                                >
                                    <path
                                        d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1h8Zm-7.978-1A.261.261 0 0 1 7 12.996c.001-.264.167-1.03.76-1.72C8.312 10.629 9.282 10 11 10c1.717 0 2.687.63 3.24 1.276.593.69.758 1.457.76 1.72l-.008.002a.274.274 0 0 1-.014.002H7.022ZM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM6.936 9.28a5.88 5.88 0 0 0-1.23-.247A7.35 7.35 0 0 0 5 9c-4 0-5 3-5 4 0 .667.333 1 1 1h4.216A2.238 2.238 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816ZM4.92 10A5.493 5.493 0 0 0 4 13H1c0-.26.164-1.03.76-1.724.545-.636 1.492-1.256 3.16-1.275ZM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0Zm3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4Z"
                                    />
                                </svg>
                                <p class="card-mlength">
                                    ${fn:length(project.crews) + 1}
                                </p>
                            </div>
                        </h5>
                        <p class="card-text">${project.info}</p>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
    <h2 class="mt-3">참여중인 프로젝트</h2>
    <div id="comProjectList">
        <c:forEach items="${includeProjects}" var="project">
            <a href="/projects/detail?id=${project.id}" class="projectCard">
                <div class="card shadow mb-1 bg-body-tertiary rounded">
                    <div class="card-body">
                        <h5
                            class="card-title d-flex justify-content-between align-items-center"
                        >
                            <span class="text-truncate">${project.name}</span>

                            <div
                                class="d-flex justify-contents-center align-items-center"
                            >
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    width="16"
                                    height="16"
                                    fill="currentColor"
                                    class="bi bi-people"
                                    viewBox="0 0 16 16"
                                >
                                    <path
                                        d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1h8Zm-7.978-1A.261.261 0 0 1 7 12.996c.001-.264.167-1.03.76-1.72C8.312 10.629 9.282 10 11 10c1.717 0 2.687.63 3.24 1.276.593.69.758 1.457.76 1.72l-.008.002a.274.274 0 0 1-.014.002H7.022ZM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM6.936 9.28a5.88 5.88 0 0 0-1.23-.247A7.35 7.35 0 0 0 5 9c-4 0-5 3-5 4 0 .667.333 1 1 1h4.216A2.238 2.238 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816ZM4.92 10A5.493 5.493 0 0 0 4 13H1c0-.26.164-1.03.76-1.724.545-.636 1.492-1.256 3.16-1.275ZM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0Zm3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4Z"
                                    />
                                </svg>
                                <p class="card-mlength">
                                    ${fn:length(project.crews) + 1}
                                </p>
                            </div>
                        </h5>
                        <p class="card-text">${project.info}</p>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
</div>

<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>

<!-- 윈도우 사이즈 변경할 때 프로젝트 리스트 높이 조절 -->
<script src="/resources/js/project/project.js"></script>
