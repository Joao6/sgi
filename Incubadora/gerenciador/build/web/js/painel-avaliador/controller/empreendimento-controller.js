/* global angular */
/* global Materialize */

(function () {
    angular.module('painelAvaliador').controller('EmpCtrl', function ($scope, EmpreendimentoAPI) {
        /* MESSAGES */

        this.MESSAGE_GET_EMPREENDIMENTOS_ERROR = "Erro ao tentar receber empreendimentos.";
        this.MESSAGE_GET_SERVER_BAD_CONNECTION = "Erro ao tentar comunicar com o servidor.";
        this.MESSAGE_EDITAL_GET_ERROR = "Erro ao tentar obter lista de Editais em abterto.";
        this.MESSAGE_RAMOS_GET_ERROR = "Erro ao tentar obter lista de Ramos de Atividade.";


        $scope.empreendimentoList = [];
        $scope.eixoList = [];
        $scope.notaList = [];
        $scope.criterioList = [];
        $scope.empreendimento = {};
        $scope.apresentacaoNegocio = {empreendimento: {'id': 0}};
        $scope.editalList = [];
        $scope.nextForm = 1;
        $scope.ramoAtividadeList = [];
        var statusList = [];
        $scope.empreendimento = {};
        $scope.rowsPerPage = 5;

        statusList[0] = 'Proposta Enviada';
        statusList[1] = 'Apresentação Agendada';
        statusList[2] = 'Apresentação Realizada';
        statusList[3] = 'Avaliação Realizada';
        statusList[4] = 'Aprovado';

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

        var status = $("#status").val();
        var idAvaliador = $("#avaliador-id").val();
        var idEmpreendimento = $("#empreendimento-id").val();

        $scope.apresentacaoNegocio.empreendimento.id = idAvaliador;


        var app = this;

        $(document).ready(function () {
            // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
            $('.modal-trigger').leanModal();
        });

        //openModal (JP)
        $scope.openModal = function (id, empreendimentoId) {
            delete $scope.empreendimento;
            _getEmpreendimento(empreendimentoId);
            
            
            $("#modal-" + id).openModal(configModal);
        };
        //openModal (JP)

        function _getEmpreendimentos() {
            try {
                EmpreendimentoAPI.getEmpreendimentos(idAvaliador).success(function (data) {
                    $scope.empreendimentoList = data;
                }).error(function () {
                    Materialize.toast(app.MESSAGE_GET_EMPREENDIMENTOS_ERROR, 4000, 'orange rounded');
                });
            } catch (e) {
                Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'red rounded');
                console.log(e);
            }

        }

        if (idAvaliador !== undefined && idAvaliador !== "") {
            _getEmpreendimentos();
        }


        function _getEmpreendimento(idEmpreendimento) {
            try {
                EmpreendimentoAPI.getEmpreendimento(idEmpreendimento).success(function (data) {
                    $scope.empreendimento = data;
                    $scope.apresentacaoNegocio = $scope.empreendimento.apresentacaoNegocio;
                }).error(function () {
                    Materialize.toast(app.MESSAGE_GET_EMPREENDIMENTOS_ERROR, 4000, 'orange rounded');
                });
            } catch (e) {
                Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'red rounded');
                console.log(e);
            }

        }
        
        if (idEmpreendimento !== undefined && idEmpreendimento !== "") {
            _getEmpreendimento();
        }

        function _getEixos() {
            try {
                EmpreendimentoAPI.getEixos().success(function (data) {
                    $scope.eixoList = data;
                    $scope.eixoList[0].active = true; // default
                }).error(function () {

                });
            } catch (e) {
                Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'red rounded');
                console.log(e);
            }

        }

        _getEixos();

        function _getCriterios() {
            try {
                EmpreendimentoAPI.getCriterios().success(function (data) {
                    $scope.criterioList = data;
                }).error(function () {

                });
            } catch (e) {
                Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'red rounded');
                console.log(e);
            }

        }
        _getCriterios();


        $scope.save = function (empreendimento) {
            try {
                EmpreendimentoAPI.save(empreendimento).success(function () {
                    location.href = "/gerenciador/empreendedor/empreendimentos";
                }).error(function () {
                    Materialize.toast('Erro ao tentar cadastrar empreendimento.', 4000, 'rounded orange');
                });
            } catch (e) {
                Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'red rounded');
                console.log(e);
            }

        };



        $scope.addApNegocio = function (apresentacaoNegocio) {
            try {
                EmpreendimentoAPI.addApNegocio(apresentacaoNegocio).success(function () {
                    window.location.href = '/gerenciador/empreendedor/empreendimentos';
                }).error(function () {
                    Materialize.toast('Erro ao tentar salvar dados!', 4000, 'orange rounded');
                });
            } catch (e) {
                console.log(e);
                Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'red rounded');
            }

        };

        $scope.remover = function (id) {
            try {
                EmpreendimentoAPI.remover(id).success(function () {
                    _getEmpreendimentos();
                    Materialize.toast('Empreendimento removido com sucesso!', 4000, 'green rounded');
                }).error(function () {
                    Materialize.toast('Erro ao tentar excluir empeendimento.', 4000, 'orange rounded');
                });
            } catch (e) {
                console.log(e);
                Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'red rounded');
            }

        };

        $scope.isEmpreendimentoListEmpty = function () {
            return $scope.empreendimentoList.length < 1;
        };

        function onActive(id) {
            var eixo = $scope.eixoList.filter(function (e) {
                if (e.id === id)
                    return e;
            });

            for (var i = 0; i < $scope.eixoList.length; i++) {
                $scope.eixoList[i].active = false;
            }

            if (!eixo[0].active) {
                eixo[0].active = true;
            }
        }

        $scope.setActive = function (eixo) {
            onActive(eixo.id);
            if (eixo.show !== true) {
                eixo.show = true;
            } else {
                eixo.show = false;
            }
        };


        $scope.isActive = function (eixo) {
            return eixo.active;
        };


        $scope.setNextForm = function (next) {
            $scope.nextForm = next;
            console.log($scope.nextForm);
        };
        $scope.validStatus = function (empreendimento, status) {
            return statusList.indexOf(empreendimento.status) >= statusList.indexOf(status);
        };

        $scope.concluir = function () {
            try {
                for (var i = 0; i < $scope.criterioList.length; i++) {
                    if ($scope.criterioList[i].nota !== undefined) {
                        $scope.notaList.push({
                            'nota': $scope.criterioList[i].nota.nota,
                            criterioAvaliacao: {'id': $scope.criterioList[i].id},
                            empreendimento: {'id': idEmpreendimento},
                            avaliador: {'id': idAvaliador},
                            dataHora: new Date()
                        });
                    }
                }
                console.log($scope.notaList);
            } catch (e) {
                console.log(e);
            }

            saveNotaList();
        };

        function saveNotaList() {
            try {
                EmpreendimentoAPI.saveNotas($scope.notaList).success(function () {
                    setTimeout(function () {
                        Materialize.toast('Avalição realizada com sucess!', 1000, 'green rounded');
                        window.location.href = window.location.protocol + "//" + window.location.host + "/gerenciador/avaliador/empreendimentos";
                    }, 5000);
                }).error(function () {

                    Materialize.toast('Erro ao tentar salvar avaliação', 4000, 'red rounded');
                });
            } catch (e) {
                console.log(e);
            }

        }

    });
})();