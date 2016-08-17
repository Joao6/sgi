/* global angular */

angular.module("loginApp").directive('uiModalLogin', function(){
   return {
      templateUrl:window.location.protocol + "//" + window.location.host + "/gerenciador/html/template/uiModalLogin.html",
      restrict:'E',
      scope:{
         title:"@",
         tipo:"@",
         userType:"@"
      }
   };
});