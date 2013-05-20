require([
  'jquery',
  'underscore',
  'Util',
  'Foursquare'
], function ($, _, Util, Foursquare) {

  // Change when real one is available
  var backUrl = 'http://localhost:3000';

  var mainTemplate  = _.template($('#edit-template').html()),
      venueTemplate = _.template($('#venue-template').html()),
      $main         = $('#m-content'),
      $venue        = $('#venue'),

      venueId = Util.queryString.venue;

  var renderFoursquareVenue = function (foursquareId) {
    Foursquare.request('venues/' + foursquareId, { v: Foursquare.v }, function (res) {
      var foursquareVenue = res.response.venue;
      $venue.html(venueTemplate({ f: foursquareVenue }));
      var location = foursquareVenue.location;
      var center = new google.maps.LatLng(location.lat, location.lng);
      var mapOptions = {
        center: center,
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP
      };
      var map = new google.maps.Map(document.getElementById('map'), mapOptions);
      var marker = new google.maps.Marker({
        position: center,
        title: foursquareVenue.name
      });
      marker.setMap(map);
    }, function(err) {
      console.error(err);
    });
  };

  var update = function (params) {
    var req = $.ajax({
      url: backUrl + '/venues/edit/' + venueId,
      type: 'GET',
      dataType: 'jsonp',
      data: params
    });
    req.done(function (res) {
      window.location = '/pages/venues/list.html';
    });
    req.fail(function (err) {
      console.error(err);
    });
  };

  var handleForm = function() {
    $form = $main.find('form');
    $form.find('.update-venue').click(function (e) {
      e.preventDefault();
      var newId = $form.find('#idFoursquare').val();
      renderFoursquareVenue(newId);
    });
    $form.on('submit', function (e) {
      e.preventDefault();
      var partnership = $form.find('#partnership').val(),
          foursquareId = $form.find('#idFoursquare').val();
      update({ partnership: partnership, foursquare_id: foursquareId });
    });
  };

  var req = $.ajax({
    url: backUrl + '/venues/' + venueId,
    type: 'GET',
    dataType: 'jsonp'
  });
  req.done(function (venue) {
    var partnerships = ['medium', 'high'];
    $main.html(mainTemplate({ m: venue, p: partnerships}));
    handleForm();
    renderFoursquareVenue(venue.foursquare_id);
  });
  req.fail(function (err) {
    console.error(err);
  });

});