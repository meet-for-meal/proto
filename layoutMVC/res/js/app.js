define(['jquery', 'backbone'], function ($, Backbone) {
  'use strict';

  var MainView = Backbone.View.extend({

    el: 'body',
    $categories: $('.categories'),
    $actions: $('.actions'),
    foursquareApiUrl: 'https://api.foursquare.com/v2/',
    foursquareOauthToken: 'CKTMK32OZVMXUXXHSHBUJXGLIV2AYFUN00SG5ICMET3B5TQN',
    params: $('#require-js').data('params'),
    map: null,

    events: {
      'click .find-venues': 'fireVenuesSearch',
      'click .check-all':   'checkAllCategories',
      'click .uncheck-all': 'uncheckAllCategories'
    },

    initialize: function () {
    	
      var self = this;
      this.foursquareCategories = this.params.foursquareCategories;
      if (this.geolocIsAvailable()) {
        navigator.geolocation.getCurrentPosition(function (position) {
          self.renderMap(position);
          self.renderCategoryList(self.foursquareCategories);
        });
      } else {
        console.warn('You don\'t have HTML Geolocation API available on this browser.');
      }
    },



    /*
     * LOCATION
     */

    // Check is geolocation is available on this browser
    geolocIsAvailable: function () {
      return typeof navigator.geolocation !== 'undefined';
    },

    // Display Google Maps
    renderMap: function (position) {
      // User's location
      this.userLatitude = position.coords.latitude;
      this.userLongitude = position.coords.longitude;

      var center = new google.maps.LatLng(this.userLatitude, this.userLongitude);
      var mapOptions = {
        center: center,
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP
      };
      this.map = new google.maps.Map(document.getElementById('googlemaps'), mapOptions);
      this.renderUserLocation(this.map);
    },

    // Create marker to display user's location
    renderUserLocation: function (map) {
      var position = new google.maps.LatLng(this.userLatitude, this.userLongitude);
      var customMarker = this.greenMarker();
      var marker = this.addMarker(map, 'users', position, 'You are here!', { icon: customMarker[0], shadow: customMarker[1] });
      var infowindow = this.addInfoWindow (map, marker, {label: 'You are here!'});
      this.renderFriendsLocation(map);
    },

    // Create markers to close friends' location
    renderFriendsLocation: function (map) {
      var self = this;
      
      $.ajax({
    	  url: '/layoutMVC/user/display.ajax',
    	  type: 'GET',
    	  dataType: 'json',
    	  success: function (friends) {
    		  var customMarker = {icon: '/layoutMVC/res/styles/default/img/logo-marker.png'};
    	      for(var i in friends) {
    	        var friend = friends[i];
    	        var position = new google.maps.LatLng(friend.lastLatitude, friend.lastLongitude);
    	        var marker = self.addMarker(map, 'users', position, friend.firstname + ' ' + friend.lastname, customMarker);

    	        (function(marker, friend) {
    	          var infowindow = self.addInfoWindow(map, marker, {id: friend.id, label: friend.firstname + ' ' + friend.lastname});
    	        }(marker, friend));
    	      }
    	  }
      });
    },



    /*
     * VENUES
     */

    // Foursquare API Request maker
    foursquareRequest: function (url, params) {
      var params = params || {};
      params.oauth_token = this.foursquareOauthToken;
      return $.ajax({
        url: this.foursquareApiUrl + url,
        dataType: 'jsonp',
        type: 'get',
        data: params
      });
    },

    // Handle venues request
    fireVenuesSearch: function (e) {
      var choices = []
          self = this;
      var inputs = this.$categories.find('input:checked');
      if(inputs.length > 0) {
        inputs.each(function (k, v) {
          choices.push($(v).attr('name'));
        });
        choices = choices.join(',');

        var venuesRequest = this.foursquareRequest('venues/search', {
          ll: this.userLatitude + ',' + this.userLongitude,
          intent: 'browse',
          limit: 50,
          radius: 5000, // 5000 meters around user's location
          categoryId: choices
        }).done(function (results) {
          var items = results.response.groups[0].items;
          self.showNearVenues(self.map, items);
        });
      } else {
        alert('You must choose at least one category.');
      }
    },

    // Display venue markers to a map
    showNearVenues: function (map, items) {
      this.clearMap(map, 'venues');
      for(var k in items) {
        var item = items[k];
        var location = item.location;
        this.addMarker(map, 'venues', new google.maps.LatLng(location.lat, location.lng), item.name);
      }
    },

    // Render list of checkboxes to Categories div
    renderCategoryList: function (categories) {
      var inputs = '';
      for(var i in categories) {
        var category = categories[i];
        inputs += '<label><input type="checkbox" name="' + category.id + '" checked /> ' + category.name + '</label>';
      }
      this.$actions.show();
      this.$categories.append(inputs);
    },



    /*
     * Checkboxes
     */

    checkAllCategories: function (e) {
      e.preventDefault();
      this.$categories.find('input').each(function (k, input) {
        $(input).prop('checked', true)
      });
    },

    uncheckAllCategories: function (e) {
      e.preventDefault();
      this.$categories.find('input').each(function (k, input) {
        $(input).prop('checked', false);
      });
    },



    /*
     * MARKERS
     */

    // Add one marker to a map
    addMarker: function (map, type, position, title, layout) {
      map.markers = map.markers || {};
      map.markers[type] = map.markers[type] || [];
      var params = {
        position: position,
        title: title,
      };
      if(typeof layout !== 'undefined') {
        params = _.extend(params, layout);
      }
      var marker = new google.maps.Marker(params);
      map.markers[type].push(marker);
      marker.setMap(map);
      return marker;
    },

    // Add one infowindow to a marker from a map
    addInfoWindow: function (map, marker, label) {
      var contentString = label.hasOwnProperty('id') ? '<h2><img width="70" src="/layoutMVC/res/styles/default/img/' + label.id + '.jpg" alt="" />' + label.label + '</h2>' : '<h2>' + label.label + '</h2>',
          infowindow = new google.maps.InfoWindow({ content: contentString });
      map.infowindows = map.infowindows || [];
      map.infowindows.push(infowindow);
      google.maps.event.addListener(marker, 'click', function() {
        for(var j in map.infowindows) {
          var mapinfowindow = map.infowindows[j];
          mapinfowindow.close();
        }
        infowindow.open(map, marker);
      });
      return infowindow;
    },

    // Remove one marker from a map
    removeMarker: function (marker, map) {
      marker.setMap(null);
      return map;
    },

    // Remove all the markers from a map
    clearMap: function (map, type) {
      for(var i in map.markers[type]) {
        var marker = map.markers[type][i];
        this.removeMarker(marker);
      }
      return map;
    },

    // Create green marker to make difference with venue markers
    greenMarker: function () {
      // User's location marker
      // Credit: http://stackoverflow.com/users/3800/jack-b-nimble
      var pinColor = "37c855";
      var GMaps = google.maps;
      var pinImage = new GMaps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
        new GMaps.Size(21, 34),
        new GMaps.Point(0, 0),
        new GMaps.Point(10, 34)
      );
      var pinShadow = new GMaps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
        new GMaps.Size(40, 37),
        new GMaps.Point(0, 0),
        new GMaps.Point(12, 35)
      );
      return [pinImage, pinShadow];
    }

  });

  return MainView;
});