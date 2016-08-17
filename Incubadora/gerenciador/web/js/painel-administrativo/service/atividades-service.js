  /* global angular */

  (function () {
     angular.module('painelAdmin').factory('Service', function ($http, values) {
        var _HEADERS = {headers: {
                     'Content-Type': 'application/json; charset=UTF-8',
                     "Accept": "application/json;charset=utf-8"
                  },
                  dataType: "json"};
        
        var _getAtividades = function () {
           return $http.get(values.url_atividades, _HEADERS);
        };

        var _getItens = function () {
           return values.itens;
        };

        var _sendAtividade = function (atividade) {
           return $http.post(values.url_atividade, atividade);
        };

        var _removeAtividade = function (id) {
           return $http.post(values.url_remove_atividade + id);
        };
        
        var _getAtividadeById = function(id){
          return $http.get(values.url_get_atividade_by_id + id, _HEADERS);
        };
        
        var _updateAtividade = function(atividade){
          return $http.post(values.url_update_atividade, atividade, _HEADERS);
        };

        return {
           getAtividades: _getAtividades,
           getItens: _getItens,
           sendAtividade: _sendAtividade,
           removeAtividade: _removeAtividade,
           getAtividadeById: _getAtividadeById,
           updateAtividade: _updateAtividade
        };
     });
  })();
