require([
  'jquery',
  'underscore',
  'Foursquare'
], function ($, _, Foursquare) {
  // Change when real one is available
  var backUrl = 'http://localhost:3000';

  var venueListTemplate = _.template($('#list-template').html()),
      $main             = $('#venues-list');

  var loadVenues = function (venues) {
    var len = venues.length;
    for(var i = 0; i < len; i++) {
      var venue = venues[i];
      (function(v) {
        Foursquare.request('venues/' + venue.foursquare_id, { v: Foursquare.v }, function (res) {
          var foursquareVenue = res.response.venue;
          var tr = $('tr.venue-' + v.id);
          var url = '<a href="' + foursquareVenue.canonicalUrl + '">' + foursquareVenue.name + '</a>';
          tr.find('.name').html(url);
        }, function(err) {
          console.error(err);
        });
      })(venue);
    }
  };

  var req = $.ajax({
    url: backUrl + '/venues',
    type: 'GET',
    dataType: 'jsonp'
  });
  req.done(function (res) {
    $main.html(venueListTemplate({venues: res}));
    loadVenues(res);
  });
  req.fail(function (err) {
    console.error(err);
  });
});