
/* global angular */
(function () {
   angular.module('candidatoApp')
           .factory('CandidatoService', function ($http, CandidatoValue) {

              var HEADERS = {headers: {
                    'Content-Type': 'application/json; charset=UTF-8',
                    "Accept": "application/json;charset=utf-8"
                 },
                 dataType: "json"};

              var _checkEmail = function (email) {
                 return $http.post(CandidatoValue.url_check_email, email);
              };

              var _validaCPF = function (cpf) {
                 var soma;
                 var resto;
                 var i;

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


              var _createCandidato = function (candidato) {
                 return $http.post(CandidatoValue.url_create_candidato,candidato, HEADERS);
              };


              return {
                 checkEmail: _checkEmail,
                 validaCPF: _validaCPF,
                 createCandidato: _createCandidato
              };

           });
})();
