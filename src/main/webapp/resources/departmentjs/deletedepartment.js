
let checks = document.getElementsByClassName("checks");
let deletebtn = document.getElementById("deleteDepartment");
let department_id = document.getElementsByClassName("id");



deletebtn.addEventListener("click", function(){
let num =[];
   for(let i = 0; i<checks.length;i++){
    if(checks[i].checked){
        num.push(department_id[i].value)
    }}
        fetch("/department/departmentDelete",{

            method:"POST",
            headers:{
                "Content-type":"application/x-www-form-urlencoded;charset=UTF-8"
            },
            body:"id="+num
        })
        .then(r =>r.text())
        .then(re =>{
        
            if(re>0){
                alert("부서 삭제 성공했습니다")
            }else{
                alert("부서 삭제 실패했습니다")
            }
            location.href="/department/departmentList"
        })





})
