window.onload = function() {
    showSlide(1);
    startAutoSlide();
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