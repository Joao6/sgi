/* global angular */

var app = angular.module('painelEmpreendedor', ['caco.ClientPaginate', 'ngRoute']);
app.controller('MainCtrl', function ($scope, InfoService) {

    var configModal = {
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .5, // Opacity of modal background
        in_duration: 1, // Transition in duration
        out_duration: 1, // Transition out duration
        ready: function () {
            return;
        }, // Callback for Modal open
        complete: function () {
        } // Ca
    };

    // Estados listados no selct UF
    $scope.estados = [
        {'id': 01, 'sigla': 'AC'}, {'id': 02, 'sigla': 'AP'}, {'id': 03, 'sigla': 'AL'},
        {'id': 04, 'sigla': 'AM'}, {'id': 05, 'sigla': 'BA'}, {'id': 06, 'sigla': 'CE'},
        {'id': 07, 'sigla': 'DF'}, {'id': 08, 'sigla': 'ES'}, {'id': 09, 'sigla': 'GO'},
        {'id': 10, 'sigla': 'MA'}, {'id': 11, 'sigla': 'MT'}, {'id': 12, 'sigla': 'MS'},
        {'id': 13, 'sigla': 'MG'}, {'id': 14, 'sigla': 'PA'}, {'id': 15, 'sigla': 'PB'},
        {'id': 16, 'sigla': 'PR'}, {'id': 17, 'sigla': 'PE'}, {'id': 18, 'sigla': 'PI'},
        {'id': 19, 'sigla': 'RJ'}, {'id': 20, 'sigla': 'RN'}, {'id': 21, 'sigla': 'RS'},
        {'id': 22, 'sigla': 'RO'}, {'id': 23, 'sigla': 'RR'}, {'id': 24, 'sigla': 'SC'},
        {'id': 25, 'sigla': 'SP'}, {'id': 26, 'sigla': 'SE'}, {'id': 27, 'sigla': 'TO'}
    ];


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
//                Materialize.toast('Dados atualizados com sucesso! =)', 4000, 'rounded green');
                $("#modal-success-update").openModal(configModal);
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