/* global angular */
/* global Materialize */

angular.module('painelAdmin')
        .controller('ProcessoChaveCtrl', function ($scope, ProcessoChaveService) {
           /**
            * Messages
            */
           this._MESSAGE_ERROR_GET_PROCESSOS_CHAVE = "Erro ao tentar receber processos-chaves";
           this._MESSAGE_ERROR_CONNECT_SERVER = "Erro ao tentar conectar ao servidor";
           this._MESSAGE_CADASTRO_SUCCESS = "Proceso-Chave cadastrado com sucesso! =)";
           this._MESSAGE_CADASTRO_ERROR = "Erro ao tentar cadastrar Processo-Chave!";
           this._MESSAGE_PROCESSO_DELETE_SUCCESS = "Processo-Chave excluído com sucesso!";
           this._MESSAGE_PROCESSO_DELETE_ERROR = "Erro ao tentar excluir Processo-Chave. =(";
           this._MESSAGE_PROCESSO_UPDATE_SUCCESS = "Processo-Chave atualizado com sucesso!";
           this._MESSAGE_PROCESSO_UPDATE_ERROR = "Erro ao tentar atualizar Processo-Chave. =(";
           this._MESSAGE_PRATICA_GET_ERROR = "Erro ao tentar receber Práticas-Chave. =(";

           /**
            * Variáveis globais
            */
           var _app = this;

           /**
            * Variáveis de $scope
            */

           $scope.processosChave = [];
           $scope.processoDetail = {anexo: {path: "teste"}};
           $scope.praticasChave = [];
           $scope.processoIdToDelete = 0;
           $scope.processoIdDetail = 0;
           $scope.processoSelected = 0;
           $scope.path = "";
           $scope.testValues = [];
           $scope.rowsPerPage = 4; // setar para paginar

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


           /**
            * Métodos de $scope
            * @returns {Boolean}
            */

           // Checa se o array de processos-chave está vazio
           $scope.isProcessosChaveEmpty = function () {
              if ($scope.processosChave.length < 1)
                 return true;
              else
                 return false;
           };

           //Obtém os processos-chave cadastrados
           var _getProcessosChave = function () {
              try {
                 ProcessoChaveService.getProcessoChave().success(function (data) {
                    $scope.processosChave = data;
                    console.log($scope.processosChave);
                    for (var p in $scope.processoChave) {
                       $scope.testValues.push(p);
                    }
                 }).error(function () {
                    Materialize.toast(_app._MESSAGE_ERROR_GET_PROCESSOS_CHAVE, 4000, 'rounded red');
                 });
              } catch (e) {
                 Materialize.toast(_app._MESSAGE_ERROR_CONNECT_SERVER, 4000, 'rounded red');
                 console.log(e);
              }
           };

           _getProcessosChave();

           /* MODAL */

           $scope.showModalAnexo = function (id) {
              $scope.processoSelected = id;
              $("#modal-anexo").openModal(configModal);
           };

           $(document).on('change', 'input#arquivo', function (e) {
              var tamanhoArquivo = parseInt(this.files[0].size);
              if (tamanhoArquivo > 26214400) { //MAX_FILE_SIZE = 26214400 Bytes
                 $(this).val("");
                 $(".file-path.validate").val("");
                 Materialize.toast('<strong>Limite de tamanho excedido! Carregue arquivos de até 25MB</strong>', 10000, 'orange rounded');
              }
           });

           // Checando input file no submit
           $(document).on('submit', '#form-anexo', function (e) {
              if ($("input#arquivo").val() === "" || $("input#arquivo").val() === undefined) {
                 e.preventDefault();
                 Materialize.toast('Carregue um arquivo!', 4000, 'rounded orange');
              }
           });


           $scope.setCriterio = function (criterio) {
              if ($scope.criterio === undefined) {
                 $scope.criterioDeBusca = criterio;
              } else {
                 $scope.criterioDeBusca = undefined;
              }

           };

           // Checa se o processo-chave possui algum arquivo já anexado ou não
           $scope.hasAnexo = function (processoId) {
              var ok = false;
              for (var p in $scope.processosChave) {
                 if ($scope.processosChave[p].id === processoId) {
                    if ($scope.processosChave[p].anexo !== undefined &&
                            $scope.processosChave[p].anexo.path !== undefined) {
                       ok = true;
                    } else {
                       ok = false;
                    }
                 }
              }
              return ok;
           };

//           //View Práticas-Chave
//           var _viewPraticasChave = function (id) {
//              try {
//                 ProcessoChaveService.viewPraticasChave(id)
//                         .success(function (data) {
//                            if (data !== undefined || data !== null) {
//                               $scope.praticasChave = data;
//                            } else {
//                               $scope.praticasChave = [];
//                            }
//                         })
//                         .error(function () {
//                            Materialize.toast(_app._MESSAGE_PRATICA_GET_ERROR, 4000, 'rounded red');
//                         });
//              } catch (e) {
//                 console.log(e);
//                 Materialize.toast(_app._MESSAGE_PRATICA_GET_ERROR, 4000, 'rounded red');
//              }
//
//           };
//
//           _viewPraticasChave();


           // Add processo-chave
           $scope.addProcessoChave = function (processoChave) {
              try {
                 if (processoChave !== undefined && processoChave.nome !== "") {
                    ProcessoChaveService.addProcessoChave(processoChave)
                            .success(function () {
                               _getProcessosChave();
                               Materialize.toast(_app._MESSAGE_CADASTRO_SUCCESS, 4000, 'rounded green');
                               delete $scope.processoChave;
                               $("#modal-cad-processo").closeModal();
                            }).error(function () {
                       Materialize.toast(_app._MESSAGE_CADASTRO_ERROR, 4000, 'rounded red');
                       delete $scope.processoChave;

                    });
                 } else {
                    Materialize.toast("Digite um nome!", 4000, 'orange rounded');
                 }

              } catch (e) {
                 Materialize.toast(_app._MESSAGE_CADASTRO_ERROR, 4000, 'rounded red');
                 console.log(e);
              }
           };

           // Atualiza Processo-Chave
           $scope.updateProcessoChave = function (processoChave) {
              try {
                 ProcessoChaveService.updateProcessoChave(processoChave).success(function () {
                    _getProcessosChave();
                    Materialize.toast(_app._MESSAGE_PROCESSO_UPDATE_SUCCESS, 4000, 'rounded green');
                 }).error(function () {
                    Materialize.toast(_app._MESSAGE_PROCESSO_UPDATE_ERROR, 4000, 'rounded red');
                 });
              } catch (e) {
                 Materialize.toast(_app._MESSAGE_PROCESSO_UPDATE_ERROR, 4000, 'rounded red');
                 console.log(e);
              }

           };

           // Excluir processo-chave
           $(document).on('click', '.btn-excluir', function (e) {
              var id = $(this).attr('id').toString().substring(12);
              $scope.processoIdToDelete = id;
              $(".alert-modal-excluir").openModal(configModal);
           });


           /**
            * Entrontra e retorna nome do arquivo anexado ao processo-chave
            * 
            * @param {type} id
            * @returns {unresolved}
            */
           function findNameFileByProcessId(id) {
              var nameFile = null;

              var processos = $scope.processosChave.filter(function (p) {
                 if (p.id == id) {
                    return p;
                 }
              });

              var processo = processos[0];

              if (processo !== undefined && processo.id == id) {
                 if (processo.anexo !== undefined && processo.anexo.path !== undefined) {
                    nameFile = processo.anexo.path.split("\\")[8];
                    return nameFile;
                 }
              }
              return nameFile;
           }
           
           function getProcessoChaveById(id){
              var _processo = {};
              try {
                 var processos = $scope.processosChave.filter(function(p){
                    if(p.id == id){
                       return p;
                    }
                 });
                 
                 _processo = processos[0];
              } catch (e) {
                 console.log(e);
              }                            
              return _processo;
           };
           // Detail processo-chave
           $(document).on('click', '.btn-detail', function () {
              var id = $(this).attr('id').toString().substring(11);
              $scope.processoIdDetail = id;

              var _path = findNameFileByProcessId(id);
              var urlBase = window.location.protocol + "//" + window.location.host + "/gerenciador/";
              
              if (_path !== null) {
                 $("span#nameFile").text(_path);
                 $("a#linkAnexo").attr({"href": urlBase + "processo-chave/" + id + "/anexo/download"});
              } else {
                 $("a#linkAnexo").removeAttr("href");
              }
              $("a#praticasChave").attr({"href": urlBase + "processo-chave/" + id + "/praticas-chave"});
              var _nomeProcesso = getProcessoChaveById(id).nome;
              $("strong#processo-detalhes").text(_nomeProcesso);
              
              $(".alert-modal-detail").openModal(configModal);
           });



           // Excluir Processo-Chave
           $scope.deleteProcessoChave = function (id) {
              try {
                 ProcessoChaveService.deleteProcessoChave(id).success(function () {
                    _getProcessosChave();
                    $(".alert-modal-excluir").closeModal();
                    Materialize.toast(_app._MESSAGE_PROCESSO_DELETE_SUCCESS, 4000, 'rounded green');
                 }).error(function () {
                    $(".alert-modal-excluir").closeModal();
                    Materialize.toast(_app._MESSAGE_PROCESSO_DELETE_ERROR, 4000, 'rounded red');
                 });
              } catch (e) {
                 $(".alert-modal-excluir").closeModal();
                 console.log(e);
              }

           };

           // Efeito toggle para exibição do modo de edição do Processo-Chave
           $scope.showProcesso = function (processoChave) {
              if (processoChave.show === undefined) {
                 processoChave.show = true;
              } else if (processoChave.show === true) {
                 processoChave.show = false;
              } else {
                 processoChave.show = true;
              }
           };


           // Limpra campo após cadastro
           $scope.clearForm = function () {
              delete $scope.processoChave;
           };

        });

