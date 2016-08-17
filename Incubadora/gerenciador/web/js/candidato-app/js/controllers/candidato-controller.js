
/* global angular */
/* global Materialize */
/* global $ */

(function () {
   angular.module('candidatoApp')
           .controller('CandidatoCtrl', function ($scope, CandidatoService) {
              /**
               * Mensagens 
               */
              this._MESSAGE_EMAIL_CADASTRADO = "Este E-mail já está cadastrado! =(";
              this._MESSAGE_EMAIL_DISPONIVEL = "Este E-mail está disponível para cadastro. =)";
              this._MESSAGE_ERRO_PROCESSAR_EMAIL = "Erro ao tentar processar E-mail!";
              this._MESSAGE_BAD_CONNECTION = "Erro ao tentar conectar como servidor para checar E-mail!";
              this._MESSAGE_ERRO_CAMPO_EMAIL_VAZIO = "Por favor, preencha o campo E-mail!";
              this._MESSAGE_CADASTRO_ERROR = "Erro ao tentar cadastrar empreendedor! =(";
              this._MESSAGE_CADASTRO_SUCCESS = "Candidato cadastrado com sucesso!";
              this._MESSAGE_FIELD_REQUIRED = "Este campo é obrigatório";

              var app = this;

              /**
               * Variáveis de $scope
               */
              $scope.emailOk = true;
              $scope.validCPF = true;
              $scope.candidato = {};

              /**
               * Arrays do $scope
               */

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

              // Escolaridade listadas no select Escolaridade
              $scope.escolaridades = [
                 {'id': 1, 'nivel': 'Ensino médio'},
                 {'id': 2, 'nivel': 'Ensino Técnico'},
                 {'id': 3, 'nivel': 'Ensino Superior Incompleto'},
                 {'id': 4, 'nivel': 'Ensino Superior Completo'},
                 {'id': 5, 'nivel': 'Pós-gradução'}
              ];



              /**
               * Método par checar se o email já foi cadastrado ou não
               * 
               * @param {type} email
               * @returns {undefined}
               */
              var _checkEmailCandidato = function (email) {

                 try {
                    if (email !== "" && email !== undefined) {
                       CandidatoService.checkEmail(email)
                               .success(function (data) {
                                  if (data.result === 'exist') {
                                     Materialize.toast(app._MESSAGE_EMAIL_CADASTRADO, 4000, 'orange rounded toast');
                                     $("form #email").css({"background-color": "rgba(255, 192, 206, 0.4)"});
                                     $scope.emailOk = false;
                                  }
                                  else if (data.result === 'not') {
                                     Materialize.toast(app._MESSAGE_EMAIL_DISPONIVEL, 4000, 'green rounded toast');
                                     $("form #email").css({"background-color": "#FFF"});
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
                       $("form #email").css({"background-color": "rgba(255, 192, 206, 0.4)"});
                       $scope.emailOk = false;
                    }
                 } catch (e) {
                    Materialize.toast(app._MESSAGE_BAD_CONNECTION, 4000, 'rounded red toast');
                    $scope.emailOk = false;
                 }

                 return $scope.emailOk;
              };

              /* validar data */
              $(".datepicker").change(function () {
                 var val = $(this).val();

                 if (val === "" || val === undefined) {
                     var ico = "<a href='#!' class='close-toast white-text'><i class='material-icons right valign' style='font-size:18px'>clear</i></a>";
                    Materialize.toast("<span class='valign-wrapper'>" + app._MESSAGE_FIELD_REQUIRED + ": <strong class='valign'>&nbsp;&nbsp; Data de Nascimento</strong>" + ico + "</span>",360000, 'orange rounded');
                    $(this).css({"background-color": "rgba(255, 192, 206, 0.4)"});
                 } else {
                    $(this).css({"background-color": "#FFF"});
                 }
              });

              /**
               * Checer se email já está cadastrado
               * 
               * @param {type} email
               * @returns {undefined|Boolean}
               */
              $scope.checkEmail = function (email) {
                 return _checkEmailCandidato(email);
              };


              /**
               * Verifica validade do cpf
               * 
               * @param {type} cpf
               * @returns {undefined}
               */
              $scope.validaCPF = function (cpf) {
                 //Caso o campo esteja em branco a mensagem de erro é exibida
                 if (cpf !== undefined) {

                    // De 000.000.000-00 para 00000000000
                    var cpfReplaced = cpf.substr(0, 3);
                    cpfReplaced += cpf.substr(4, 3);
                    cpfReplaced += cpf.substr(8, 3);
                    cpfReplaced += cpf.substr(12, 2);

                    // Valida CPF
                    var ok = CandidatoService.validaCPF(cpfReplaced);
                    if (!ok) {
                       $scope.validCPF = false;
                    } else {
                       $scope.validCPF = true;
                    }
                 }

              };


              // Exibe mensagem informando que o campo deixado em branco é obrigatório
              $("#candidatoForm .validate").blur(function () {
                 var val = $(this).val();
                 var nome = $(this).parent('.input-field').find('label').text();
                 if(nome === undefined || nome === ""){
                    nome = $(this).find('[selected]').text();
                 }
                 
                 if (val === "" || val === undefined) {
                    var ico = "<a href='#!' class='close-toast white-text'><i class='material-icons right valign' style='font-size:18px'>clear</i></a>";
                    Materialize.toast("<span class='valign-wrapper'>" + app._MESSAGE_FIELD_REQUIRED + ": <strong class='valign'>&nbsp;&nbsp;" + nome + "</strong>" + ico + "</span>",360000, 'orange rounded');
                    $(this).css({"background-color": "rgba(255, 192, 206, 0.4)"});
                 } else {
                    $(this).css({"background-color": "#FFF"});
                 }
                 
                 var telefonePattern = /^\(?[0-9]{2}\)?\s*9?[0-9]{4}-?[0-9]{4}$/;
                 var id = $(this).attr("id");
                 if(id === "telefone" && val !== ""){
                   var telefone = $(this).val();
                   if(!telefonePattern.test(telefone)){                     
                      Materialize.toast('O número do <strong>&nbsp;Telefone&nbsp;</strong> não é inválido!', 4000, 'orange rounded');
                      $(this).css({"background-color": "rgba(255, 192, 206, 0.4)"});
                   }
                 }
              });                           
              
              // Remove toat ao clicar no X
              $(document).on('click', '.close-toast', function(){
                 $(this).parents('.toast').remove();
              });

              // Cadastra um novo candidato
              $scope.create = function (candidato) {
                 try {
                    CandidatoService.createCandidato(candidato)
                            .success(function () {
                               Materialize.toast(app._MESSAGE_CADASTRO_SUCCESS, 3000, 'green rounded', function () {
                                  window.location.href = window.location.protocol + "//" + window.location.host + "/gerenciador";
                               });
                               delete $scope.candidato;
                            })
                            .error(function (data) {
                               console.log(data);
                               Materialize.toast(app._MESSAGE_CADASTRO_ERROR, 5000, 'red rounded');
                            });

                 } catch (e) {
                    console.log(e);
                 }

              };


           });
})();

