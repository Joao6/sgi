/* global angular */

(function () {
   angular.module('painelAdmin').factory('AvaliadorService', function ($http, AvaliadorValues) {
      var HEADERS = {headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            "Accept": "application/json;charset=utf-8"
         },
         dataType: "json"};

      var _getAvaliadores = function () {
         return $http.get(AvaliadorValues.url_obter_avaliadores, HEADERS);
      };         

      var _getAvaliadorById = function (id) {
         return $http.get(AvaliadorValues.url_get_avaliador + id, HEADERS);
      };

      var _getItens = function () {
         return AvaliadorValues.itens;
      };

      var _addAvaliador = function (avaliador) {
         return $http.post(AvaliadorValues.url_add_avaliador, avaliador);
      };
      
      var _update = function(avaliador){
        return $http.post(AvaliadorValues.url_update, avaliador, HEADERS);;
      };

      var _removerAvaliador = function (id) {
         return $http.get(AvaliadorValues.url_remover_avaliadore + id);
      };


      var _checkEmail = function (email) {
         return $http.post(AvaliadorValues.url_check_email, email, HEADERS);
      };

      var _checkCPF = function (cpf) {
         return $http.post(AvaliadorValues.url_check_cpf, cpf);
      };

      var _validaCPF = function (cpf) {
         var soma;
         var resto;
         var i;
         cpf = cpf.trim();

         if ((cpf.length !== 11) ||
                 (cpf === "00000000000") || (cpf === "11111111111") ||
                 (cpf === "22222222222") || (cpf === "33333333333") ||
                 (cpf === "44444444444") || (cpf === "55555555555") ||
                 (cpf === "66666666666") || (cpf === "77777777777") ||
                 (cpf === "88888888888") || (cpf === "99999999999")) {
            return false;
         }

         soma = 0;

         for (i = 1; i <= 9; i++) {
            soma += Math.floor(cpf.charAt(i - 1)) * (11 - i);
         }

         resto = 11 - (soma - (Math.floor(soma / 11) * 11));

         if ((resto === 10) || (resto === 11)) {
            resto = 0;
         }

         if (resto !== Math.floor(cpf.charAt(9))) {
            return false;
         }

         soma = 0;

         for (i = 1; i <= 10; i++) {
            soma += cpf.charAt(i - 1) * (12 - i);
         }

         resto = 11 - (soma - (Math.floor(soma / 11) * 11));

         if ((resto === 10) || (resto === 11)) {
            resto = 0;
         }

         if (resto !== Math.floor(cpf.charAt(10))) {
            return false;
         }

         return true;
      };



      return {
         getAvaliadores: _getAvaliadores,       
         getItens: _getItens,
         addAvaliador: _addAvaliador,
         removerAvaliador: _removerAvaliador,
         checkEmail: _checkEmail,
         checkCPF: _checkCPF,
         validaCPF: _validaCPF,
         getAvaliadorById: _getAvaliadorById,
         update:_update

      };
   });
})();
