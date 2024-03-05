const listTitle = document.querySelectorAll(".listTitle");
// const atag = document.getElementById("atag");

for (let i = 0; i < listTitle.length; i++) {
    listTitle[i].addEventListener("click", function (e) {
        let child = e.target.children;
        window.location.href = child[0].href;
    });
}
