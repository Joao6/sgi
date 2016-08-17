/* global angular  */
/* global Materialize */
/* global $ */
angular.module('painelAdmin')
        .controller('DuvidaCtrl', function ($scope, DuvidaService) {

           /**
            * MENSAGENS                                    
            *                                              
            * @type String                                 
            **/
           var _STATUS_DUVIDA_VIEW = "visualizada";
           var _STATUS_DUVIDA_NOT_VIEW = "nao visualizada";
           var _MESSAGE_GET_QUESTIONS_ERROR = "Erro ao tentar obter duvidas";
           var _MESSAGE_CONNECT_SERVER_ERROR = "Erro ao tentar conectar ao servidor. =(";
           var _MESSAGE_TRY_ANSWER_ERROR = "Erro ao tentar responder dúvida. =(";
           var _MESSAGE_TRY_DELETE_ANSWER_ERROR = "Erro ao tentar excluir resposta. =(";
           var _MESSAGE_TRY_DELETE_QUESTION_ERROR = "Erro ao tentar excluir Dúvida. =(";
           var _MESSAGE_TRY_UPDATE_ANSWER_SUCCESS = "Resposta atualizada com sucesso! =)";
           var _MESSAGE_TRY_UPDATE_ANSWER_ERROR = "Erro ao tentar atualizar resposta. =(";
           var _MESSAGE_FIELD_EMPTY_ERROR = "Digite alguma coisa!";

           /**
            * CONSTANTES
            * @type Number
            **/
           var _KEY_ENTER = 13;

           /**
            * VARIÁVEIS DE $scope
            **/
           $scope.pessoas = [];
           $scope.duvidas = [];
           $scope.clicked = false;
           $scope.idByEmpreendedor = 0;

           /**
            * URLs DAS IMAGENS DOS AVATARES DO CASO DE USO Dúvidas)
            **/

           // URL da imagem do Avatar do Empreendedor, na seção de dúvidas
           $scope.url_img_empreendedor_src = window.location.protocol + "//" +
                   window.location.host + '/gerenciador/img/avatar.jpg';

           // URL da imagem do Avatar da Intef
           $scope.url_img_usuario_src = window.location.protocol + "//" +
                   window.location.host + '/gerenciador/img/avatar-intef-gestao.png';

           // URL da imagem default quando ainda não há Dúvida selecionada
           $scope.url_img_chat_ico_src = window.location.protocol + "//" +
                   window.location.host + '/gerenciador/img/chat-ico.png';
           
           // URL da imagem default quando ainda não há Dúvidas enviadas
           $scope.url_img_intef_src = window.location.protocol + "//" +
                   window.location.host + '/gerenciador/img/intef.png';

           /**
            * MÉTODOS PRIVADOS
            **/

           /**
            * Recupera todas as dúvidas enviadas pelos empreendedores 
            * 
            * @returns {undefined}
            */
           function _getDuvidas() {
              try {
                 DuvidaService.getDuvidas()
                         // Success
                         .success(function (data) {
                            $scope.duvidas = data;
                            _configDataHora();
                         })
                         // Error
                         .error(function () {
                            Materialize.toast(_MESSAGE_GET_QUESTIONS_ERROR, 4000);
                         });

              } catch (e) {
                 console.log(e);
              }
           }

           /**
            * Remove espaço em branco usando expressão regular 
            * É utilizado pelos para validação dos campos de texto 
            * 
            * @param {type} str
            * @returns {String}
            */
           function trim(str) {
              return str.replace(/^\s+|\s+$/gm, '');
           }


           /**
            * Configura a hora que será exibida nos cards 
            * 
            * @returns {Boolean}
            */
           function _configDataHora() {
              $scope.duvidas = DuvidaService.formatDataHora($scope.duvidas);
              try {
                 for (var d in $scope.duvidas) {
                    $scope.duvidas[d].respostaDuvidaList = DuvidaService.formatDataHora($scope.duvidas[d].respostaDuvidaList);
                 }
                 return true;
              } catch (e) {
                 return false;
              }

           }

           // Chamada para obter as dúvidas no momento do carregamento do javascipt
           _getDuvidas();


           /**************************
            *    MÉTODOS DE $scope   *         
            **************************/


           /**
            * Checa se há algum objeto Dúvida no array 
            * É utilizado no view pelas diretivas do AngularJS 
            * 
            * @returns {Boolean}
            */
           $scope.isDuvidasEmpty = function () {
              if ($scope.duvidas.length === 0)
                 return true;
              else
                 return false;
           };

           /**
            * Testa id para saber liberar ações que são restritas apenas ao usuário logado
            * Ações: excluir e atualizar suas respostas, ver seu avatar com cor em destaque 
            * nas conversas
            * 
            * @param {type} id
            * @returns {Boolean}
            */
           $scope.isUsuarioId = function (id) {
              return $("#user-logado-id").val() === id.toString();
           };


           /**
            * Executado a partir no ng-class. Caso a duvida tenha sido visualizada 
            * ('click sobre o empreendedor na caixa de entrada de dúvidas'), são carregadas
            * as duvidas correspondetes a ele.
            * 
            * @param {type} duvida
            * @returns {Boolean}
            */
           $scope.isDuvidaVisualizada = function (duvida) {
              if (duvida.status === _STATUS_DUVIDA_NOT_VIEW) {
                 return true;
              }
              else {
                 return false;
              }

           };


           /**
            * Responsável por fazer a contagem da quantidade de duvidas que 
            * ainda não foram respondidas
            * 
            * @param {type} duvida
            * @returns {Number}
            */
           $scope.countDuvidasToAnswer = function (duvida) {

              var idEmpr = duvida.empreendedor.id;
              var count = 0;

              for (var i = 0; i < $scope.duvidas.length; ++i) {
                 if ($scope.duvidas[i].empreendedor.id === idEmpr) {
                    if ($scope.duvidas[i].respostaDuvidaList.length < 1) {
                       count += 1;
                    }
                 }
              }

              return count;
           };


           /**
            * Calcula o total de dúvidas respondidas
            * 
            * @returns {Number}
            */
           $scope.totalDuvidasRespondidas = function () {
              var total = 0;
              if ($scope.duvidas.length > 0) {
                 for (var d in $scope.duvidas) {
                    if ($scope.duvidas[d].respostaDuvidaList !== undefined &&
                            $scope.duvidas[d].respostaDuvidaList.length > 0) {
                       total += 1;
                    }
                 }
              }
              return total;
           };


           /**
            * Ação de visualizar as duvidas relacionadas ao empreendedor selecionado
            * 
            * @param {type} duvida
            * @returns {undefined}
            */
           $scope.visualizar = function (duvida) {
              if (duvida.status !== _STATUS_DUVIDA_VIEW) {
                 duvida.status = _STATUS_DUVIDA_VIEW;
              }
              $scope.idByEmpreendedor = duvida.empreendedor.id;
              $scope.clicked = true; // Faz desaparecer o card default 
           };

           /**
            * Checa se a Dúvida possui alguma resposta
            * 
            * @param {type} duvida
            * @returns {Boolean}
            */
           $scope.isEmptyResp = function (duvida) {

              if (duvida.respostaDuvidaList.length < 1) {
                 return true;
              } else {
                 return false;
              }

           };

           /**
            * Excluir a resposta de uma determinada Dúvida
            * 
            * @param {type} idResposta
            * @returns {undefined}
            */
           $scope.removerResposta = function (idResposta) {
              try {
                 DuvidaService.removerResposta(idResposta)
                         .success(function () {
                            _getDuvidas();
                         })
                         .error(function () {
                            Materialize.toast(_MESSAGE_TRY_DELETE_ANSWER_ERROR, 4000, 'red rounded');
                         });
              } catch (ex) {
                 Materialize.toast(_MESSAGE_CONNECT_SERVER_ERROR, 4000, 'orange rounded');
                 console.log(ex);
              }

           };

           /**
            * Exclui uma determinada Dúvida
            * 
            * @param {type} id
            * @returns {undefined}
            */
           $scope.removerDuvida = function (id) {

              if (id !== "" && id !== undefined) {
                 try {
                    DuvidaService.removerDuvida(id)

                            .success(function () {
                               _getDuvidas();
                               $("#alert-modal").closeModal();
                            })

                            .error(function () {
                               $("#alert-modal").closeModal();
                               Materialize.toast(_MESSAGE_TRY_DELETE_QUESTION_ERROR, 4000, 'red rounded');
                            });

                 } catch (e) {
                    Materialize.toast(_MESSAGE_CONNECT_SERVER_ERROR, 4000, 'orange rounded');
                 }

              }

           };

           /**
            * Sai do modo de edição da resposta de uma determinada dúvida
            * 
            * @param {type} resposta
            * @returns {undefined}
            */
           $scope.exitEditMode = function (resposta) {
              resposta.show = false;
           };

           /**
            * Ativa e desativa o textarea de edição da resposta
            * 
            * @param {type} resposta
            * @returns {undefined}
            */
           $scope.showResposta = function (resposta) {
              if (resposta.show === undefined) {
                 resposta.show = true;
              } else if (resposta.show === true) {
                 resposta.show = false;
              } else {
                 resposta.show = true;
              }
           };


           /**
            * Atualiza uma determinada resposta
            * 
            * @param {type} resposta
            * @returns {undefined}
            */
           $scope.updateResposta = function (resposta) {

              try {
                 if (trim(resposta.resposta) !== "" && resposta.resposta !== undefined) {
                    DuvidaService.updateResposta(resposta).success(function () {
                       _getDuvidas();
                       Materialize.toast(_MESSAGE_TRY_UPDATE_ANSWER_SUCCESS, 2000, 'green rounded');
                    }).error(function () {
                       Materialize.toast(_MESSAGE_TRY_UPDATE_ANSWER_ERROR, 4000, 'orange rounded');
                    });

                 } else {
                    Materialize.toast(_MESSAGE_FIELD_EMPTY_ERROR, 4000, 'orange roundewd');
                 }
              } catch (e) {
                 Materialize.toast(_MESSAGE_CONNECT_SERVER_ERROR, 4000, 'red rounded');
              }
              resposta.show = false;

           };

           /*****************************
            *       EVENTOS JQuery      *
            *****************************/


           /**             
            * ACIONA MODAL DE ALERTA PARA EXCLUSÃO QUANDO CLICADO SOBRE O BOTÃO EXCLUIR
            * @param {e} event object
            * @param {document} object
            * @param {click} String
            * @param {.btn-remove-duvida} String
            * 
            * @return {type} undefined
            */
           $(document).on('click', '.btn-remove-duvida', function (e) {

              $("#alert-modal").openModal({
                 dismissible: false, // Modal can be dismissed by clicking outside of the modal
                 opacity: .5, // Opacity of modal background
                 in_duration: 1, // Transition in duration
                 out_duration: 1, // Transition out duration
                 ready: function () {
                    return;
                 }, // Callback for Modal open
                 complete: function () {

                 } // Callback for Modal close
              });
           });


           /**
            * EVENTO RESPONSAVEL POR ENVIAR A RESPOSTA
            */
           $(document).on('keypress', 'textarea.send-resposta', function (e) {
              var textoResposta = $(this).val();
              var _tecla = e.keyCode;


              if (_tecla === _KEY_ENTER) {

                 if (trim(textoResposta) !== "" && textoResposta !== undefined) {

                    var idDuvida = $(this).attr('id').toString().split('-')[2];
                    var idUsuarioLogado = $("#user-logado-id").val();

                    var respostaDuvida = {
                       resposta: textoResposta,
                       usuario: {
                          id: idUsuarioLogado
                       },
                       duvida: {
                          id: idDuvida
                       }
                    };


                    try {
                       DuvidaService.responderDuvida(respostaDuvida)

                               .success(function () {
                                  _getDuvidas();
                                  $("#resp-duvida-" + idDuvida).val("");
                               })
                               .error(function () {
                                  Materialize.toast(_MESSAGE_TRY_ANSWER_ERROR, 4000, 'red rounded');
                               });

                    } catch (e) {
                       Materialize.toast(_MESSAGE_CONNECT_SERVER_ERROR, 4000, 'orange rounded');
                    }

                    e.preventDefault();
                 } else {
                    e.preventDefault();
                    Materialize.toast(_MESSAGE_FIELD_EMPTY_ERROR, 4000, 'orange rounded');
                 }
              }

           });

        });
    