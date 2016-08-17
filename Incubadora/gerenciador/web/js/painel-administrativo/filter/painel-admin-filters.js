/* global angular */

angular.module('painelAdmin')

        /* Limia a exibição to texto de cada item da lista de empreendedores colocando */
        .filter('truncate', function () {
           return function (input, size) {
              if (input.length <= size)
                 return input;
              var output = input.substring(0, (size || 30)) + "...";
              return output;
           };
        })

        /* Valida campos usando expressão regular */
        .filter('validade', function () {

           return function (input) {
              if (/()/.test(input)) {
                 return input;
              }
              return input;
           };

        })

        .filter('dotToComma', function () {
           return function (input) {
              var pattern = /^\d{1}\d*.\d{1}\d*$/;
              if (input !== undefined) {
                 if (pattern.test(input)) {
                    var peso = input;
                    input = String(peso).replace(".", ",");
                 }
              }
              return input;
           };
        })

        /* Não permite repetições de itens da lista de empreendedores*/
        .filter('unrepeated', function () {
           var newDuvidas = [];
           return function (duvidas) {

              // Obetém empreendedores presentes nas dúvidas

              var temp = [];
              for (var i in duvidas) {
                 temp.push(duvidas[i]);
              }
              var count = {id: '0'};
              newDuvidas = temp.filter(function (d) {

                 if (!count['id-' + d.empreendedor.id]) {
                    count['id-' + d.empreendedor.id] = d.empreendedor.id;
                    return d;
                 }

              });
              return newDuvidas;
           };
        })

        /*  Não permite repetições na listagem de resposta das dúvidas */
        .filter('unrepeatedResp', function () {
           var newResp = [];
           return function (resp) {

              // Obetém empreendedores presentes nas dúvidas

              var temp = [];
              for (var i in resp) {
                 temp.push(resp[i]);
              }
              var count = {id: '0'};
              newResp = temp.filter(function (r) {

                 if (!count['id-' + r.id]) {
                    count['id-' + r.id] = r.id;
                    return r;
                 }

              });
              return newResp;
           };

        })

        .filter('byEixoId', function () {
           var newCriterios = [];
           return function (criterios, eixoId) {
              newCriterios = criterios.filter(function(c){
                 if(c.eixo.id == eixoId) return c;
              });
              return newCriterios
           };
        })


        /* Exibe apenas a dúvida pertinente a cada empreendedor sem repetições */
        .filter('duvidaByEmp', function () {
           var newDuvidas = [];
           return function (oldDuvidas, idEpreendedor) {
              newDuvidas = oldDuvidas.filter(function (d) {
                 if (d.empreendedor.id === idEpreendedor) {
                    return d;
                 }

              });

              return newDuvidas;
           };

        });

        