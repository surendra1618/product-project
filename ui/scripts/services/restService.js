"use strict";
// alert("outside restService");

angular.module("onlineStore").service("restService", function ($http) {
    // alert("inside restService");

    this.add = function (product) {
        //console.log("from service");
        return $http({
            "method": "post",
            "url": "http://localhost:8090/online-stores/product/",
            "data": product
        }).then(function (result) {
            alert("Successfully added")
            return result.status;
        }).catch(function (errResult) {
            return errResult.data;
        })
    }

    this.put = function (product) {
        //console.log("from service");
        return $http({
            "method": "put",
            "url": "http://localhost:8090/online-stores/product/",
            "data": product
        }).then(function (result) {
            return result.status;
        }).catch(function (errResult) {
            return errResult.data;
        })
    }

    this.searchAll = function () {
        //console.log("from service");
        return $http({
            "method": "get",
            "url": "http://localhost:8090/online-stores/product/"
        }).then(function (result) {
            return result.data;
        })
    }

    this.searchName = function (name) {
        //console.log("from service");
        return $http({
            "method": "get",
            "url": "http://localhost:8090/online-stores/product/name?searchName=" + name
        }).then(function (result) {
            return result.data;
        }).catch(function (errResult) {
            return errResult.data;
        })
    }

    this.searchProductNumber = function (productNumber) {
        //console.log("from service");
        return $http({
            "method": "get",
            "url": "http://localhost:8090/online-stores/product/number?searchNumber=" + productNumber
        }).then(function (result) {
            return result.data;
        }).catch(function (errResult) {
            return errResult.data;
        })
    }

    this.searchUPC = function (upcCode) {
        //console.log("from service");
        return $http({
            "method": "get",
            "url": "http://localhost:8090/online-stores/product/upc?searchUPC=" + upcCode
        }).then(function (result) {
            return result.data;
        }).catch(function (errResult) {
            return errResult.data;
        })
    }

    this.remove = function (pk) {
        //console.log("from service");
        return $http({
            "method": "delete",
            "url": "http://localhost:8090/online-stores/product/" + pk
        }).then(function (result) {
            alert("Product Deleted");
            return result.data;
        })
    }


});