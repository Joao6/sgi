  /*global angular */
  angular.module('painelAvaliador').factory('AvaliadorService', function ($http, AvaliadorValue) {
     var HEADERS = {headers: {
           'Content-Type': 'application/json; charset=UTF-8',
           "Accept": "application/json;charset=utf-8"
        },
        dataType: "json"};

     var _getInfo = function () {
        return $http.get(AvaliadorValue.url_getInfoAv, HEADERS);
     };

     var _updateInfo = function (avaliador) {
        return $http.post(AvaliadorValue.url_update_infoAv, avaliador, HEADERS);
     };

     return {
        getInfo: _getInfo,
        updateInfo: _updateInfo
     };
  });
