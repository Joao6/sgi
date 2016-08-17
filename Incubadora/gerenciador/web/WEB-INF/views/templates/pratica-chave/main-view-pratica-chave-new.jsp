<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="PraticaChaveCtrl">

   <div class="row">
      <div class="col s12 l8">

         <div class=" col s12 m12 l12 card-panel">    
            <div class="col s12 m12 l12">
               <div class="card center white" style="border-radius: 0px">
                  <div class="card-content black-text valign-wrapper">
                     <i class="small material-icons">poll</i> &nbsp;&nbsp;
                     <span class="card-title truncate valign">Cadastrar nova Pr&aacute;tica-Chave</span>                  
                  </div>
               </div>

               <div class="card-content">
                  <!-- // FORMULÁRIO -->
                  <form name="praticaForm">
                     <input hidden="true"id="praticaEditId"  value="${praticaEdit}" />
                     <!--// FORMULÁRIO 3 //-->
                     <span></span>
                     <div id="form-1" class="card-panel z-depth-1 col s12 m12 l12" style="border-radius: 0px">

                        <div class="card-content">                 
                           <div class="row">

                              <div class="input-field col s12 m6 l6">
                                 <input id="nome" name="nome" data-ng-required="true" data-ng-model="praticaChave.nome" type="text">
                                 <label for="nome">Nome</label>
                              </div>

                              <div class="input-field col s12 m6 l6">

                                 <select name="estagioEvolucao" id="estagioEvolucao" class="browser-default" data-ng-required="true" data-ng-model="praticaChave.estagioEvolucao.id">
                                    <option value="" selected>Est&aacute;gio de Evolu&ccedil;&atilde;o</option>    
                                    <option data-ng-repeat="estagioEvolucao in estagiosEvolucao" value="{{estagioEvolucao.id}}">{{estagioEvolucao.nome}}</option>
                                 </select>                                             
                              </div>                              
                           </div>   

                           <div class="row">
                              <div class="input-field col s12 m6 l6">
                                 <input type="date" class="datepicker" placeholder="Data de Evolução" data-ng-required="true" data-ng-model="praticaChave.dataEvolucao"  id="dataEvolucao" name="dataEvolucao">                        
                              </div>

                              <div class="input-field col s12 m6 l6">
                                 <input type="date" class="datepicker" placeholder="Data de Certificação"  data-ng-model="praticaChave.dataCertificacao"  id="dataCertificao" name="dataCertificacao">                        
                              </div>

                           </div>

                           <div class="row">
                              <div class="input-field col s12 m12 l12">
                                 <textarea id="descricao" name="descricao" data-ng-model="praticaChave.descricao" data-ng-required="true" type="text" class="validate materialize-textarea" cols="1" role="1">
                                 </textarea>
                                 <label for="descricao">Descri&ccedil;&atilde;o</label>
                              </div>

                           </div>
                           <button type="button" data-ng-click="addPratica(praticaChave)" data-ng-disabled="praticaForm.$invalid" style="margin-left: 2%; margin-bottom: 2%" class="btn-floating circle waves-effect waves-green blue right">
                              <i class="material-icons">add</i>
                           </button>
                           <button type="button" data-ng-click="clearPratica()"  style="margin-left: 2%" class="btn-floating circle waves-effect waves-green green right">
                              <i class="material-icons">refresh</i>
                           </button>


                        </div>
                     </div>    
                  </form>
               </div>


               <div class="card-content" data-ng-if="praticasAdicionadas.length > 0">
                  <div  class="card-panel z-depth-1 col s12 m12 l12" style="border-radius: 0px">
                     <div class="card-title green lighten-4 center-align" style="padding-left: 5%; padding-right: 5%; padding-top: 2%; padding-bottom: 2%; margin-top: 1rem">
                        <h6 style="font-size: 14pt">Pr&aacute;ticas-Chave adiciondas</h6>
                     </div>
                     <div class="card-content">                 
                        <div class="row">                            
                           <ul class="collection">
                              <li class="collection-item" data-ng-repeat="pratica in praticasAdicionadas| orderBy:'nome'">
                                 <a href="#!" class="secondary-content close modal-trigger" data-ng-click="removePraticaAdded(pratica)"><i class="material-icons">clear</i></a>
                                 <div class="valign-wrapper">
                                    <i class="material-icons valign green-text" style="margin-right:5%">dashboard</i>
                                    <a href="#!" data-ng-click="verDetalhesPraticaAdicionada(pratica)"><span class="valign" data-ng-bind="pratica.nome"></span></a>
                                 </div>
                              </li>                                 
                           </ul>
                        </div>

                     </div>                 
                  </div>
               </div> 
               <button type="button" data-ng-click="savePraticasChave()" data-ng-disabled="praticasAdicionadas.length < 1" style="margin-left: 2%; margin-bottom: 2%" class="btn-floating circle waves-effect waves-blue blue right" >
                  <i class="material-icons">done</i>
               </button>

               <a href="<c:url value="/incubadora/processo-chave"/>">
                  <button type="button" style="margin-left: 2%;  margin-bottom: 2%" class="btn-floating circle waves-effect waves-orange orange right">
                     <i class="material-icons">undo</i>
                  </button>
               </a>
               <!-- // FIM-FORMULÁRIO -->


            </div>

         </div>

      </div>
   </div>
</div>
