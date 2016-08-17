/*global angular */

angular.module('painelAdmin')
        .controller('EditalCtrl', function ($scope, EditalService, $http) {
           $scope.edital = {};

           var file = {name: '', file: {}};
           function uploadFiles(FileList) {
              file.name = FileList[0].name;
              file.file = FileList[0];
           }
           



           $scope.sendForm = function(form) {
              var formData = new FormData(form);
              formData.append('secret_token', '1234567890'); // Append extra data before send.
              formData.append(file.name, file.file);
              $http.post('/gerendiador/incubadora/edital/novo', formData).success(function(){
                 Materialize.toast('Deu certo', 4000, 'orange');
              }).error(function(){
                 Materialize.toast('Erro', 4000, 'red');
              });
              return false; // Prevent page from submitting.
           };
           



           document.querySelector('input[type="file"]').addEventListener('change', function (e) {
              uploadFiles(this.files);
           }, false);


           $scope.create = function (edital) {
              try {
                 EditalService.create(edital).success(function () {
                    delete $scope.edital;
                 }).error(function () {
                    Materialize.toast('Erro ao tentar cadastrar Edital', 4000, 'orange rounded');
                 });
              } catch (e) {
                 console.log(e);
              }

           };

        });
