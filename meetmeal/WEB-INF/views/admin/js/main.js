require(['jquery', 'bootstrap'], function ($, Bootstrap) {
  var page = $('#require-js').data('script');
  require(['text!templates/layout.html'], function (layout) {
    $('body').prepend(layout);
    var $content = $('#content');
    if(page) {
      require(['text!templates/' + page + '.html'], function (pageContent) {
        $content.html(pageContent);
        require(['js/' + page]);
      });
    }
  });
});