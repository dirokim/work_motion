const pw = document.getElementById("pw");
const pwResult = document.getElementById("pwResult");
const pwCheckBtn = document.getElementById("pwCheckBtn");
const mypageFrm = document.getElementById("mypageFrm");
const updateBtn = document.getElementById("updateBtn");
const updatePw = document.getElementById("updatePw");
const updateFrm =document.getElementById("updateFrm");
let check = false;

pwCheckBtn.addEventListener("click",()=>{       
    // let formdata = new FormData(mypageFrm);
    fetch("/member/pwCheck",{                       //ajax post 
        method:"POST",
        headers : {'Content-type': ' application/x-www-form-urlencoded;charset=utf-8'},
        body:"pass="+pw.value
    }).then(r=>r.text())
    .then(r=>{
        if(r>0){
            pwResult.innerHTML= '비밀번호 확인되었습니다';
            check = true;
 
        }else{
            pwResult.innerHTML= '비밀번호 확인 실패';
            check = false;
        }
    })
})
   
updateBtn.addEventListener("click",()=>{
            if(check){                 
                updateFrm.submit();
            }else{
                alert("비밀번호를 확인해주세요");
                location.href = "/member/mypage";
            }
})






