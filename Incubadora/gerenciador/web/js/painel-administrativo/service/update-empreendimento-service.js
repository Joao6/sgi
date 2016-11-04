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
        
        return {            
            getEmpreendimento: _getEmpreendimento          
        };
    });
})();

