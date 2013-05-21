require([
  'jquery',
  'underscore'
], function ($, _) {
  // Change when real one is available
  var backUrl = 'http://localhost:3000';

  var partnershipListTemplate = _.template($('#list-template').html()),
      $main                   = $('#partnerships-list');

  var req = $.ajax({
    url: backUrl + '/partnerships',
    type: 'GET',
    dataType: 'jsonp'
  });
  req.done(function (res) {
    $main.html(partnershipListTemplate({partnerships: res}));
  });
  req.fail(function (err) {
    console.error(err);
  });
});