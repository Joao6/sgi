(function () {
    angular.module('painelAdmin').factory('UpdateService', function ($http, UpdateValues) {
        var HEADERS = {
            headers: {
                'Content-Type': 'application/json; charset=UTF-8',
                "Accept": "application/json;charset=utf-8"
            },
            dataType: "json"};

        var _getEmpreendimento = function (id) {
            return $http.get(UpdateValues.url_get_empreendimento_by_id + id, HEADERS);
        };
        
        var _getRamos = function () {
            return $http.get(UpdateValues.url_get_ramo_atividade, HEADERS);
        };

        var _getEditais = function () {
            return $http.get(UpdateValues.url_get_editais, HEADERS);
        };

        var _save = function (empreendimento) {
            return $http.post(UpdateValues.url_save_empreendimento, empreendimento, HEADERS);
        };

        var _update = function (empreendimento) {
            return $http.post(UpdateValues.url_update_empreendimento, empreendimento, HEADERS);
        };

        var _addApNegocio = function (apNegocio) {
            return $http.post(UpdateValues.url_add_ap_negocio, apNegocio, HEADERS);
        };
        var _remover = function (id) {
            return $http.get(UpdateValues.url_remove_empreendimento + id);
        };

        var _alterarStatus = function (empreendimento) {
            return $http.post(UpdateValues.url_change_state, empreendimento, HEADERS);
        };

        var _agendarApresentacao = function (apresentacao) {
            return $http.post(UpdateValues.url_agendar_apresentacao, apresentacao, HEADERS);
        };

        var _getAvaliadores = function () {
            return $http.get(UpdateValues.url_get_avaliadores, HEADERS);
        };

        var _associarAvaliadores = function (empreendimento) {
            return $http.post(UpdateValues.url_add_avaliadores, empreendimento, HEADERS);
        };

        var _getEmpreendedores = function () {
            return $http.get('/gerenciador/incubadora/all/empreendedores', HEADERS);
        };
        
        var _associarEmpreendedores = function(empreendimento){
          return $http.post('/gerenciador/empreendimento/add/empreendedores', empreendimento, HEADERS);
        };
        
        var _updateEmpreendimento = function(empreendimento){
          return $http.post(UpdateValues.url_update_empreendimento_by_gestor, empreendimento, HEADERS);  
        };
        
        return {            
            getEmpreendimento: _getEmpreendimento,
            getRamos: _getRamos,
            getEditais: _getEditais,
            getAvaliadores: _getAvaliadores,
            save: _save,
            remover: _remover,            
            update: _update,
            addApNegocio: _addApNegocio,
            alterarStatus: _alterarStatus,
            agendarApresentacao: _agendarApresentacao,
            associarAvaliadores: _associarAvaliadores,
            getEmpreendedores: _getEmpreendedores,
            associarEmpreendedores: _associarEmpreendedores,
            updateEmpreendimento: _updateEmpreendimento
        };
    });
})();

