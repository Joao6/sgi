angular.module('painelAdmin').controller('UpdateCtrl', function ($scope, UpdateService) {
    
    this.MESSAGE_GET_EMPREENDIMENTOS_ERROR = "Erro ao tentar receber empreendimentos.";
    this.MESSAGE_GET_AVALIADORES_ERROR = "Erro ao tentar receber avaliadores.";
    this.MESSAGE_GET_EMPREENDEDORES_ERROR = "Erro ao tentar receber empreendedores.";
    this.MESSAGE_GET_SERVER_BAD_CONNECTION = "Erro ao tentar comunicar com o servidor.";
    this.MESSAGE_EDITAL_GET_ERROR = "Erro ao tentar obter lista de Editais em abterto.";
    this.MESSAGE_RAMOS_GET_ERROR = "Erro ao tentar obter lista de Ramos de Atividade.";
    
    $scope.empreendimento = {};
    
     var app =this;
    
    function _getEmpreendimentoById(){
        try {
            var id = angular.element('#idEmpree').val();
            UpdateService.getEmpreendimento(id).success(function (data){
                $scope.empreendimento = data;
            }).error(function(){
                Materialize.toast(app.MESSAGE_GET_EMPREENDIMENTOS_ERROR, 4000, 'orange rounded');
            });
        }catch (e){
            Materialize.toast(app.MESSAGE_GET_EMPREENDIMENTOS_ERROR, 4000, 'orange rounded');
            console.log(e);
        }
    };
    
    _getEmpreendimentoById();
});


