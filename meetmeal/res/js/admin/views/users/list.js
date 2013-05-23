define([
  'jquery',
  'lodash',
  'backbone',
  'Util',
  'text!admin/templates/users/list.html'
], function ($, _, Backbone, Util, Template) {

  return Backbone.View.extend({

    el: '#content',

    events: {
      'click .delete': 'removeUser'
    },

    initialize: function() {
      var self = this;
      this.render();

      // Bind from elements recently rendered
      this.$list = this.$el.find('#users-list');
      this.template = _.template(this.$el.find('#list-template').html());

      var success = function (res) {
        self.renderList(res);
      };
      Util.apiRequest('/users', 'GET', null, null, success);
    },

    render: function() {
      this.$el.html(Template);
    },

    renderList: function (users) {
      this.$list.html(this.template({ users: users }));
    },

    removeUser: function (e) {
      e.preventDefault();
      var userId = $(e.currentTarget).data('id');
      Util.apiRequest('/user/' + userId, 'DELETE', null, null, function (res) {
        if(res && res.status === 'ok') {
          document.location.reload(true);
        }
      });
    }

  });

});