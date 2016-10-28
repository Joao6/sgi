/* global angular */
(function () {
   angular.module('painelAdmin').factory('EmpreendedorService', function ($http, EmpreendedorValues) {
      var HEADERS = {
         headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            "Accept": "application/json;charset=utf-8"
         },
         dataType: "json"};

      var _getEmpreendedores = function () {
         return $http.get(EmpreendedorValues.url_obter_empreendedores, HEADERS);
      };
      
   
      
      var _getEmpreendedorById = function (id) {
         return $http.get(EmpreendedorValues.url_get_empreendedor + id, HEADERS);
      };

      var _addEmpreendedor = function (empreendedor) {
         return $http.post(EmpreendedorValues.url_add_empreendedor, empreendedor, HEADERS);
      };

      var _removerEmpreendedor = function (id) {
         return $http.get(EmpreendedorValues.url_remover_empreendedor + id);
      };

      var _getEmpreendimentoByEmpreendedorId = function (id) {
          return $http.get(EmpreendedorValues.url_get_empreendimento + id);
      };

      return {
         getEmpreendedores: _getEmpreendedores,
         removerEmpreendedor: _removerEmpreendedor,
         addEmpreendedor: _addEmpreendedor,
         getEmpreendimentoByEmpreendedorId: _getEmpreendimentoByEmpreendedorId,
         getEmpreendedorById: _getEmpreendedorById,
      };
   });
})();
