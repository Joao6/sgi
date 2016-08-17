/* global angular */

(function () {
   angular.module('painelEmpreendedor').factory('EmpreendimentoService', function (EmpreendimentoValues, $http) {
      var HEADERS = {headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            "Accept": "application/json;charset=utf-8"
         },
         dataType: "json"};

      var _getEmpreendimentos = function () {
         return $http.get(EmpreendimentoValues.url_get_empreendimeto_by_emp, HEADERS);
      };

      var _getEmpreendimento = function (id) {
         return $http.get(EmpreendimentoValues.url_get_empreendimento_by_id + id, HEADERS);
      };

      var _getRamos = function () {
         return $http.get(EmpreendimentoValues.url_get_ramo_atividade, HEADERS);
      };

      var _getEditais = function () {
         return $http.get(EmpreendimentoValues.url_get_editais, HEADERS);
      };

      var _save = function (empreendimento) {
         return $http.post(EmpreendimentoValues.url_save_empreendimento, empreendimento, HEADERS);
      };

      var _update = function (empreendimento) {
         return $http.post(EmpreendimentoValues.url_update_empreendimento, empreendimento, HEADERS);
      };

      var _addApNegocio = function (apNegocio) {
         return $http.post(EmpreendimentoValues.url_add_ap_negocio, apNegocio, HEADERS);
      };
      var _remover = function (id) {
         return $http.get(EmpreendimentoValues.url_remove_empreendimento + id);
      };



      return {
         getEmpreendimentos: _getEmpreendimentos,
         getRamos: _getRamos,
         getEditais: _getEditais,
         save: _save,
         remover: _remover,
         getEmpreendimento: _getEmpreendimento,
         update: _update,
         addApNegocio: _addApNegocio
      };

   });
})();