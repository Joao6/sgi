<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="PainelCtrl">

   <div class="row">
      <div class="col s12 l8">

         <div class=" col s12 m12 l12 card-panel ember">    
            <div class="col s12 m12 l12">
               <div class="card center white">
                  <div class="card-content black-text valign-wrapper">
                     <i class="small material-icons valigin">event_note</i> &nbsp;&nbsp;
                     <span class="card-title truncate valigin">Adicionar &nbsp; Atividade &nbsp; ao Cronograma Anual</span>                  
                  </div>
               </div>

               <!-- Modal Structure -->
               <div id="modal-progress" class="modal bottom-sheet" data-ng-show="ok">
                  <div class="modal-content">
                     <h5>Salvando...</h5>
                     <div class="progress amber lighten-4" data-ng-show="ok">
                        <div class="indeterminate amber"></div>
                     </div>
                     <input hidden="true" id="input-hidden" data-ng-model="ok"/>
                  </div>  
               </div>
               <!-- Message -->

               <!-- Modal Success -->
               <div id="modal-success" class="modal amber modal-trigger">            
                  <div class="modal-content white-text">
                     <div class="row">
                        <div class="col s12 m12 l12">
                           <div class="card-panel white">
                              <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                              <h5 class="black-text">
                                 Atividade inclu&iacute;da com sucesso no Cronograma Anual!
                              </h5>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Voltar</a>
                  </div>
               </div>
               <!-- Modal Success update-->
               <div id="modal-success-update" class="modal amber modal-trigger">            
                  <div class="modal-content white-text">
                     <div class="row">
                        <div class="col s12 m12 l12">
                           <div class="card-panel white">
                              <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                              <h5 class="black-text">
                                 Informações atualizadas com sucesso!
                              </h5>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Voltar</a>
                  </div>
               </div>

               <!-- Modal Error -->
               <div id="modal-error" class="modal amber modal-trigger">            
                  <div class="modal-content white-text">
                     <div class="row">
                        <div class="col s12 m12 l12">
                           <div class="card-panel white">
                              <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                              <h5 class="black-text">
                                 Erro ao tentar incluir a atividade ao Cronograma Anual!
                              </h5>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <a href="#!"  class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Voltar</a>
                  </div>
               </div>


            </div>
            <div class="card-content">

               <div class="row">
                  <form class="col s12 l12 m12" name="formCronograma" method="post" id="form-cronograma">
                     <input hidden="true" id="atividadeId" value="${atividade.id}"/>
                     <div class="row">

                        <div class="input-field col s6">        
                           <label for="atividade">Nome da Atividade</label>
                           <input id="atividade" type="text" placeholder="Nome da Atividade" class="validate" data-ng-required="true" data-ng-model="atividade.atividade">
                        </div>
                        <div class="input-field col s6">
                           <input id="total_horas" type="text" class="validate time" data-ng-required="true" placeholder="ex.: 05:00" data-ng-model="atividade.totalHoras">
                           <label for="total_horas">Total de Horas</label>
                        </div>
                     </div>
                     <div class="row">
                        <div class="input-field col s6">
                           <input type="text" id="dataInicio" class="datepicker" placeholder="Data de Início" data-ng-required="true" data-ng-model="atividade.dataInicio">                          
                        </div>
                        <div class="input-field col s6">
                           <input type="text" id="dataFim" class="datepicker " placeholder="Data de Término" data-ng-required="true" data-ng-model="atividade.dataFim">                          
                        </div>
                     </div>

                     <div class="row">
                        <div class="input-field col s12 l6">
                           <input id="totalDias" class="black-text" type="number" readonly="true" disabled data-ng-model="atividade.totalDias" placeholder="Total de dias">                           
                        </div>

                        <div class="col s12 l6">           
                           <label for="status">Status</label>
                           <select id="status" class="browser-default" data-ng-required="true" data-ng-model="atividade.status">                              
                              <option value="">Selecione...</option>
                              <option value="Realizado">Realizado</option>
                              <option value="Não Realizado">N&atilde;o Realizado</option>
                           </select>                           
                        </div>          
                     </div>

                     <div class="row">
                        <div class="input-field col s12">
                           <textarea id="comentario" class="materialize-textarea" placeholder="Comentário" length="120" data-ng-model="atividade.comentario"></textarea>
                           <label for="comentario">Coment&aacute;rio</label>
                        </div>
                     </div>

                     <div class="row card select-group">
                        <div class="col s12 l6 responsavel-field">      
                           <label for="responsavel" class="black-text">Respons&aacute;vel</label>
                           <select class="browser-default" id="responsavel" data-ng-model="gestor.id" data-ng-change="addGestor(gestor)"> 
                              <option value="">Respons&aacute;vel</option>
                              <option data-ng-repeat="gestor in gestores| orderBy:'nome' track by $index" value="{{gestor.id}}" data-ng-bind="gestor.nome"></option>
                           </select>  

                           <div class="chip pratica-chip" data-ng-repeat="gestor in atividade.gestorList"> 
                              <span>
                                 <img data-ng-src="/gerenciador/img/ico-responsavel.png" alt="{{gestor.nome}}">
                                 {{gestor.nome}}
                              </span>
                              <i class="material-icons" data-ng-click="removeGestor(gestor.id)">close</i>
                           </div>

                        </div>   

                        <div class="col s12 l6 pratica-field">
                           <label for="pratica-chave" class="black-text">Pr&aacute;tica-Chave</label>
                           <select id="pratica-chave" class="browser-default" data-ng-model="pratica.id" data-ng-change="addPratica(pratica)">                           
                              <option value="">Pr&aacute;tica-Chave</option>
                              <option data-ng-repeat="pratica in praticas | orderBy:'nome' track by $index" value="{{pratica.id}}" data-ng-bind="pratica.nome"></option>
                           </select>    

                           <div class="chip pratica-chip" data-ng-repeat="pratica in atividade.praticaList"> 
                              <span>{{pratica.nome}}</span>
                              <i class="material-icons" data-ng-click="removePratica(pratica.id)">close</i>
                           </div>
                        </div>
                     </div>                              

                     <a href="<c:url value="/incubadora/cronograma-anual"/>" style="margin-top: 5%; margin-right: 2%;" class="btn-floating btn-small waves-effect waves-light  orange darken-4"><i class="material-icons">undo</i></a>

                     <button type="submit" data-ng-click="sendAtividade(atividade)" style="margin-top: 5%" class="btn-floating btn-small waves-effect waves-light green" data-ng-disabled="!formCronograma.$valid"><i class="material-icons">done</i></button>                                        
                  </form>

               </div>

            </div>
         </div>
      </div>
   </div>
</div>
</div>
