const saveButton = document.getElementById("saveButton");
const deleteButton = document.getElementById("deleteButton");
const pillsMember = document.querySelector("[aria-controls=pills-member]");
const container = document.getElementById("edit_container");
const project_id = container.getAttribute("data-bs-projectId");
const memberList = document.getElementById("memberList");
const deleteProjectButton = document.getElementById("deleteProjectButton");

deleteProjectButton.addEventListener("click", () => {
    if (confirm("정말 삭제하시겠습니까?")) {
        const form = document.createElement("form");
        const input = document.createElement("input");
        input.setAttribute("name", "id");
        input.setAttribute("value", project_id);
        form.appendChild(input);
        form.setAttribute("method", "POST");
        form.setAttribute("action", "/projects/delete");
        document.body.appendChild(form);
        form.submit();
    }
});
pillsMember.addEventListener("click", () => {});

function createCrewList(memberList) {
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

async function loadCrewList() {
    const response = await fetch(`/v1/projects/${project_id}/crews`);
    const data = await response.json();
    memberList.innerHTML = createCrewList(data);
}
//loadCrewList();

async function deleteCrew(members) {}

deleteButton.addEventListener("click", async function () {
    const checked = document.querySelectorAll("input[type=checkbox]:checked");
    let deleteMembers = [];
    for (let i = 0; i < checked.length; i++) {
        const member_id = checked[i].value;
        deleteMembers.push(member_id);
    }
    const response = await fetch(
        `/v1/projects/${project_id}/crews/` + deleteMembers.join(","),
        {
            method: "DELETE",
        }
    );

    const data = await response.json();
    loadCrewList();
});

const changeOwnerButton = document.getElementById("changeOwnerButton");

changeOwnerButton.addEventListener("click", async function () {
    if (confirm("그룹장을 변경하겠습니까?")) {
        const checked = document.querySelectorAll(
            "input[type=checkbox]:checked"
        );
        let owner = [];
        for (let i = 0; i < checked.length; i++) {
            const member_id = checked[i].value;
            owner.push(member_id);
        }
        if (owner.length != 1) {
            alert("한 명을 선택해주세요.");
            return;
        }
        const response = await fetch(
            `/v1/projects/${project_id}/changeOwner/` + owner.join(""),
            {
                method: "PUT",
            }
        );
        const data = await response.json();
        if (data == 1) {
            alert("성공적으로 변경되었습니다.");
            location.href = "/projects/detail?id=" + project_id;
        }
    }
});
