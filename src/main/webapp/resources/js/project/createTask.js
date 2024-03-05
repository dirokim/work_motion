// min 날짜 설정 및 스케줄 등록 관리
let sDate = new Date();
let minStr = sDate.toISOString().split("T")[0];
const inputDate = document.getElementsByClassName("inputDate");
for (let i = 0; i < inputDate.length; i++) {
    inputDate[i].setAttribute("min", minStr);
    inputDate[i].value = minStr;
}

//일정 추가 토글
const has_limitButton = document.getElementById("has_limit");
const has_limitInput = document.getElementById("has_limitInput");
has_limitButton.addEventListener("change", (e) => {
    const dataInputContainer = document.getElementById("dataInputContainer");
    if (e.target.checked == true) {
        dataInputContainer.style.display = "block";
        has_limitInput.value = 1;
    } else {
        has_limitInput.value = 0;
        dataInputContainer.style.display = "none";
    }
});

//스케줄 등록
const scheduleCheckbox = document.getElementById("scheduleCheckbox");
const addScheduleInput = document.getElementById("addScheduleInput");
scheduleCheckbox.addEventListener("change", (e) => {
    if (e.target.checked == true) {
        addScheduleInput.value = 1;
    } else {
        addScheduleInput.value = 0;
    }
});

//제출 버튼 클릭시 이벤트
const submitButton = document.getElementById("submitButton");

submitButton.addEventListener("click", function () {
    if (checkInput()) {
        const frm = document.getElementById("frm");
        frm.submit();
    }
});

function checkInput() {
    const name = document.getElementById("name");
    const has_limit = document.getElementById("has_limit");
    const inputDate = document.getElementsByClassName("inputDate");

    if (name.value == "") {
        name.classList.add("is-invalid");
        alert("업무 이름을 적어주세요.");
        return false;
    } else {
        name.classList.remove("is-invalid");
    }
    if (has_limit.checked) {
        for (let i = 0; i < inputDate.length; i++) {
            if (inputDate[i].value == "") {
                alert("일정을 입력해주세요.");
                return false;
            }
        }
    }
    return true;
}

function createMemberList() {
    let html = "";
    for (member of memberList) {
        if (member.selected == true) continue;
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

async function loadCrewList(projectId) {
    const response = await fetch(`/v1/projects/${projectId}/crews`);
    const data = await response.json();
    memberList = data;
    return data;
}
let tagify;
let memberList = [];
document.addEventListener("DOMContentLoaded", async function () {
    const project_id = document.getElementById("projectId").value;
    const modalBody = document.getElementById("modalBody");
    const crewList = await loadCrewList(project_id);
    modalBody.innerHTML = createMemberList(crewList);

    //Tagify
    const input = document.querySelector("input[name=basic]");
    tagify = new Tagify(input, {
        enforceWhitelist: true,
        whitelist: memberList.map(
            (member) => member.name + "(" + member.email + ")"
        ), // 화이트리스트 배열

        dropdown: {
            classname: "tags-look", // 드롭다운 메뉴 엘리먼트 클래스 이름. 이걸로 css 선택자로 쓰면 된다.
            enabled: 1, // 단어 몇글자 입력했을떄 추천 드롭다운 메뉴가 나타날지
            closeOnSelect: true, // 드롭다운 메뉴에서 태그 선택하면 자동으로 꺼지는지 안꺼지는지
        },
    }); // initialize Tagify

    tagify.on("remove", function (e) {
        const deleteTag = e.detail.data.value;
        for (idx in memberList) {
            if (
                memberList[idx].name + "(" + memberList[idx].email + ")" ==
                deleteTag
            ) {
                memberList[idx].selected = false;
            }
        }
        refreshPage();
    });

    tagify.on("dropdown:select", function (e) {
        const deleteTag = e.detail.data.value;
        for (idx in memberList) {
            if (
                memberList[idx].name + "(" + memberList[idx].email + ")" ==
                deleteTag
            ) {
                memberList[idx].selected = true;
            }
        }
        refreshPage();
    });

    tagify.on("add", function (e) {
        const deleteTag = e.detail.data.value;
        for (idx in memberList) {
            if (
                memberList[idx].name + "(" + memberList[idx].email + ")" ==
                deleteTag
            ) {
                memberList[idx].selected = true;
            }
        }
        refreshPage();
    });
});

//멤버 추가 버튼 클릭시 이벤트
const addMemberButton = document.getElementById("addMemberButton");

addMemberButton.addEventListener("click", function () {
    const checked = document.querySelectorAll(
        "#modalBody input[type=checkbox]:checked"
    );
    for (let i = 0; i < checked.length; i++) {
        const member_id = checked[i].value;
        const index = memberList.findIndex((member) => member.id == member_id);
        memberList[index].selected = true;
        const member = memberList[index];
        tagify.addTags(member.name + "(" + member.email + ")");
    }
    refreshPage();
});

function refreshPage() {
    const modalBody = document.getElementById("modalBody");
    modalBody.innerHTML = createMemberList(memberList);

    const charge = document.getElementById("charge");
    const memberIdList = [];
    tagify.value.forEach((value) => {
        for (idx in memberList) {
            if (
                memberList[idx].name + "(" + memberList[idx].email + ")" ==
                value.value
            ) {
                memberIdList.push(memberList[idx].id);
            }
        }
    });
    charge.value = memberIdList.join(",");
}

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
