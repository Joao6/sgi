  /* global angular */

  angular.module('painelAdmin')
      .factory('ProcessoChaveService', function ($http, ProcessoChaveValue) {

     var _HEADERS = {headers: {
           'Content-Type': 'application/json; charset=UTF-8',
           "Accept": "application/json;charset=utf-8"
        },
        dataType: "json"};

     var _getProcessoChave = function () {
        return $http.get(ProcessoChaveValue.url_get_processo_chave, _HEADERS);
     };

     var _addProcessoChave = function (processoChave) {
        return $http.post(ProcessoChaveValue.url_add_processo_chave, processoChave, _HEADERS);
     };
     
     var _updateProcessoChave = function(processoChave){
       return $http.post(ProcessoChaveValue.url_update_processo_chave, processoChave, _HEADERS);
     };
     
     var _deleteProcessoChave = function(id){
       return $http.get(ProcessoChaveValue.url_delete_processo_chave + id);
     };
     
     var _viewPraticasChave = function(processoId){
        return $http.get(ProcessoChaveValue.url_view_pratica_chave + processoId,_HEADERS);
     };
     
     return {
        getProcessoChave: _getProcessoChave,
        addProcessoChave: _addProcessoChave,
        updateProcessoChave:_updateProcessoChave,
        deleteProcessoChave: _deleteProcessoChave,
        viewPraticasChave: _viewPraticasChave
     };
  });