  /* 
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
   */

  angular.module('cronogramaAnual').factory('service', function ($http, values) {

     var _getPraticas = function () {
        return $http.get(values.url_praticas);
     };
     
     var _getResponsaveis = function(){
       return $http.get(values.url_responsaveis); 
     };

     return {
        getPraticas: _getPraticas,
        getResponsaveis: _getResponsaveis
     };
  });
