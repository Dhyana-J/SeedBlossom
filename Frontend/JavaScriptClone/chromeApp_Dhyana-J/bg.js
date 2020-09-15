const body = document.querySelector("body");

const IMG_NUMBER = 4; 

function handleImgLoad(){
    console.log('finished loading');
}

function paintImage(imgNumber){
    const image = new Image();
    image.src=`./img/${imgNumber}.jpg`;
    body.appendChild(image);
    // image.addEventListener("loadend",handleImgLoad());
    image.classList.add('bgImage');
}

function genRandom(){
    const number = Math.floor(Math.random()*IMG_NUMBER+1);
    return number;
}

function init(){
    const randomNumber = genRandom();
    paintImage(randomNumber);
}

init();