/* global angular */

(function () {
   angular.module('painelAdmin').factory('DuvidaService', function ($http, DuvidaValues) {

      var HEADERS = {headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            "Accept": "application/json;charset=utf-8"
         },
         dataType: "json"};

      var _getDuvidas = function () {
         return $http.get(DuvidaValues.url_obter_duvidas, HEADERS);
      };

      var _responderDuvida = function (respostaDuvida) {
         return $http.post(DuvidaValues.url_responder_duvida, respostaDuvida, HEADERS);
      };

      var _removerDuvida = function (id) {
         return $http.get(DuvidaValues.url_remover_duvida + id);
      };

      var _removerResposta = function (idResposta) {
         return $http.post(DuvidaValues.url_remover_resposta, idResposta, HEADERS);
      };

      var _updateResposta = function (resposta) {
         return $http.post(DuvidaValues.url_atualizar_resposta, resposta, HEADERS);
      };

      var _getAllDuvidas = function () {
         return $http.get('/gerenciador/duvidas/all');
      };


      var _getConnection = function () {
         return $http.get(DuvidaValues.url_get_connection, HEADERS);
      };

      var _formatDataHora = function (array) {

         try {
            for (var element in array) {
               var AJUSTE_DUAS_HORAS = 7200000;
               var dataHora = Date.parse(array[element].dataHora) - AJUSTE_DUAS_HORAS;

               var dia = new Date(dataHora).getUTCDate();
               var mes = new Date(dataHora).getUTCMonth() + 1;
               var ano = new Date(dataHora).getUTCFullYear();
               var hh = new Date(dataHora).getUTCHours();
               var mm = new Date(dataHora).getUTCMinutes();
               var ss = new Date(dataHora).getUTCSeconds();

               var newDataHora = dia.toString()
                       + "/"
                       + mes.toString() + "/"
                       + ano.toString()
                       + " "
                       + hh.toString() + ":"
                       + mm.toString() + ":"
                       + ss.toString();


               array[element].dataHora = newDataHora;
            }

         } catch (e) {
            console.log(e);
         }

         return array;
      };



      return {
         getDuvidas: _getDuvidas,
         responderDuvida: _responderDuvida,
         removerDuvida: _removerDuvida,
         removerResposta: _removerResposta,
         updateResposta: _updateResposta,
         getAllDuvidas: _getAllDuvidas,
         formatDataHora: _formatDataHora,
         getConnection: _getConnection
      };

   });
})();