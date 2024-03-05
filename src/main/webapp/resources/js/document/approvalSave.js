const am = document.getElementById("approval_modal");
const a_s = document.getElementById("approval_search");
const a_modal = document.getElementById("modal-approval");

let a_save = [];
let a_save_name = [];
let save_department_name = [];

let a_count = 0;
const a_max = 3;

let usedIndices = []; //사용된 인덱스 추적하는 배열
let deletedIndices = []; // 삭제된 인덱스를 추적하는 배열

const a_check = document.getElementsByClassName("checkbox_save");
let approval_update = document.getElementById("approval-update");

const approval_list = document.getElementById("approval-list");

//임시저장 이미 저장되어있는 결재자 id 배열에 넣기
let spanElements = document.querySelectorAll(".sign_rank");

spanElements.forEach((span) => {
    const approvlaId = span.getAttribute("data-approval-id");
    const departmentName = span.getAttribute("data-department-name");
    const memberName = span.getAttribute("data-member-name");
    const indexId = span.getAttribute("data-department-id");

    if (approvlaId.trim() !== "") {
        a_save.push(approvlaId);
        a_save_name.push(memberName + "," + indexId);
        save_department_name.push(departmentName + "," + indexId);
        usedIndices.push(parseInt(indexId));
    }
});

//모달창
a_modal.addEventListener("click", function (e) {
    if (e.target.classList.contains("approval-btn")) {
        fetch("/docTemplete/approval", {
            method: "GET",
        })
            .then((r) => r.text())
            .then((r) => {
                am.innerHTML = r;
                for (let i = 0; i < a_save.length; i++) {
                    for (let j = 0; j < check.length; j++) {
                        if (
                            a_save[i] ===
                            a_check[j].getAttribute("data-referrer-id")
                        ) {
                            a_check[j].checked = true;
                        }
                    }
                }
            });
    }
});

//페이번호 누르면 리스트
am.addEventListener("click", (e) => {
    //페이번호 누르면 리스트
    if (e.target.classList.contains("approval")) {
        let page = e.target.getAttribute("data-page");
        let search = e.target.getAttribute("data-search");
        let kind = e.target.getAttribute("data-kind");

        fetch(
            "/docTemplete/approval?page=" +
                page +
                "&search=" +
                search +
                "&kind=" +
                kind,
            {
                method: "GET",
            }
        )
            .then((r) => r.text())
            .then((r) => {
                am.innerHTML = r;

                for (let i = 0; i < a_save.length; i++) {
                    for (let j = 0; j < check.length; j++) {
                        if (
                            a_save[i] ===
                            a_check[j].getAttribute("data-referrer-id")
                        ) {
                            a_check[j].checked = true;
                        }
                    }
                }
            });
    }

    //검색버튼 누르면 검색 리스트
    if (e.target.classList.contains("ref-btn")) {
        // 선택한 값과 검색한 값을 가져오기
        const kind = document.getElementById("approvla_kind").value;
        const search = document.getElementById("approval_search").value;

        // AJAX 요청 보내기
        fetch("/docTemplete/approval?search=" + search + "&kind=" + kind, {
            method: "GET",
        })
            .then((r) => r.text())
            .then((r) => {
                // AJAX 요청이 완료되고 결과를 처리하기
                am.innerHTML = r;
            });
    }

    //체크박스 체크하면 멤버ID 저장
    if (e.target.classList.contains("member_id")) {
        if (e.target.checked) {
            if (a_count >= a_max) {
                alert("결재자는 최대 3명입니다.");
                e.target.checked = false;
                return;
            }

            function getNextIndex() {
                for (let i = 0; i < 3; i++) {
                    if (
                        !usedIndices.includes(i) ||
                        deletedIndices.includes(i)
                    ) {
                        if (deletedIndices.includes(i)) {
                            // 삭제된 인덱스를 다시 사용할 때는 해당 인덱스를 제거하고 사용
                            deletedIndices.splice(deletedIndices.indexOf(i), 1);
                        }
                        usedIndices.push(i); // 사용된 인덱스를 추적
                        return i;
                    }
                }
                return -1; // 모든 인덱스가 사용되었음을 나타내는 값
            }

            //member id 값 배열에 저장
            a_save.push(e.target.getAttribute("data-referrer-id"));

            let memberName = e.target
                .getAttribute("data-member-name")
                .split(",");
            let departmentName = e.target
                .getAttribute("data-department-name")
                .split(",");

            for (let i = 0; i < memberName.length; i++) {
                let currentIndex = getNextIndex(); // 0, 1, 2 중 하나를 반복
                // 모든 인덱스가 사용되었을 경우 처리
                if (currentIndex === -1) {
                    alert("결재자는 3명까지 가능합니다.");
                    break;
                }

                let dataString = memberName + "," + currentIndex;
                a_save_name.push(dataString);

                let dataString2 = departmentName + "," + currentIndex;
                save_department_name.push(dataString2);
            }
            a_count++;
        } else {
            for (let i = 0; i < a_save.length; i++) {
                if (a_save[i] === e.target.getAttribute("data-referrer-id")) {
                    let index = a_save_name[i].split(",")[1]; // 삭제된 인덱스 추적
                    deletedIndices.push(parseInt(index)); // 삭제된 인덱스를 추적 배열에 추가
                    a_save.splice(i, 1);
                    a_save_name.splice(i, 1);
                    save_department_name.splice(i, 1);
                    i--;
                    a_count--;
                }
            }
        }
    }
});
approval_update.addEventListener("click", function (e) {
    for (let i = 0; i < 3; i++) {
        let indexExists = a_save_name.some((entry) => entry.split(",")[1] == i);

        // 배열에 현재 인덱스가 저장되어 있는지 확인
        if (indexExists) {
            // 배열에 현재 인덱스가 저장되어 있으면 해당 요소의 값을 가져와서 설정
            let nameElement = document.getElementById("approval-name" + i);
            let departmentElement = document.getElementById(
                "approvla-department-name" + i
            );

            // 배열에서 인덱스 i에 해당하는 요소를 찾음
            let entry = a_save_name.find((entry) => entry.split(",")[1] == i);

            // 요소가 존재하면 해당 요소의 값을 설정, 아니면 빈 문자열 설정
            if (entry) {
                nameElement.textContent = entry.replace(/[0-9,]/g, "");
                departmentElement.textContent = save_department_name[i].replace(
                    /[0-9,]/g,
                    ""
                );
            } else {
                nameElement.textContent = "";
                departmentElement.textContent = "";
            }
        } else {
            // 배열에 현재 인덱스가 없으면 빈 텍스트를 설정
            let nameElement = document.getElementById("approval-name" + i);
            let departmentElement = document.getElementById(
                "approvla-department-name" + i
            );

            // 빈 텍스트 설정
            nameElement.textContent = "";
            departmentElement.textContent = "";
        }

        let string = a_save.join(",");
        let input = document.getElementById("approval-member-id");
        input.value = string;
    }
});
