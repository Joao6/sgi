
/* global angular */

angular.module('painelAdmin').config(function ($routeProvider) {

   $routeProvider.when("/gestor/new", {
      templateUrl: "/gerenciador/html/gestor/formGestor.html",
      controller: "GestaoIntefCtrl",
      resolve: {
         gestores: function(GestorService){
            return GestorService.getGestores();
         }
      }
   });
 
   $routeProvider.when("/gestor/list", {
      templateUrl: "/gerenciador/html/gestor/gestorList.html",
      controller: "GestaoIntefCtrl",
      resolve: {
         gestores: function(GestorService){
            return GestorService.getGestores();
         }         
      }
   });
   

   $routeProvider.otherwise({redirectTo: '/gestor/list'});


});

