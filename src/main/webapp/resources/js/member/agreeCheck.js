const all = document.getElementById("all");
const checks = document.getElementsByClassName("checks");
const btn = document.getElementById("btn");  
const abc = document.getElementsByClassName("abc");
const fil1 = document.getElementById("fil1");
const fil2 = document.getElementById("fil2");
const fil3 = document.getElementById("fil3");


//전체 동의
 all.addEventListener("click",()=>{
    let v = all.getAttribute("checked");
    v = all.checked;
    for(let ch of checks){
        ch.checked = v;
    }

 });

for(let che of checks){
    che.addEventListener("click",()=>{
        
        if(this.checked== false){
            all.checked = false;
        }else if(checks!=false){
            all.checked=true; 
        }
    });
}




btn.addEventListener("click",(e)=>{
    e.preventDefault();
    let flag = true;

 for(let ah of abc){
        if(!ah.checked){
            flag=!flag;
            break;
        }
    }
    if(flag){
            location.href="/member/join";
    }else{
        alert('필수 약관 동의해주세요');
    }

    if(fil1.checked==true && fil2.checked==true && fil3.checked==true){
        location.href="/member/join";
    }

 });



 

