define([
  'jquery',
  'lodash',
  'backbone',
  'Util',
  'text!admin/templates/interests/list.html'
], function ($, _, Backbone, Util, Template) {

  return Backbone.View.extend({

    el: '#content',

    events: {
      'click .delete': 'removeInterest'
    },

    initialize: function() {
      var self = this;
      this.render();

      // Bind from elements recently rendered
      this.$list = this.$el.find('#interests-list');
      this.template = _.template(this.$el.find('#list-template').html());

      var success = function (res) {
        self.renderList(res);
      };
      Util.apiRequest('/interests', 'GET', null, null, success);
    },

    render: function() {
      this.$el.html(Template);
    },

    renderList: function (interests) {
      this.$list.html(this.template({ interests: interests }));
    },

    removeInterest: function (e) {
      e.preventDefault();
      var interestId = $(e.currentTarget).data('id');
      Util.apiRequest('/interests/' + interestId, 'DELETE', null, null, function (res) {
        if(res && res.status === 'ok') {
          document.location.reload(true);
        }
      });
    }

  });

});