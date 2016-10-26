/* global angular */

(function () {
   angular.module('painelAdmin').factory('EixoService', function ($http, EixoValues) {
      var HEADERS = {headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            "Accept": "application/json;charset=utf-8"
         },
         dataType: "json"};

      var _getEixos = function () {
         return $http.get(EixoValues.url_get_eixos, HEADERS);
      };

      var _getCriterios = function () {
         return $http.get(EixoValues.url_get_criterios, HEADERS);
      };

      var _addEixo = function (eixo) {
         return $http.post(EixoValues.url_add_eixo, eixo, HEADERS);
      };

      var _addCriterio = function (criterio) {
         return $http.post(EixoValues.url_add_criterio, criterio, HEADERS);
      };

      var _delete = function (id) {
         return $http.get(EixoValues.url_delete_eixo + id + "/api");
      };

      var _deleteCriterio = function (id) {
         return $http.get(EixoValues.url_delete_criterio + id + '/api');
      };
      
      var _getCriterioById = function(id){
          return $http.get(EixoValues.url_get_criterio_by_id + id);
      };

      var _update = function (eixo) {
         return $http.post(EixoValues.url_update_eixo, eixo, HEADERS);
      };

      return {
         getEixos: _getEixos,
         getCriterios: _getCriterios,
         addEixo: _addEixo,
         addCriterio: _addCriterio,
         update: _update,
         delete: _delete,
         deleteCriterio: _deleteCriterio,
         getCriterioById: _getCriterioById
      };

   });

})();


