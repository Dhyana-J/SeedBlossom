const toDoForm = document.querySelector(".js-toDoForm"),
toDoInput = toDoForm.querySelector("input"),
toDoList = document.querySelector(".js-toDoList");

const TODOS_LS = "toDos";

let toDos = [];

function deleteToDo(event){
    const btn = event.target;
    const li = btn.parentNode;
    toDoList.removeChild(li);
    //filter는 ()안에 for each처럼 수행되는 함수에서 true값만 가지고 array를만든다.
    const cleanToDos = toDos.filter(function(toDo){
        // console.log(toDo.id, li.id); 삭제버튼눌린 타겟의 list.id랑 다른것만 반환.
        return toDo.id !== parseInt(li.id);
    });
    toDos = cleanToDos;
    saveToDos();
}

function saveToDos(){
    //기본적으로 자바스크립트는 localStorage에 있는 모든 데이터를 String으로 저장한다.
    localStorage.setItem(TODOS_LS,JSON.stringify(toDos));
    //JSON.stringify는 자바스크립트 object를 string으로 바꿔준다.
    //JSON (JavaScript Object Notation)
}

function paintToDo(text){
    const li=document.createElement("li");
    const delBtn = document.createElement("button");
    const span = document.createElement("span");
    const newId = toDos.length+1;
    delBtn.innerHTML='❌';
    delBtn.addEventListener("click",deleteToDo);
    span.innerText=text;
    li.appendChild(span);
    li.appendChild(delBtn);
    li.id=newId;
    toDoList.appendChild(li);
    const toDoObj = {
        text:text,
        id:newId
    }
    toDos.push(toDoObj);  
    saveToDos();
}

function handleSubmit(event){
    event.preventDefault();
    const currentValue = toDoInput.value;
    paintToDo(currentValue);
    toDoInput.value="";
}



function loadToDos(){
    const loadedToDos = localStorage.getItem(TODOS_LS);
    if(loadedToDos!==null){
        const parsedToDos = JSON.parse(loadedToDos);
         parsedToDos.forEach(function something(toDo){
            paintToDo(toDo.text);
        });
    }
}

function init(){
    loadToDos();
    toDoForm.addEventListener("submit",handleSubmit);
}

init();