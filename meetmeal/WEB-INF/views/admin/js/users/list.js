require(['jquery', 'underscore'], function ($, _) {
  // Change when real one is available
  var backUrl = 'http://localhost:3000';

  var userListTemplate = _.template($('#list-template').html()),
      $main            = $('#users-list');

  var req = $.ajax({
    url: backUrl + '/users',
    type: 'GET',
    dataType: 'jsonp'
  });
  req.done(function (res) {
    $main.html(userListTemplate({users: res}));
  });
  req.fail(function (err) {
    console.error(err);
  });
});