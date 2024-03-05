var socket = new SockJS("/websocket-example");
var stompClient = Stomp.over(socket);
const sendMessageButton = document.getElementById("sendMessageButton");
const room_name = document
    .getElementsByTagName("body")[0]
    .getAttribute("data-bs-roomName");
const member_id = document
    .getElementsByTagName("body")[0]
    .getAttribute("data-bs-memberId");
//무한로딩
let options = {
    threshold: 0,
};
let Page = 1;
let $end;
//무한스크롤
const callback = (entries, observer) => {
    entries.forEach(async (entry) => {
        if (entry.isIntersecting) {
            const $result = document.querySelector("#messageBox");
            Page++;
            observer.unobserve($end);
            const loadingElement = document.createElement("div");
            loadingElement.classList.add(
                "d-flex",
                "flex-row",
                "justify-content-center"
            );
            const loadingHtml = `
            <div class="spinner-border mb-3 mt-1" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
            `;

            loadingElement.innerHTML = loadingHtml;
            $result.insertAdjacentElement("afterbegin", loadingElement);
            loadingElementHeight = loadingElement.offsetHeight;
            const data = await getMessage();
            loadingElement.remove();
            let messageElements = new DocumentFragment();
            for (let message of data.messages) {
                $result.prepend();
                messageElements.appendChild(
                    showMessage(message, messageElements)
                );
            }
            const tempHeight = messageBox.scrollHeight;
            $result.insertBefore(messageElements, $result.firstChild);
            $end = $result.firstElementChild;
            messageBox.scrollTop =
                messageBox.scrollHeight - tempHeight - loadingElementHeight;
            if (data.messages.length < 20) return;
            observer.observe($end);
        }
    });
};

const observer = new IntersectionObserver(callback, options);
function chageDate(timestamp) {
    let date = new Date(timestamp);
    // 월을 약어 형식으로 변환
    var monthNames = [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec",
    ];
    var monthAbbreviation = monthNames[date.getMonth()];

    // 날짜와 시간을 AM/PM 형식으로 변환
    var hours = date.getHours();
    var ampm = hours >= 12 ? "오후" : "오전";
    hours = hours % 12;
    hours = hours ? hours : 12; // 0 시간을 12시로 표시
    var minutes = date.getMinutes();
    minutes = minutes < 10 ? "0" + minutes : minutes;

    // 결과 문자열 생성
    var result = ampm + " " + hours + ":" + minutes;
    // +
    // " " +
    // ampm +
    // " | " +
    // monthAbbreviation +
    // " " +
    // date.getDate();
    return result;
}

const messageInput = document.getElementById("messageInput");

messageInput.addEventListener("keydown", function (e) {
    if (e.key == "Enter" && !e.shiftKey) {
        e.preventDefault();
    }
});
messageInput.addEventListener("keyup", function (e) {
    if (e.key == "Enter" && !e.shiftKey) {
        sendMessageButton.click();
    }
});
sendMessageButton.addEventListener("click", function () {
    sendMessage(messageInput.value, "message");
    messageInput.value = "";
});

function sendMessage(value, type) {
    if (value.trim() != "" && value.length != 0) {
        message = {
            sender_id: member_id,
            message: value,
            time: new Date(),
            room_name: room_name,
            type: type,
        };
        stompClient.send("/app/sendMessage", {}, JSON.stringify(message));
    }
}
function readMessage() {
    message = {
        sender_id: member_id,
        message: "",
        time: new Date(),
        room_name: room_name,
    };
    stompClient.send("/app/readMessage", {}, JSON.stringify(message));
}
const messageBox = document.getElementById("messageBox");
stompClient.connect({}, function (frame) {
    stompClient.subscribe(
        "/chat/messages/" + room_name,
        function (outputMessage) {
            readMessage();
            messageBox.append(
                showMessage(JSON.parse(outputMessage.body), messageBox)
            );
            messageBox.scrollTop = messageBox.scrollHeight;
        }
    );
});

async function getMessage() {
    const response = await fetch(
        "/chat/getMessage?room_name=" +
            room_name +
            "&member_id=" +
            member_id +
            "&page=" +
            Page
    );
    const data = await response.json();
    if (response.status == 200) {
        readMessage();
        return data;
    }
    return null;
}

document.addEventListener("DOMContentLoaded", async function () {
    const data = await getMessage();
    let temp = true;
    let tempHeight;
    const messages = data.messages.entries();
    for (let [index, message] of messages) {
        if (data.room.recently_dt < message.time && temp) {
            temp = false;
            tempHeight = messageBox.scrollHeight;
            if (data.messages.length > 10) {
                showPrevLine();
            }
        }
        messageBox.appendChild(showMessage(message, messageBox));
    }

    const $result = document.querySelector("#messageBox");
    $end = $result.firstElementChild;
    if (data.messages.length >= 20) {
        observer.observe($end);
    }

    if (tempHeight != null) {
        messageBox.scrollTop = tempHeight;
    } else {
        messageBox.scrollTop = messageBox.scrollHeight;
    }
});

function showMessage(message, element) {
    var messageElement = document.createElement("li");
    message.time = chageDate(message.time);
    if (message.sender_id == member_id) {
        messageElement.innerHTML = createSendMessage(message, element, "send");
    } else {
        messageElement.innerHTML = createReceiveMessage(
            message,
            element,
            "recieve"
        );
    }
    return messageElement;
}

function showPrevLine() {
    messageBox.innerHTML += `
    <div
        class="d-flex flex-row justify-content-center"
    >
        <p
        class="small p-1 me-3 mb-2 mt-2 text-white rounded-3 bg-warning"
        >
            여기까지 읽었습니다.
        </p>
    </div<
    `;
}

function createSendMessage(message, element, type) {
    let show = true;
    let html = "";
    const prevMessage = getPrevMessageInfo(element, type);
    if (
        prevMessage != null &&
        prevMessage?.name == message.sender.name &&
        prevMessage?.time == message.time
    ) {
        prevMessage.pTag[1].classList.add("d-none");
        show = false;
    }

    if (show) {
        html = `
            <div
            class="d-flex flex-row justify-content-end mt-2 "
            >
                <div class="d-flex flex-column align-items-end ">
                        <p
                        class="small text-muted me-3 mb-0 d-none"
                        >${message.sender.name}</p>
                    <div class="d-flex align-items-end">
                        <p
                            class="small me-1 mb-0 rounded-3 text-muted"
                        >${message.time}</p>
                        ${
                            message.type == "message"
                                ? `<p
                                        class="small p-2 me-2 mb-0 text-white rounded-4 bg-primary message"
                                        style="border-top-right-radius:0px!important;"
                                    >${message.message.replace(
                                        /\n/g,
                                        "<br>"
                                    )}</p>`
                                : `<img
                                        src="${message.message}"
                                        class="me-2 mb-0 rounded-4 message"
                                    /><p></p>`
                        }
                        
                    </div>
                </div>
            </div>
        `;
    } else {
        html = `
            <div
            class="d-flex flex-row justify-content-end mt-1"
            >
                <div class="d-flex flex-column align-items-end message">
                        <p
                        class="small text-muted me-3 mb-0 d-none"
                        >${message.sender.name}</p>
                    <div class="d-flex align-items-end">
                        <p
                            class="small me-1 mb-0 rounded-3 text-muted"
                        >${message.time}</p>
                        ${
                            message.type == "message"
                                ? `<p
                                        class="small p-2 me-2 mb-0 text-white rounded-4 bg-primary message"
                                    >${message.message.replace(
                                        /\n/g,
                                        "<br>"
                                    )}</p>`
                                : `<img
                                        src="${message.message}"
                                        class="me-2 mb-0 rounded-4 message"
                                    /><p></p>`
                        }
                    </div>
                </div>
            </div>
            `;
        return html;
    }
    return html;
}

function createReceiveMessage(message, element, type) {
    let show = true;
    let html = "";
    const prevMessage = getPrevMessageInfo(element, type);
    if (
        prevMessage != null &&
        prevMessage?.name == message.sender.name &&
        prevMessage?.time == message.time
    ) {
        prevMessage.pTag[2].classList.add("d-none");
        show = false;
        html = `
        <div
        class="d-flex flex-row justify-content-start mt-1"
        >
            <span style='width:45px;'></span>
            <div class="d-flex flex-column align-items-start">
                        <p
                        class="small text-muted ms-3 mb-0 d-none"
                        >${message.sender.name}</p>
                <div class="d-flex align-items-end">
                ${
                    message.type == "message"
                        ? `<p
                                class="small p-2 ms-2 mb-0  rounded-4  message"
                                style="background-color: #f5f6f7;"
                            >${message.message.replace(/\n/g, "<br>")}</p>`
                        : `<img
                                src="${message.message}"
                                class="me-2 mb-0 rounded-4 message"
                            /><p></p>`
                }
                <p
                    class="small ms-1 rounded-3 text-muted mb-0"
                >${message.time}</p>
                </div>
            </div>
        </div>
        `;
        return html;
    }
    html = `
    <div
        class="d-flex flex-row justify-content-start mt-2"
    >
        <img
            src="${message.sender.avatar.name}"
            alt="avatar 1"
            style="width: 45px; height: 100%"
        />
        <div class="d-flex flex-column align-items-start">
            <p
            class="small text-muted ms-3 mb-0"
            >${message.sender.name}</p>
            <div class="d-flex align-items-end">
                ${
                    message.type == "message"
                        ? `<p
                                class="small p-2 ms-2 mb-0  rounded-4  message"
                                style="border-top-left-radius:0px!important;background-color: #f5f6f7;"
                            >${message.message.replace(/\n/g, "<br>")}</p>`
                        : `<img
                                src="${message.message}"
                                class="me-2 mb-0 rounded-4 message"
                            /><p></p>`
                }
                <p
                    class="small ms-1 rounded-3 text-muted mb-0"
                >${message.time}</p>
            </div>
        </div>
    </div>`;
    return html;
}

function getPrevMessageInfo(element, type) {
    const pTag =
        element?.lastChild?.firstElementChild?.getElementsByTagName("p");
    if (!pTag || pTag?.length == 0) return null;
    let name, message, time;
    if (type == "recieve") {
        name = pTag[0].innerText;
        // message = pTag[1].innerText;
        time = pTag[2].innerText;
    } else {
        name = pTag[0].innerText;
        // message = pTag[2].innerText;
        time = pTag[1].innerText;
    }

    return { name, message, time, pTag };
}

const emojiButton = document.querySelector("#emojiButton");
const picker = new EmojiButton({
    i18n: {
        search: "Search emojis...",
        categories: {
            recents: "Recent Emojis",
            smileys: "Smileys & Emotion",
            people: "People & Body",
            animals: "Animals & Nature",
            food: "Food & Drink",
            activities: "Activities",
            travel: "Travel & Places",
            objects: "Objects",
            symbols: "Symbols",
            flags: "Flags",
        },
        notFound: "No emojis found",
    },
});

picker.on("emoji", (emoji) => {
    messageInput.value += emoji;
});

emojiButton.addEventListener("click", () => {
    picker.togglePicker(document.getElementById("emoji-wrapper"));
});

const attachButton = document.getElementById("attachButton");
attachButton.addEventListener("click", () => {
    const attachInput = document.getElementById("attachInput");
    attachInput.click();
    attachInput.addEventListener("change", async (e) => {
        const form = document.getElementById("frm");
        const formData = new FormData(form);
        const response = await fetch("/v1/files", {
            method: "POST",
            body: formData,
        });
        const data = await response.json();
        for (let url of data) {
            sendMessage(url, "image");
        }
    });
});

const exitRoomButton = document.getElementById("exitRoomButton");
exitRoomButton.addEventListener("click", async () => {
    // const formData = new FormData();
    // formData.append("room_name", room_name);
    // const response = await fetch("/chat/exitRoom", {
    //     method: "POST",
    //     body: formData,
    // });
    stompClient.send(
        "/app/exitRoom",
        {},
        JSON.stringify({ room_name: room_name })
    );
    window.close();
});
