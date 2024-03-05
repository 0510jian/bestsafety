window.onload = function() {
    showSlide(1);
    startAutoSlide();
    cloneRoller();
    resizeHeaderMenuItem();
}

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

function resizeHeaderMenuItem() {
    var logo = document.querySelector('#logo');
    var header = document.querySelector('.header_area');
    var menu = document.querySelector('.menu').childElementCount;

    var length = ((header.offsetWidth - logo.offsetWidth) - (header.offsetWidth / 10)) / menu;
    var menuItems = document.querySelectorAll('.menu_item');

    for(var i=0; i<menuItems.length; i++) {
        menuItems[i].style.width = length + "px";
    }
}