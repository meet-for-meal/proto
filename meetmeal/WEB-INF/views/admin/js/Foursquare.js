define([], function() {

  return (function() {

    return {

      foursquareApiUrl: 'https://api.foursquare.com/v2/',
      foursquareOauthToken: 'CKTMK32OZVMXUXXHSHBUJXGLIV2AYFUN00SG5ICMET3B5TQN',
      request: function (url, params, success, error) {
        if(!params) params = {};
        if(!success) success = function(){};
        if(!error) error = function(){};
        params.oauth_token = this.foursquareOauthToken;
        var req = $.ajax({
          url: this.foursquareApiUrl + url,
          dataType: 'jsonp',
          type: 'get',
          data: params
        });
        req.done(success);
        req.fail(error);
      },
      v: (function() {
        var date = new Date(),
            day = date.getDate(),
            month = date.getMonth();
        day = day     < 10 ? '0' + day   : day.toString();
        month = month < 10 ? '0' + month : month.toString();
        return day + month + date.getFullYear().toString();
      })()

    }

  })();

});