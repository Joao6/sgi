/* global angular */

angular.module('painelAdmin')
        .directive('uiAlertEmpreendedor', function () {
           return {
              templateUrl: window.location.protocol + "//" + window.location.host + "/gerenciador/html/template/ui-alert.html",
              restrict: 'E',
              replace:true,
              escope: {
                 
              }
           };
        });