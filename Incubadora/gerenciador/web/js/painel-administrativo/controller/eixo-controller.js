/* global angular */
/* global Materialize */

(function () {
   angular.module('painelAdmin').controller('EixoCtrl', ['$scope', 'EixoService', function ($scope, EixoService) {

         this.MESSAGE_DELETE_EIXO_SUCCESS = "Eixo excluído com sucesso!";
         this.MESSAGE_UPDATE_EIXO_SUCCESS = "Eixo atualizado com sucesso!";
         this.MESSAGE_CRITERIO_ADD_SUCCESS = "Critério adicionado com sucesso!";
         this.MESSAGE_SERVER_ERROR_CONNECTION = "Erro ao tentar comunicar com o servidor.";
         this.MESSAGE_ERROR_GET_EIXOS = "Erro ao tentar obter os eixos que compões o modelo de avaliação.";
         this.MESSAGE_ERROR_GET_CRITERIOS = "Erro ao tentar Obter critérios.";
         this.MESSAGE_ERROR_ADD_EIXO = "Erro ao tentar salvar dados.";
         this.MESSAGE_ERROR_ADD_CRITERIO_AVALIACAO = "Erro ao tentar salvar critério de avaliação.";
         this.MESSAGE_ERROR_UPDATE_EIXO = "Erro ao tentar atualizar dados.";
         this.MESSAGE_ERROR_DELETE_EIXO = "Erro ao tentar excluir eixo.";
         // config modal
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

         $scope.eixoList = [];
         $scope.criterioList = [];
         $scope.eixoIdToDelete = 0;
         $scope.criterioIdToDelete = 0;
         $scope.eixo = {};
         $scope.criterio = {};
         $scope.criterioAvaliacao = {};


         var app = this;
         var _MODE_CREATE = false;
         var _MODE_UPDATE = false;

         /**
          * Obtém os eixos do modelo de avaliação
          * 
          * @returns {undefined}
          */
         function _getEixoList() {
            try {
               EixoService.getEixos().success(function (data) {
                  $scope.eixoList = data;
               }).error(function () {
                  Materialize.toast(app.MESSAGE_ERROR_GET_EIXOS, 4000, 'orange rounded');
               });
            } catch (e) {
               console.log(e);
               Materialize.toast(app.MESSAGE_SERVER_ERROR_CONNECTION, 4000, 'red rounded');
            }
         }
         _getEixoList();
         
         function _getCriterioList(){
            try {
               EixoService.getCriterios().success(function(data){
                  $scope.criterioList = data;
               }).error(function(){
                  Materialize.toast(app.MESSAGE_ERROR_GET_CRITERIOS, 4000, 'orange rounded');
               });
            } catch (e) {
               console.log(e);
               Materialize.toast(app.MESSAGE_SERVER_ERROR_CONNECTION, 4000, 'red rounded');
            }

         }
         
         _getCriterioList();

         /**
          * Abre modais
          * 
          * @param {int} id
          * @returns {undefined}
          */
         $scope.openModal = function (id) {
            switch (id) {
               case 1:
                  // Excluir               
                  $("#modal-" + id).openModal(configModal);
                  break;
               case 2:
                  // Create Eixo
                  _MODE_CREATE = true;
                  $("#modal-2 h6").text('Novo Eixo');
                  $("#modal-" + id).openModal(configModal);
                  break;
               case 3:
                  // Edit eixo
                  _MODE_UPDATE = true;
                  $("#modal-2 h6").text('Alterar Informações');
                  $("#modal-2").openModal(configModal);
                  break;
               case 4:
                  // Add ceritério
                  $("#modal-" + id).openModal(configModal);
                  break;
               case 5:
                  // Detalhes Eixo
                  $("#modal-criterio-detalhes").openModal(configModal);
                  break;

               default:
                  Materialize.toast('Opção Inválida', 3000, 'orange rounded');
            }
         };

         /**
          * 
          * @param {type} eixo
          * @returns {undefined}
          */
         $scope.addEixo = function (eixo) {
            var pattern = /^\d{1}\d*.\d{1}\d*$/;
            if (!pattern.test(eixo)) {
               var copy = eixo.peso;
               eixo.peso = String(copy).replace(",", ".");

            }
            if (_MODE_CREATE) {
               create(eixo);
               _MODE_CREATE = false;
            } else if (_MODE_UPDATE) {
               update(eixo);
               _MODE_UPDATE = false;
            }

         };

         $scope.addCriterio = function (criterioAvaliacao) {
            try {
               EixoService.addCriterio(criterioAvaliacao).success(function () {
                  Materialize.toast(app.MESSAGE_CRITERIO_ADD_SUCCESS, 4000, 'green rounded');
                  _getCriterioList();
                  $("#modal-4").closeModal();
                  delete $scope.criterioAvaliacao;
               }).error(function () {
                  Materialize.toast(app.MESSAGE_ERROR_ADD_CRITERIO_AVALIACAO, 4000, 'orange rounded');
               });
            } catch (e) {
               console.log(e);
               Materialize.toast(app.MESSAGE_SERVER_ERROR_CONNECTION, 4000, 'red rounded');
            }

         };

         /**
          * 
          * @param {type} eixo
          * @returns {undefined}
          */
         function create(eixo) {
            try {
               EixoService.addEixo(eixo).success(function () {
                  _getEixoList();
                  $("#modal-2").closeModal();
                  delete $scope.eixo;
               }).error(function () {
                  Materialize.toast(app.MESSAGE_ERROR_ADD_EIXO, 4000, 'orange rounded');
               });
            } catch (e) {
               console.log(e);
               Materialize.toast(app.MESSAGE_SERVER_ERROR_CONNECTION, 4000, 'red rounded');
            }
         }

         /**
          * 
          * @param {type} eixo
          * @returns {undefined}
          */
         function update(eixo) {
            try {
               EixoService.update(eixo).success(function () {
                  _getEixoList();
                  $("#modal-2").closeModal();
                  Materialize.toast(app.MESSAGE_UPDATE_EIXO_SUCCESS, 4000, 'green rounded');
                  delete $scope.eixo;
               }).error(function () {
                  Materialize.toast(app.MESSAGE_ERROR_UPDATE_EIXO, 4000, 'orange rounded');
               });
            } catch (e) {
               console.log(e);
               Materialize.toast(app.MESSAGE_SERVER_ERROR_CONNECTION, 4000, 'red rounded');
            }
         }


         $(document).on('click', '.btn-editar,.btn-detalhes', function (e) {
            var id = $(this).attr('id').substring(11);
            for (var i = 0; i < $scope.eixoList.length; ++i) {
               if ($scope.eixoList[i].id == id) {
                  $scope.eixo = $scope.eixoList[i];
                  break;
               }
            }
            $scope.$digest();

         });

         $(document).on('click', '.btn-criterio', function (e) {
            var id = $(this).attr('id').substring(13);
            for (var i = 0; i < $scope.eixoList.length; ++i) {
               if ($scope.eixoList[i].id == id) {
                  $scope.criterioAvaliacao.eixo = $scope.eixoList[i];
                  break;
               }
            }
            $scope.$digest();
         });


         $(document).on('click', '.btn-excluir', function (e) {
            var id = $(this).attr('id').substring(12);
            $scope.eixoIdToDelete = id;
            $scope.$digest();
         });
                 
         /**
          * 
          * @param {type} id
          * @returns {undefined}
          */
         $scope.delete = function (id) {
            try {
               EixoService.delete(id).success(function () {
                  Materialize.toast(app.MESSAGE_DELETE_EIXO_SUCCESS, 4000, 'rounded green');
                  $("#modal-1").closeModal();
                  _getEixoList();
               }).error(function () {
                  Materialize.toast(app.MESSAGE_ERROR_DELETE_EIXO, 4000, 'rounded orange');
               });

            } catch (e) {
               console.log(e);
               Materialize.toast(app.MESSAGE_SERVER_ERROR_CONNECTION, 4000, 'rounded red');
            }

         };
         
         $scope.deleteCriterio = function(id){
            try {
               EixoService.deleteCriterio(id).success(function(){
                  _getCriterioList();
                  $("#modal-criterio-detalhes").closeModal();
                  Materialize.toast('Critério excluído com sucesso!', 4000, 'green rounded');
               }).error(function(){
                  Materialize.toast('Erro ao tentar excluir critério', 4000, 'orange rounded');
               });
            } catch (e) {
               console.log(e);
               Materialize.toast(app.MESSAGE_SERVER_ERROR_CONNECTION, 4000, 'red rounded');
            }
 
         };

      }]); // and controller


})(); // end module pattern