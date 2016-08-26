/* global angular */

/* global Materialize */

angular.module('painelEmpreendedor').controller('EmpreendimentoCtrl', function ($scope, EmpreendimentoService) {

   /* MESSAGES */

   this.MESSAGE_GET_EMPREENDIMENTOS_ERROR = "Erro ao tentar receber empreendimentos.";
   this.MESSAGE_GET_SERVER_BAD_CONNECTION = "Erro ao tentar comunicar com o servidor.";
   this.MESSAGE_EDITAL_GET_ERROR = "Erro ao tentar obter lista de Editais em abterto.";
   this.MESSAGE_RAMOS_GET_ERROR = "Erro ao tentar obter lista de Ramos de Atividade.";


   $scope.empreendimentoList = [];
   $scope.empreendimento = {};
   $scope.apresentacaoNegocio = {empreendimento: {'id': 0}};
   $scope.editalList = [];
   $scope.nextForm = 1;
   $scope.ramoAtividadeList = [];
   var statusList = [];
   $scope.empreendimento = {};
   $scope.rowsPerPage = 5;
   
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

//   $scope.empreendimento = empreendimento.data;
   var _STATUS_CREATE_MODE = false;
   var _STATUS_EDIT_MODE = false;

   statusList[0] = 'Proposta Enviada';
   statusList[1] = 'Apresentação Agendada';
   statusList[2] = 'Apresentação Realizada';
   statusList[3] = 'Avaliação Realizada';
   statusList[4] = 'Aprovado';
   statusList[5] = 'Reprovado';



   var status = $("#status").val();
   var idEmpreendimento = $("#empreendimento-id").val();

   $scope.apresentacaoNegocio.empreendimento.id = idEmpreendimento;
   
   //openModal (JP)
   $scope.openModal = function (id, empreendimento) {
        delete $scope.empreendimento;        
        $scope.empreendimento = empreendimento;
        var index = 0;
        $("#modal-" + id).openModal(configModal);
    };
    //openModal (JP)

   var app = this;
   function _getEmpreendimentos() {
      try {
         EmpreendimentoService.getEmpreendimentos().success(function (data) {
            $scope.empreendimentoList = data;
         }).error(function () {
            Materialize.toast(app.MESSAGE_GET_EMPREENDIMENTOS_ERROR, 4000, 'orange rounded');
         });
      } catch (e) {
         Materialize.toast(app.MESSAGE_GET_EMPREENDIMENTOS_ERROR, 4000, 'orange rounded');
         console.log(e);
      }

   }

   _getEmpreendimentos();


   if (status === "create") {
      function getEditais() {
         try {
            EmpreendimentoService.getEditais().success(function (data) {
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
            EmpreendimentoService.getRamos().success(function (data) {
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
   }

   $scope.save = function (empreendimento) {
      try {
         EmpreendimentoService.save(empreendimento).success(function () {
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
         EmpreendimentoService.addApNegocio(apresentacaoNegocio).success(function () {
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
         EmpreendimentoService.remover(id).success(function () {
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

});
