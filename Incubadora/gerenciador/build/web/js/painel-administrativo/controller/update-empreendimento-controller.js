angular.module('painelAdmin').controller('UpdateCtrl', function ($scope, UpdateService) {

    this.MESSAGE_GET_EMPREENDIMENTOS_ERROR = "Erro ao tentar receber empreendimentos.";
    this.MESSAGE_GET_AVALIADORES_ERROR = "Erro ao tentar receber avaliadores.";
    this.MESSAGE_GET_EMPREENDEDORES_ERROR = "Erro ao tentar receber empreendedores.";
    this.MESSAGE_GET_SERVER_BAD_CONNECTION = "Erro ao tentar comunicar com o servidor.";
    this.MESSAGE_EDITAL_GET_ERROR = "Erro ao tentar obter lista de Editais em abterto.";
    this.MESSAGE_RAMOS_GET_ERROR = "Erro ao tentar obter lista de Ramos de Atividade.";
    this.MESSAGE_UPDATE_SUCCESS = "Empreendimento atualizado com sucesso.";
    this.MESSAGE_UPDATE_ERROr = "Erro ao tentar atualizar empreendimento.";


    $scope.empreendimentoList = [];
    $scope.avaliadorList = [];
    $scope.avaliadorList = [];
    $scope.avaliadores = [];
    $scope.empreendimento = {};
    $scope.avaliador = {};
    //(JP)
    $scope.empreendedor = {};
    $scope.empreendedores = [];
    $scope.empreendedorList = [];
    //(JP)
    $scope.apresentacaoNegocio = {empreendimento: {'id': 0}};
    $scope.apresentacao = {};
    $scope.editalList = [];
    $scope.nextForm = 1;
    $scope.btnStatusOk = false;
    $scope.ramoAtividadeList = [];
    $scope.nextStatus = 'Alterar Status';
    $scope.statusResult = 'Reprovado';
    $scope.showProgress = false;
    var statusList = [];
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
    $scope.empreendimento = {};
    $scope.rowsPerPage = 5;
    // Inicialização dos Status
    statusList[0] = 'Proposta Enviada';
    statusList[1] = 'Apresentação Agendada';
    statusList[2] = 'Apresentação Realizada';
    statusList[3] = 'Avaliação Realizada';
    statusList[4] = 'Aprovado';
    statusList[5] = 'Reprovado';

    var status = $("#status").val();
    var idEmpreendimento = $("#empreendimento-id").val();
    $scope.apresentacaoNegocio.empreendimento.id = idEmpreendimento;


    var app = this;




    var app = this;

    function _getEmpreendimentoById() {
        try {
            var id = angular.element('#idEmpree').val();
            UpdateService.getEmpreendimento(id).success(function (data) {
                if (data.dataAbertura !== null & data.dataAbertura !== undefined) {
                    var _d = data.dataAbertura.split("-");
                    //dd/MM/yyyy          
                    var _dd = _d[2].split(" ");
                    data.dataAbertura = (_d[0] + "/" + _d[1] + "/" + _dd[0]);
                } else {
                    data.dataAbertura = null;
                }

                if (data.dataIngresso !== null & data.dataIngresso !== undefined) {
                    var _d = data.dataIngresso.split("-");
                    //dd/MM/yyyy          
                    var _dd = _d[2].split(" ");
                    data.dataIngresso = (_d[0] + "/" + _d[1] + "/" + _dd[0]);
                } else {
                    data.dataIngresso = null;
                }

                if (data.dataPrevisaoGraduacao !== null & data.dataPrevisaoGraduacao !== undefined) {
                    var _d = data.dataPrevisaoGraduacao.split("-");
                    //dd/MM/yyyy          
                    var _dd = _d[2].split(" ");
                    data.dataPrevisaoGraduacao = (_d[0] + "/" + _d[1] + "/" + _dd[0]);
                } else {
                    data.dataPrevisaoGraduacao = null;
                }
                $scope.empreendimento = data;
            }).error(function () {
                Materialize.toast(app.MESSAGE_GET_EMPREENDIMENTOS_ERROR, 4000, 'orange rounded');
            });
        } catch (e) {
            Materialize.toast(app.MESSAGE_GET_EMPREENDIMENTOS_ERROR, 4000, 'orange rounded');
            console.log(e);
        }
    }
    ;

    _getEmpreendimentoById();

    function _getAvaliadores() {
        try {
            UpdateService.getAvaliadores().success(function (data) {
                $scope.avaliadorList = data;
                $scope.avaliadores = angular.copy($scope.avaliadorList);
            }).error(function () {
                Materialize.toast(app.MESSAGE_GET_AVALIADORES_ERROR, 4000, 'orange rounded');
            });
        } catch (e) {
            Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'orange rounded');
            console.log(e);
        }

    }
    _getAvaliadores();

    function getEditais() {
        try {
            UpdateService.getEditais().success(function (data) {
                $scope.editalList = data;
            }).error(function () {
                Materialize.toast(app.MESSAGE_EDITAL_GET_ERROR, 4000, 'rounded orange');
            });
        } catch (e) {
            console.log(e);
            Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'rounded red');
        }
    }

    getEditais();

    function getRamos() {
        try {
            UpdateService.getRamos().success(function (data) {
                $scope.ramoAtividadeList = data;
            }).error(function () {
                Materialize.toast(app.MESSAGE_RAMOS_GET_ERROR, 4000, 'rounded orange');
            });
        } catch (e) {
            console.log(e);
            Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'rounded red');
        }

    }

    getRamos();

    $scope.save = function (empreendimento) {
        try {
            UpdateService.save(empreendimento).success(function () {
                location.href = "/gerenciador/empreendedor/empreendimentos";
            }).error(function () {
                Materialize.toast('Erro ao tentar cadastrar empreendimento.', 4000, 'rounded orange');
            });
        } catch (e) {
            Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'red rounded');
            console.log(e);
        }

    };

    $scope.updateEmpreendimento = function (empreendimento) {
        try {
            if (empreendimento.dataIngresso !== null && empreendimento.dataIngresso !== undefined) {
                var _di = empreendimento.dataIngresso.split("/");
                empreendimento.dataIngresso = (_di[0] + "-" + _di[1] + "-" + _di[2]);
            } else {
                empreendimento.dataIngresso = "";
            }

            if (empreendimento.dataAbertura !== null && empreendimento.dataAbertura !== undefined) {
                var _da = empreendimento.dataAbertura.split("/");
                empreendimento.dataAbertura = (_da[0] + "-" + _da[1] + "-" + _da[2]);
            } else {
                empreendimento.dataAbertura = "";
            }

            if (empreendimento.dataPrevisaoGraduacao !== null && empreendimento.dataPrevisaoGraduacao !== undefined) {
                var _dp = empreendimento.dataPrevisaoGraduacao.split("/");
                empreendimento.dataPrevisaoGraduacao = (_dp[0] + "-" + _dp[1] + "-" + _dp[2]);
            } else {
                empreendimento.dataPrevisaoGraduacao = "";
            }

            UpdateService.updateEmpreendimento(empreendimento).success(function () {
                _getEmpreendimentoById();
                Materialize.toast(app.MESSAGE_UPDATE_SUCCESS, 4000, 'rounded green');
            }).error(function () {
                Materialize.toast(app.MESSAGE_UPDATE_ERROR, 4000, 'rounded orange');
            });
        } catch (e) {
            Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'red rounded');
            console.log(e);
        }
    };

    $scope.addApNegocio = function (apresentacaoNegocio) {
        try {
            UpdateService.addApNegocio(apresentacaoNegocio).success(function () {
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
            UpdateService.remover(id).success(function () {
//                _getEmpreendimentos();
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

    $scope.isAvaliadorListEmpty = function () {
        return $scope.avaliadorList.length < 1;
    };

    $scope.addAvaliador = function (avaliador) {
        $scope.empreendimento.avaliadorList.push(avaliador);
        console.log($scope.empreendimento.avaliadorList);
    };

    $scope.setActive = function (empreendimento) {
        if (empreendimento.show !== true) {
            empreendimento.show = true;
        } else {
            empreendimento.show = false;
        }
    };


    $scope.isActive = function (id) {
        if (id === $scope.nextForm)
            return true;

    };


    $(document).on('mouseover', 'a.btn', function (e) {
        var id = $(this).attr('id');
        var message = "";
        var tagClose = ".";
        switch (id) {
            case 'btn-add-avaliador':
                message += "Selecione os Avaliadores deste empreendimento" + tagClose;
                break;
            case 'btn-add-empreendedor':
                message += "Empreendimento" + tagClose;
                break;
            case 'btn-status':
                message += "Altere o status deste empreendimento" + tagClose;
                break;
            case 'btn-ver-proposta':
                message += "Clique para ver a prosposta" + tagClose;
                break;
            case 'btn-detalhes':
                message += "Clique para ver mais informações" + tagClose;
                break;
            case 'btn-editar':
                message += "Clique para editar" + tagClose;
                break;
        }

//        $(this).addClass('tooltiped');
//        $(this).attr({'data-tooltip': message});
//        $(this).tooltip();


    });


    setTimeout(function () {
        $('.collection-item .card-content').each(function (index) {

            $(this).hide();
        });
    }, 1);


    // Eifeito accordion
    $(document).on('click', 'a.emp-accordion-link', function () {
        var id = $(this).attr('id');
        accordion(id);

    });

    var _onShow = {id: '0', show: false};
    function accordion(id) {

        if (!_onShow['id-' + id] || !_onShow.show) {
            _onShow['id-' + id] = id;
            _onShow.show = true;

            $("a#" + id).parents('.collection-item ').find('.card-content').fadeIn(500, function () {
                $(this).show();
            });
            console.log(_onShow);
        } else {
            _onShow.show = false;
            $("a#" + id).parents('.collection-item').find(".card-content").fadeOut(80);
            console.log(_onShow);
        }


    }

    $scope.setNextForm = function (next) {
        $scope.nextForm = next;
        console.log($scope.nextForm);
    };
    $scope.validStatus = function (empreendimento, status) {
        return statusList.indexOf(empreendimento.status) >= statusList.indexOf(status);
    };
    $scope.validStatusResult = function (empreendimento) {
        return ((empreendimento.status === 'Aprovado') || (empreendimento.status === 'Reprovado'));
    };
    $scope.validStatusReprovado = function (empreendimento) {
        return (empreendimento.status === 'Reprovado');
    };
    $scope.validStatusAprovado = function (empreendimento) {
        return (empreendimento.status === 'Aprovado');
    };

    $scope.openModal = function (id, empreendimento) {
        delete $scope.empreendimento;
        $scope.apresentacaoNegocio = empreendimento.apresentacaoNegocio;
        $scope.empreendimento = empreendimento;
//        _getAvaliadores();
        var index = 0;
        if (id === 2) {
            index = statusList.indexOf(empreendimento.status);
            switch (index) {
                case 0 :
                    $("#modal-apresentacao").openModal(configModal);
                    break;
                case 1:
                    if (index < statusList.length - 1) {
                        $scope.nextStatus = statusList[index + 1];
                        $(".btn-status").text($scope.nextStatus);
                    }
                    $("#modal-" + id).openModal(configModal);
                    break;
                case 2:
                    if (index < statusList.length - 1) {
                        $scope.nextStatus = statusList[index + 1];
                        $(".btn-status").text($scope.nextStatus);
                    }
                    $("#modal-" + id).openModal(configModal);
                    break;
                case 3:
                    if (index < statusList.length - 1) {
                        $scope.nextStatus = statusList[index + 1];
                        $(".btn-status").text($scope.nextStatus);

//                        $(".btn-status-result").text($scope.statusResult);
                    }

                    $("#modal-" + id).openModal(configModal);
                    break;
                case 4:
                    if (index < statusList.length - 1) {
                        $scope.nextStatus = statusList[index + 1];
                        $(".btn-status").text($scope.nextStatus);
                    }
                    $(".btn-status").text("Processo Finalizado");
                    $(".btn-status").attr({'disabled': true});
                    $("#modal-" + id).openModal(configModal);
                    break;
                case 5:
                    if (index < statusList.length - 1) {
                        $scope.nextStatus = statusList[index + 1];
                        $(".btn-status").text($scope.nextStatus);
                    }
                    $(".btn-status").text("Processo Finalizado");
                    $(".btn-status").attr({'disabled': true});
                    $("#modal-" + id).openModal(configModal);
                    break;
                default:

            }

        } else {
            if ($scope.empreendimento.avaliadorList.length > 0) {
//                _getEmpreendimentos();
                // Garante que somente os avaliadores que não estão associados ao empreendimento
                // Sejam exibidos no Select.
                var aux = [];
                for (var i = 0; i < $scope.avaliadores.length; ++i) {
                    var found = $scope.empreendimento.avaliadorList.some(function (av) {
                        return (av.id === $scope.avaliadores[i].id);
                    });
                    if (!found) {
                        aux.push($scope.avaliadores[i]);
                    }
                }
                $scope.avaliadores = aux;
            }
//            $("#modal-" + id).openModal(configModal);

            if ($scope.empreendimento.empreendedorList.length > 0) {
//                _getEmpreendimentos();
                // Garante que somente os empreendedores que não estão associados ao empreendimento
                // Sejam exibidos no Select.
                var aux = [];
                for (var i = 0; i < $scope.empreendedores.length; ++i) {
                    var found = $scope.empreendimento.empreendedorList.some(function (av) {
                        return (av.id === $scope.empreendedores[i].id);
                    });

                    if (!found) {
                        aux.push($scope.empreendedores[i]);
                    }
                }
                $scope.empreendedores = aux;
            }
            $("#modal-" + id).openModal(configModal);
        }
    };

    $scope.agendarApresentacao = function (apresentacao) {
        try {
            apresentacao.id = $scope.empreendimento.id;
            UpdateService.agendarApresentacao(apresentacao).success(function () {
                var index = statusList.indexOf($scope.empreendimento.status);
                if (index < statusList.length) {
                    $scope.nextStatus = statusList[index + 1];
                    $scope.setStatus($scope.empreendimento.id);
                }
                Materialize.toast('Apresentação Agendada - Aguarde um instante...', 4000, 'green rounded');
                $("#modal-apresentacao").closeModal(configModal);
            }).error(function () {
                Materialize.toast('Erro ao tentar agendar Apresentação', 4000, 'orange rounded');
            });
        } catch (e) {
            $scope.showProgress = false;
            console.log(e);
            Materialize.toast('Erro ao tentar comunicar com o servidor', 4000, 'red rounded');
        }

    };


    $scope.desmarcarApresentacao = function (empreendimento) {
        $scope.nextStatus = statusList[0];
        $scope.openModal(11, empreendimento);
        $scope.setStatus(empreendimento.id);
    };


    $scope.setStatus = function (id) {
        var emp = {id: id, status: $scope.nextStatus};
        try {
            $scope.showProgress = true;
            UpdateService.alterarStatus(emp)
                    .success(function () {
//                        _getEmpreendimentos();
                        $scope.showProgress = false;
                        $("#modal-2").closeModal(configModal);
                        $("#modal-11").closeModal(configModal);
                    }).error(function () {
                $scope.showProgress = false;
                Materialize.toast('Erro ao tentar alterar o status do empreendimento', 4000, 'orange rounded');
            });
        } catch (e) {
            console.log(e);
            Materialize.toast('Erro ao tentar comunicar com o servidor', 4000, 'red rounded');
        }
    };
    $scope.setStatusAprovado = function (id, status) {
        var emp = {id: id, status: status};
        try {
            UpdateService.alterarStatus(emp)
                    .success(function () {
//                        _getEmpreendimentos();
                        //$("#modal-2").closeModal(configModal);
                    }).error(function () {
                Materialize.toast('Erro ao tentar alterar o status do empreendimento', 4000, 'orange rounded');
            });
        } catch (e) {
            console.log(e);
            Materialize.toast('Erro ao tentar comunicar com o servidor', 4000, 'red rounded');
        }
    };
    $scope.setStatusReprovado = function (id, status) {
        var emp = {id: id, status: status};
        try {
            UpdateService.alterarStatus(emp)
                    .success(function () {
//                        _getEmpreendimentos();
                        $("#modal-7").closeModal(configModal);
                    }).error(function () {
                Materialize.toast('Erro ao tentar alterar o status do empreendimento', 4000, 'orange rounded');
            });
        } catch (e) {
            console.log(e);
            Materialize.toast('Erro ao tentar comunicar com o servidor', 4000, 'red rounded');
        }
    };
    $scope.addAvaliador = function (avaliador) {
        if (avaliador.id !== "" && avaliador.id !== undefined) {
            // Remover do select
            $scope.avaliadores = $scope.avaliadores.filter(function (a) {
                if (a.id != avaliador.id) {
                    return a;
                } else {
                    $scope.empreendimento.avaliadorList.push(angular.copy(a));
                }
            });
        }
    };

    $scope.removeAvaliador = function (id) {
        if (id !== "" && id !== undefined) {
            $scope.empreendimento.avaliadorList = $scope.empreendimento.avaliadorList.filter(function (a) {
                if (a.id !== id) {
                    return a;
                } else {
                    $scope.avaliadores.push(angular.copy(a));
                }
            });
        }
    };

    $scope.associarAvaliadores = function () {
        try {
            UpdateService.associarAvaliadores($scope.empreendimento).success(function () {
//                _getEmpreendimentos();
                _getAvaliadores();
                Materialize.toast('Avaliadores associados com sucesso!', 4000, 'green rounded');
                $("#modal-4").closeModal(configModal);
            }).error(function () {
                Materialize.toast('Erro ao tentar associar Avaliadores!', 4000, 'orange rounded');
            });
        } catch (e) {
            console.log(e);
            Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION);
        }

    };

//    EMPREENDEDORES
//(JP)
    function _getEmpreendedores() {
        try {
            UpdateService.getEmpreendedores().success(function (data) {
                $scope.empreendedorList = data;
                $scope.empreendedores = angular.copy($scope.empreendedorList);
            }).error(function () {
                Materialize.toast(app.MESSAGE_GET_EMPREENDEDORES_ERROR, 4000, 'orange rounded');
            });
        } catch (e) {
            Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION, 4000, 'orange rounded');
            console.log(e);
        }

    }

    _getEmpreendedores();

    $scope.isEmpreendedorListEmpty = function () {
        return $scope.empreendedorList.lenght < 1;
    };

    $scope.addEmpreendedor = function (empreendedor) {
        $scope.empreendimento.empreendedorList.push(empreendedor);
        console.log($scope.empreendimento.empreendedorList);
    };

    $scope.addEmpreendedor = function (empreendedor) {
        if (empreendedor.id !== "" && empreendedor.id !== undefined) {
            // Remover do select
            $scope.empreendedores = $scope.empreendedores.filter(function (a) {
                if (a.id != empreendedor.id) {
                    return a;
                } else {
                    $scope.empreendimento.empreendedorList.push(angular.copy(a));
                }
            });
        }
    };

    $scope.removeEmpreendedor = function (id) {
        if (id !== "" && id !== undefined) {
            $scope.empreendimento.empreendedorList = $scope.empreendimento.empreendedorList.filter(function (a) {
                if (a.id != id) {
                    return a;
                } else {
                    $scope.empreendedores.push(angular.copy(a));
                }
            });
        }
    };

    $scope.associarEmpreendedores = function () {
        try {
            UpdateService.associarEmpreendedores($scope.empreendimento).success(function () {
//                _getEmpreendimentos();
                _getEmpreendedores();
                Materialize.toast('Empreendedores associados com sucesso!', 4000, 'green rounded');
                $("#modal-5").closeModal(configModal);
            }).error(function () {
                Materialize.toast('Erro ao tentar associar Empreendedores!', 4000, 'orange rounded');
            });
        } catch (e) {
            console.log(e);
            Materialize.toast(app.MESSAGE_GET_SERVER_BAD_CONNECTION);
        }

    };
    //(JP)
});


