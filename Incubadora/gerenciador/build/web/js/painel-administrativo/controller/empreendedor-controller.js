/* global angular */

angular.module('painelAdmin')
        .controller('EmpreendedorCtrl', function ($scope, $rootScope, $timeout, EmpreendedorService) {

            /**
             * Mensagens 
             */
            this._MESSAGE_EMAIL_CADASTRADO = "Este E-mail já está cadastrado! =(";
            this._MESSAGE_EMAIL_DISPONIVEL = "E-mail disponível para cadastro. =)";
            this._MESSAGE_ERRO_PROCESSAR_EMAIL = "Erro ao tentar processar E-mail!";
            this._MESSAGE_BAD_CONNECTION = "Erro ao tentar conectar com o servidor. =(";
            this._MESSAGE_ERRO_CAMPO_EMAIL_VAZIO = "Por favor, preencha o campo E-mail!";
            this._MESSAGE_CADASTRO_ERROR = "Erro ao tentar cadastrar empreendedor! =(";
            this._MESSAGE_CADASTRO_SUCCESS = "Candidato cadastrado com sucesso!";
            this._MESSAGE_FIELD_REQUIRED = "Este campo é obrigatório";

            $scope.empreendedorList = [];
            $scope.empreendedimentoList = [];
            $scope.testValues = [];
            $scope.rowsPerPage = 5; // setar para paginar

            var app = this;
            $scope.empreendedorIdToDelete = 0;
            $scope.emailOk = true;
            $scope.validCPF = true;
            $scope.validTelefone = true;
            $scope.validEmail = true;


            // Config modal
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


            // Excluir empreendedor
            $(document).on('click', '.btn-excluir', function (e) {
                var id = $(this).attr('id').toString().substring(12);
                $scope.empreendedorIdToDelete = id;
                $(".alert-modal-excluir").openModal(configModal);
            });


            /* Visualizar detalhes */
            $scope.openModalDetalhes = function (empreendedor) {
                $scope.empreendedor = empreendedor;
                try {

                    EmpreendedorService.getEmpreendimentoByEmpreendedorId(empreendedor.id)
                            .success(function (data) {
                                if (data.length < 1) {
                                    $("table.table-detalhes [data-field='empresa']").text("Nenhuma");
                                } else {
                                    $scope.empreendedor.empreendimentoList = data;
                                }
                            })
                            .error(function () {
                                $("table.table-detalhes [data-field='empresa']").text("Nenhuma");
                            });
                } catch (e) {
                    console.log(e);
                    Materialize.toast(app._MESSAGE_BAD_CONNECTION, 4000, 'red rounded');
                }
                $("#modal-detalhes").openModal(configModal);
            };


            /* Obtém do servidor os empreendedores cadastrados no sistema */
            var _getEmpreendedores = function () {

                try {
                    EmpreendedorService.getEmpreendedores().success(function (data) {
                        $scope.empreendedorList = data;
                        for (var e in $scope.empreendedorList) {
                            $scope.testValues.push(e);
                        }
                    }).error(function () {
                        console.log('erro ao receber empreendedorList');
                    });
                } catch (e) {
                    console.log('erro ao obter empreendedores');
                }
            };

            _getEmpreendedores();


            /* Checa se existe algum empreendedor em empreendedorList */
            $scope.isEmpreendedorEmpty = function () {
                if ($scope.empreendedorList.length < 1)
                    return true;
                else
                    return false;
            };

            /* Adicionar empreendedor a base de dados */
            $scope.addEmpreendedor = function (empreendedor) {
                EmpreendedorService.addEmpreendedor(empreendedor).success(function () {
                    $("#modal-success").openModal();
                }).error(function () {
                    $("#modal-error").openModal();
                });
                _getEmpreendedores();
            };

            /* Exclui Empreendedor do banco de dados */
            $scope.removerEmpreendedor = function (id) {
                if (id !== undefined && id !== null) {
                    try {
                        EmpreendedorService.removerEmpreendedor(id)
                                .success(function () {
                                    _getEmpreendedores();
                                    $(".alert-modal-excluir").closeModal();
                                    Materialize.toast('Empreendedor excluído com sucesso! =)', 4000, 'green rounded');
                                })
                                .error(function () {
                                    Materialize.toast('Erro ao tentar excluir Empreendedor. =(', 4000, 'orange rounded');
                                    console.log('erro ao excluir Empreendedor');
                                });
                    } catch (e) {
                        console.log(e);
                        Materialize.toast('Erro ao tentar comunicar com o servidor. =(', 4000, 'rounded red');
                    }

                } else {
                    console.log('id inválido de Empreendedor: ' + id);
                }

            };


        });
