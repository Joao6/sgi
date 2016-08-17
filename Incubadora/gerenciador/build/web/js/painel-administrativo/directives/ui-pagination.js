/* global angular */

angular.module('painelAdmin').directive('uiPagination', function () {
   return {
      templateUrl: window.location.protocol + "//" + window.location.host + "/gerenciador/html/template/ui-pagination.html",
      restrict: 'E',
      escope: {
      }
   };
});