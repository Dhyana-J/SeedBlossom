
const canvas = document.getElementById('jsCanvas');
const ctx = canvas.getContext('2d');
const colors = document.getElementsByClassName("jsColor");
const range = document.querySelector("#jsRange");
const mode = document.querySelector("#jsMode");
const saveBtn = document.querySelector("#jsSave");

console.log(range);

const INITIAL_COLOR = "#2c2c2c";
const CANVAS_SIZE = 700;

canvas.width=CANVAS_SIZE;
canvas.height=CANVAS_SIZE;


// html style속성에 들어있는 color값 가져오기
// let style = document.querySelector('#jsColors>:first-child').getAttribute('style').split(' ')[1];


ctx.fillStyle = "white";
ctx.fillRect(0,0,canvas.width,canvas.height);
ctx.strokeStyle = INITIAL_COLOR;
ctx.fillStyle = INITIAL_COLOR; 
ctx.lineWidth = 5;


let painting = false;
let filling = false;


function stopPainting(){
    painting = false;
}


function startPainting(){
    painting = true;
}


function onMouseMove(event){

    const x = event.offsetX;
    const y = event.offsetY;
    console.log(x,y);

    if(painting){ //painting===true
        ctx.lineTo(x,y);
        ctx.stroke();
        // ctx.closePath();//이거 자체가 경로시작과 연결된 직선을 그리는 메소드다.
    }else{
        ctx.beginPath(); // 그림을 그리기 시작할 포인터 경로 시작점. 첫 beginPath()는 moveTo()로 여겨진다.
        ctx.moveTo(x,y);
    }

}


function handleColorClick(event){
    ctx.strokeStyle = event.target.style.backgroundColor;
    ctx.fillStyle = ctx.strokeStyle;
}

function handleRangeChange(event){
    ctx.lineWidth = event.target.value;   
}

function handleModeClick(event){
    if(filling === true){
        filling = false;
        mode.innerText = "FILL";
    }else{
        filling = true;
        mode.innerText="PAINT";
    }
}

function handleCanvasClick(){
    if(filling===true){
        ctx.fillRect(0,0,canvas.width,canvas.height);
    }
}

function handleCM(event){
    event.preventDefault();
}

function handleSaveClick(event){
    // const image = canvas.toDataURL("image/jpeg");
    const image = canvas.toDataURL(); // default가 png이다.
    let link = document.createElement("a");
    link.href = image; //href로 URL 써주고,
    link.download = "PaintJS" //다운로드시 파일명을 써줘야한다.
    link.click();
}

function changeCursor(event){
    event.target.style="cursor:crosshair";
}

if(canvas){
    canvas.addEventListener('mousemove',onMouseMove);
    canvas.addEventListener('mousedown',startPainting);
    canvas.addEventListener('mouseup',stopPainting);
    canvas.addEventListener('mouseenter',changeCursor);
    canvas.addEventListener('mouseleave',stopPainting);
    canvas.addEventListener('click',handleCanvasClick);
    canvas.addEventListener('contextmenu',handleCM);
} 


if(colors){
    console.log(colors);
    //Array.from(대상배열).forEach(대상배열i번째index 들어갈 값 저장하는 변수 => 가져올값); 대상배열 index수 만큼 반복
    Array.from(colors).forEach(color => color.addEventListener('click',handleColorClick));
    console.log(Array.from(colors)); //document.getElementsByClassName("jsColor"); 로 가져온 배열 출력
}

if(range){
    range.addEventListener("input",handleRangeChange);
}

if(mode){
    mode.addEventListener("click",handleModeClick);
}

if(saveBtn){
    saveBtn.addEventListener('click',handleSaveClick);
}



