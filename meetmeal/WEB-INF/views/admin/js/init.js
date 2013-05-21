require.config({
  baseUrl: '/',
  deps: ['js/main'],
  paths: {
    jquery:     'libs/jquery-2.0.0',
    bootstrap:  'libs/bootstrap',
    underscore: 'libs/underscore',
    text:       'libs/text',
    Util:       'js/Util',
    Foursquare: 'js/Foursquare'
  },
  shim: {
    underscore: {
      exports: '_'
    }
  }
});