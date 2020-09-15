
const clear = document.querySelector(".js-clear");

function clearToDo(){
    localStorage.clear();
    location.href=location.href;
}

function init(){
    clear.addEventListener("click",clearToDo);
}

init();