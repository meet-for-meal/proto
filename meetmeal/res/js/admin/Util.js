define(['jquery'], function ($) {

  return (function() {

    return {

      backUrl: $('#require-js').data('params').applicationPath + '/ajax', // 'http://localhost:3000',

      queryString: (function() {
        // This function is anonymous, is executed immediately and
        // the return value is assigned to QueryString
        var queryString = {};
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
          var pair = vars[i].split("=");
            // If first entry with this name
          if (typeof queryString[pair[0]] === "undefined") {
            queryString[pair[0]] = pair[1];
            // If second entry with this name
          } else if (typeof queryString[pair[0]] === "string") {
            var arr = [ queryString[pair[0]], pair[1] ];
            queryString[pair[0]] = arr;
            // If third or later entry with this name
          } else {
            query_string[pair[0]].push(pair[1]);
          }
        }
        return queryString;
      })(),

      apiRequest: function (url, method, dataType, data, success, error) {
        url = url || '/';
        method = method || 'GET';
        dataType = dataType || 'json'; // 'jsonp';
        data = data || {};
        success = success || function (){};
        error = error || function (err) {
          console.error(err);
        };

        var req = $.ajax({
          url: this.backUrl + url,
          type: method,
          dataType: dataType,
          data: data
        });
        req.done(success);
        req.fail(error);
        return req;
      },

      renderGoogleMap: function (lat, lng, markerName) {
        var center = new google.maps.LatLng(lat, lng);
        var mapOptions = {
          center: center,
          zoom: 15,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById('map'), mapOptions);
        var marker = new google.maps.Marker({
          position: center,
          title: markerName
        });
        marker.setMap(map);
      },

      dateToString: function (date) {
        var month = parseInt(date.getMonth(), 10) + 1;
        month = this.addZero(month);
        return date.getDate() + '/' + month + '/' + date.getFullYear();
      },

      timeToString: function (date) {
        var hours   = this.addZero(date.getHours()),
            minutes = this.addZero(date.getMinutes()),
            seconds = this.addZero(date.getSeconds());
        return hours + ':' + minutes + ':' + seconds;
      },

      addZero: function (number) {
        return number < 10 ? '0' + number : number;
      }

    };

  })();

});