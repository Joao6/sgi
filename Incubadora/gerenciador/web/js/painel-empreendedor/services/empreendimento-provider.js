/* global angular */

angular.module('painelEmpreendedor').provider('empreendimentoProvider', function(){
   this.$get = function(){
      return {
         empreendimento:function(){
            return {nome:''};
         }
      };
   };
});