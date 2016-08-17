/* global angular */
(function () {
   angular.module('painelAdmin').factory('GestorService', function ($http, GestorValues) {

      var HEADERS = {headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            "Accept": "application/json;charset=utf-8"
         },
         dataType: "json"};

      var _getGestores = function () {
         return $http.get(GestorValues.url_obter_gestores, HEADERS);
      };

      var _removeGestor = function (idGestor) {
         return $http.get(GestorValues.url_remove_gestor + idGestor);
      };

      var _addGestor = function (gestor) {
         return $http.post(GestorValues.url_add_gestor, gestor);
      };

      var _getGestorById = function (id) {
         return $http.get(GestorValues.url_get_gestor_by_id + id, HEADERS);
      };

      var _editarGestor = function (gestor) {
         return $http.post(GestorValues.url_edit_gestor, gestor, HEADERS);
      };

      var _checkEmail = function (email) {
         return $http.post(GestorValues.url_check_email, email, HEADERS);
      };
      
      var _checkCPF = function(cpf){
        return $http.post(GestorValues.url_check_cpf, cpf);
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
         getGestores: _getGestores,
         removeGestor: _removeGestor,
         addGestor: _addGestor,
         getGestorById: _getGestorById,
         editarGestor: _editarGestor,
         checkEmail: _checkEmail,
         checkCPF:_checkCPF,
         validaCPF: _validaCPF
      };

   });
})();
