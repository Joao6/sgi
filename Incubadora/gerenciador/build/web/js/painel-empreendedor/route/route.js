/* global angular */
var app = angular.module('painelEmpreendedor');
app.config(function ($routeProvider) {
   $routeProvider.when("/proposta/empreendimento/:id", {
      templateUrl: '/gerenciador/html/empreendedor/empreendimento/form-proposta.html',
      controller: 'EmpreendimentoCtrl',
      resolve: {
         empreendimento: function (EmpreendimentoService,$route) {
           return EmpreendimentoService.getEmpreendimento($route.current.params.id);
         }
      }
   });
   $routeProvider.otherwise({redirectTo: '/proposta'});
});