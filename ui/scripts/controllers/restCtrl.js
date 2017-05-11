"use strict";

angular.module("onlineStore").controller("restCtrl", ["$scope", "restService", function ($scope, rs) {

    $scope.isAddButton = true;

    $scope.productModel = {};
    //$scope.productModel.activeStatus;

    initializeProductModel();

    function initializeProductModel() {
        $scope.productModel = {
            "activeStatus": "",
            "category": "",
            "manufacturer": "",
            "upcCode": "",
            "name": "",
            "price": "",
            "version": "",
            "qtyCurrent": "",
            "qtyThreshold": "",
            "qtyOrder": ""
        }
    }

    $scope.addBarClicked = function () {
        initializeProductModel();
        $scope.isAddButton = true;
    }

    $scope.addProduct = function () {
        //console.log("check controller");
        rs.add($scope.productModel).then(function (result) {
            if (Object.keys(result).length == 2) {
                alert(result.errorCode +"\n"+ result.errorDesc);
            }else{
                initializeProductModel();
            }
        });
    }

    $scope.editProduct = function (product) {
        $scope.isAddButton = false;
        $scope.productModel = angular.copy(product);

    }

    $scope.putProduct = function () {
        rs.put(angular.copy($scope.productModel)).then(function (result) {
            $scope.searchAll();
            if (Object.keys(result).length == 2) {
                alert(result.errorCode +"\n"+ result.errorDesc);
            }else{
                initializeProductModel();
            }
        });
    }

    $scope.searchResultDisplay = false;

    $scope.search = function (searchBy, searchValue) {
        console.log("ng-click called");
        $scope.searchResultDisplay = true;
        console.log(searchBy);
        if (searchBy == "allSearch") {
            $scope.searchAll();
        } else if (searchBy == "nameSearch") {
            $scope.searchByName(searchValue);
        } else if (searchBy == "productNumberSearch") {
            $scope.searchByProductNumber(searchValue);
        } else if (searchBy == "upcSearch") {
            $scope.searchByUPC(searchValue);
        }
    }

    $scope.nameSearch = "";
    $scope.productNumberSearch = "";
    $scope.upcSearch = "";

    $scope.searchProduct = [];

    $scope.searchAll = function () {
        updateSearchProductAll();
    }

    $scope.searchByName = function (searchValue) {
        //console.log("check searchByName()");
        console.log("searchByName called");
        console.log(searchValue);
        updateSearchName(searchValue)
    }

    $scope.searchByProductNumber = function (searchValue) {
        console.log("searchByProductNumber() called");
        console.log(searchValue);
        updateSearchProductNumber(searchValue);
    }

    $scope.searchByUPC = function (searchValue) {
        //console.log("check searchByUPC()");
        console.log("searchByUPC() called");
        updateSearchUPC(searchValue);

    }

    $scope.removeProduct = function (pk) {
        //console.log("inside check removeProduct()");
        var deleteDecision = confirm("Are you sure?");
        if(deleteDecision) {
            rs.remove(pk).then(function () {
                rs.searchAll().then(function (result) {
                    if (result.products == null) {
                        console.log("is null");
                        $scope.searchProduct = [];
                    }
                    else if (Object.keys(result.products).indexOf("activeStatus") == 1 || Object.keys(result.products).indexOf("activeStatus") == 0) {
                        console.log("only one left");
                        $scope.searchProduct = [];
                        $scope.searchProduct.push(result.products);

                    } else {
                        console.log("more than one left");
                        $scope.searchProduct = [];
                        $scope.searchProduct = result.products;
                    }
                })
            })
        }
    }

    function updateSearchProductAll(searchName) {
        //console.log("updatesearchproduct called");
        rs.searchAll().then(function (result) {
            if (Object.keys(result.products).indexOf("activeStatus") == 1 || Object.keys(result.products).indexOf("activeStatus") == 0) {

                $scope.searchProduct = [];
                $scope.searchProduct.push(result.products);

            } else {
                $scope.searchProduct = result.products;
            }
            //console.log(searchName);
        })
    }

    function updateSearchName(searchAll) {
        //console.log("updateSearchName called");
        rs.searchName(searchAll).then(function (result) {
            if (Object.keys(result).length == 2) {
                alert(result.errorCode +"\n"+ result.errorDesc);
            } else if (Object.keys(result.products).indexOf("activeStatus") == 1 || Object.keys(result.products).indexOf("activeStatus") == 0) {

                $scope.searchProduct = [];
                $scope.searchProduct.push(result.products);

            } else {
                $scope.searchProduct = result.products;
            }
            //console.log(searchName);
        })
    }

    function updateSearchProductNumber(productNumber) {
        console.log("updateSearchProductNumber called");
        console.log(productNumber);
        rs.searchProductNumber(productNumber).then(function (result) {
                if (Object.keys(result).length == 2) {
                    alert(result.errorCode +"\n"+ result.errorDesc);
                } else {
                    $scope.searchProduct = [];
                    $scope.searchProduct.push(result.products);
                    //console.log(productNumber);
                }
            }
        )
    }

    function updateSearchUPC(upcCode) {
        //console.log("updateSearchUPC called");
        rs.searchUPC(upcCode).then(function (result) {
            if (Object.keys(result).length == 2) {
                alert(result.errorCode +"\n"+ result.errorDesc);
            } else if (Object.keys(result.products).indexOf("activeStatus") == 1 || Object.keys(result.products).indexOf("activeStatus") == 0) {

                $scope.searchProduct = [];
                $scope.searchProduct.push(result.products);

            } else {
                $scope.searchProduct = result.products;
            }
            //console.log(upcCode);
        })
    }

}]);