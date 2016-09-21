/* global angular */

var app = angular.module('painelEmpreendedor', ['caco.ClientPaginate', 'ngRoute']);
app.controller('MainCtrl', function ($scope, InfoService) {
    $scope.empreendedor = {};

    var _getInfo = function () {
        try {
            InfoService.getInfo()
                    .success(function (data) {
                        if (data.dataNascimento !== null & data.dataNascimento !== undefined) {
                            var _d = data.dataNascimento.split("-");
                            //dd/MM/yyyy          
                            data.dataNascimento = (_d[2] + "/" + _d[1] + "/" + _d[0]);
                        } else {
                            data.dataNascimento = null;
                        }

                        $scope.empreendedor = data;
                        console.log(data);
                    }).error(function () {

            });
        } catch (e) {

            console.log(e);
        }
    };

    _getInfo();

    //Update todo
    $scope.updateInfo = function (empreendedor) {
        try {
            if (empreendedor.dataNascimento !== "" && empreendedor.dataNascimento !== undefined) {
                var _d = empreendedor.dataNascimento.split("/");
                empreendedor.dataNascimento = new Date(_d[1] + "-" + _d[0] + "-" + _d[2]);
            } else {
                empreendedor.dataNascimento = "";
            }

            InfoService.updateInfo(empreendedor).success(function () {
                _getInfo();
                Materialize.toast('Dados atualizados com sucesso! =)', 4000, 'rounded green');
            }).error(function () {
                Materialize.toast('Erro ao tentar atualizar dados! =(', 4000, 'rounded red');
            });
        } catch (e) {
            Materialize.toast('Erro ao tentar atualizar dados! =(', 4000, 'rounded red');
        }

    };

});


//   $routeProvider.when("/proposta/page/2", {
//      templateUrl: '/gerenciador/html/empreendedor/empreendimento/form2-proposta.html',
//      controller: 'EmpreendimentoCtrl',
//      resolve: {
//         apresentacao: function($route){
//            return $route.current;
//         }
//      }
//   });
//
//   $routeProvider.when("/proposta/page/3", {
//      templateUrl: '/gerenciador/html/empreendedor/empreendimento/form3-proposta.html',
//      controller: 'EmpreendimentoCtrl',
//      resolve: {
//         apresentacao: function($route){
//            return $route.current;
//         }
//      }
//   });
//
//   $routeProvider.when("/proposta/page/4", {
//      templateUrl: '/gerenciador/html/empreendedor/empreendimento/form4-proposta.html',
//      controller: 'EmpreendimentoCtrl',
//      resolve: {
//         apresentacao: function($route){
//            return $route.current;
//         }
//      }
//   });
//
//   $routeProvider.when("/proposta/page/5", {
//      templateUrl: '/gerenciador/html/empreendedor/empreendimento/form5-proposta.html',
//      controller: 'EmpreendimentoCtrl',
//      resolve: {
//         apresentacao: function($route){
//            return $route.current;
//         }
//      }
//   });
//
//   
//});