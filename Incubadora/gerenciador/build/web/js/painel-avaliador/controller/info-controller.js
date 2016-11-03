/* global angular */

angular.module('painelAvaliador')
        .controller('MainCtrlAvaliador', function ($scope, AvaliadorService) {

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
                        $("#modal-success-update").openModal(configModal);
//                        Materialize.toast('Dados atualizados com sucesso! =)', 4000, 'rounded green');
                    }).error(function () {
                        Materialize.toast('Erro ao tentar atualizar dados! =(', 4000, 'rounded orange');
                    });
                } catch (e) {
                    Materialize.toast('Erro ao tentar atualizar dados! =(', 4000, 'rounded red');
                }

            };



        });