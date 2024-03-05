const createBtn = document.getElementsByClassName("createBtn");
const div = document.getElementById("layoutSidenav_content");


for(let crea of createBtn)
crea.addEventListener("click",(e)=>{
      let mid = e.target.getAttribute("data-id");
      let companyId = e.target.getAttribute("data-company");
        fetch("/hr/create",{
            method:"POST",
            headers : {'Content-type': ' application/x-www-form-urlencoded;charset=utf-8'},
            body:"id="+mid
        }).then(r=>r.text())
        .then(r=>{
            r= r.trim();
            if(r>0){
                alert("사원 추가 성공");
                location.href = "/hr/list";
            }else{
                alert("사원 등록 실패");
                location.href = "/hr/list";
            }
        
        })

    })

