  /* global angular */
  
  (function () {
     angular.module('painelEmpreendedor').factory('DuvidaService', function ($http, DuvidaValues) {

            var HEADERS = {headers: {
                     'Content-Type': 'application/json; charset=UTF-8',
                     "Accept": "application/json;charset=utf-8"
                  },
                  dataType: "json"};
               
        var _getDuvidas = function () {
           return $http.get(DuvidaValues.url_obter_duvidas,HEADERS);
        };

        var _responderDuvida = function (duvida) {
           return $http.post(DuvidaValues.url_responder_duvida, duvida);
        };

        var _removerDuvida = function (id) {
           return $http.get(DuvidaValues.url_remover_duvida + id);
        };

        var _getAllDuvidas = function () {
           return $http.get('/gerenciador/duvidas/all');
        };

        var _addDuvida = function(duvida){
           return $http.post(DuvidaValues.url_add_duvida, duvida, HEADERS);
        };
        
        return {
           getDuvidas: _getDuvidas,
           responderDuvida: _responderDuvida,
           removerDuvida: _removerDuvida,
           getAllDuvidas: _getAllDuvidas,
           addDuvida: _addDuvida
        };

     });
  })();