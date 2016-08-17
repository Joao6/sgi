/* global angular */
/* global Materialize */

angular.module('painelAdmin')
        .controller('PraticaChaveCtrl', function ($scope, PraticaChaveService) {

           /**
            * Mensagens
            */
           this._MESSAGE_GET_ESTAGIO_EVOLUCAO = "Erro ao tentar obter Estágio de Evolução. =(";
           this._MESSAGE_SAVE_PRATICA_CHAVE_ERROR = "Erro ao tentar salvar Práticas-Chave. =(";
           this._MESSAGE_SAVE_PRATICA_CHAVE_SUCCESS = "Práticas-Chave cadastrada com sucesso!";
           this._MESSAGE_GET_PRATICA_CHAVE_ERROR = "Erro ao tentar carregar Práticas-chave. =(";
           this._MESSAGE_GET_PROCESSOS_CHAVE_ERROR = "Erro ao tentar carregar Processo-Chave. =(";
           this._MESSAGE_PRATICA_DELETE_SUCCESS = "Prática-Chave excluída com sucesso!";
           this._MESSAGE_PRATICA_DELETE_ERROR = "Erro ao tentar excluir a Pratica-Chave. =(";

           /**          
            * Variáveis locais
            */
           var _app = this;
           var _index = 0;
           var _idPraticaEdit = $("#praticaEditId").val();


           /**
            * Variáveis de $scope
            */
           $scope.estagiosEvolucao = [];
           $scope.praticasChave = [];
           $scope.praticasAdicionadas = [];
           $scope.praticaSelected = 0;
           $scope.praticaIdToDelete = 0;
           $scope.processoChave = {};
           $scope.praticaChave = {};
           $scope.ok = false;
           $scope.forms = [];
           $scope.testValues = [];
           $scope.rowsPerPage = 5; // setar para paginar
           var _id = 0;

           /**
            * Métodos privados
            */

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

        
              //View Práticas-Chave
              var _viewPraticasChave = function () {
                 try {
                    PraticaChaveService.viewPraticasChave()
                            .success(function (data) {
                               if (data !== undefined || data !== null) {
                                  $scope.praticasChave = data;
                                  console.log($scope.praticasChave);
                               }
                            })
                            .error(function () {
                               Materialize.toast(_app._MESSAGE_GET_PRATICA_CHAVE_ERROR, 4000, 'rounded red');
                            });
                 } catch (e) {
                    console.log(e);
                    Materialize.toast(_app._MESSAGE_GET_PRATICA_CHAVE_ERROR, 4000, 'rounded red');
                 }

              };

              _viewPraticasChave();
           
           
           function getPraticaChaveById(id) {
              var _pratica = {};
              try {
                 var praticas = $scope.praticasChave.filter(function (p) {
                    if (p.id == id) {
                       return p;
                    }
                 });

                 _pratica = praticas[0];
              } catch (e) {
                 console.log(e);
              }
              return _pratica;
           }

           // TODO edit de pratica-chave
           if (_idPraticaEdit !== undefined && _idPraticaEdit !== "") {
              var _pratica = getPraticaChaveById(_idPraticaEdit);
              $scope.praticaChave = _pratica;
              console.log(_idPraticaEdit);
              console.log($scope.praticasChave);
           }



           // Obtém Processos-Chave cadastrados
           var _getProcessoChave = function () {

              try {
                 PraticaChaveService.getProcessoChave()
                         .success(function (data) {
                            $scope.processoChave = data;
                         })
                         .error(function () {
                            Materialize.toast(_app._MESSAGE_GET_PROCESSOS_CHAVE_ERROR, 4000, 'rounded red');
                         });
              } catch (e) {
                 Materialize.toast(_app._MESSAGE_GET_PROCESSOS_CHAVE_ERROR, 4000, 'rounded red');
                 console.log(e);
              }
           };

           _getProcessoChave();



           // Obtém Estágios de Evolução cadastrados
           var _getEstagiosEvolucao = function () {
              try {
                 PraticaChaveService.getEstagiosEvolucao()
                         .success(function (data) {
                            $scope.estagiosEvolucao = data;
                         })
                         .error(function () {
                            Materialize.toast(_app._MESSAGE_GET_ESTAGIO_EVOLUCAO, 4000, 'rounded red');
                         });
              } catch (e) {
                 Materialize.toast(_app._MESSAGE_GET_ESTAGIO_EVOLUCAO, 4000, 'rounded red');
                 console.log(e);
              }
           };

           _getEstagiosEvolucao();

           // Checa se o array de processos-chave está vazio
           $scope.isPraticasChaveEmpty = function () {
              if ($scope.praticasChave.length < 1)
                 return true;
              else
                 return false;
           };

           // Add novo form dinamicamente
           $scope.addPratica = function (praticaChave) {
              praticaChave.id = ++_id;
              $scope.praticasAdicionadas.push(praticaChave);
              delete $scope.praticaChave;

           };
           
           $scope.removePraticaAdded = function(pratica){
             $scope.praticasAdicionadas = $scope.praticasAdicionadas.filter(function(p){
                if(p.id !== pratica.id) return p;
             });
           };
           
           $scope.verDetalhesPraticaAdicionada = function(pratica){
              $scope.praticaChave = pratica;
           };
           
           // Remover formulário selecionado
           $scope.removeForm = function (id) {
              $scope.forms = $scope.forms.filter(function (element) {
                 if (element.id !== id)
                    return element;
              });
           };
           
           $scope.clearPratica = function(){
             delete $scope.praticaChave; 
           };

           // Salvar Práticas-Chave
           $scope.savePraticasChave = function () {
              if (_idPraticaEdit === undefined || _idPraticaEdit === "") {
                 try {
           
                    PraticaChaveService.savePraticasChave($scope.praticasAdicionadas)
                            .success(function () {
                               Materialize.toast(_app._MESSAGE_SAVE_PRATICA_CHAVE_SUCCESS, 4000, 'rounded green');

                               setTimeout(function () {
                                  window.location.href = window.location.protocol + "//" + window.location.host + "/gerenciador/incubadora/processo-chave";
                               }, 500);
                            }).error(function () {
                       Materialize.toast(_app._MESSAGE_SAVE_PRATICA_CHAVE_ERROR, 4000, 'rounded orange');
                    });
                 } catch (e) {
                    Materialize.toast("Erro ao tentar comunicar como o servidor! =(", 4000, 'rounded red');
                    console.log(e);
                 }

              } else {


              }

           };


           // Excluir Pratica-Chave
           $scope.deletePraticaChave = function (id) {

              try {
                 PraticaChaveService.deletePraticaChave(id).success(function () {
                    _viewPraticasChave();
                    $(".alert-modal-excluir").closeModal();
                    Materialize.toast(_app._MESSAGE_PRATICA_DELETE_SUCCESS, 4000, 'rounded green');
                 }).error(function () {
                    Materialize.toast(_app._MESSAGE_PRATICA_DELETE_ERROR, 4000, 'rounded red');
                 });
              } catch (e) {
                 console.log(e);
              }

           };
           
           
           $scope.deleteAnexo = function(id){              
              try {
                 PraticaChaveService.deleteAnexo(id).success(function(){
                    $(".modal").closeModal(configModal);          
                    _viewPraticasChave();
                    Materialize.toast("Arquivo excluído com sucesso!", 4000, 'rounded green');
                 }).error(function(){
                    Materialize.toast('Erro ao tentar excluir arquivo', 4000, 'rounded orange');
                 });
              } catch (e) {
                 console.log(e);
                 Materialize.toast("Erro ao tentar se comunicar com o servidor");
              }
 
           };


           // Detail processo-chave
           $(document).on('click', '.btn-detail', function () {
              var id = $(this).attr('id').toString().substring(11);
              $scope.praticaIdDetail = id;

              var urlBase = window.location.protocol + "//" + window.location.host + "/gerenciador/";
              if ($scope.hasAnexo(id)) {
                 $("a#linkAnexos").removeClass("disabled");
                 $("a#linkAnexos").attr({"href": urlBase + "processo-chave/" + $scope.processoChave.id + "/pratica-chave/" + id + "/anexos"});
              } else {
                 $("a#linkAnexos").attr({'href': '#!'}).addClass("disabled");
              }

              var _p = getPraticaChaveById(id);

              $("strong#pratica-detalhes").text(_p.nome);
              $("table tr td#estagioEvolucao").text(_p.estagioEvolucao.nome);
              $("table tr td#dataEvolucao").text(_p.dataEvolucao);
              if (_p.dataCertificacao !== undefined && _p.dataCertificacao !== null) {
                 $("table tr td#dataCertificacao").text(_p.dataCertificacao);
                 console.log(_p.dataCertificacao);
              } else {
                 $("table tr td#dataCertificacao").text("-- Não Definido --");
              }
              $("table tr td#descricao").text(_p.descricao);


              $(".alert-modal-detail").openModal(configModal);
           });


           // Excluir processo-chave
           $(document).on('click', '.btn-excluir', function (e) {
              var id = $(this).attr('id').toString().substring(12);
              $scope.praticaIdToDelete = id;
              $(".alert-modal-excluir").openModal(configModal);
           });


           $scope.showModalAnexo = function (id) {
              $scope.praticaSelected = id;
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
           $scope.hasAnexo = function (praticaId) {
              var ok = false;
              for (var p in $scope.praticasChave) {
                 if ($scope.praticasChave[p].id == praticaId) {
                    if ($scope.praticasChave[p].arquivos !== undefined &&
                            $scope.praticasChave[p].arquivos.length > 0) {
                       ok = true;
                    } else {
                       ok = false;
                    }
                 }
              }
              return ok;
           };


           // Limpra campo após cadastro
           $scope.clearForm = function () {
              delete $scope.praticaChave;
           };

           $(document).on('click', '.btn-excluir', function (e) {
              var id = $(this).attr("id").toString().split("-")[1];
              try {
                 PraticaChaveService.deleteAnexo(id).success(function () {
                    $("#modal-excluir-" + id).closeModal();
                    e.preventDefault();
                 }).error(function () {
                    e.preventDefault();
                    $("#modal-excluir-" + id).closeModal();
                 });
              } catch (e) {
                 console.log(e);
                 $(".modal").closeModal();
              }

           });

        });