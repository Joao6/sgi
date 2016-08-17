/* global angular */

(function () {
   angular.module('painelEmpreendedor').factory('EmpreendimentoAPI', function (EmpValues, $http) {
      var HEADERS = {headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            "Accept": "application/json;charset=utf-8"
         },
         dataType: "json"};

      var _getEmpreendimentos = function (id) {
         return $http.get(EmpValues.url_get_empreendimeto_by_emp + id, HEADERS);
      };

      var _getEmpreendimento = function (id) {
         return $http.get(EmpValues.url_get_empreendimento_by_id + id, HEADERS);
      };

      var _getRamos = function () {
         return $http.get(EmpValues.url_get_ramo_atividade, HEADERS);
      };
      
      var _getEixos = function(){
         return $http.get(EmpValues.url_get_eixos, HEADERS);
      };
      
      var _getCriterios = function(){
        return $http.get(EmpValues.url_get_criterios, HEADERS);
      };
      
      var _getEditais = function () {
         return $http.get(EmpValues.url_get_editais, HEADERS);
      };

      var _save = function (empreendimento) {
         return $http.post(EmpValues.url_save_empreendimento, empreendimento, HEADERS);
      };
      
      var _saveNotas = function(notas){
         return $http.post(EmpValues.url_save_notas, notas, HEADERS);
      };
      
      var _update = function (empreendimento) {
         return $http.post(EmpValues.url_update_empreendimento, empreendimento, HEADERS);
      };

      var _addApNegocio = function (apNegocio) {
         return $http.post(EmpValues.url_add_ap_negocio, apNegocio, HEADERS);
      };
      var _remover = function (id) {
         return $http.get(EmpValues.url_remove_empreendimento + id);
      };



      return {
         getEmpreendimentos: _getEmpreendimentos,
         getRamos: _getRamos,
         getEditais: _getEditais,
         getEixos: _getEixos,
         getCriterios: _getCriterios,
         save: _save,
         saveNotas: _saveNotas,
         remover: _remover,
         getEmpreendimento: _getEmpreendimento,
         update: _update,
         addApNegocio: _addApNegocio
      };

   });
})();