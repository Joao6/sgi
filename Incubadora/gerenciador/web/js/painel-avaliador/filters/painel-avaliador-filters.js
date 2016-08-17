/* global angular */

angular.module('painelAvaliador')

        /* Limia a exibição to texto de cada item da lista de empreendedores colocando */
        .filter('truncate', function () {
           return function (input, size) {
              if (input.length <= size)
                 return input;
              var output = input.substring(0, (size || 30)) + "...";
              return output;
           };
        })


       .filter('byEixoId', function () {
           var newCriterios = [];
           return function (criterios, eixoId) {
              newCriterios = criterios.filter(function(c){
                 if(c.eixo.id == eixoId) return c;
              });
              return newCriterios;
           };
        });



        