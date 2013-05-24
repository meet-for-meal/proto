define([
  'jquery',
  'lodash',
  'backbone',
  'Util',
  'Foursquare',
  'text!admin/templates/announcements/edit.html'
], function ($, _, Backbone, Util, Foursquare, Template) {

  return Backbone.View.extend({

    el: '#content',

    events: {
      'click .update-announcement': 'updateMap',
      'submit form': 'updateAnnouncement'
    },

    initialize: function (params) {
      var self = this;
      this.announcementId = params.announcementId;
      this.render();

      // Bind from elements recently rendered
      this.$announcement = this.$el.find('#announcement');
      this.template = _.template(this.$el.find('#edit-template').html());

      Util.apiRequest('/announce/' + this.announcementId, 'GET', null, null, function (res) {
        self.announcement = res;
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
        this.announcement.latitude,
        this.announcement.longitude,
        this.announcement.creator.firstname +
        ' ' + this.announcement.creator.lastname
      );
    },

    updateAnnouncement: function (e) {
      e.preventDefault();
      var params = {
    	id:				   this.announcementId,
        createdDate:       this.$el.find('#createdDate').val(),
        disponibilityDate: this.$el.find('#disponibilityDate').val(),
        latitude:          this.$el.find('#latitude').val(),
        longitude:         this.$el.find('#longitude').val(),
        message:           this.$el.find('#message').val(),
      };
      Util.apiRequest('/announce/update/'+ this.announcementId, 'POST', null, params, function (res) {
        if(res && res.status === 'ok') {
          window.location = '#/announcements';
        }
      });
    },

    updateMap: function (e) {
      e.preventDefault();
      var latitude  = this.$el.find('#latitude').val(),
          longitude = this.$el.find('#longitude').val();
      Util.renderGoogleMap(
        latitude,
        longitude,
        this.announcement.creator.firstname +
        ' ' + this.announcement.creator.lastname
      );
    }

  });

});