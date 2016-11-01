/* global angular */

angular.module('painelAvaliador')
        .controller('MainCtrlAvaliador', function ($scope, AvaliadorService) {

            $scope.avaliador = {};

            var _getInfo = function () {
                try {
                    AvaliadorService.getInfo()
                            .success(function (data) {
//                                var _d = data.dataNascimento.split("-");
//                                //yyyy-MM-dd           
//                                data.dataNascimento = new Date(_d[0], _d[1] - 1, _d[2]);

                                $scope.avaliador = data;
                            }).error(function () {
                        Materialize.toast('Erro ao tentar buscar o avaliador! =(', 4000, 'rounded orange');
                    });
                } catch (e) {

                    console.log(e);
                }
            };
            _getInfo();

            //Update todo
            $scope.updateInfo = function (avaliador) {
                try {
                    AvaliadorService.updateInfo(avaliador).success(function () {
                        _getInfo();
                        Materialize.toast('Dados atualizados com sucesso! =)', 4000, 'rounded green');
                    }).error(function () {
                        Materialize.toast('Erro ao tentar atualizar dados! =(', 4000, 'rounded orange');
                    });
                } catch (e) {
                    Materialize.toast('Erro ao tentar atualizar dados! =(', 4000, 'rounded red');
                }

            };



        });