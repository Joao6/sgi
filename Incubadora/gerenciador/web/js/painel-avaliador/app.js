/* global angular */

var app = angular.module('painelAvaliador', ['caco.ClientPaginate', 'ngRoute']);
app.controller('MainCtrlAvaliador', function ($scope, InfoService) {
   $scope.avaliador = {};

   var _getInfo = function () {
      try {
         InfoService.getInfo()
                 .success(function (data) {
                    var _d = data.dataNascimento.split("-");
                    //yyyy-MM-dd           
                    data.dataNascimento = new Date(_d[0], _d[1] - 1, _d[2]);

                    $scope.avaliador = data;
                    console.log(data);
                 }).error(function () {

         });
      } catch (e) {

         console.log(e);
      }
   };

   _getInfo();

   //Update todo
   $scope.updateInfo = function (avaliador) {
      try {
         InfoService.updateInfo(avaliador).success(function () {
            _getInfo();
            Materialize.toast('Dados atualizados com sucesso! =)', 4000, 'rounded green');
         }).error(function () {
            Materialize.toast('Erro ao tentar atualizar dados! =(', 4000, 'rounded red');
         });
      } catch (e) {
         Materialize.toast('Erro ao tentar atualizar dados! =(', 4000, 'rounded red');
      }

   };

});