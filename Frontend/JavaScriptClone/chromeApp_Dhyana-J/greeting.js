const form = document.querySelector(".js-form"),
input = form.querySelector("input");
// input = form.querySelectorAll("input");same with line below.
// const input = document.getElementsByTagName("input");

const greeting = document.querySelector(".js-greeting");

const USER_LS="currentUser",
SHOWING_CN = "showing";

function saveName(text){
    localStorage.setItem(USER_LS,text);
}

function handleSubmit(event){
    event.preventDefault();//새로고침하면서 어디론가 값 보내는거 방지함.
    const currentValue=input.value;
    paintGreeting(currentValue);
    saveName(currentValue);
}

function askForName(){
    form.classList.add(SHOWING_CN);
    form.addEventListener("submit",handleSubmit);
}

function loadName(){
    const currentUser = localStorage.getItem(USER_LS);

    if(currentUser===null){
        askForName();
    }else{
        paintGreeting(currentUser);
    }
}

function paintGreeting(text){
    form.classList.remove(SHOWING_CN);
    greeting.classList.add(SHOWING_CN);
    greeting.innerText=`Hello ${text} :)`;
}

function init(){
    loadName();
}


init();