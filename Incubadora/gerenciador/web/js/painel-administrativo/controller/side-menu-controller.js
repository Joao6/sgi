  /* global angular */
  /* Materialize */
  
  (function () {
     angular.module('painelAdmin').controller('SideMenuCtrl', function ($scope, SideMenuService) {
        $scope.duvidas = null;


        var getAllDuvidas = function () {
           SideMenuService.getAllDuvidas().success(function (data) {
              $scope.duvidas = data;
           }).error(function () {
              console.log('erro ao carredas numero de duvidas enviadas');
           });
        };

        getAllDuvidas();


     });
  })();