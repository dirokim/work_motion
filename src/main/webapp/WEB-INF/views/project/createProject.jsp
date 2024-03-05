<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<link
    rel="stylesheet"
    href="/resources/css/project/create.css"
    type="text/css"
/>
<div class="container p-4 pt-3" data-bs-projectId="${project.id}">
    <h1 class="h3 mb-3">프로젝트 생성</h1>
    <div class="row">
        <div class="col-md-5 col-xl-4">
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title mb-0">메뉴</h5>
                </div>

                <div
                    class="list-group list-group-flush nav nav-pills"
                    role="tablist"
                    id="pills-tab"
                >
                    <a
                        class="list-group-item list-group-item-action active"
                        href="#account"
                        role="tab"
                        aria-controls="pills-home"
                        aria-selected="true"
                        data-bs-toggle="pill"
                        data-bs-target="#pills-home"
                    >
                        기본 설정
                    </a>
                    <a
                        class="list-group-item list-group-item-action"
                        aria-controls="pills-member"
                        aria-selected="true"
                        data-bs-toggle="pill"
                        data-bs-target="#pills-member"
                        href="#member"
                        role="tab"
                    >
                        멤버
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-7 col-xl-8">
            <div class="tab-content" id="pills-tabContent">
                <div
                    class="tab-pane fade show active"
                    id="pills-home"
                    role="tabpanel"
                    aria-labelledby="pills-home-tab"
                >
                    <div class="card">
                        <div class="card-header">
                            <h5 class="card-title mb-0">기본 정보</h5>
                        </div>
                        <div class="card-body">
                            <form
                                id="frm"
                                action="/projects/create"
                                method="post"
                                onsubmit="return false"
                            >
                                <input
                                    type="hidden"
                                    name="id"
                                    value="${project.id}"
                                />
                                <input
                                    type="hidden"
                                    name="crew"
                                    id="crewInput"
                                />
                                <div class="row mb-3">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <input
                                                type="hidden"
                                                value="${project.owner_id}"
                                                name="owner_id"
                                            />
                                            <label for="projectName"
                                                >프로젝트 이름</label
                                            >
                                            <input
                                                type="text"
                                                class="form-control mb-3"
                                                id="projectName"
                                                placeholder="프로젝트 이름"
                                                name="name"
                                                value="${project.name}"
                                            />
                                        </div>
                                        <div class="form-group">
                                            <label for="projectInfo"
                                                >프로젝트 설명</label
                                            >
                                            <textarea
                                                rows="2"
                                                class="form-control"
                                                id="projectInfo"
                                                name="info"
                                                placeholder="프로젝트 설명을 적어주세요."
                                            >
${project.info}</textarea
                                            >
                                        </div>
                                    </div>
                                </div>

                                <button
                                    type="button"
                                    class="btn btn-primary"
                                    id="saveButton"
                                >
                                    프로젝트 생성
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
                <div
                    class="tab-pane fade d-flex"
                    id="pills-member"
                    role="tabpanel"
                    aria-labelledby="pills-member-tab"
                >
                    <div class="card w-50">
                        <div class="card-body">
                            <h5 class="card-title mb-3">멤버</h5>
                            <form
                                id="memberFrm"
                                action="/projects/${project.id}/crews"
                                method="post"
                                enctype="application/x-www-form-urlencoded"
                            >
                                <div id="memberList"></div>
                            </form>
                        </div>
                    </div>
                    <div
                        class="div p-2 d-flex flex-column justify-content-center"
                    >
                        <button
                            type="button"
                            class="btn btn-primary mb-4"
                            style="width: max-content"
                            id="addButton"
                        >
                            추가
                        </button>
                        <button
                            type="button"
                            class="btn btn-primary"
                            style="width: max-content"
                            id="deleteButton"
                        >
                            삭제
                        </button>
                    </div>
                    <div class="card w-50">
                        <div class="card-body">
                            <h5 class="card-title mb-3">크루</h5>
                            <form
                                id="memberFrm"
                                action="/projects/${project.id}/crews"
                                method="post"
                                enctype="application/x-www-form-urlencoded"
                            >
                                <div id="crewList"></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script
    type="text/javascript"
    src="/resources/js/project/createProject.js"
></script>
