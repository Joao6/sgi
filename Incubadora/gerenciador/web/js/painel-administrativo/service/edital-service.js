/* global angular */

angular.module('painelAdmin')
        .factory('EditalService', function ($http) {
           var _create = function (edital) {
              return $http.post(edital);
           };
           
           return {
              create: _create
           };
        });
