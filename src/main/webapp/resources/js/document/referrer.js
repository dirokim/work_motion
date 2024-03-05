const rm = document.getElementById("referrer_modal");
const s = document.getElementById("referrer_search");
const modal = document.getElementById("budgetTeamInfo");

const filelist = document.getElementById("filelist");
const fileAdd = document.getElementById("fileAdd");
const del = document.getElementsByClassName("del");
let count = 0;
let max = 5;

let save = []; //new Array();
let save_name = [];
const check = document.getElementsByClassName("checkbox_save");
let update = document.getElementById("update");

filelist.addEventListener("click", (e) => {
    if (e.target.classList.contains("del")) {
        let id = e.target.getAttribute("data-file-id");
        document.getElementById(id).remove();
        count--;
    }
});

let idx = 0;

fileAdd.addEventListener("click", function () {
    if (count >= max) {
        alert("파일5개까지");
        return;
    }
    count++;

    idx++;

    let div = document.createElement("div");
    let class1 = document.createAttribute("class");
    class1.value = "input-group mb-3";
    div.setAttributeNode(class1);
    //id 추가
    class1 = document.createAttribute("id");
    class1.value = "file" + idx;
    div.setAttributeNode(class1);

    let input = document.createElement("input");
    let type = document.createAttribute("type");
    type.value = "file";
    let name1 = document.createAttribute("name");
    name1.value = "file";
    class1 = document.createAttribute("class");
    class1.value = "form-control";
    input.setAttributeNode(type);
    input.setAttributeNode(name1);
    input.setAttributeNode(class1);
    div.appendChild(input);

    let span = document.createElement("span");
    let text = document.createTextNode("X");
    class1 = document.createAttribute("class");
    class1.value = "input-group-text text-danger del";
    span.appendChild(text);
    span.setAttributeNode(class1);
    div.appendChild(span);

    class1 = document.createAttribute("data-file-id");
    class1.value = "file" + idx;
    span.setAttributeNode(class1);

    filelist.appendChild(div);
});

//모달창
modal.addEventListener("click", function (e) {
    if (e.target.classList.contains("modal-btn")) {
        fetch("/docTemplete/referrer", {
            method: "GET",
        })
            .then((r) => r.text())
            .then((r) => {
                rm.innerHTML = r;
                for (let i = 0; i < save.length; i++) {
                    for (let j = 0; j < check.length; j++) {
                        if (
                            save[i] ===
                            check[j].getAttribute("data-referrer-id")
                        ) {
                            check[j].checked = true;
                        }
                    }
                }
            });
    }
});

//페이번호 누르면 리스트
rm.addEventListener("click", (e) => {
    //페이번호 누르면 리스트
    if (e.target.classList.contains("referrer")) {
        let page = e.target.getAttribute("data-page");
        let search = e.target.getAttribute("data-search");
        let kind = e.target.getAttribute("data-kind");

        fetch(
            "/docTemplete/referrer?page=" +
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
                rm.innerHTML = r;
                //체크박스 유지하기
                for (let i = 0; i < save.length; i++) {
                    for (let j = 0; j < check.length; j++) {
                        if (
                            save[i] ===
                            check[j].getAttribute("data-referrer-id")
                        ) {
                            check[j].checked = true;
                        }
                    }
                }
            });
        //체크유무 확인 후 체크
    }

    //검색버튼 누르면 검색 리스트
    if (e.target.classList.contains("ref-btn")) {
        // 선택한 값과 검색한 값을 가져오기
        const kind = document.getElementById("ref_kind").value;
        const search = document.getElementById("search").value;

        // AJAX 요청 보내기
        fetch("/docTemplete/referrer?search=" + search + "&kind=" + kind, {
            method: "GET",
        })
            .then((r) => r.text())
            .then((r) => {
                // AJAX 요청이 완료되고 결과를 처리하기
                rm.innerHTML = r;
            });
    }

    //체크박스 체크하면 멤버ID 저장
    if (e.target.classList.contains("member_id")) {
        if (e.target.checked) {
            //member id 값 배열에 저장
            save.push(e.target.getAttribute("data-referrer-id"));
            save_name.push(e.target.getAttribute("data-member-name"));
        } else {
            for (let i = 0; i < save.length; i++) {
                if (save[i] === e.target.getAttribute("data-referrer-id")) {
                    save.splice(i, 1);
                    save_name.splice(i, 1);
                    i--;
                }
            }
        }
    }
});

update.addEventListener("click", function () {
    let stringName = save_name.join(",");
    let inputName = document.getElementById("referrer");
    inputName.value = stringName;

    let string = save.join(",");
    let input = document.getElementById("member_id_add");
    input.value = string;
});
