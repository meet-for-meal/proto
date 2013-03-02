$(document).ready(function(){

    $('html').click(function() {
        //Hide the menus if visible
        $('#notifications').slideUp(200);
    });

    $('.toggle-notif').click(function(e){
        e.stopPropagation();
        $('#notifications').slideToggle(200);
        $(this).children('.notif').remove();
    });

    $('#featured-restaurants').after('<div id="nav">').cycle({
        fx:     'fade',
        speed:   1000,
        timeout: 3000,
        pager:  '#nav'
    });

});
