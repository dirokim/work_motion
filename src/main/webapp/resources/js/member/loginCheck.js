const signUpBtn = document.getElementById("signUp");
const signInBtn = document.getElementById("signIn");
const container = document.querySelector(".container");
const findIdBtn = document.getElementById("findIdBtn");
const nameId =document.getElementById("nameId");
const phoneId = document.getElementById("phoneId");
const findIdFrm = document.getElementById("findIdFrm");
let msg2 =document.getElementById("msg2");
let msg3 = document.getElementById("msg3");
const findPwBtn = document.getElementById("findPwBtn");
const emailNumBtn = document.getElementById("emailNumBtn");
const emailNum = document.getElementById("emailNum");
const findPWname = document.getElementById("findPWname");
const findPWemail = document.getElementById("findPWemail");
const findPWphone = document.getElementById("findPWphone");

signUpBtn.addEventListener("click", () => {
  container.classList.add("right-panel-active");
});
signInBtn.addEventListener("click", () => {
  container.classList.remove("right-panel-active");
});

findPwBtn.addEventListener("click",()=>{
  if(findPWname.value == ""){
    alert("이름을 입력해 주세요");
  }else{
    if(findPWemail.value ==""){
      alert("이메일을 입력해 주세요");
    }else{
      if(findPWphone.value == ""){
        alert("전화번호를 입력해 주세요");
      }else{
        fetch("/member/findPassWord",{
          method:"post",
          headers : {'Content-type': ' application/x-www-form-urlencoded;charset=utf-8'},
          body:"name="+findPWname.value+"&email="+findPWemail.value+"&phone="+findPWphone.value
        }).then(r=>r.text())
        .then(r=>{
          r.trim();
          if(r==2){
            alert("회원님의 메일로 임시 비밀번호를 발송해 드렸습니다");
            msg3.innerHTML = "회원님의 메일로 임시 비밀번호를 발송해 드렸습니다";
          }else if(r==1){ 
            msg3.innerHTML ="찾을 수 없는 회원 입니다"; 
            alert("찾을 수 없는 회원 입니다")
          }else if(r==0){
            msg3.innerHTML ="잘못된 요청입니다 재시도 해주세요"; 
            alert("잘못된 요청입니다 재시도 해주세요")
          }
        })
      }
    }

  }

});


findIdBtn.addEventListener("click",()=>{
     if(nameId.value ==""){         //아이디 입력
         alert("이름을 입력해 주세요");
       }else{
         if(phoneId.value ==""){     //전화번호 입력
         alert("전화번호를 입력해 주세요");
         }else{
            fetch("/member/findId",{
              method:"post",
              headers : {'Content-type': ' application/x-www-form-urlencoded;charset=utf-8'},
              body:"name="+nameId.value+"&phone="+phoneId.value
            }).then(result=>result.text())
            .then(result=>{
              result.trim();
              if(result==""){
                msg2.innerHTML = "등록된 아이디가 없습니다"
              }else{
                msg2.innerHTML =""+nameId.value+"님 ID:"+result+"입니다"; 
              }
            })
          
         }
       }
     

})
