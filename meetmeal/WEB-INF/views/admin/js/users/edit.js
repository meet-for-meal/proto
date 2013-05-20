require([
  'jquery',
  'underscore',
  'Util'
], function ($, _, Util) {
  // Change when real one is available
  var backUrl = 'http://localhost:3000',
      userTemplate = _.template($('#edit-template').html()),
      $main = $('#user'),
      $alertSuccess = $('#alert-success'),
      $alertError = $('#alert-error');

  var bindForm = function() {
    $form = $('#user-form');
    $form.on('submit', function (e) {
      e.preventDefault();
      var $this = $(this);
      var action = $this.attr('action'),
          method = $this.attr('method');
      var update = $.ajax({
        url: action,
        type: method,
        dataType: 'jsonp',
        data: {
          username: $('#username').val(),
          firstname: $('#firstname').val(),
          lastname: $('#lastname').val()
        }
      });
      update.done(function() {
        $alertSuccess.show();
      });
      update.fail(function() {
        $alertError.show();
      });
    });
  };

  var userId = Util.queryString.user;

  var req = $.ajax({
    url: backUrl + '/users/' + userId,
    type: 'GET',
    dataType: 'jsonp'
  });
  req.done(function (res) {
    var apiUrl = backUrl + '/users/edit/' + res.id;
    $main.html(userTemplate({user: res, apiUrl: apiUrl}));
    bindForm();
  });
  req.fail(function (err) {
    console.error(err);
  });
});