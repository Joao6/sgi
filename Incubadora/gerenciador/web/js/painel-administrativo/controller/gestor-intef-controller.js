/* global angular */
/* Materialize */
(function () {
   angular.module('painelAdmin').controller("GestaoIntefCtrl", function ($scope, GestorService) {

      /**
       * Mensagens 
       */
      this._MESSAGE_EMAIL_CADASTRADO = "Este E-mail já está cadastrado! =(";
      this._MESSAGE_EMAIL_DISPONIVEL = "E-mail disponível para cadastro. =)";
      this._MESSAGE_ERRO_PROCESSAR_EMAIL = "Erro ao tentar processar E-mail!";
      this._MESSAGE_BAD_CONNECTION = "Erro ao tentar conectar com o servidor para chegar E-mail!";
      this._MESSAGE_ERRO_CAMPO_EMAIL_VAZIO = "Por favor, preencha o campo E-mail!";
      this._MESSAGE_CADASTRO_ERROR = "Erro ao tentar cadastrar empreendedor! =(";
      this._MESSAGE_CADASTRO_SUCCESS = "Candidato cadastrado com sucesso!";
      this._MESSAGE_FIELD_REQUIRED = "Este campo é obrigatório";

      var _MODE_EDIT = 1;
      var _MODE_CREATE = 2;
      var app = this;

      $scope._gestorList = [];
      $scope.gestorIdToDelete = 0;
      $scope.gestor = {};
      $scope.testValues = [];
      $scope.rowsPerPage = 5; // setar para paginar   
      $scope.emailOk = true;
      $scope.validCPF = true;
      $scope.validTelefone = true;
      $scope.validEmail = true;
      $scope.MODE = 0;

      var gestorCopy = {};
      var cpfCopy = "";

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


      /* Obter gestores cadastrador  */
      var getGestor = function () {
         GestorService.getGestores().success(function (data) {
            $scope._gestorList = data;
            for (var p in $scope._gestorList) {
               $scope.testValues.push(p);
            }
         }).error(function () {

         });
      };
      getGestor();
      /* Cheva que o Array de Gestores está limpo */
      $scope.isGestorEmpty = function () {
         if ($scope._gestorList.length < 1)
            return true;
         else
            return false;
      };

      /* Pega id do gestor  */
      $(document).ready(function () {
         var gestorId = $("#gestor-id").val();
         if (gestorId !== null && gestorId !== "" && gestorId !== undefined) {
            $scope.MODE = _MODE_EDIT;
            try {
               GestorService.getGestorById(gestorId).success(function (data) {
                  $scope.gestor = data;
                  gestorCopy = angular.copy($scope.gestor);
                  cpfCopy = angular.copy($scope.gestor.cpf);
               }).error(function () {

               });
            } catch (e) {
               console.log(e);
            }

         } else {
            $scope.MODE = _MODE_CREATE;
         }
      });


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
               GestorService.checkCPF(cpf).success(function () {
                  $scope.validCPF = true;
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

      /* Visualizar detalhes */
      $scope.openModalDetalhes = function (gestor) {
         $scope.gestor = gestor;
         $("#modal-detalhes").openModal(configModal);
      };

      /* Insere gestor no banco de dados */
      $scope.addGestor = function (gestor) {
         $scope.ok = false;
         var _send = true;
         $("#form-gestor").find("input.validate").each(function () {
            if ($(this).val() === "" || $(this).val() === null || $(this).val() === "0") {
               $(this).addClass('red lighten-5');
               _send = false;
            } else {
               $(this).removeClass('red lighten-5');
            }
         });

         if (_send) {
            if ($scope.MODE === _MODE_CREATE) {
               // Create    
               if (_checkCPF(gestor.cpf)) {
                  create(gestor);
               } else {
                  $scope.validCPF = false;
               }
            }
         }
         if (_send) {
            if ($scope.MODE === _MODE_EDIT) {
               // Edit
               editarGestor(gestor);
            }
         }
      };

      // Cadastra Gestor
      function create(gestor) {
         try {
            var patternCPF = /^[0-9]{11}$/;
            var cpf = gestor.cpf;
            if (patternCPF.test(gestor.cpf)) {

               // De 00000000000 para 000.000.000-00
               var cpfReplaced = cpf.substr(0, 3) + ".";
               cpfReplaced += cpf.substr(3, 3) + ".";
               cpfReplaced += cpf.substr(6, 3) + "-";
               cpfReplaced += cpf.substr(9);
               gestor.cpf = cpfReplaced;
            }

            $scope.ok = true;
            $('#modal-progress').openModal(configModal);
            GestorService.addGestor(gestor).success(function () {               
               $scope.ok = false;
               delete $scope.gestor;
               $('#modal-progress').closeModal();
               $("#modal-success").openModal(configModal);
            }).error(function () {
               $scope.ok = false;
               $("#modal-error").openModal(configModal);
               $('#modal-progress').closeModal();
               console.log("erro ao cadastrar gestor");
            });
         } catch (e) {
            $('#modal-progress').closeModal();
            console.log(e);
         }

      }

      // Atualiza Gestor
      function editarGestor(gestor) {
         try {

            var patternCPF = /^[0-9]{11}$/;
            var cpf = gestor.cpf;
            if (patternCPF.test(gestor.cpf)) {

               // De 00000000000 para 000.000.000-00
               var cpfReplaced = cpf.substr(0, 3) + ".";
               cpfReplaced += cpf.substr(3, 3) + ".";
               cpfReplaced += cpf.substr(6, 3) + "-";
               cpfReplaced += cpf.substr(9);
               gestor.cpf = cpfReplaced;
            }

            $scope.ok = true;
            GestorService.editarGestor(gestor).success(function () {
               getGestor();
               $scope.ok = false;
               $("#modal-success-update").openModal(configModal);
            }).error(function () {
               $scope.ok = false;
               $('#modal-progress').closeModal();
               $("#modal-error").openModal(configModal);
               console.log("erro ao tentar editar gestor");
            });
         } catch (e) {
            $scope.ok = false;
            $('#modal-progress').closeModal();
            Materialize.toast('Erro ao tentar comunicar com o servidor', 4000, 'red rounded');
            console.log(e);
         }
      }


      // Excluir gestor
      $(document).on('click', '.btn-excluir', function (e) {
         var id = $(this).attr('id').toString().substring(12);
         $scope.gestorIdToDelete = id;
         $(".alert-modal-excluir").openModal(configModal);
      });

      /* Exclui Gestor do banco de dados */
      $scope.removerGestor = function (id) {
         if (id !== undefined && id !== null) {
            try {
               GestorService.removeGestor(id)
                       .success(function () {
                          getGestor();
                          $(".alert-modal-excluir").closeModal();
                          Materialize.toast('Gestor excluído com sucesso! =)', 4000, 'green rounded');
                       })
                       .error(function () {
                          Materialize.toast('Erro ao tentar excluir Gestor. =(', 4000, 'orange rounded');
                          console.log('erro ao excluir gestor');
                       });
            } catch (e) {
               console.log(e);
               Materialize.toast('Erro ao tentar comunicar com o servidor. =(', 4000, 'rounded red');
            }

         } else {
            console.log('id inválido de gestor: ' + id);
         }

      };

      // Tira o fundo vermelho dos campos preenchidos após blur
      $(document).on('blur', '.validate', function () {
         var val = $(this).val();
         if (val !== undefined && val !== "") {
            $(this).removeClass("red lighten-5");
         } else {
            $(this).addClass('red lighten-5');
         }
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
               ok = GestorService.validaCPF(cpfReplaced);

            } else if (patternCPF2.test(cpf)) {
               ok = GestorService.validaCPF(cpf);

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
         return _checkEmailGestor(email);
      };

      /**
       * Método par checar se o email já foi cadastrado ou não
       * 
       * @param {type} email
       * @returns {undefined}
       */
      var _checkEmailGestor = function (email) {
         if (email !== gestorCopy.email) {
            try {
               if (email !== "" && email !== undefined && email && $scope.validEmail) {
                  GestorService.checkEmail(email)
                          .success(function (data) {
                             if (data.result === 'exist') {
                                Materialize.toast(app._MESSAGE_EMAIL_CADASTRADO, 4000, 'orange rounded toast');
                                $("form-gestor #email").css({"background-color": "rgba(255, 192, 206, 0.4)"});
                                $scope.emailOk = false;
                             }
                             else if (data.result === 'not') {
                                Materialize.toast(app._MESSAGE_EMAIL_DISPONIVEL, 4000, 'green rounded toast');
                                $("form-gestor #email").css({"background-color": "#FFF"});
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
                  $("form-gestor #email").css({"background-color": "rgba(255, 192, 206, 0.4)"});
                  $scope.emailOk = false;
               }
            } catch (e) {
               Materialize.toast(app._MESSAGE_BAD_CONNECTION, 4000, 'rounded red toast');
               $scope.emailOk = false;
            }

         } else {
            console.log(email + ", " + gestorCopy.email);
            $scope.emailOk = true;
         }
         return $scope.emailOk;
      };



   }); // end Controller
})(); //end Module Pattern
