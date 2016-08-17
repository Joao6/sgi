/* global angular */
/* global Materialize */

(function () {
   angular.module('painelAdmin')
           .controller('AvaliadorCtrl', function ($scope, AvaliadorService) {

              this._MESSAGE_ERRO_CAMPO_EMAIL_VAZIO = "Campo obrigatório!";
              this._MESSAGE_BAD_CONNECTION = "Erro ao tentar conectar com o servidor.";
              this._MESSAGE_EMAIL_DISPONIVEL = "E-mail disponível =)";
              this._MESSAGE_EMAIL_CADASTRADO = "E-mail já cadastrado! =(";
              this._MESSAGE_ERRO_PROCESSAR_EMAIL = "Erro ao tentar processar e-mail.";

              $scope.avaliadorList = [];
              $scope.avaliador = {};
              $scope.emailOk = true;
              $scope.testValues = [];
              $scope.rowsPerPage = 5; // setar para paginar
              $scope.avaliadorIdToDelete = 0;
              $scope.validCPF = true;
              $scope.validTelefone = true;
              $scope.validEmail = true;

              var avaliadorCopy = {};
              var cpfCopy = "";
              var _MODE_EDIT = false;
              var _MODE_CREATE = false;
              var app = this;


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


              var avaliadorId = $("#avaliador-id").val();
              if (avaliadorId !== null && avaliadorId !== "" && avaliadorId !== undefined) {
                 _MODE_EDIT = true;
                 try {
                    AvaliadorService.getAvaliadorById(avaliadorId).success(function (data) {
                       $scope.avaliador = data;
                       avaliadorCopy = angular.copy($scope.avaliador);
                       cpfCopy = angular.copy($scope.avaliador.cpf);
                    }).error(function () {
                       Materialize.toast('Erro ao tentar obter dados do Avaliador. =(', 4000, 'orange rounded');
                    });
                 } catch (e) {
                    Materialize.toast(app._MESSAGE_BAD_CONNECTION, 4000, 'red rounded');
                    console.log(e);
                 }

              } else {
                 _MODE_CREATE = true;
              }


              /*Check CPF */
              function _checkCPF(cpf) {
                 var patternCPF = /^[0-9]{11}$/;

                 if (patternCPF.test(cpf)) {

                    // De 00000000000 para 000.000.000-00
                    var cpfReplaced = cpf.substr(0, 3) + ".";
                    cpfReplaced += cpf.substr(3, 3) + ".";
                    cpfReplaced += cpf.substr(6, 3) + "-";
                    cpfReplaced += cpf.substr(9);
                    cpf = cpfReplaced;
                 }
                 if (patternCPF.test(cpfCopy)) {

                    // De 00000000000 para 000.000.000-00
                    var cpfReplaced = cpfCopy.substr(0, 3) + ".";
                    cpfReplaced += cpfCopy.substr(3, 3) + ".";
                    cpfReplaced += cpfCopy.substr(6, 3) + "-";
                    cpfReplaced += cpfCopy.substr(9);
                    cpfCopy = cpfReplaced;
                 }

                 if (cpfCopy !== cpf) {
                    try {
                       AvaliadorService.checkCPF(cpf).success(function () {
                          $scope.validCPF = true;
                          $("#cpf").removeClass('red lighten-5');
                       }).error(function () {
                          Materialize.toast('CPF já cadastrado', 4000, 'orange rounded');
                          $scope.validCPF = false;
                       });
                    } catch (e) {
                       Materialize.toast('Erro ao comunicar com o servidor', 4000, 'red rounded');
                    }
                 } else {
                    $scope.validCPF = true;
                 }
                 return $scope.validCPF;

              }


              /* Obtém do servidor os avaliadores cadastrados no sistema */
              var _getAvaliadores = function () {

                 try {
                    AvaliadorService.getAvaliadores().success(function (data) {
                       $scope.avaliadorList = data;
                       $scope.testValues = $scope.avaliadorList;
                    }).error(function () {
                       console.log('erro ao receber empreendedorList');
                    });
                 } catch (e) {
                    console.log('erro ao obter avaliadores: ' + e);
                 }
              };

              _getAvaliadores();


              /* Checa se existe algum avaliador em avaliadorList */
              $scope.isAvaliadoresEmpty = function () {
                 if ($scope.avaliadorList.length < 1)
                    return true;
                 else
                    return false;
              };

              /* Adicionar avaliador a base de dados */
              $scope.addAvaliador = function (avaliador) {
                 var _send = true;
                 $("#form-avaliador").find(".validate").each(function () {
                    if ($(this).val() === "" || $(this).val() === null || $(this).val() === "0") {
                       $(this).addClass('red lighten-4');
                       _send = false;
                    } else {
                       $(this).removeClass('red lighten-4');
                    }
                 });

                 var patternCPF = /^[0-9]{11}$/;
                 var cpf = avaliador.cpf;
                 if (patternCPF.test(avaliador.cpf)) {

                    // De 00000000000 para 000.000.000-00
                    var cpfReplaced = cpf.substr(0, 3) + ".";
                    cpfReplaced += cpf.substr(3, 3) + ".";
                    cpfReplaced += cpf.substr(6, 3) + "-";
                    cpfReplaced += cpf.substr(9);
                    avaliador.cpf = cpfReplaced;
                 }

                 if (_send) {

                    var patternCPF = /^[0-9]{11}$/;

                    if (patternCPF.test(avaliador.cpf)) {

                       // De 00000000000 para 000.000.000-00
                       var cpfReplaced = cpf.substr(0, 3) + ".";
                       cpfReplaced += cpf.substr(3, 3) + ".";
                       cpfReplaced += cpf.substr(6, 3) + "-";
                       cpfReplaced += cpf.substr(9);
                       cpf = cpfReplaced;
                    }

                    if (_MODE_CREATE) {
                       try {
                          $scope.ok = true;
                          $('#modal-progress').openModal(configModal);
                          AvaliadorService.addAvaliador(avaliador).success(function () {
                             $scope.ok = false;
                             $("#modal-success").openModal(configModal);

                             console.log($scope.ok);
                          }).error(function () {
                             $scope.ok = false;
                             $("#modal-error").openModal(configModal);
                             console.log('erro ao cadastrar avaliador');
                          });
                       } catch (e) {
                          console.log(e);
                       }
                    } else if (_MODE_EDIT) {

                       try {
                          $scope.ok = true;
                          $("#modal-progress").openModal(configModal);
                          $scope.ok = false;
                          AvaliadorService.update(avaliador).success(function () {
                             $("#modal-progress").closeModal(configModal);
                             $("#modal-success-update").openModal(configModal);
                          }).error(function () {
                             $scope.ok = false;
                             $("#modal-progress").closeModal(configModal);
                             Materialize.toast('Erro ao tentar editar dados do Avaliador.', 4000, 'orange rounded');
                          });
                       } catch (e) {
                          console.log(e);
                          $("#modal-progress").closeModal(configModal);
                       }

                    }

                 }
              };

              /* Exclui avaliador selecionado */
              $scope.removerAvaliador = function (id) {

                 AvaliadorService.removerAvaliador(id).success(function () {
                    _getAvaliadores();
                    $(".alert-modal-excluir").closeModal(configModal);
                    Materialize.toast('Avaliador excluído com sucesso!', 4000, 'green rounded');
                 }).error(function () {
                    Materialize.toast('Erro ao tentar excluir Avaliador', 4000, 'orange rounded');
                 });
              };

              /* Visualizar detalhes */
              $scope.openModalDetalhes = function (avaliador) {
                 $scope.avaliador = avaliador;
                 $("#modal-detalhes").openModal(configModal);
              };

              $(document).on('click', '.modal-close', function () {
                 $("#modal-success").closeModal();
              });

              // Tira o fundo vermelho dos campos preenchidos após blur
              $(document).on('blur', '.validate', function () {
                 var val = $(this).val();
                 if (val !== undefined && val !== "") {
                    $(this).removeClass("red lighten-5");
                 } else {
                    $(this).addClass('red lighten-5');
                 }
              });


              // Excluir atividade
              $(document).on('click', '.btn-excluir', function (e) {
                 var id = $(this).attr('id').toString().substring(12);
                 $scope.avaliadorIdToDelete = id;
                 $(".alert-modal-excluir").openModal(configModal);
              });

              /**
               * Verifica validade do cpf
               * 
               * @param {type} cpf
               * @returns {undefined}
               */
              $scope.validaCPF = function (cpf) {
                 //Caso o campo esteja em branco a mensagem de erro é exibida
                 var ok = false;
                 if (cpf !== undefined) {

                    var patternCPF = /^[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}$/;
                    var patternCPF2 = /^[0-9]{11}$/;
                    if (patternCPF.test(cpf)) {

                       // De 000.000.000-00 para 00000000000
                       var cpfReplaced = cpf.substr(0, 3);
                       cpfReplaced += cpf.substr(4, 3);
                       cpfReplaced += cpf.substr(8, 3);
                       cpfReplaced += cpf.substr(12, 2);
                       // Valida CPF
                       ok = AvaliadorService.validaCPF(cpfReplaced);

                    } else if (patternCPF2.test(cpf)) {
                       ok = AvaliadorService.validaCPF(cpf);

                    } else {
                       Materialize.toast('O número digitado não está em um formato válido para CPF.', 5000, 'orange rounded');
                       $("#cpf").addClass('red lighten-5');
                    }
                    if (!ok) {
                       Materialize.toast('CPF inválido.', 5000, 'orange rounded');
                       $("#cpf").addClass('red lighten-5');
                       $scope.validCPF = false;
                    } else {
                       if (_checkCPF(cpf)) {
                          $scope.validCPF = true;
                       } else {
                          $scope.validCPF = false;
                       }
                    }
                 }

              };

              /* Valida campo Telefone */
              $(document).on('blur', 'input#telefone', function () {
                 var telefone = $(this).val();
                 var telefonePattern = /^\(?[0-9]{2}\)?\s?[0-9]{4,5}-?[0-9]{4}$/;

                 if (!telefonePattern.test(telefone)) {
                    Materialize.toast('Este número não está em um formato válido para Telefone', 4000, 'orange rounded');
                    $(this).removeClass('white');
                    $(this).addClass('red lighten-5');
                    return false;
                 }
                 return true;
              });

              $(document).on('blur', 'input#email', function () {
                 var email = $(this).val();
                 if (email !== undefined && email !== "") {
                    var emailPattern = /^[a-z][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2}/;
                    if (!emailPattern.test(email)) {
                       Materialize.toast('Este email não está em um formato válido!', 4000, 'orange rounded');
                       $(this).removeClass('white');
                       $(this).addClass('red lighten-5');
                       $scope.validEmail = false;
                       return false;
                    } else {
                       $scope.validEmail = true;
                    }
                 } else {
                    Materialize.toast(app._MESSAGE_ERRO_CAMPO_EMAIL_VAZIO, 4000, 'orange rounded');
                 }
                 return true;
              });

              /**
               * Checer se email já está cadastrado
               * 
               * @param {type} email
               * @returns {undefined|Boolean}
               */
              $scope.checkEmail = function (email) {
                 return _checkEmailAvaliador(email);
              };

              /**
               * Método par checar se o email já foi cadastrado ou não
               * 
               * @param {type} email
               * @returns {undefined}
               */
              var _checkEmailAvaliador = function (email) {
                 if (email !== avaliadorCopy.email) {
                    try {
                       if (email !== "" && email !== undefined && email && $scope.validEmail) {
                          AvaliadorService.checkEmail(email)
                                  .success(function (data) {
                                     if (data.result === 'exist') {
                                        Materialize.toast(app._MESSAGE_EMAIL_CADASTRADO, 4000, 'orange rounded toast');
                                        $("#email").css({"background-color": "rgba(255, 192, 206, 0.4)"});
                                        $scope.emailOk = false;
                                     }
                                     else if (data.result === 'not') {
                                        Materialize.toast(app._MESSAGE_EMAIL_DISPONIVEL, 4000, 'green rounded toast');
                                        $("#email").css({"background-color": "#FFF"});
                                        $scope.emailOk = true;
                                     }
                                     console.log(data.result);
                                  })
                                  .error(function (data) {
                                     Materialize.toast(app._MESSAGE_ERRO_PROCESSAR_EMAIL, 4000, 'red rounded toast');
                                     $scope.emailOk = false;
                                     console.log(data);
                                  });
                       } else {
                          $("#email").css({"background-color": "rgba(255, 192, 206, 0.4)"});
                          $scope.emailOk = false;
                       }
                    } catch (e) {
                       Materialize.toast(app._MESSAGE_BAD_CONNECTION, 4000, 'rounded red toast');
                       $scope.emailOk = false;
                    }

                 } else {
                    console.log(email + ", " + avaliadorCopy.email);
                    $scope.emailOk = true;
                 }
                 return $scope.emailOk;
              };




           });

})();
