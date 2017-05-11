"use strict";
angular.module("onlineStore", ["ngRoute"]);
angular.module("onlineStore").config(function ($routeProvider) {

    $routeProvider
        .when("/home", {
            templateUrl: "/views/home.html",
            //  controller: "restCtrl"

        })
        .when("/search", {
            templateUrl: "/views/search.html",
          //  controller: "restCtrl"

        })
        .when("/addUpdate", {
            templateUrl: "/views/productAdd.html",
           // controller: "restCtrl"

        });


});