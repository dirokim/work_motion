const pass = document.getElementById("password");
const passCheck = document.getElementById("passwordCheck");
const result = document.getElementById("passResult");
const checkResult = document.getElementById("passCheckResult");
const email = document.getElementById("email");
const emailResult =document.getElementById("emailResult");
const joinBtn = document.getElementById("joinBtn");
const frm = document.getElementById("frm");
const reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$/;
let p1 = false;
let p2 = false;
let p3 = false;

pass.addEventListener("click",()=>{
    pass.value='';
    result.innerHTML='';
    passCheck.value='';
    checkResult.innerHTML='';
})

pass.addEventListener("blur",()=>{
    val = pass.value;
    if(reg.test(val)){
        passResult.className = "green";
        result.innerHTML = '비밀번호 사용 가능';
        p1 = true;
    }else{
        passResult.className = "red";
        result.innerHTML = '8이상 숫자,소대문자,특수문자 포함해야함';
        p1 = false;
    }
    passCheck.value='';
    checkResult.innerHTML= '';
});

passCheck.addEventListener('keyup',()=>{
    if(passCheck.value==pass.value){
        passCheckResult.className = "green";
        checkResult.innerHTML="비밀번호 일치 사용 가능";
        p2=true;
    }else{
        passCheckResult.className = "red";
        checkResult.innerHTML="비밀번호가 맞지 않습니다.";
        p2=false;
    }
})



email.addEventListener("blur",()=>{
    fetch("/member/emailcheck?email="+email.value,{                 //ajax 
        method:"GET",
    }).then((r)=>r.text())
    .then(r=>{
        let result = r.trim();
        if(result>0){
            emailResult.className = "green";
            emailResult.innerHTML = "이메일 사용 가능";
            p3=true;
        }else{
            emailResult.className = "red";
            emailResult.innerHTML = "이메일 중복 사용 불가 ";
            p3=false;
        }
    })



})

joinBtn.addEventListener("click",()=>{
    if(p3){
        if(p2){
            if(p1){
                frm.submit();
            }else{
                alert("비밀번호를 확인해주세요")
            }
        
        }else{
            alert("비밀번호가 맞지않습니다");
        }

    }else{
        alert("이메일 중복입니다");

    }
})


