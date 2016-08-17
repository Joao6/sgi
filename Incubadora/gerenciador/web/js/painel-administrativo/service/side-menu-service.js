  /* 
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
   */
  
  (function(){
     angular.module('painelAdmin').factory('SideMenuService', function($http){
        
        var _getAllDuvidas = function(){
          return $http.get('/gerenciador/duvidas/all');
        };
        
        return {
          getAllDuvidas: _getAllDuvidas 
        };
     });
  })();
