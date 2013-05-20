define([
  'jquery',
  'lodash',
  'backbone',
  'text!admin/templates/home.html'
], function ($, _, Backbone, Template) {

  return Backbone.View.extend({

    el: '#content',
    template: _.template(Template),

    initialize: function() {
      this.render();
    },

    render: function() {
      this.$el.html(this.template());
    }

  });

});