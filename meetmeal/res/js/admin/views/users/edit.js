define([
  'jquery',
  'lodash',
  'backbone',
  'Util',
  'text!admin/templates/users/edit.html'
], function ($, _, Backbone, Util, Template) {

  return Backbone.View.extend({

    el: '#content',

    events: {
      'submit form': 'updateUser'
    },

    initialize: function (params) {
      var self = this;
      this.userId = params.userId;
      this.render();

      // Bind from elements recently rendered
      this.$user = this.$el.find('#user');
      this.template = _.template(this.$el.find('#edit-template').html());
      this.$alertSuccess = this.$el.find('#alert-success'),
      this.$alertError = this.$el.find('#alert-error');

      Util.apiRequest('/users/' + this.userId, 'GET', null, null, function (res) {
        self.renderUser(res);
      });
    },

    render: function() {
      this.$el.html(Template);
    },

    renderUser: function (user) {
      this.$user.html(this.template({ user: user }));
      Util.renderGoogleMap(user.lastLatitude, user.lastLongitude, user.firstname + ' ' + user.lastname);
    },

    updateUser: function (e) {
      e.preventDefault();
      var self = this,
          $form = $(e.currentTarget),
          data = {
        firstname: $form.find('#firstname').val(),
        lastname:  $form.find('#lastname').val(),
        email:     $form.find('#email').val(),
        age:       $form.find('#age').val(),
        gender:    $form.find('#gender').val(),
        password:  $form.find('#password').val()
      };
      Util.apiRequest('/users/edit/' + this.userId, 'GET', null, data, function() {
        self.$alertSuccess.show();
      }, function() {
        self.$alertError.show();
      });
    }

  });

});