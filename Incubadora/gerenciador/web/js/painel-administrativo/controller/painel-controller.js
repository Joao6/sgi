/* global angular */
/* Materialize */
angular.module('painelAdmin')

        .controller('PainelCtrl', function ($scope, Service, PraticaChaveService, GestorService) {
           var REALIZADO = 'Realizado';

           $scope.atividades = [];
           $scope.atividade = {praticaList: [], gestorList: []};
           $scope.praticaList = [];
           $scope.testValues = [];
           $scope.rowsPerPage = 5; // setar para paginar
           $scope.praticas = [];
           $scope.gestorList = [];
           $scope.gestores = [];
           $scope.ok = false;
           $scope.atividadeIdToDelete = 0;
           $scope.menuItens = [];
           $scope.messageSuccess = false;
           $scope.messageError = false;

           var atividadeCopy = {};

           /* Config Modal */
           var configModal = {
              dismissible: false, // Modal can be dismissed by clicking outside of the modal
              opacity: .5, // Opacity of modal background
              in_duration: 1, // Transition in duration
              out_duration: 1, // Transition out duration
              ready: function () {
                 $scope.path;
                 return;
              }, // Callback for Modal open
              complete: function () {

              } // Ca
           };
           var app = this;
           /* */
           this._MODE_CREATE = false;
           this._MODE_EDIT = false;


           var atividadeId = $("#atividadeId").val();
           if (atividadeId !== null && atividadeId !== "" && atividadeId !== undefined) {
              app._MODE_EDIT = true;
              try {
                 Service.getAtividadeById(atividadeId).success(function (data) {
                    $scope.atividade = data;

                    /* Calculo inícial to total de dias para a realização da atividade */
                    var dInicio = Date.parse($scope.atividade.dataInicio);
                    var dFim = Date.parse($scope.atividade.dataFim);

                    var dataFim = new Date(dFim);
                    var dataInicio = new Date(dInicio);
                    $scope.atividade.totalDias = Math.abs(dataFim.getDay() - dataInicio.getDay() + 1);
                    
                    /* cópia do estado original da atividade */
                    atividadeCopy = angular.copy($scope.atividade);
                 }).error(function () {

                 });
              } catch (e) {
                 console.log(e);
              }

           } else {
              app._MODE_CREATE = true;
           }


           /* Obtém os nomes dos itens do menu lateral */
           $scope.menuItens = Service.getItens();

           /* Remove Atividade da lista de atividades */
           $scope.remover = function (id) {
              Service.removeAtividade(id).success(function () {
                 _getAtividades();
                 Materialize.toast('Atividade excluída com sucesso!', 4000, 'green rounded');
                 $(".alert-modal-excluir").closeModal();
              }).error(function () {
                 $("#modal-error").openModal(configModal);
              });

           };

           /* Checa se o a atividade já foi realizada, caso tenha sido retorna true*/
           $scope.isRealizada = function (atividade) {
              if (atividade.status === REALIZADO)
                 return true;
              else
                 return false;
           };

           /* Obtendo atividades do cronograma anual */
           var _getAtividades = function () {
              try {
                 Service.getAtividades().success(function (data) {
                    $scope.atividades = data;
                    $scope.testValues = $scope.atividades;
                 }).error(function () {
                    Materialize.toast('Erro ao receber as atividades do Cronograma Anual.', 4000, 'rounded orange');
                 });
              } catch (e) {
                 console.log(e);
                 Materialize.toast('Erro ao tentar comunicar com o servidor.', 4000, 'red rounded');
              }

           };

           _getAtividades();

           /**
            * Obtém Práticas-Chave
            * 
            * @returns {undefined}
            */

           function _getPraticasChave() {
              try {
                 PraticaChaveService.getPraticasChave().success(function (data) {
                    $scope.praticaList = data;
                    $scope.praticas = angular.copy($scope.praticaList);
                 }).error(function () {
                    Materialize.toast('Erro ao tentar receber práticas-chave', 4000, 'orange rounded');
                 });
              } catch (e) {
                 console.log(e);
                 Materialize.toast('Erro ao tentar comunicar com o servidor', 4000, 'red rounded');
              }

           }
           _getPraticasChave();


           function _getGestores() {
              try {
                 GestorService.getGestores().success(function (data) {
                    $scope.gestorList = data;
                    $scope.gestores = angular.copy($scope.gestorList);
                 }).error(function () {
                    Materialize.toast('Erro ao tentar receber gestores', 4000, 'orange rounded');
                 });
              } catch (e) {
                 console.log(e);
                 Materialize.toast('Erro ao tentar comunicar com o servidor', 4000, 'red rounded');
              }
           }

           _getGestores();

           /* Visualizar detalhes */
           $scope.openModalDetalhes = function (atividade) {
              $scope.atividade = atividade;
              $("#modal-detalhes").openModal(configModal);
           };
           
                    
           /* Envia a atividade para ser persistida no servidor */
           $scope.sendAtividade = function (atividade) {
              var _send = true;

              $("#form-cronograma").find("input,select, textarea").each(function () {
                 if ($(this).val() === null || $(this).val() === undefined) {
                    $(this).addClass('.red .lighten-4');
                    _send = false;
                 } else {
                    $(this).removeClass('.red .lighten-4');
                 }
              });

              if (_send) {
                 if (app._MODE_CREATE) {
                    create(atividade);
                 } else if (app._MODE_EDIT) {
                    update(atividade);
                 }
              }
           };

           function create(atividade) {
              try {
                 $scope.ok = true;
                 $('#modal-progress').openModal();
                 Service.sendAtividade(atividade).success(function () {
                    $scope.ok = false;
                    $("#modal-success").openModal(configModal);

                    console.log($scope.ok);
                 }).error(function () {
                    $scope.ok = false;
                    $("#modal-error").openModal(configModal);

                    console.log('erro ao enviar atividade');
                 });

              } catch (e) {
                 console.log(e);
                 Materialize.toast("Erro ao tentar conectar com o servidor.", 4000, 'red rouned');
              }

           }

           function update(atividade) {
              try {
                 Service.updateAtividade(atividade).success(function () {
                    _getAtividades();
                    $("#modal-success-update").openModal(configModal);
                 }).error(function () {
                    Materialize.toast('Erro ao tentar atualizar dados!', 4000, 'orange rounded');
                 });
              } catch (e) {
                 console.log(e);
                 Materialize.toast("Erro ao tentar conectar com o servidor.", 4000, 'red rouned');
              }

           }


           // Excluir atividade
           $(document).on('click', '.btn-excluir', function (e) {
              var id = $(this).attr('id').toString().substring(12);
              $scope.atividadeIdToDelete = id;
              $(".alert-modal-excluir").openModal(configModal);
           });



           /* Chega se há atividades */
           $scope.isAtividadeEmpty = function () {
              if ($scope.atividades.length < 1)
                 return true;
              else
                 return false;
           };


           $scope.addPratica = function (pratica) {
              if (pratica.id !== "" && pratica.id !== undefined) {
                 // Remover do select
                 $scope.praticas = $scope.praticas.filter(function (p) {
                    if (p.id != pratica.id) {
                       return p;
                    } else {
                       $scope.atividade.praticaList.push(angular.copy(p));
                    }
                 });
              }
           };

           $scope.addGestor = function (gestor) {
              if (gestor.id !== "" && gestor.id !== undefined) {
                 // Remover do select
                 $scope.gestores = $scope.gestores.filter(function (g) {
                    if (g.id != gestor.id) {
                       return g;
                    } else {
                       $scope.atividade.gestorList.push(angular.copy(g));
                    }
                 });
              }
           };

           $scope.removePratica = function (id) {
              if (id !== "" && id !== undefined) {
                 $scope.atividade.praticaList = $scope.atividade.praticaList.filter(function (p) {
                    if (p.id !== id) {
                       return p;
                    } else {
                       $scope.praticas.push(angular.copy(p));
                    }
                 });
              }
           };

           $scope.removeGestor = function (id) {
              if (id !== "" && id !== undefined) {
                 $scope.atividade.gestorList = $scope.atividade.gestorList.filter(function (g) {
                    if (g.id !== id) {
                       return g;
                    } else {
                       $scope.gestores.push(angular.copy(g));
                    }
                 });
              }
           };



        });