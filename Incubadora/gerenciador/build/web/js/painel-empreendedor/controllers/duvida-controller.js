
  /* global angular */

  (function () {
     angular.module('painelEmpreendedor').controller('DuvidaCtrl', function ($scope, DuvidaService) {
        $scope.duvidaList = [];
        $scope.duvidas = {};
        $scope.respostaDuvida = {};
        $scope.hideTextarea = true;
        $scope.hideTextButton = false;
        $scope.hideSendButton = true;

        /* Obter todas as dúvidas postadas */
        var getAllDuvidas = function () {
           DuvidaService.getAllDuvidas().success(function (data) {
              $scope.duvidas = data;
           }).error(function () {
              Materialize.toast('Erro ao obter número de dúvias novas', 4000);
           });
        };

        getAllDuvidas();

        /* Obter gestores cadastrados  */
        var getDuvidas = function () {
           DuvidaService.getDuvidas().success(function (data) {
               
              $scope.duvidaList = data;
              console.log($scope.duvidaList);
           }).error(function () {
               Materialize.toast('Erro ao obter número de dúvias novas', 4000);
           });
        };

        getDuvidas();

        /* Cheva que o Array de Gestores está limpo */
        $scope.isDuvidasEmpty = function () {
           if ($scope.duvidaList.length === 0)
              return true;
           else
              return false;
        };


        $scope.addDuvida = function (duvida) {

           if ($("#pergunta").val() !== "" && $("#pergunta").val() !== null) {

              DuvidaService.addDuvida(angular.copy(duvida)).success(function () {
                 delete $scope.duvida;
                 getDuvidas();
              }).error(function () {
                 Materialize.toast('Erro ao tentar postar dúvida', 4000);
              });
           } else {
              Materialize.toast('Você não digitou nada para ser postado!', 4000);
           }
        };

        /* Insere gestor no banco de dados */
        $scope.responderDuvida = function (duvida, resposta) {

           $scope.ok = false;
           var _send = true;

           if ($("#resposta-" + duvida.id).val() === "" || $("#resposta-" + duvida.id) === null) {
              $("textarea").css({'border-bottom': '2px solid red'});
              _send = false;
              Materialize.toast('Antes, responda a dúvida do(a)  ' + duvida.empreendedor.nome, 4000);
           }
           ;


           if (_send) {
              $scope.ok = true;
              //  $('#modal-progress').openModal();
              console.log(duvida);
              $scope.respostaDuvida.resposta = angular.copy(resposta);
              $scope.respostaDuvida.duvida = angular.copy(duvida);
              DuvidaService.responderDuvida($scope.respostaDuvida).success(function () {
                 getDuvidas();
                 $scope.ok = false;                
              }).error(function () {
                 $scope.ok = false;
                 Materialize.toast('Erro ao tentar responder', 4000);
              });

           }
        };


        /* Exclui Gestor do banco de dados */
        $scope.removerDuvida = function (duvida) {
           var resposta = $("#resposta").val();
           duvida.respostaDuvidaList.push(resposta);
           $scope.duvidaList.filter(function (elemento) {
              if (elemento.id === duvida.id) {
                 DuvidaService.removerDuvida(duvida.id).success(function () {
                    getDuvidas();
                 }).error(function () {
                    Materialize.toast('Erro ao excluir Dúvida!', 4000);
                 });
              }
           });
        };

        $scope.isRespDuvidaListEmpty = function (duvida) {
           if (duvida.respostaDuvidaList.legth < 1)
              return true;
           else
              return false;
        };

        $scope.responderAgora = function () {
           $scope.hideTextarea = false;
           $scope.hideTextButton = true;
           $scope.hideSendButton = false;
        };

        $scope.cancel = function () {
           $scope.hideSendButton = true;
           $scope.hideTextarea = true;
           $scope.hideTextButton = false;
        };

     });


  })();