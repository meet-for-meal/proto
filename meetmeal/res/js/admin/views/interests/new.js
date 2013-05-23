define([
  'jquery',
  'lodash',
  'backbone',
  'Util',
  'text!admin/templates/interests/new.html'
], function ($, _, Backbone, Util, Template) {

  return Backbone.View.extend({

    el: '#content',

    events: {
      'submit form': 'createInterest'
    },

    initialize: function (params) {
      var self = this;
      this.render();

      // Bind from elements recently rendered
      this.$main = this.$el.find('#main');
      this.form = this.$el.find('form');
      this.$alertSuccess = this.$el.find('#alert-success'),
      this.$alertError = this.$el.find('#alert-error');
    },

    render: function() {
      this.$el.html(Template);
    },

    cleanContent: function (status) {
      if(status === 'success') {
        this.$main.html('');
        this.$alertSuccess.show();
      } else {
        this.$alertError.show();
      }
    },

    createInterest: function (e) {
      e.preventDefault();
      var self = this;
      var label = this.form.find('#tag').val();
      Util.apiRequest('/interest', 'POST', null, { tag: label }, function (res) {
        if(res && res.status === 'ok') {
          self.cleanContent('success');
        } else {
          self.cleanContent('error');
        }
      }, function() {
        self.cleanContent('error');
      });
    }

  });

});