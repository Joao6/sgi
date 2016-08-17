/* global angular */

var app = angular.module('painelEmpreendedor', ['caco.ClientPaginate', 'ngRoute']);
app.controller('MainCtrl', function ($scope, InfoService) {
   $scope.empreendedor = {};

   var _getInfo = function () {
      try {
         InfoService.getInfo()
                 .success(function (data) {
                    var _d = data.dataNascimento.split("-");
                    //yyyy-MM-dd           
                    data.dataNascimento = new Date(_d[0], _d[1] - 1, _d[2]);

                    $scope.empreendedor = data;
                    console.log(data);
                 }).error(function () {

         });
      } catch (e) {

         console.log(e);
      }
   };

   _getInfo();

   //Update todo
   $scope.updateInfo = function (empreendedor) {
      try {
         InfoService.updateInfo(empreendedor).success(function () {
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


//   $routeProvider.when("/proposta/page/2", {
//      templateUrl: '/gerenciador/html/empreendedor/empreendimento/form2-proposta.html',
//      controller: 'EmpreendimentoCtrl',
//      resolve: {
//         apresentacao: function($route){
//            return $route.current;
//         }
//      }
//   });
//
//   $routeProvider.when("/proposta/page/3", {
//      templateUrl: '/gerenciador/html/empreendedor/empreendimento/form3-proposta.html',
//      controller: 'EmpreendimentoCtrl',
//      resolve: {
//         apresentacao: function($route){
//            return $route.current;
//         }
//      }
//   });
//
//   $routeProvider.when("/proposta/page/4", {
//      templateUrl: '/gerenciador/html/empreendedor/empreendimento/form4-proposta.html',
//      controller: 'EmpreendimentoCtrl',
//      resolve: {
//         apresentacao: function($route){
//            return $route.current;
//         }
//      }
//   });
//
//   $routeProvider.when("/proposta/page/5", {
//      templateUrl: '/gerenciador/html/empreendedor/empreendimento/form5-proposta.html',
//      controller: 'EmpreendimentoCtrl',
//      resolve: {
//         apresentacao: function($route){
//            return $route.current;
//         }
//      }
//   });
//
//   
//});