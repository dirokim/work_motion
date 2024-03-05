<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    #chat3 .form-control {
        border-color: transparent;
    }

    #chat3 .form-control:focus {
        border-color: transparent;
        box-shadow: inset 0px 0px 0px 1px transparent;
    }

    .badge-dot {
        border-radius: 50%;
        height: 10px;
        width: 10px;
        margin-left: 2.9rem;
        margin-top: -0.75rem;
    }
    li {
        list-style-type: none;
    }
    #messageBox {
        padding-bottom: 1vh;
    }
    .small.text-muted {
        font-size: 0.7rem !important;
        overflow-wrap: break-word;
    }
    .message {
        max-width: 40vh;
        overflow-wrap: anywhere;
    }
    #messageInput {
        overflow-wrap: break-word;
    }
</style>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Chat</title>
        <!-- Jquery -->
        <script
            src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"
        ></script>
        <!-- Socket -->
        <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

        <!-- Bootstrap CSS -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous"
        />
        <script
            src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
            crossorigin="anonymous"
        ></script>
        <script src="/resources/js/emoji-button3.0.3 min.js"></script>
    </head>
    <body data-bs-roomName="${room.room_name}" data-bs-memberId="${member.id}">
        <section class="h-100">
            <div class="h-100">
                <div class="row h-100">
                    <div class="col-md-12 h-100">
                        <div class="row h-100">
                            <div
                                class="col-md-12 col-lg-12 col-xl-12 d-flex flex-column h-100"
                            >
                                <div
                                    class="d-flex justify-content-between align-items-center p-2"
                                    style="background-color: #e7e3ff"
                                >
                                    <div class="d-flex">
                                        <img
                                            src="${room.avatar}"
                                            alt="avatar"
                                            class="d-flex align-self-center me-3 rounded-1"
                                            width="60"
                                        />
                                        <div>
                                            <div>${room.name}</div>
                                            <!-- <i
                                                class="fa-solid fa-user text-muted"
                                            ></i> -->
                                        </div>
                                    </div>
                                    <div class="p-2">
                                        <a
                                            class="nav-link dropdown-toggle"
                                            id="navbarDropdown"
                                            href="#"
                                            role="button"
                                            data-bs-toggle="dropdown"
                                            aria-expanded="false"
                                            ><i class="fas fa-bars"></i
                                        ></a>
                                        <ul
                                            class="dropdown-menu dropdown-menu-end"
                                            aria-labelledby="navbarDropdown"
                                        >
                                            <li>
                                                <a
                                                    class="dropdown-item"
                                                    href="#"
                                                    id="exitRoomButton"
                                                    >채팅방 나가기</a
                                                >
                                            </li>
                                            <!-- <li>
                                                <hr class="dropdown-divider" />
                                            </li> -->
                                        </ul>
                                    </div>
                                </div>
                                <!-- 대화 들어가는 곳 -->
                                <div
                                    class="pt-3 overflow-auto ps-1 pe-1"
                                    data-mdb-perfect-scrollbar="true"
                                    style="
                                        position: relative;
                                        background-color: #e7e3ff;
                                        height: 100%;
                                    "
                                    id="messageBox"
                                ></div>

                                <div
                                    class="text-muted d-flex justify-content-start align-items-center ps-1 pe-1 mt-2 mb-2"
                                >
                                    <!-- 메시지 타이핑 -->
                                    <textarea
                                        type="text"
                                        class="form-control form-control-lg"
                                        id="messageInput"
                                        placeholder="Type message"
                                        style="height: 0.5rem; resize: none"
                                    ></textarea>
                                    <a
                                        class="ms-1 text-muted ps-1"
                                        id="attachButton"
                                        href="#!"
                                        ><i class="fas fa-paperclip"></i
                                    ></a>
                                    <form
                                        id="frm"
                                        enctype="multipart/form-data"
                                    >
                                        <input
                                            type="file"
                                            name="attach"
                                            hidden="true"
                                            id="attachInput"
                                        />
                                    </form>
                                    <a
                                        class="ps-2 text-muted"
                                        id="emojiButton"
                                        href="#!"
                                        ><i class="fas fa-smile"></i
                                    ></a>
                                    <!-- 전송버튼 -->
                                    <a
                                        class="ms-2 pe-2"
                                        href="javascript:void(0);"
                                        id="sendMessageButton"
                                        ><i class="fas fa-paper-plane"></i
                                    ></a>
                                    <div id="emoji-wrapper"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script type="text/javascript" src="/resources/js/chat.js"></script>
        <!-- Bootstrap JS -->
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
