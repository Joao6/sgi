/* global angular */
/* Materialize */

angular.module('painelAdmin')
        .factory('PraticaChaveService', function ($http, PraticaChaveValue) {
           var _HEADERS = {headers: {
                 'Content-Type': 'application/json; charset=UTF-8',
                 "Accept": "application/json;charset=utf-8"
              },
              dataType: "json"};

           var _getProcessoChave = function () {
              return $http.get(PraticaChaveValue.url_get_processo_chave, _HEADERS);
           };

           var _getEstagiosEvolucao = function () {
              return $http.get(PraticaChaveValue.url_get_estagios_evolucao, _HEADERS);
           };

           var _savePraticasChave = function (praticasChave) {
              return $http.post(PraticaChaveValue.url_get_pratica_chave_save, praticasChave, _HEADERS);
           };

           var _viewPraticasChave = function () {
              return $http.get(PraticaChaveValue.url_view_pratica_chave, _HEADERS);
           };
           
           var _getPraticasChave = function(){
             return $http.get(PraticaChaveValue.url_get_all_praticas, _HEADERS);
           };

           var _deletePraticaChave = function (id) {
              return $http.get(PraticaChaveValue.url_delete_pratica_chave + id);
           };
           
           var _deleteAnexo = function(id){             
             return $http.get(PraticaChaveValue.url_delete_anexo + id);
           };


           return {
              getProcessoChave: _getProcessoChave,
              getEstagiosEvolucao: _getEstagiosEvolucao,
              savePraticasChave: _savePraticasChave,
              viewPraticasChave: _viewPraticasChave,
              deletePraticaChave: _deletePraticaChave,
              deleteAnexo: _deleteAnexo,
              getPraticasChave:_getPraticasChave
           };

        });
