<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Meet For Meal - Administration</title>
  <link href="<%= request.getContextPath() %>/res/styles/default/bootstrap.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/res/styles/default/bootstrap-responsive.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/res/styles/default/admin.css" rel="stylesheet">
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container-fluid">
      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="brand" href="<%= request.getContextPath() %>/admin#">Meet For Meal</a>
      <div class="nav-collapse collapse">
        <p class="navbar-text pull-right">
          Connecté en tant que <a href="#" class="navbar-link">Username</a>
        </p>
        <ul class="nav">
          <li><a href="<%= request.getContextPath() %>/admin#/">Frontend</a></li>
        </ul>
      </div><!--/.nav-collapse -->
    </div>
  </div>
</div>

<div class="container-fluid">
  <div class="row-fluid">
    <div class="span3">
      <div class="well sidebar-nav">
        <ul class="nav nav-list">
          <li class="nav-header">Utilisateurs</li>
          <li><a href="<%= request.getContextPath() %>/admin#users">Liste</a></li>
        </ul>
        <ul class="nav nav-list">
          <li class="nav-header">Restaurants partenaires</li>
          <li><a href="<%= request.getContextPath() %>/admin#venues">Liste</a></li>
        </ul>
        <ul class="nav nav-list">
          <li class="nav-header">Partenariats</li>
          <li><a href="<%= request.getContextPath() %>/admin#partnerships">Liste</a></li>
        </ul>
        <ul class="nav nav-list">
          <li class="nav-header">Annonces</li>
          <li><a href="<%= request.getContextPath() %>/admin#announcements">Liste</a></li>
        </ul>
        <ul class="nav nav-list">
          <li class="nav-header">Interets</li>
          <li><a href="<%= request.getContextPath() %>/admin#interests">Liste</a></li>
          <li><a href="<%= request.getContextPath() %>/admin#interests/new">Ajouter</a></li>
        </ul>
      </div><!--/.well -->
    </div><!--/span-->
    <div id="content" class="span9"></div><!--/span-->
  </div><!--/row-->

  <hr>

  <footer>
    <p>&copy; Meet For Meal 2013</p>
  </footer>

</div><!--/.fluid-container-->

<!--[if lt IE 9]>
<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script id="require-js" data-params='{"applicationPath": "<%= request.getContextPath() %>"}' data-main="<%= request.getContextPath() %>/res/js/admin/init" src="<%= request.getContextPath() %>/res/js/require.js"></script>
</body>
</html>