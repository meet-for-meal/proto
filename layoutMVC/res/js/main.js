require.config({
  paths: {
    jquery: 'jquery',
    cycle: 'jquery.cycle.all',
    bootstrap: 'bootstrap',
    backbone: 'backbone',
    lodash: 'lodash'
  },
  shim: {
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
  baseUrl: '/layoutMVC/res/js/'
});

require(['app', 'jquery', 'cycle', 'bootstrap'], function (MainView, $) {
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

	    $('#featured-restaurants').after('<div id="nav">').cycle({
	        fx:     'fade',
	        speed:   1000,
	        timeout: 3000,
	        pager:  '#nav'
	    });

	});

  var mainView = new MainView();
});