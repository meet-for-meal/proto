define([
  'jquery',
  'lodash',
  'backbone',
  'Util',
  'Foursquare',
  'text!admin/templates/announcements/show.html'
], function ($, _, Backbone, Util, Foursquare, Template) {

  return Backbone.View.extend({

    el: '#content',

    initialize: function (params) {
      var self = this;
      this.announcementId = params.announcementId;
      this.render();

      // Bind from elements recently rendered
      this.$announcement = this.$el.find('#announcement');
      this.template = _.template(this.$el.find('#show-template').html());

      Util.apiRequest('/announce/' + this.announcementId, 'GET', null, null, function (res) {
        var disponibilityDate = new Date(res.disponibilityDate),
            createdDate       = new Date(res.createdDate),
            disponibilityStr  = Util.dateToString(disponibilityDate) + ' à ' + Util.timeToString(disponibilityDate),
            createdStr        = Util.dateToString(createdDate) + ' à ' + Util.timeToString(createdDate);
        res.rdvDate = disponibilityStr;
        res.crDate = createdStr;
        self.renderAnnouncement(res);
      });
    },

    render: function() {
      this.$el.html(Template);
    },

    renderAnnouncement: function (announcement) {
      this.$announcement.html(this.template({ announcement: announcement }));
      Util.renderGoogleMap(
        announcement.latitude,
        announcement.longitude,
        announcement.creator.firstname +
        ' ' + announcement.creator.lastname
      );
    }

  });

});