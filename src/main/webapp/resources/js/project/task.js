//전역 변수
let calendar; //캘린더
const projectInfo = document.getElementsByClassName("projectInfo")[0];
const project_id = projectInfo.getAttribute("data-bs-projectId");
const crewList = document.getElementById("crewList");
const owner_id = projectInfo.getAttribute("data-bs-ownerId"); //그룹장 아이디

//탭 네이게이션 이벤트
const homeButton = document.getElementById("homeButton");
homeButton.addEventListener("click", function () {});
const taskButton = document.getElementById("taskButton");
taskButton.addEventListener("click", function () {});
const scheduleButton = document.getElementById("scheduleButton");

scheduleButton.addEventListener("click", function () {
    const prev = document.querySelector("button[aria-label='prev']");
    const next = document.querySelector("button[aria-label='next']");
    prev.click();
    next.click();
});

//DOM 로드 후 한 번만 실행
document.addEventListener("DOMContentLoaded", async () => {
    //컨텐츠 로드(ajax)
    loadArticle();
    loadTask();
    loadCalendar();

    //해시에 맞는 탭 선택
    const url = window.location.hash;
    if (url == "#homeTab") {
        homeButton.click();
    }
    if (url == "#taskTab") {
        taskButton.click();
    }
    if (url == "#scheduleTab") {
        scheduleButton.click();
    }
});

//멤버 추가 버튼 클릭시 프로젝트에 포함돼있지 않은 멤버 리스트 불러오기
const addCrewButton = document.getElementById("addCrewButton");
addCrewButton.addEventListener("click", async function () {
    const modalBody = document.getElementById("modalBody");
    modalBody.innerHTML = "";
    const response = await fetch(`/v1/projects/${project_id}/members`);
    const data = await response.json();
    let html = "<form id='frm'>";
    html += createMemberList(data);
    html += "</form>";
    modalBody.innerHTML = html;
});
//멤버 리스트 마크업 생성후 리턴
function createMemberList(memberList) {
    let html = "";
    for (member of memberList) {
        html += `
                <div class="d-flex form-check align-items-center">
                    <input class="form-check-input me-3" type="checkbox" name="member_id" value="${member.id}" id="checbox${member.id}">
                    <label
                            class="d-flex align-items-center pb-1 w-100"
                            id="tooltips-container"
                            for="checbox${member.id}"
                        >
                            <img
                                src="${member.avatar.name}"
                                class="rounded-circle img-fluid avatar-md img-thumbnail bg-transparent"
                                alt=""
                            />
                            <div class="w-100 ms-2">
                                <h5 class="mb-1">
                                    ${member.name}<i
                                        class="mdi mdi-check-decagram text-info ms-1"
                                    ></i>
                                </h5>
                                <p
                                    class="mb-0 font-13 text-muted"
                                >
                                    ${member.position.name}
                                </p>
                            </div>
                        </label>
                </div>
            `;
    }
    return html;
}

//멤버 추가 후 멤버 리스트 재로딩
async function loadCrewList() {
    const response = await fetch(`/v1/projects/${project_id}/crews`);
    const data = await response.json();
    crewList.innerHTML = createCrewList(data);
}
//멤버 리스트 마크업 생성 후 리턴
function createCrewList(crewList) {
    let html = "";
    for (crew of crewList) {
        html += `
                    <button
                    class="list-group-item list-group-item-action"
                    data-bs-toggle="modal"
                    data-bs-target="#profileModal"
                    data-bs-memberId="${crew.id}"
                    onclick="createProfile(${crew.id})"
                    >
                        <div
                            class="d-flex align-items-center pb-1"
                            id="tooltips-container"
                        >
                            <img
                                src="${crew.avatar.name}"
                                class="rounded-circle img-fluid avatar-md img-thumbnail bg-transparent"
                                alt=""
                            />
                            <div class="w-100 ms-2">
                                <h5 class="mb-1">
                                    ${crew.name}<i
                                        class="mdi mdi-check-decagram text-info ms-1"
                                    ></i>
                                </h5>
                                <p
                                    class="mb-0 font-13 text-muted"
                                >
                                    ${crew.position.name}
                                </p>
                            </div>
                            <i
                                class="mdi mdi-chevron-right h2"
                            ></i>
                        </div>
                    </button>
            `;
    }
    return html;
}

//추가 할 멤버 제출
const submitButton = document.getElementById("submitButton");
submitButton.addEventListener("click", function () {
    const checked = document.querySelectorAll(
        "#modalBody input[type=checkbox]:checked"
    );
    let addMembers = [];
    const formData = new FormData();
    for (let i = 0; i < checked.length; i++) {
        const member_id = checked[i].value;
        addMembers.push(member_id);
    }
    formData.append("member_ids", addMembers.join(","));
    fetch(`/v1/projects/${project_id}/crews`, {
        method: "POST",
        body: formData,
    })
        .then((response) => response.json())
        .then((datas) => {
            alert(datas + "명의 멤버가 추가되었습니다.");
            loadCrewList();
        });
});

//멤버 정보 로딩
async function loadProfile(member_id) {
    const response = await fetch(
        `/v1/projects/${project_id}/crews/` + member_id
    );
    const data = await response.json();
    return data;
}
//오너 정보 로딩
async function loadOwnerProfile() {
    const response = await fetch(`/v1/projects/${project_id}/owner`);
    const data = await response.json();
    return data;
}

//멤버 프로필 클릭시 프로필 모달창 데이터 로딩 및 마크업 생성
async function createProfile(member_id, is_owner) {
    const profileBody = document.getElementById("profileBody");
    profileBody.innerHTML = "";
    let member = null;
    if (is_owner) {
        member = await loadOwnerProfile();
    } else {
        member = await loadProfile(member_id);
    }
    profileBody.innerHTML = `
        <div class="card">
            <div class="card-body">
                <div class="d-flex align-items-start">
                    <img
                        src="${member.avatar.name}"
                        class="rounded-circle avatar-lg img-thumbnail"
                        alt="profile-image"
                    />
                    <div class="w-100 ms-3">
                        <h4 class="my-0">${member.name}</h4>
                        <p class="text-muted">${member.position.name}</p>
                        <button
                            type="button"
                            class="btn btn-soft-success btn-xs waves-effect mb-2 waves-light"
                            data-bs-memberId="${member.id}"
                            data-bs-memberName="${member.name}"
                        onclick="sendMessage(${member.id},'${member.name}')"
                        >
                            메시지
                        </button>
                    </div>
                </div>
                <div class="mt-3">
                    <p class="text-muted mb-2 font-13">
                        <strong>전화번호 :</strong
                        ><span class="ms-2">${member.phone}</span>
                    </p>
                    <p class="text-muted mb-2 font-13">
                        <strong>이메일 :</strong>
                        <span class="ms-2">${member.email}</span>
                    </p>
                </div>
            </div>
        </div>
    `;
}

//채팅창 오픈
async function sendMessage(sendMemberId, memberName) {
    const member_id = projectInfo.getAttribute("data-bs-memberId");
    let room_name = "";
    if (Number(member_id) > Number(sendMemberId)) {
        room_name = sendMemberId + "-" + member_id;
    } else {
        room_name = member_id + "-" + sendMemberId;
    }
    const response = await fetch(
        "/chat/getRoom?room_name=" +
            room_name +
            "&memberName=" +
            memberName +
            "&memberId=" +
            sendMemberId
    );
    const data = await response.text();
    if (response.status == 200) {
        var popupWidth = 400;
        var popupHeight = 700;

        var popupX = window.screen.width / 2 - popupWidth / 2;
        // 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

        var popupY = window.screen.height / 2 - popupHeight / 2;
        const popupWindow = window.open(
            "/chat?name=" + room_name,
            "",
            `toolbar=no, menubar=no,scrollbars=no,resizable=no, width=${popupWidth}, height=${popupHeight}, left=${popupX},top=${popupY}`
        );
        popupWindow.resizeTo(popupWidth, popupHeight);
        popupWindow.onresize = (_) => {
            popupWindow.resizeTo(popupWidth, popupHeight);
        };
    }
}

//아티클 작성
const submitArticleButton = document.getElementById("submitArticleButton");
submitArticleButton.addEventListener("click", async function () {
    const articleForm = document.getElementById("articleForm");
    const formData = new FormData(articleForm);
    const response = await fetch(`/v1/projects/${project_id}/articles`, {
        method: "POST",
        body: formData,
    });
    const data = await response.json();
    articleForm.reset();
    loadArticle();
    $("#summernote").summernote("code", "");
    if (data == 1) {
        alert("작성되었습니다.");
    }
});

//아티클 무한로딩
let options = {
    threshold: 0,
};
let articlePage = 1;
const $articleResult = document.querySelector("#article");
let $articleEnd;
const articleCallback = (entries, observer) => {
    entries.forEach((entry) => {
        if (entry.isIntersecting) {
            articlePage++;
            observer.unobserve($articleEnd);
            loadArticle(articlePage);
        }
    });
};
const articleObserver = new IntersectionObserver(articleCallback, options);

//아티클 로딩
async function loadArticle(page) {
    let response;
    if (page != undefined) {
        response = await fetch(
            `/v1/projects/${project_id}/articles?page=` + page
        );
    } else {
        response = await fetch(`/v1/projects/${project_id}/articles`);
    }

    const data = await response.json();
    const article = document.getElementById("article");
    if (page != undefined) {
        article.innerHTML += createArticle(data);
    } else {
        article.innerHTML = createArticle(data);
        articlePage = 1;
    }

    $articleEnd = $articleResult.children[$articleResult.children.length - 2];
    if (data.length < 10) return;
    articleObserver.observe($articleEnd);
}

//아티클 마크업 생성
function createArticle(articles) {
    let html = "";
    for (let article of articles) {
        html += `
        <div class='border border-light shadow p-2 mb-3 taskArticle' data-bs-id=${
            article.id
        }>
            <div class='d-flex align-items-start'>
                <a
                    class="memberProfileImage"
                    href="javascript:void(0);"
                    data-bs-toggle="modal"
                    data-bs-target="#profileModal"
                    data-bs-memberId="${article.writer.id}"
                    onclick="createProfile(${article.writer.id},
                        ${article.writer.id == owner_id ? true : false})"
                >
                    <img
                        class='me-2 avatar-sm rounded-circle'
                        src="${article.writer.avatar.name}"
                        alt='Generic placeholder image'
                    />
                </a>
                <div class='w-100'>
                    <h5 class='m-0'>${article.writer.name}</h5>
                    <p class='text-muted'>
                        <small>${new Date(article.create_dt).toLocaleString(
                            "ko-KR"
                        )}</small>
                    </p>
                </div>
                ${
                    article.writer.id == member_id
                        ? `
                    <div class="dropdown float-end">
                    <a
                        href="#"
                        class="dropdown-toggle arrow-none card-drop"
                        data-bs-toggle="dropdown"
                        aria-expanded="false"
                    >
                        <i class="mdi mdi-dots-vertical"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-end">
                        <!-- item-->
                        <a
                            href="javascript:void(0); updateArticle(${article.id});"
                            class="dropdown-item"
                            >수정하기</a
                        >
                        <!-- item-->
                        <a
                            href="javascript:void(0); deleteArticle(${article.id});"
                            class="dropdown-item"
                            >삭제하기</a
                        >
                    </div>
                </div>`
                        : ""
                }
                
            </div>
            <div class="article_content" data-bs-id=${article.id}>
                ${article.content}
            </div>
            <div class='mt-2 bottom-menu'  data-bs-id=${article.id}>
                <a href='javascript: void(0);' class='btn btn-sm btn-link text-muted'>
                    <i class='mdi mdi-reply'></i> Reply
                </a>
                <a href='javascript: void(0);' class='btn btn-sm btn-link text-muted'>
                    <i class='mdi mdi-heart-outline'></i>
                    Like
                </a>
                <a href='javascript: void(0);' class='btn btn-sm btn-link text-muted'>
                    <i class='mdi mdi-share-variant'></i>
                    Share
                </a>
            </div>
        </div>
        `;
    }
    return html;
}

//수정중인 글있는지 전역변수
let creating = false;
//아티클 수정 함수
function updateArticle(article_id) {
    if (creating) {
        alert("수정중인 글이 있습니다.");
        return;
    }
    creating = true;
    const article = document.querySelector(
        `.taskArticle[data-bs-id='${article_id}']`
    );
    const writer_id = article.firstElementChild
        .querySelector(".memberProfileImage")
        .getAttribute("data-bs-memberId");
    const wyswyg = document.querySelector(
        `.article_content[data-bs-id='${article_id}']`
    );
    const bottom_menu = document.querySelector(
        `.bottom-menu[data-bs-id='${article_id}']`
    );
    bottom_menu.innerHTML += `
        <button class='btn btn-sm btn-primary float-end' id="updateArticleContent" >
            저장하기
        </button>
    `;
    $(wyswyg).summernote({
        placeholder: "내용을 입력해주세요.",
        tabsize: 4,
        height: 150,
        toolbar: [
            ["style", ["style"]],
            ["font", ["bold", "underline", "clear"]],
            ["color", ["color"]],
            ["para", ["ul", "ol", "paragraph"]],
            ["table", ["table"]],
            ["insert", ["link", "picture", "video"]],
        ],
    });
    const updateArticleContent = document.getElementById(
        "updateArticleContent"
    );
    updateArticleContent.addEventListener("click", async function () {
        const response = await fetch(
            `/v1/projects/${project_id}/articles/${article_id}`,
            {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    content: $(wyswyg).summernote("code"),
                    writer_id: writer_id,
                }),
            }
        );
        const data = await response.json();
        if (data == 1) {
            this.remove();
            $(wyswyg).summernote("destroy");

            alert("수정 성공했습니다.");
            creating = false;
        }
    });
}
//아티클 삭제 함수
async function deleteArticle(id) {
    if (confirm("삭제하시겠습니까?")) {
        const article = document.querySelector(
            `.taskArticle[data-bs-id='${id}']`
        );
        const response = await fetch(
            `/v1/projects/${project_id}/articles/` + id,
            { method: "delete" }
        );
        const data = await response.json();
        if (data == 1) {
            article.remove();
            alert("삭제 되었습니다.");
        } else {
            alert("삭제 실패했습니다.");
        }
    }
}

//테스크 무한로딩
const $taskResult = document.querySelector("#task");
let taskPage = 1;
let $taskEnd;

const taskCallback = (entries, observer) => {
    entries.forEach((entry) => {
        if (entry.isIntersecting) {
            taskPage++;
            observer.unobserve($taskEnd);
            loadTask();
        }
    });
};
const taskObserver = new IntersectionObserver(taskCallback, options);

async function loadTask() {
    const response = await fetch(
        `/v1/projects/${project_id}/tasks?page=${taskPage}`
    );
    const data = await response.json();
    const task = document.getElementById("task");
    task.innerHTML += createTask(data);

    //높이 리사이즈
    const taskContentSeciton = document.getElementById("container");
    const firstHeight = window.innerHeight;
    container.style.height = firstHeight - 56 + "px";

    let currentProject = null;

    window.addEventListener("resize", () => {
        const afterHeight = window.innerHeight;
        const diff = afterHeight - firstHeight;
        const parent = document.getElementById("container").parentElement;
        container.style.height = firstHeight + diff - 56 + "px";
    });

    const changeStatus = document.getElementsByClassName("changeStatus");
    for (let i = 0; i < changeStatus.length; i++) {
        changeStatus[i].addEventListener("click", async function (e) {
            const selectedEl = e.target.parentElement.parentElement;
            const taskId = selectedEl.getAttribute("data-bs-taskId");
            const selectedValue = e.target.innerText;
            const statusButton = document.querySelector(
                `.status[data-bs-taskId='${taskId}']`
            );
            if (statusButton.innerText != selectedValue) {
                const value = selectedValue == "완료" ? 1 : 0;
                const response = await fetch(
                    `/v1/projects/${project_id}/tasks/${taskId}/status`,
                    {
                        method: "PUT",
                        body: JSON.stringify({ status: value }),
                        headers: {
                            "Content-Type": "application/json",
                        },
                    }
                );
                const data = await response.json();
                //상태 변경 성공 시 스케줄도 변경
                if (data == 1) {
                    statusButton.innerText = selectedValue;
                    statusButton.classList.toggle("text-bg-primary");
                    statusButton.classList.toggle("text-bg-success");

                    const response = await fetch(
                        `/v1/projects/${project_id}/schedules`
                    );
                    const data = await response.json();
                    for (let i in data) {
                        if (data[i].status == 1) {
                            data[i].backgroundColor = "green";
                        }
                    }
                    const eventSource = calendar.getEventSources();
                    for (let i in eventSource) {
                        eventSource[i].remove();
                    }
                    calendar.addEventSource(data);
                }
            }
        });
    }
    $taskEnd = $taskResult.children[$taskResult.children.length - 5];
    if (data.length < 10) return;
    taskObserver.observe($taskEnd);
}

//테스크 마크업 생성
function createTask(tasks) {
    const status = { 0: "진행", 1: "완료" };
    let html = "";
    for (let task of tasks) {
        html += `
        <div class='border border-light shadow p-2 mb-3 taskArticle' data-bs-id=${
            task.id
        }>
            <div class='d-flex align-items-start'>
                <a
                    href="javascript:void(0);"
                    data-bs-toggle="modal"
                    data-bs-target="#profileModal"
                    data-bs-memberId="${task.writer.id}"
                    onclick="createProfile(${task.writer.id},
                        ${task.writer.id == owner_id ? true : false})"
                >
                    <img
                        class='me-2 avatar-sm rounded-circle'
                        src="${task.writer.avatar.name}"
                        alt='Generic placeholder image'
                    />
                </a>
                <div class='w-100'>
                    <h5 class='m-0'>${task.writer.name}</h5>
                    <p class='text-muted'>
                        <small>${new Date(task.create_dt).toLocaleString(
                            "ko-KR"
                        )}</small>
                    </p>
                </div>
                <div class='float-end'>
                    <div class="dropdown">
                    <h3 class='m-0'>
                    ${
                        task.status == 0
                            ? `
                        <a class="btn badge rounded-pill text-bg-primary status" data-bs-taskId="${
                            task.id
                        }" data-bs-toggle="dropdown" aria-expanded="false">${
                                  status[task.status]
                              }</a> 
                        `
                            : `
                        <a class="btn badge rounded-pill text-bg-success status" data-bs-taskId="${
                            task.id
                        }" data-bs-toggle="dropdown" aria-expanded="false">${
                                  status[task.status]
                              }</a> 
                        `
                    }
                    <ul class="dropdown-menu" data-bs-taskId="${task.id}">
                        <li><button class="dropdown-item changeStatus" type="button">완료</button></li>
                        <li><button class="dropdown-item changeStatus" type="button">진행</button></li>
                    </ul>
                    </h3>
                    </div>
                </div>
                ${
                    task.writer.id == member_id
                        ? `
                <div class="dropdown float-end">
                    <a
                        href="#"
                        class="dropdown-toggle arrow-none card-drop"
                        data-bs-toggle="dropdown"
                        aria-expanded="false"
                    >
                        <i class="mdi mdi-dots-vertical"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-end">
                        <!-- item-->
                        <a
                            href="javascript:void(0); location.href='/tasks/setting?id=${task.id}'"
                            class="dropdown-item"
                            >수정하기</a
                        >
                        <!-- item-->
                        <a
                            href="javascript:void(0); deleteTask(${task.id});"
                            class="dropdown-item"
                            >삭제하기</a
                        >
                    </div>
                </div>
                `
                        : ""
                }
            </div>
            <div class='mb-3'>
                    <h3 class='m-0'>${task.name}</h3>
            </div>
            <div class="mb-4">
                ${task.content}
            </div>
            <div class="d-flex">
                ${
                    task.has_limit == 1
                        ? `
                    <div class="mb-3 w-50">
                        <h6 class='m-0'>일정</h6>
                        <small>시작일: ${new Date(task.start).toLocaleString(
                            "ko-KR"
                        )}</small><br>
                        <small>종료일: ${new Date(task.end).toLocaleString(
                            "ko-KR"
                        )}</small><br>
                    </div>
                    `
                        : ""
                }
                
                ${
                    task.task_member.length > 0
                        ? `
                    <div class="mb-3 w-50">
                    <h6 class='m-0 mb-1'>담당자</h6>
                    <small>${task.task_member
                        .map((member) => {
                            return `
                            <a
                                data-bs-toggle="modal"
                                data-bs-target="#profileModal"
                                data-bs-memberId="${member.id}"
                                href="javascript: void(0);"
                                onclick="createProfile(${member.id},
                                ${member.id == owner_id ? true : false})">
                                <span class="badge text-bg-primary mb-1">
                                    <img src="${
                                        member.avatar.name
                                    }" class="rounded-circle" style="width:2vh; heigth:2vh; background-color:white;">
                                    ${member.name}
                                </span>
                            </a>
                            `;
                        })
                        .join(" ")}</small><br>
                
                    </div>`
                        : ""
                }
            </div>
            
            
            <div class='mt-2'>
                <a href='javascript: void(0);' class='btn btn-sm btn-link text-muted'>
                    <i class='mdi mdi-reply'></i> Reply
                </a>
                <a href='javascript: void(0);' class='btn btn-sm btn-link text-muted'>
                    <i class='mdi mdi-heart-outline'></i>
                    Like
                </a>
                <a href='javascript: void(0);' class='btn btn-sm btn-link text-muted'>
                    <i class='mdi mdi-share-variant'></i>
                    Share
                </a>
            </div>
        </div>
        `;
    }
    return html;
}

//테스크 삭제 함수
async function deleteTask(id) {
    if (confirm("삭제하시겠습니까?")) {
        const task = document.querySelector(`.taskArticle[data-bs-id='${id}']`);
        const response = await fetch(`/v1/projects/${project_id}/tasks/` + id, {
            method: "delete",
        });
        const data = await response.json();
        if (data == 1) {
            alert("삭제 되었습니다.");
            task.remove();
        } else {
            alert("삭제 실패했습니다.");
        }
    }
}

//summernote init
$("#summernote").summernote({
    placeholder: "내용을 입력해주세요.",
    tabsize: 4,
    height: 150,
    toolbar: [
        ["style", ["style"]],
        ["font", ["bold", "underline", "clear"]],
        ["color", ["color"]],
        ["para", ["ul", "ol", "paragraph"]],
        ["table", ["table"]],
        ["insert", ["link", "picture", "video"]],
    ],
});

//켈린더 로딩 함수
async function loadCalendar() {
    // calendar element 취득
    var calendarEl = $("#calendar")[0];
    const response = await fetch(`/v1/projects/${project_id}/schedules`);
    const data = await response.json();
    for (let i in data) {
        if (data[i].status == 1) {
            data[i].backgroundColor = "green";
        }
    }
    // full-calendar 생성하기
    calendar = new FullCalendar.Calendar(calendarEl, {
        height: "700px", // calendar 높이 설정
        expandRows: true, // 화면에 맞게 높이 재설정
        slotMinTime: "08:00", // Day 캘린더에서 시작 시간
        slotMaxTime: "20:00", // Day 캘린더에서 종료 시간
        // 해더에 표시할 툴바
        headerToolbar: {
            left: "prev,next today",
            center: "title",
            right: "dayGridMonth,timeGridWeek,timeGridDay,listWeek",
        },
        initialView: "dayGridMonth", // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
        navLinks: true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
        editable: false, // 수정 가능?
        selectable: true, // 달력 일자 드래그 설정가능
        nowIndicator: true, // 현재 시간 마크
        dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
        locale: "ko", // 한국어 설정
        eventAdd: function (obj) {
            // 이벤트가 추가되면 발생하는 이벤트
        },
        eventChange: function (obj) {
            // 이벤트가 수정되면 발생하는 이벤트
        },
        eventRemove: function (obj) {
            // 이벤트가 삭제되면 발생하는 이벤트
        },
        select: function (arg) {
            // 캘린더에서 드래그로 이벤트를 생성할 수 있다.
            // var title = prompt("Event Title:");
            // if (title) {
            //     calendar.addEvent({
            //         title: title,
            //         start: arg.start,
            //         end: arg.end,
            //         allDay: arg.allDay,
            //     });
            // }
            // calendar.unselect();
        },
        eventClick: function (info) {
            const calandarModalEl = document.getElementById("calandarModal");
            const myModal = new bootstrap.Modal(calandarModalEl);
            myModal.show();

            //콜백함수
            function deleteEvent() {
                if (
                    confirm(
                        "'" + info.event.title + " 일정을 삭제하시겠습니까 ?"
                    )
                ) {
                    fetch(`/v1/schedules/${info.event.id}`, {
                        method: "delete",
                    }).then(() => {
                        info.event.remove();
                    });
                }
            }
            function removeAllEventListeners(element) {
                var clone = element.cloneNode(true);
                element.parentNode.replaceChild(clone, element);
                clone.addEventListener("click", deleteEvent);
            }
            const deleteEventButton =
                document.getElementById("deleteEventButton");

            removeAllEventListeners(deleteEventButton);
            var events = new Array(); // Json 데이터를 받기 위한 배열 선언
            var obj = new Object();
            obj.title = info.event._def.title;
            obj.start = info.event._instance.range.start;
            obj.end = info.event._instance.range.end;
            events.push(obj);
            const calandarModalBody =
                document.getElementById("calandarModalBody");
            calandarModalBody.innerHTML = `
                    <div>
                        <h5>${events[0].title}</h5>
                        <p>시작: ${new Date(events[0].start).toLocaleString(
                            "ko-KR"
                        )}</p>
                        <p>종료: ${new Date(events[0].end).toLocaleString(
                            "ko-KR"
                        )}</p>
                    </div>
                `;
        },
        // 이벤트
        events: data,
    });
    // 캘린더 랜더링
    calendar.render();
}
