require(['jquery', 'bootstrap', 'backbone'], function ($, Bootstrap, Backbone) {

  var Router = Backbone.Router.extend({

    routes: {
      '':                'index',           // #
      'users':           'userList',        // #users
      'users/:id/edit':  'userEdit',        // #users/:id/edit
      'venues':          'venueList',       // #venues
      'venues/:id':      'venueShow',       // #venues/:id
      'venues/:id/edit': 'venueEdit',       // #venues/:id/edit
      'partnerships':    'partnershipList', // #partnerships
      'announcements':   'announcementList' // #announcements
    },

    index: function() {
      require(['admin/views/indexView'], function (IndexView) {
        new IndexView();
      });
    },


    /* Users */

    userList: function() {
      require(['admin/views/users/list'], function (userListView) {
        new userListView();
      });
    },

    userEdit: function(id) {
      require(['admin/views/users/edit'], function (userEditView) {
        new userEditView({ userId: id });
      });
    },


    /* Venues */

    venueList: function() {
      require(['admin/views/venues/list'], function (venueListView) {
        new venueListView();
      });
    },

    venueShow: function (id) {
      require(['admin/views/venues/show'], function (venueShowView) {
        new venueShowView({ venueId: id });
      });
    },

    venueEdit: function (id) {
      require(['admin/views/venues/edit'], function (venueEditView) {
        new venueEditView({ venueId: id });
      });
    },


    /* Partnerships */

    partnershipList: function() {
      require(['admin/views/partnerships/list'], function (partnershipListView) {
        new partnershipListView();
      });
    },


    /* Announcements */

    announcementList: function() {
      require(['admin/views/announcements/list'], function (announcementListView) {
        new announcementListView();
      });
    }

  });

  new Router();
  Backbone.history.start();

});