define([
  'jquery',
  'lodash',
  'backbone',
  'Util',
  'text!admin/templates/partnerships/list.html'
], function ($, _, Backbone, Util, Template) {

  return Backbone.View.extend({

    el: '#content',

    initialize: function() {
      var self = this;
      this.render();

      // Bind from elements recently rendered
      this.$list = this.$el.find('#partnerships-list');
      this.template = _.template(this.$el.find('#list-template').html());

      var success = function (res) {
        self.renderList(res);
      };
      Util.apiRequest('/partnerships', 'GET', null, null, success);
    },

    render: function() {
      this.$el.html(Template);
    },

    renderList: function (partnerships) {
      this.$list.html(this.template({ partnerships: partnerships }));
    }

  });

});