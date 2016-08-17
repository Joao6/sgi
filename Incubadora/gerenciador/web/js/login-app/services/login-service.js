  (function () {
     'use strict';

     /* global angular */
     angular.module('loginApp')
         .factory('LoginService', function ($http, LoginValue) {
            var HEADERS = {
               headers: {
                  'Content-Type': 'application/json; charset=UTF-8',
                  "Accept": "application/json;charset=utf-8"}, dataType: "json"};

            var _validadeUser = function (user) {
               return $http.post(LoginValue.url_check_user_exist, user, HEADERS);
            };

            var _loginDefault = function (user) {
               return $http.post(LoginValue.url_login_default, user, HEADERS);
            };

            return {
               validateUser: _validadeUser,
               loginDefault: _loginDefault
            };

         });
  })();