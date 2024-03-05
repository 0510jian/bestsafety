let slideIndex = 1;

function moveSlide(num) {
    showSlide(slideIndex += num);
}

function currentSlide(num) {
    showSlide(slideIndex = num);
}

function showSlide(index) {
    const slides = document.getElementsByClassName("slide");
    const dots = document.getElementsByClassName("dot");

    if(index > slides.length) { // 마지막 사진에서 다음 사진으로
        slideIndex = 1;
    }
    if(index < 1) { // 첫번째 사진에서 이전 사진으로
        slideIndex = slides.length;
    }

    resetSlide(slides, dots);
    setSlide(slides, dots, slideIndex);
}

function resetSlide(slides, dots) {
    for(let i=0; i<slides.length; i++) {
        slides[i].style.display = "none";
        dots[i].className = dots[i].className.replace(" active", "");
    }
}

function setSlide(slides, dots, index) {
    slides[index - 1].style.display = "block";
    dots[index - 1].className += " active";
}

function autoSlide() {
    moveSlide(1);
}

function startAutoSlide() {
    setInterval(autoSlide, 7000); // 1s = 1000
}

function cloneRoller() {
    var roller = document.getElementById('roller');
    var children = roller.children;
    var childrenLength = children.length;

    for (var i = 0; i < childrenLength; i++) {
        var clonedChild = children[i].cloneNode(true);
        roller.appendChild(clonedChild);
    }
}

const blank = 20;

function resize() {
    resizeField();
    resizeBoard();
}



function resizeField() {
    var field = document.querySelector('.field');
    var fieldShortcuts = document.querySelectorAll('.field_shortcut');

    var fieldLength = Math.floor((field.offsetWidth - (blank * (fieldShortcuts.length - 1))) / fieldShortcuts.length) - 3;

    for(var i=0; i<fieldShortcuts.length; i++) {
        fieldShortcuts[i].style.width = fieldLength + "px";
        if(i == fieldShortcuts.length-1) continue;
        fieldShortcuts[i].style.marginRight = blank + "px";
    }
}

function resizeBoard() {
    var boards = document.querySelector('.boards');
    var board = document.querySelectorAll('.board');

    var boardLength = Math.floor((boards.offsetWidth - (blank * (board.length - 1))) / board.length) - 3;

    for(var i=0; i<board.length; i++) {
        board[i].style.width = boardLength + "px";
        if(i == board.length-1) continue;
        board[i].style.marginRight = blank + "px";
    }
}