/* global angular */

//Core


angular.module('sideMenuApp', [])

        .value('SideMenuValue', {
           'url_get_new_duvidas': window.location.protocol + "//" +
                   window.location.host + "/gerenciador/duvidas/news"
        })

        // SideMenu Service
        .factory('SideMenuService', function ($http, SideMenuValue) {

           var HEADERS = {headers: {
                 'Content-Type': 'applicaton/json; charset=UTF-8',
                 'Accept': 'application/json;Charset=utf-8'
              },
              dataType: 'json'};


           var _getTotalNewDuvidas = function () {
              return $http.get(SideMenuValue.url_get_new_duvidas, HEADERS);
           };

           return {
              getTotalNewDuvidas: _getTotalNewDuvidas
           };
        })

        // SideMenu Controller
        .controller('SideMenuCtrl', function ($scope, SideMenuService) {
           $scope.newDuvidas = {'quantidade': 0};

           var _getTotalNewDuvidas = function () {

              SideMenuService.getTotalNewDuvidas()

                      .success(function (data) {
                         $scope.newDuvidas = data;
                         console.log(data);
                      })

                      .error(function () {
                         $scope.newDuvidas = {'quantidade': 0};
                      });
           };

           _getTotalNewDuvidas();

        });


