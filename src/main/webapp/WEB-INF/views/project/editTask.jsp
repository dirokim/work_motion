<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<link
    rel="stylesheet"
    href="/resources/css/project/createTask.css"
    type="text/css"
/>
<div class="container m-3 p-4 mt-3">
    <div class="text-center mb-2">
        <h1>업무 작성</h1>
    </div>
    <form
        action="/tasks/update"
        method="POST"
        enctype="multipart/form-data"
        id="frm"
    >
        <input type="hidden" name="status" value="${task.status}" />
        <input
            type="hidden"
            name="project_id"
            value="${task.project_id}"
            id="projectId"
        />
        <input type="hidden" name="id" value="${task.id}" />
        <input
            type="hidden"
            value="
            <c:forEach items='${task.task_member}' var='item'>
                ${item.id}
            </c:forEach>
            "
            id="member_id"
        />

        <div class="mb-3">
            <label for="name" class="form-label">제목</label>
            <input
                type="text"
                class="form-control"
                id="name"
                name="name"
                value="${task.name}"
            />
        </div>
        <div class="mb-3">
            <label for="summernote" class="form-label">본문</label>
            <textarea id="summernote" name="content"></textarea>
        </div>
        <div class="mb-3">
            <label for="charge" class="form-label">담당자</label>
            <input
                type="hidden"
                name="charge"
                class="form-control"
                id="charge"
            />
            <div class="input-group mb-1">
                <input
                    type="text"
                    class="form-control"
                    placeholder="담당자를 선택해주세요"
                    aria-label="charge"
                    name="basic"
                    aria-describedby="addChargeButton"
                />
                <button
                    class="btn btn-outline-primary"
                    type="button"
                    id="addChargeButton"
                    data-bs-toggle="modal"
                    data-bs-target="#staticBackdrop"
                >
                    추가하기
                </button>
            </div>
        </div>
        <div class="mb-3">
            <div class="form-check form-switch">
                <input
                    type="hidden"
                    class="form-control"
                    name="has_limit"
                    id="has_limitInput"
                    value="${task.has_limit}"
                />
                <input
                    class="form-check-input"
                    type="checkbox"
                    role="switch"
                    id="has_limit"
                />
                <label class="form-check-label" for="has_limit"
                    >일정 추가하기</label
                >
            </div>
        </div>
        <div class="mb-3" id="dataInputContainer" style="display: none">
            <div class="input-group mb-1">
                <span class="input-group-text" id="start_dt">시작일</span>
                <input
                    type="date"
                    class="form-control inputDate"
                    name="start"
                    value="${task.start}"
                />
            </div>
            <div class="input-group mb-1">
                <span class="input-group-text" id="end_dt">종료일</span>
                <input
                    type="date"
                    class="form-control inputDate"
                    name="end"
                    value="${task.end}"
                />
            </div>
            <div class="form-check form-switch">
                <input
                    type="hidden"
                    class="form-control"
                    name="has_schedule"
                    id="addScheduleInput"
                    value="${task.has_schedule}"
                />
                <input
                    class="form-check-input"
                    type="checkbox"
                    role="switch"
                    id="scheduleCheckbox"
                />
                <label class="form-check-label" for="scheduleCheckbox"
                    >스케줄에 등록하기</label
                >
            </div>
        </div>

        <button id="submitButton" type="button" class="btn btn-primary">
            작성하기
        </button>
    </form>
</div>
<!-- Modal -->
<div
    class="modal fade"
    id="staticBackdrop"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
>
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">
                    멤버 추가
                </h1>
                <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                ></button>
            </div>
            <div class="modal-body" id="modalBody"></div>
            <div class="modal-footer">
                <button
                    type="button"
                    class="btn btn-secondary"
                    data-bs-dismiss="modal"
                >
                    닫기
                </button>
                <button
                    type="button"
                    class="btn btn-primary"
                    id="addMemberButton"
                    data-bs-dismiss="modal"
                >
                    추가하기
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/resources/js/project/editTask.js"></script>
<script>
    //summernote init
    $("#summernote").summernote({
        placeholder: "내용을 입력해주세요.",
        tabsize: 4,
        height: 400,
        toolbar: [
            ["style", ["style"]],
            ["font", ["bold", "underline", "clear"]],
            ["color", ["color"]],
            ["para", ["ul", "ol", "paragraph"]],
            ["table", ["table"]],
            ["insert", ["link", "picture", "video"]],
        ],
    });
    $("#summernote").summernote("code", `${task.content}`);
</script>
