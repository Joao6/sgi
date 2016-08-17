/**
 * @author William Daniel de Oliveira
 * e-mail: william.d.o@hotmail.com
 * @version 1.0
 * @description Controller responsável por todas as ações referentes à
 * parte de login do sistema.
 * 
 * 29/12/2015 06:07
 */


/* global angular */
/* global Materialize */
(function () { // Module pattern

   angular.module('loginApp')
           .controller('LoginCtrl', function ($scope, LoginService) {

              this._MESSAGE_SERVER_CONNECT_ERROR = "Erro ao tentar comunicar com o servidor";
              this._MESSAGE_PASSWAORD_ERROR = "Senha inválida.";
              this._MESSAGE_LOGIN_ADMINISTRADOR_ERROR = "Este usuário não é de um Gestor!";
              this._MESSAGE_LOGIN_EMPREENDEDOR_ERROR = "Este usuário não é um Empreendedor!";
              this._MESSAGE_LOGIN_AVALIADOR_ERROR = "Este usuário não é um Avaliador!";
              this._MESSAGE_USER_NOT_EXIST_ERROR = "Não existe nenhum usuário cadastrado com esse e-mail.";
              this._MESSAGE_EMAIL_FIELD_EMPTY = "Digite seu email!";

              /**
               * Constantes
               */

              this._TIPO_USUARIO_ADMINISTRADOR = '1';
              this._TIPO_USUARIO_EMPREENDEDOR = '2';
              this._TIPO_USUARIO_AVALIADOR = '3';

              $scope.usuario = null;
              var _app = this;

              /**
               * Variáveis do $scope
               */

              $scope.showProgress = false;
              $scope.isBtnEntrarDisable = true;

              /**
               * Função para limpar formulário        
               *
               * @returns {undefined}
               */
              $scope.clearForm = function () {
                 delete $scope.usuario;
              };

              $scope.btnEnabled = false;

              /**
               * Checa se usuário existe e se é um administrador
               * 
               * @param {type} usuario
               * @param {type} tipo Int
               * @returns {undefined}
               */
              $scope.check = function (usuario, tipo) {

                 if (usuario !== "" && usuario !== undefined) {
                    try {
                       $scope.showProgress = true;
                       LoginService.validateUser(usuario).success(function (data) {
                          
                          var digest_md5 = md5(usuario.email); 
                          if(digest_md5 === data.email){
                             data.email = usuario.email;
                          }
                          
                          var senha = md5(usuario.senha); 
                          if(senha === data.senha){
                             data.senha = senha;
                          }
                          
                          var tel = md5(usuario.telefone); 
                          if(tel === data.telefone){
                             data.telefone = tel;
                          }
                          
                          var nome = md5(usuario.nome); 
                          if(nome === data.nome){
                             data.nome = usuario.nome;
                          }
                          
                          var sobrenome = md5(usuario.sobreNome); 
                          if(sobrenome === data.sobrenome){
                             data.sobrenome = sobrenome;
                          }
                          
                          $scope.usuario = data;
                          $scope.usuario.senha = "";
                          if (data.id !== null) {
                             $scope.showProgress = false;
                             action(tipo);
                          }

                       }).error(function () {
                          $scope.showProgress = false;
                          $scope.isBtnEntrarDisable = true;
                          Materialize.toast(_app._MESSAGE_USER_NOT_EXIST_ERROR, 4000, 'orange rounded');
                       });
                    } catch (e) {
                       Materialize.toast(_app._MESSAGE_SERVER_CONNECT_ERROR, 4000, 'red rounded');
                       console.log(e);
                    }
                 } else {
                    Materialize.toast(_app._MESSAGE_EMAIL_FIELD_EMPTY, 4000, 'orange rounded');
                 }

              };

              function action(tipo) {
                 switch (tipo) {
                    case '1':
                       if ($scope.usuario.tipoUsuario === _app._TIPO_USUARIO_ADMINISTRADOR) {
                          $scope.isBtnEntrarDisable = false;
                       } else {
                          Materialize.toast(_app._MESSAGE_LOGIN_ADMINISTRADOR_ERROR, 4000, 'orange rounded');
                       }
                       break;

                    case '2':
                       if ($scope.usuario.tipoUsuario === _app._TIPO_USUARIO_EMPREENDEDOR) {
                          $scope.isBtnEntrarDisable = false;
                       } else {
                          Materialize.toast(_app._MESSAGE_LOGIN_EMPREENDEDOR_ERROR, 4000, 'orange rounded');
                       }
                       break;

                    case '3':
                       if ($scope.usuario.tipoUsuario === _app._TIPO_USUARIO_AVALIADOR) {
                          $scope.isBtnEntrarDisable = false;
                       } else {
                          Materialize.toast(_app._MESSAGE_LOGIN_AVALIADOR_ERROR, 4000, 'orange rounded');
                       }
                 }
              }


              /*****
               *  Default
               * @param {type} usuario
               * @returns {undefined}
               */

              $scope.login = function (usuario) {
                 try {
                    $scope.showProgress = true;
                    $scope.isBtnEntrarDisable = false;

                    LoginService.loginDefault(usuario)
                            .success(function (data) {
                               $scope.usuario = data;

                               $scope.showProgress = false;
                               $scope.isBtnEntrarDisable = false;

                               if ($scope.usuario.tipoUsuario === _app._TIPO_USUARIO_ADMINISTRADOR) {
                                  delete $scope.usuario;
                                  window.location.href = window.location.protocol
                                          + "//" + window.location.host + "/gerenciador/incubadora/home";

                               } else if ($scope.usuario.tipoUsuario === _app._TIPO_USUARIO_AVALIADOR) {
                                  delete $scope.usuario;
                                  window.location.href = window.location.protocol
                                          + "//" + window.location.host + "/gerenciador/avaliador/home";

                               } else if ($scope.usuario.tipoUsuario === _app._TIPO_USUARIO_EMPREENDEDOR) {
                                  delete $scope.usuario;
                                  window.location.href = window.location.protocol
                                          + "//" + window.location.host + "/gerenciador/empreendedor/home";
                               }

                            })
                            .error(function () {
                               $scope.showProgress = false;
                               $scope.isBtnEntrarDisable = false;
                               Materialize.toast(_app._MESSAGE_PASSWAORD_ERROR, 4000, 'rounded red');
                            });
                 } catch (e) {
                    console.log(e);
                    Materialize.toast(_app._MESSAGE_SERVER_CONNECT_ERROR, 1000, 'red rounded');
                 }

              };


           }); //End controller

})(); // End module pattern