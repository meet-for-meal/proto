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
        res.disponibilityDate = Util.splitDate(new Date(res.disponibilityDate));
        res.createdDate = Util.splitDate(new Date(res.createdDate));
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
      var createdDate       = this.$el.find('#created_year').val() + '-' +
                              this.$el.find('#created_month').val() + '-' +
                              this.$el.find('#created_day').val() + ' ' +
                              this.$el.find('#created_hours').val() + ':' +
                              this.$el.find('#created_minutes').val() + ':' +
                              this.$el.find('#created_seconds').val();
      var disponibilityDate = this.$el.find('#disponibility_year').val() + '-' +
                              this.$el.find('#disponibility_month').val() + '-' +
                              this.$el.find('#disponibility_day').val() + ' ' +
                              this.$el.find('#disponibility_hours').val() + ':' +
                              this.$el.find('#disponibility_minutes').val() + ':' +
                              this.$el.find('#disponibility_seconds').val();

      var params = {
        id:                this.announcementId,
        createdDate:       createdDate,
        disponibilityDate: disponibilityDate,
        latitude:          this.$el.find('#latitude').val(),
        longitude:         this.$el.find('#longitude').val(),
        message:           this.$el.find('#message').val(),
        isOpen:            this.$el.find('#isOpen').prop('checked')
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