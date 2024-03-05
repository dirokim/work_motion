const saveButton = document.getElementById("saveButton");
const addButton = document.getElementById("addButton");

const deleteButton = document.getElementById("deleteButton");
const pillsMember = document.querySelector("[aria-controls=pills-member]");
const container = document.getElementById("container");
// const project_id = container.getAttribute("data-bs-projectId");
const memberList = document.getElementById("memberList");
const crewList = document.getElementById("crewList");
const deleteProjectButton = document.getElementById("deleteProjectButton");
const crewInput = document.getElementById("crewInput");

saveButton.addEventListener("click", function () {
    if (checkInput()) {
        const frm = document.getElementById("frm");
        frm.submit();
    }
});

function checkInput() {
    const projectName = document.getElementById("projectName");
    if (projectName.value == "") {
        projectName.classList.add("is-invalid");
        alert("프로젝트 이름을 적어주세요.");
        return false;
    } else {
        projectName.classList.remove("is-invalid");
        return true;
    }
}

pillsMember.addEventListener("click", () => {});

function createMemberList(memberList) {
    let html = "";
    console.log(memberList);
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

function createCrewList(memberList) {
    let html = "";
    for (member of memberList) {
        if (member.selected != true) continue;
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

async function loadMemberList() {
    const response = await fetch(`/v1/projects/members`);
    const data = await response.json();
    memberList.innerHTML = createMemberList(data);
    members = data;
}
loadMemberList();

async function deleteCrew(members) {}

let crews = [];
let members = [];

addButton.addEventListener("click", function () {
    const checked = document.querySelectorAll(
        "#memberList input[type=checkbox]:checked"
    );
    for (let i = 0; i < checked.length; i++) {
        const member_id = checked[i].value;
        const index = members.findIndex((member) => member.id == member_id);
        members[index].selected = true;
    }
    refreshPage();
});

deleteButton.addEventListener("click", function () {
    const checked = document.querySelectorAll(
        "#crewList input[type=checkbox]:checked"
    );
    for (let i = 0; i < checked.length; i++) {
        const member_id = checked[i].value;
        const index = members.findIndex((member) => member.id == member_id);
        members[index].selected = false;
    }
    refreshPage();
});

function refreshPage() {
    memberList.innerHTML = createMemberList(members);
    crewList.innerHTML = createCrewList(members);
    crewInput.value = members
        .filter((member) => member.selected == true)
        .map((member) => member.id)
        .join(",");
}
