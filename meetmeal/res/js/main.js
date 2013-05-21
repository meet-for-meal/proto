require.config({
  paths: {
    jquery: 'jquery',
    jqueryUI: 'jquery-ui.custom',
    cycle: 'jquery.cycle.all',
    bootstrap: 'bootstrap',
    backbone: 'backbone',
    lodash: 'lodash'
  },
  shim: {
    jqueryUI: {
      deps: ['jquery']
    },
    bootstrap: {
      deps: ['jquery'],
      exports: '$'
    },
    lodash: {
      exports: '_'
    },
    backbone: {
      deps: ['lodash'],
      exports: 'Backbone'
    },
    cycle: {
    	desps: ['jquery']
    }
  },
  baseUrl: '/meetformeal/res/js/'
});

require(['app', 'jquery', 'jqueryUI', 'cycle', 'bootstrap'], function (MainView, $) {
  'use strict';
  
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

        $('.close').click(function(e){
          e.preventDefault();
          $(this).parent('.alert').fadeOut(300);
        });

        var availableTags = [
            "Animaux", "Cinéma", "Concert", "Cosmétique", "Cuisine", "Informatique", "Jeux vidéo", "Mode", "Musique", "Peinture", "Religion", "Sport", "Télévision", "Théâtre", "Voiture", "Voyage"
        ];

        $("#interests").autocomplete({
          source: availableTags,
          appendTo: "#suggestions"
        });

        $('#featured-restaurants').after('<div id="nav">').cycle({
          fx:     'fade',
          speed:   1000,
          timeout: 3000,
          pager:  '#nav'
        });

	});

  var mainView = new MainView();
});