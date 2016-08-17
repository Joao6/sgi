  /* global angular */

  (function () {
     angular.module('cronogramaAnual', [])

         .controller('CronogramaCtrl', function ($scope, service) {
            $scope.responsaveis = [];
            $scope.praticas = [];

            $scope.addPratica = function (pratica) {
               $scope.praticas.push(angular.copy(pratica));
               delete pratica;
            };

            $scope.addResponsaveis = function (responsavel) {
               $scope.responsaveis.push(angular.copy(responsavel));
               delete responsavel;
            };

            service.getPraticas().success(function (data) {
               $scope.praticas = data;             
            }).error(function () {
               console.log('Erro ao carregar Praticas');
            });
            
            service.getResponsaveis().success(function(data){
               $scope.responsaveis = data;
            }).error(function(){
               console.log('Erro ao carregar Responaveis!');
            });            

            console.log($scope.praticas);




            //Todo Delete

            //Todo Services
         });

  })();

