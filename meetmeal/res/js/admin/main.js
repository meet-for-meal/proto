require(['jquery', 'bootstrap', 'backbone'], function ($, Bootstrap, Backbone) {

  var Router = Backbone.Router.extend({

    routes: {
      '':                  'index',             // #
      'users':             'userList',          // #users
      'users/:id/edit':    'userEdit',          // #users/:id/edit
      'venues':            'venueList',         // #venues
      'venues/:id':        'venueShow',         // #venues/:id
      'venues/:id/edit':   'venueEdit',         // #venues/:id/edit
      'partnerships':      'partnershipList',   // #partnerships
      'announcements':     'announcementList',  // #announcements
      'announcements/:id': 'announcementShow', // #announcement/:id
      'interests':         'interestList',      // #interests
      'interests/new':     'interestNew'        // #interests/new
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
    },

    announcementShow: function (id) {
      require(['admin/views/announcements/show'], function (announcementShowView) {
        new announcementShowView({ announcementId: id });
      });
    },


    /* Interests */

    interestList: function() {
      require(['admin/views/interests/list'], function (interestListView) {
        new interestListView();
      });
    },

    interestNew: function() {
      require(['admin/views/interests/new'], function (interestNewView) {
        new interestNewView();
      });
    }

  });

  new Router();
  Backbone.history.start();

});