/* global angular */

angular.module('painelAdmin')
        .directive('uiInfo', function () {
           return {
              templateUrl: window.location.protocol + "//" + window.location.host + "/gerenciador/html/template/ui-info.html",
              restrict: 'E',
              escope: {
              }
           };
        });

        