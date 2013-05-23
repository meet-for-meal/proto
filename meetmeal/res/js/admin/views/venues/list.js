define([
  'jquery',
  'lodash',
  'backbone',
  'Util',
  'Foursquare',
  'text!admin/templates/venues/list.html'
], function ($, _, Backbone, Util, Foursquare, Template) {

  return Backbone.View.extend({

    el: '#content',

    events: {
      'click .delete': 'removeVenue'
    },

    initialize: function() {
      var self = this;
      this.render();

      // Bind from elements recently rendered
      this.$list = this.$el.find('#venues-list');
      this.template = _.template(this.$el.find('#list-template').html());

      var success = function (res) {
        self.renderList(res);
        self.loadVenues(res);
      };
      Util.apiRequest('/restaurants', 'GET', null, null, success);
    },

    render: function() {
      this.$el.html(Template);
    },

    renderList: function (venues) {
      this.$list.html(this.template({ venues: venues }));
    },

    loadVenues: function (venues) {
      var len = venues.length;
      for(var i = 0; i < len; i++) {
        var venue = venues[i];
        (function(v) {
          Foursquare.request('venues/' + venue.foursquareId, { v: Foursquare.v }, function (res) {
            var foursquareVenue = res.response.venue;
            var tr = $('tr.venue-' + v.id);
            var url = '<a href="' + foursquareVenue.canonicalUrl + '">' + foursquareVenue.name + '</a>';
            tr.find('.name').html(url);
          }, function(err) {
            console.error(err);
          });
        })(venue);
      }
    },

    removeVenue: function (e) {
      e.preventDefault();
      var venueId = $(e.currentTarget).data('id');
      Util.apiRequest('/restaurant/' + venueId, 'DELETE', null, null, function (res) {
        if(res && res.status === 'ok') {
          document.location.reload(true);
        }
      });
    }

  });

});