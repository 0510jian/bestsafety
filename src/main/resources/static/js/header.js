function resizeHeaderMenuItem() {
    var logo = document.querySelector('#logo');
    var header = document.querySelector('.header_area');
    var menu = document.querySelector('.menu').childElementCount;

    var menuLength = ((header.offsetWidth - logo.offsetWidth) - (header.offsetWidth / 10)) / menu;
    var menuItems = document.querySelectorAll('.menu_item');

    for(var i=0; i<menuItems.length; i++) {
        menuItems[i].style.width = menuLength + "px";
    }
}