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

});
