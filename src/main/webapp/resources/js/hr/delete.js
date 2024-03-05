const deleteBtn = document.getElementById("deleteBtn");
const memberid = document.getElementById("memberid").value;
const div = document.getElementById("layoutSidenav_content");

deleteBtn.addEventListener("click",()=>{

    fetch("/hr/delete",{
        method:"POST",
        headers : {'Content-type': ' application/x-www-form-urlencoded;charset=utf-8'},
        body:"id="+memberid
    
    }).then(r=>r.text())
    .then(r=>{
            div.innerHTML = r;

   
        

    })
})