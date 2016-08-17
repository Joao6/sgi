<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="PainelCtrl">

   <div class="col s12 l8">

      <div class="col s12 m12 l12">
         <div class="card center white">
            <div class="card-content black-text valign-wrapper">
               <i class="material-icons small">event_note</i>&nbsp;&nbsp;
               <span class="card-title valign"><strong class="hide-on-small-only">Cronograma Anual</strong> &nbsp;<span style="color: #9F9F9F;" class="hide-on-small-only">|</span>&nbsp; Atividade &nbsp; </span>
               <a class="btn-floating waves-effect waves-light blue" href="<c:url value='/cronograma-anual/novo'/>"><i class="material-icons">add</i></a>
               <span class="valign hide-on-small-only" style="width: 40%; margin-left: 8%;">
                  <small class="truncate">Digite um crit&eacute;rio para a busca.<br/> Ex.: fulano da silva; não realizado; Semana da FAI.</small>
               <div class="input-field col s12">
                  <i class="material-icons prefix">search</i>
                  <input id="icon_prefix" type="text" class="validate" placeholder="ex.: FAITEC" data-ng-model="criterioDeBusca">
                  
               </div>
            </span>
            </div>
         </div>
      </div>

      <!-- Modal Success -->
      <div id="modal-success" class="modal amber">            
         <div class="modal-content white-text">
            <div class="row">
               <div class="col s12 m12 l12">
                  <div class="card-panel white">
                     <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                     <h5 class="black-text">
                        Atividade exclu&iacute;da com sucesso!
                     </h5>
                  </div>
               </div>
            </div>
         </div>
         <div class="modal-footer">
            <a href="#!"  class=" modal-action modal-close waves-effect waves-green btn-flat">Voltar</a>
         </div>
      </div>

      <!-- Modal Error -->
      <div id="modal-error" class="modal amber">            
         <div class="modal-content white-text">
            <div class="row">
               <div class="col s12 m12 l12">
                  <div class="card-panel white">
                     <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                     <h5 class="black-text">
                        Erro ao tentar excluir atividade ao Cronograma Anual!
                     </h5>
                  </div>
               </div>
            </div>
         </div>
         <div class="modal-footer">
            <a href="#!"  class=" modal-action modal-close waves-effect waves-green btn-flat">Voltar</a>
         </div>
      </div>

      <!-- Modal View detalhes -->
      <div id="modal-detalhes" class="modal amber hide-on-small-only" style="border-radius: 0px !important; height: auto !important; overflow: auto">            
         <div class="modal-content white-text" style="border-radius: 0px !important">
            <div class="row">
               <div class="col s12 m12 l12">
                  <div class="card-panel white">
                     <div class="row">                        
                        <article class="black-text col s12 m12 l12 card" style="padding: 1%; font-size: 11pt" data-ng-if="atividade.comentario.length > 0">
                           <h5 class="green-text">Coment&aacute;rio</h5>
                           <blockquote data-ng-bind="atividade.comentario"></blockquote>                           
                        </article>                            
                     </div>     
                     <div class="row">
                        <article class="black-text col s12 m6 l6 card" data-ng-if="atividade.gestorList.length > 0">
                           <h6 class="green-text">Respons&aacute;veis</h6>
                           <ul class="collection">                              
                              <li class="collection-item valign-wrapper" data-ng-repeat="gestor in atividade.gestorList"><img class="responsive-img circle valign" width="27" height="27" data-ng-src="/gerenciador/img/ico-responsavel.png"/> <span class="valign" style="margin-left: 5%; font-size: 13pt">{{gestor.nome}}</span></li>                              
                           </ul>
                        </article>   
                        <article class="black-text col s12 m6 l6 card" data-ng-if="atividade.praticaList.length > 0">
                           <h6 class="green-text">Pr&aacute;ticas-Chave</h6>
                           <ul class="collection">
                              <li class="collection-item" data-ng-repeat="pratica in atividade.praticaList" data-ng-bind="pratica.nome"></li>                              
                           </ul>
                        </article>   
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <div class="modal-footer">
            <a href="#!"  class=" modal-action modal-close waves-effect waves-green btn-flat">Fechar</a>
         </div>
      </div>          

      <!-- Modal Excluir  -->
      <div class="modal alert-modal-excluir card-panel">
         <div class="modal-content">
            <h3 class="valign-wrapper" style="font-size: 25pt"><i class="material-icons valign" style="margin-right:2%; font-size: 25pt !important">warning</i> <span class="valign">Alerta de Exclus&atilde;o</span></h3>
            <h5>Esta opera&ccedil;&atilde;o n&atilde;o poder&aacute; ser desfeita posteriormente.</h5>          
         </div>
         <div class="modal-footer card-action">
            <a href="#!" data-ng-click="remover(atividadeIdToDelete)" class="waves-effect waves-orange btn-flat">Excluir</a>
            <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
         </div>
      </div>

      <!--// fim modal //-->

      <div class="card-panel ember">         
         <div class="card-content">
            <h6 data-ng-show="isAtividadeEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o existem atividades cadastradas!</strong></h6>
            <table class="bordered">

               <thead data-ng-hide="isAtividadeEmpty()">
                  <tr>
                     <th>Atividade</th>
                     <th class="hide-on-small-only">Status</th>
                     <th class="hide-on-small-only"><span class="hide-on-med-and-down">Data de</span> In&iacute;cio</th>
                     <th class="hide-on-small-only"><span class="hide-on-med-and-down">Data de</span> T&eacute;rmino</th>
                     <th></th>
                     <th></th>
                     <th></th>
                  </tr>
               </thead>

               <tbody>


                  <tr data-ng-repeat="atividade in atividades| orderBy:dataInicio |filter:criterioDeBusca | paginate:rowsPerPage" class="hover" data-ng-class="{realizada: isRealizada(atividade)}">
                     <td>{{atividade.atividade}}</td>
                     <td class="hide-on-small-only">{{atividade.status}}</td>
                     <td class="hide-on-small-only">{{atividade.dataInicio| date:'dd/MM/yyyy'}}</td>
                     <td class="hide-on-small-only">{{atividade.dataFim| date:'dd/MM/yyyy'}}</td>
                     <td>                     
                        <a href="#modal-detalhes" id="btn-detalhes" data-ng-click="openModalDetalhes(atividade)" class="btn-floating  yellow accent-5 btn tooltipped" data-position="top"  data-tooltip="Detalhes">
                           <i class="tiny material-icons">zoom_in</i>
                        </a>
                     </td>
                     <td>
                        <a data-ng-href="/gerenciador/cronograma-anual/atividade/{{atividade.id}}/alterar" class="btn-floating  blue btn tooltipped" data-position="top" data-delay="50" data-tooltip="Alterar">
                           <i class="material-icons">mode_edit</i>
                        </a>
                     </td>
                     <td>
                        <a href="#!" class="btn-floating btn-excluir red btn tooltipped modal-trigger" id="btn-excluir-{{atividade.id}}" data-position="top" data-delay="50" data-tooltip="Excluir">
                           <i class="material-icons">clear</i>
                        </a>

                     </td>

                  </tr>


                  <c:if test="${atividadeList = null}">
                  <div class="card-panel">
                     <div class="card-title">
                        <p>N&atilde;o H&aacute; nenhuma Atividade cadastrada.</p>
                     </div>
                     <div class="card-content">

                     </div>
                  </div>
               </c:if>

               </tbody>
               <paginator class="pagination"></paginator>              
            </table>    
            <script type="text/ng-template" id="paginationControl.html">
               <ul class="pagination center"  data-ng-show="paginator.pageCount() > 1">
               <li data-ng-click="paginator.firstPage()" ng-class="paginator.isFirstPage() && 'disabled'" class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
               <li data-ng-click="paginator.setPage(i)" ng-repeat="i in [] | forLoop:paginator.lowerLimit():paginator.pageCount() | limitTo : paginator.limitPerPage" data-ng-class="paginator.page==i && 'active'">
               <a href="#!">
               {{i+1}}
               </a>
               </li>
               <li data-ng-click="paginator.perviousPage()" ng-class="paginator.isFirstPage() && 'disabled'" class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
               </ul>
            </script>
         </div>
      </div>
   </div>
</div>