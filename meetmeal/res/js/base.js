mfm = {
    host: 'meetformeal',
    config: {
        debug: false
    },
    actions: {
        popAlert : function(req){

            var type = '';
            var text = '';

            switch (req) {
                case 'accept-request':
                    type = 'succes';
                    text = 'Félicitations ! La demande d\'amitié a été confirmée.';
                    break;
                case 'ignore-request':
                    type = 'erreur';
                    text = 'La demande d\'amitié a été ignorée.';
                    break;
                case 'friend-request':
                    type = 'succes';
                    text = 'Demande d\'amitié envoyée';
                    break;
                default:
            }
            var alert = '<div class="alert '+type+'"><a class="close" href="#">x</a>'+text+'</div>';

            if($('#alerts').length !== 0){
                $('#alerts').html('');
                $('#alerts').prepend(alert);
                $('.alert').slideDown(300);
            } else {
                $('body').prepend('<div id="alerts">'+alert+'</div>');
                $('.alert').slideDown(300);
            }

            setTimeout(function(){
                jQuery('.alert').slideUp(300);
            },5000 );

        }
    }
};

$(document).ready(function(){

    // Home page
    $('body')
        .off('click', '.close')
        .on('click', '.close', function(e){
            e.preventDefault();
            $(this).parent('.alert').fadeOut(300);
    });

    $('.message.erreur').each(function(e){

       $(this).siblings('input').css({
           borderColor: '#ff524f',
           boxShadow : 'inset 0 1px 10px #ff524f'
       });

    });

    // General

    $('html').click(function() {
        //Hide the menus if visible
        $('#notifications').slideUp(200);
    });

    $('.toggle-notif').click(function(e){
        e.stopPropagation();
        $('#notifications').slideToggle(200);
        $(this).children('.notif').remove();
    });

    $('#notifications').click(function(e){
        e.stopPropagation();

    });

    $('.mfm-action').click(function(e){
        e.preventDefault();
        var self = $(this);
        var actionType = $(this).attr('data-action');
        if(typeof actionType !== 'undefined' && actionType.length !== 0){
            switch (actionType) {
                case 'accept-request':
                    if(self.parent().hasClass('notification-action')){
                        self.closest('li').slideUp(200);
                    }
                    mfm.actions.popAlert(actionType);
                    break;
                case 'ignore-request':
                    if(self.parent().hasClass('notification-action')){
                        self.closest('li').slideUp(200);
                    }
                    mfm.actions.popAlert(actionType);
                    break;
                case 'friend-request':
                    mfm.actions.popAlert(actionType);
                    break;
                default:
            }
        }
    });

    $('#toggle-map').click(function(){
        var map = $('#meetformealmap');
        if(map.hasClass('deployed')) {
            map.animate({
                height: 130
            }, 300, function() {
                map.removeClass()
                map.addClass('not-deployed');
            });
            $('#closest-users').fadeOut(150);
        } else if (map.hasClass('not-deployed')) {
            map.animate({
                height: 400
            }, 500, function() {
                map.removeClass()
                map.addClass('deployed');
            });
            $('#closest-users').fadeIn(250);
        }
    });

    $.getJSON('/'+mfm.host+'/ajax/interests/false', function(data) {

        $("#interests").autocomplete({
            source: data,
            appendTo: "#suggestions"
        });

    });

    $('#featured-restaurants').after('<div id="nav">').cycle({
        fx:     'fade',
        speed:   1000,
        timeout: 3000,
        pager:  '#nav'
    });

});