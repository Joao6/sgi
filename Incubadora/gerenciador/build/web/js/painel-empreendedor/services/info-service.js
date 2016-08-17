  /*global angular */
  angular.module('painelEmpreendedor').factory('InfoService', function ($http, InfoValue) {
     var HEADERS = {headers: {
           'Content-Type': 'application/json; charset=UTF-8',
           "Accept": "application/json;charset=utf-8"
        },
        dataType: "json"};

     var _getInfo = function () {
        return $http.get(InfoValue.url_getInfo, HEADERS);
     };

     var _updateInfo = function (empreendedor) {
        return $http.post(InfoValue.url_update_info, empreendedor, HEADERS);
     };

     return {
        getInfo: _getInfo,
        updateInfo: _updateInfo
     };
  });
