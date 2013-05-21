define([
  'jquery',
  'lodash',
  'backbone',
  'Util',
  'Foursquare',
  'text!admin/templates/venues/show.html'
], function ($, _, Backbone, Util, Foursquare, Template) {

  return Backbone.View.extend({

    el: '#content',

    initialize: function (params) {
      var self = this;
      this.venueId = params.venueId;
      this.render();

      // Bind from elements recently rendered
      this.venue = this.$el.find('#venue');
      this.template = _.template(this.$el.find('#show-template').html());

      Util.apiRequest('/venues/' + this.venueId, 'GET', null, null, function (res) {
        self.renderVenue(res);
      });
    },

    render: function() {
      this.$el.html(Template);
    },

    renderVenue: function (venue) {
      var self = this;
      Foursquare.request('venues/' + venue.foursquare_id, { v: Foursquare.v }, function (res) {
        var foursquareVenue = res.response.venue;
        self.venue.html(self.template({ f: foursquareVenue, m: venue}));
        var location = foursquareVenue.location;
        Util.renderGoogleMap(location.lat, location.lng, foursquareVenue.name);
      });
    }

  });

});