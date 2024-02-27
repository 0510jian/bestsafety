window.onload = function() {
    changePageCount();
}

function changePageCount() {
    document.getElementById("count" + count).style.background = "#185b9f";
    document.getElementById("page" + count).style.color = "white";
}