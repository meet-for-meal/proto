<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="org.essilab.module.restaurant.model.Restaurant"%>
<%@page import="java.util.ArrayList"%>

<section id="container">

    <!-- start: Map -->
    <div id="restaurant-map" style="position:relative;">

        <!-- starts: Google Maps -->
        <div id="googlemaps-container-top"></div>
        <div id="googlemaps" class="google-map google-map-full"></div>
        <div id="googlemaps-container-bottom"></div>
        <!-- end: Google Maps -->

    </div>
    <!-- end: Map -->

    <div class="wrapper">

        <div id="map-action-bar">
            <div class="actions">
                <button class="btn find-venues"><i class="icon-search"></i>Rechercher</button>
                <button class="btn check-all"><i class="icon-check"></i>Tout cocher</button>
                <button class="btn uncheck-all"><i class="icon-remove"></i>Tout décocher</button>
            </div>
            <div class="categories"></div>
        </div>

        <div class="col-3">
            <hr>
            <h2>Les restaurants autour de vous</h2>
            <div id="near-venues"></div>
        </div>

        <div class="col-1">

        </div>

        <div class="col-2">

        </div>

        <div class="col-1">

        </div>

    </div>

</section>

<script type="text/x-template" id="venues-template">
<ul id="search-result">
{{#each venues}}
    <li class="featured">
        <img src="{{ showIcon this }}" class="restaurant-category">
        <p class="restaurant-description">
            <span><strong class="title red">{{ name }}</strong></span><br>
            <span>Catégorie : <strong>{{ showCategories this }}</strong></span><br>
            <span>Adresse : <strong>{{ location.address }}</strong></span><br>
            <a href="#" class="btn"><i class="icon-eye-open icon-red"></i>Consulter</a>
        </p>
    </li>
{{/each}}
</ul>
