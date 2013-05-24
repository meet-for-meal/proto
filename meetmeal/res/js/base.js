mfm = {
    host: 'meetformeal',
    config: {
        debug: false
    },
    actions: {
        popAlert : function(text, type){

            var alert = '<div class="alert '+type+'"><a class="close" href="#">x</a>'+text+'</div>';

            if($('#alerts').length !== 0){
                $('#alerts').html('');
                $('#alerts').prepend(alert);
                $('.alert').slideDown(300);
            } else {
                $('#container').prepend('<div id="alerts">'+alert+'</div>');
                $('.alert').slideDown(300);
            }

            setTimeout(function(){
                $('.alert').slideUp(300);
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

    $('body')
        .off('click', '.mfm-action')
        .on('click', '.mfm-action', function(e){
            e.preventDefault();
            var self = $(this);
            var actionType = $(this).attr('data-action');
            if(typeof actionType !== 'undefined' && actionType.length !== 0){
                switch (actionType) {
                    case 'accept-request':
                        if(self.parent().hasClass('notification-action')){
                            self.closest('li').slideUp(200);
                        }
                        mfm.actions.popAlert('Félicitations ! Vous avez un ami de plus.', 'succes');
                        break;
                    case 'ignore-request':
                        if(self.parent().hasClass('notification-action')){
                            self.closest('li').slideUp(200);
                        }
                        mfm.actions.popAlert('La demande d\'amitié a été ignorée.', 'succes');
                        break;
                    case 'friend-request':
                        var userId = $(this).attr('data-user-id');
                        var friendId = $(this).attr('data-friend-id');
                        self.html('Chargement...').addClass('btn-neutral');
                        $.ajax({
                            type: 'POST',
                            url: '/'+mfm.host+'/ajax/friend',
                            data: { userId: userId, friendId: friendId },
                            success: function () {
                                mfm.actions.popAlert('Ami ajout�', 'succes');
                                self.html('Demande envoyée').addClass('btn-neutral');
                            },
                            error: function () {
                                mfm.actions.popAlert('Erreur, la demande n\'a pas été traitée', 'erreur');
                                self.html('Erreur dans la demande').addClass('btn-neutral');
                            }
                        });
                        break;
                    default:
                }
            }
        });

    // TODO : Fix
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
                    mfm.actions.popAlert('Félicitations ! Vous avez un ami de plus.', 'succes');
                    break;
                case 'ignore-request':
                    if(self.parent().hasClass('notification-action')){
                        self.closest('li').slideUp(200);
                    }
                    mfm.actions.popAlert('La demande d\'amitié a été ignorée.', 'succes');
                    break;
                case 'friend-request':
                    var userId = $(this).attr('data-user-id');
                    var friendId = $(this).attr('data-friend-id');
                    self.html('Chargement...').addClass('btn-neutral');
                    $.ajax({
                        type: 'POST',
                        url: '/'+mfm.host+'/ajax/friend',
                        data: { userId: userId, friendId: friendId },
                        success: function () {
                            mfm.actions.popAlert('Demande d\'amitié envoyée', 'succes');
                            self.html('Demande envoyée').addClass('btn-neutral');
                        },
                        error: function () {
                            mfm.actions.popAlert('Erreur, la demande n\'a pas été traitée', 'erreur');
                            self.html('Erreur dans la demande').addClass('btn-neutral');
                        }
                    });
                    break;
                default:
            }
        }
    });

    if(!$('.alert').is(':visible')){
        $('.alert').slideDown(300);
        setTimeout(function(){
            $('.alert').slideUp(300);
        },5000 );
    }

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

    if($('#interests').length !== 0){
        $.getJSON('/'+mfm.host+'/ajax/interests/false', function(data) {

            $("#interests").autocomplete({
                source: data,
                appendTo: "#suggestions"
            });

        });
    }

    if($('#closest-users').length !== 0){
        $.getJSON('/'+mfm.host+'/ajax/users/near/1', function(data) {

            $.each(data, function(i) {

                var interests = '';
                for(j=0; j<data[i].interests.length;j++){
                    interests = interests +'#'+data[i].interests[j].tag.toLowerCase()+' ';
                }

                var user = '<li class="user">'+
                    '<a href="/'+mfm.host+'/userprofile?id='+data[i].id+'">'+
                        '<img src="/'+mfm.host+'/res/styles/default/img/users/'+data[i].lastname+'.jpg" width="40" height="40" class="avatar" alt="">'+
                            '<span class="user-title">'+data[i].firstname+' '+data[i].lastname+'</span>'+
                            //'<a href="#" class="btn mfm-action" data-action="friend-request" data-user-id="1" data-friend-id="3">Ajouter en ami</a>'+
                            //'<span class="user-time">13H - 14H</span>'+
                            '<span class="user-tags">'+interests+'</span>'+
                        '</a>'+
                    '</li>';

                $('#closest-users-list').append(user);

                return i<3;
            });

            $('#closest-users').fadeIn(500);

        });
    }

    if($('#date-start').length !== 0){
        $('#date-start').datepicker({
            dateFormat: "dd/mm/yy",
            dayNames: [ "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi" ],
            dayNamesMin: [ "Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa" ],
            monthNames: [ "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" ]
        });
    }

    if($('#time-start').length !== 0){
        $('#time-start').timepicker({
            currentText: 'Maintenant',
            closeText: 'Valider',
            timeOnlyTitle: 'Sélectionnez l\'heure',
            timeText: 'Temps',
            hourText: 'Heure',
            minuteText: 'Minute',
            showTime: false
        });
    }

    $('#featured-restaurants').after('<div id="nav">').cycle({
        fx:     'fade',
        speed:   1000,
        timeout: 3000,
        pager:  '#nav'
    });

});