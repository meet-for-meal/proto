require.config({
  baseUrl: './res/js/',
  deps: ['admin/main'],
  paths: {
    jquery:     'jquery',
    bootstrap:  'bootstrap',
    backbone:   'backbone',
    lodash:     'lodash',
    text:       'text',
    Util:       'admin/Util',
    Foursquare: 'admin/Foursquare'
  },
  shim: {
    lodash: {
      exports: '_'
    },
    backbone: {
      deps: ['lodash'],
      exports: 'Backbone'
    }
  }
});