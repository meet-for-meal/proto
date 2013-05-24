require.config({
  paths: {
    jquery: 'jquery',
    jqueryUI: 'jquery-ui.custom',
    cycle: 'jquery.cycle.all',
    bootstrap: 'bootstrap',
    backbone: 'backbone',
    lodash: 'lodash',
    base: 'base'
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
      deps: ['jquery']
    },
    base: {
      deps: ['jquery', 'jqueryUI', 'cycle']
    }
  },
  baseUrl: '/meetformeal/res/js/'
});

require(['app', 'jquery', 'jqueryUI', 'cycle', 'bootstrap', 'base'], function (MainView) {
  'use strict';

  new MainView();

});