/* global angular */

(function () {
   angular.module('painelAdmin').factory('EmpreendimentoService', function (EmpreendimentoValues, $http) {
      var HEADERS = {headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            "Accept": "application/json;charset=utf-8"
         },
         dataType: "json"};

      var _getEmpreendimentos = function () {
         return $http.get(EmpreendimentoValues.url_get_empreendimetos, HEADERS);
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

      var _alterarStatus = function (empreendimento) {
         return $http.post(EmpreendimentoValues.url_change_state, empreendimento, HEADERS);
      };

      var _agendarApresentacao = function (apresentacao) {
         return $http.post(EmpreendimentoValues.url_agendar_apresentacao, apresentacao, HEADERS);
      };

      var _getAvaliadores = function () {
         return $http.get(EmpreendimentoValues.url_get_avaliadores, HEADERS);
      };
      
      //JP
      var _getEmpreendedores = function () {
         return $http.get(EmpreendimentoValues.url_get_empreendedor, HEADERS);
      };
      //JP

      var _associarAvaliadores = function (empreendimento) {
         return $http.post(EmpreendimentoValues.url_add_avaliadores, empreendimento, HEADERS);
      };
      
      //(JP)
      var _associarEmpreendedores = function (empreendimento) {
         return $http.post(EmpreendimentoValues.url_add_empreendedores, empreendimento, HEADERS);
      };
      //(JP)

      return {
         getEmpreendimentos: _getEmpreendimentos,
         getRamos: _getRamos,
         getEditais: _getEditais,
         getAvaliadores: _getAvaliadores,
         getEmpreendedores: _getEmpreendedores,
         save: _save,
         remover: _remover,
         getEmpreendimento: _getEmpreendimento,
         update: _update,
         addApNegocio: _addApNegocio,
         alterarStatus: _alterarStatus,
         agendarApresentacao: _agendarApresentacao,
         associarAvaliadores: _associarAvaliadores,
         associarEmpreendedores: _associarEmpreendedores
      };

   });
})();