  /*global angular */
  angular.module('painelAvaliador').factory('InfoService', function ($http, InfoValue) {
     var HEADERS = {headers: {
           'Content-Type': 'application/json; charset=UTF-8',
           "Accept": "application/json;charset=utf-8"
        },
        dataType: "json"};

     var _getInfo = function () {
        return $http.get(InfoValue.url_getInfoAv, HEADERS);
     };

     var _updateInfo = function (avaliador) {
        return $http.post(InfoValue.url_update_infoAv, avaliador, HEADERS);
     };

     return {
        getInfo: _getInfo,
        updateInfo: _updateInfo
     };
  });
