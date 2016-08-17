  (function () {
     'use strict';
     /* global angular */
     angular.module('loginApp').value('LoginValue', {
        url_check_user_exist: window.location.protocol + "//" + window.location.host + '/gerenciador/login/validate',
        url_login_default: window.location.protocol + "//" + window.location.host + '/gerenciador/login/default'
     });

  })();