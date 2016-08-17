
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal">

   <div class="col s12 l8" data-ng-controller="GestaoIntefCtrl">

      <div class="col s12 m12 l12">
         <div class="card center white">
            <div class="card-content black-text valign-wrapper">
               <i class="mdi-action-supervisor-account small valign"></i>&nbsp;&nbsp;
               <span class="card-title"><strong class="hide-on-small-only">Gest&atilde;o Intef</strong> &nbsp;<span style="color: #9F9F9F" class="hide-on-small-only">|</span>&nbsp; Gestor &nbsp; </span>
               <a class="btn-floating waves-effect waves-light blue" data-ng-click="setCreateMode()" href="<c:url value='/incubadora/novo'/>"><i class="mdi-content-add"></i></a>
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
                        Gestor exclu&iacute;do com sucesso!
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
                        Erro ao tentar excluir Gestor!
                     </h5>
                  </div>
               </div>
            </div>
         </div>
         <div class="modal-footer">
            <a href="#!"  class=" modal-action modal-close waves-effect waves-green btn-flat">Voltar</a>
         </div>
      </div>

      <!-- Modal Structure -->
      <div class="modal alert-modal-excluir card-panel">
         <div class="modal-content">
            <h3 class="valign-wrapper" style="font-size: 25pt"><i class="material-icons valign" style="margin-right:2%; font-size: 25pt !important">warning</i> <span class="valign">Alerta de Exclus&atilde;o</span></h3>
            <h5>Esta opera&ccedil;&atilde;o n&atilde;o poder&aacute; ser desfeita posteriormente.</h5>
            <p>Obs.: as respostas geradas por este Gestor na seção Dúvidas serão apagadas.</p>
         </div>
         <div class="modal-footer card-action">
            <a href="#!" data-ng-click="removerGestor(gestorIdToDelete)" class="waves-effect waves-orange btn-flat">Excluir</a>
            <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
         </div>
      </div>

      <!--// fim modal //-->


      <!-- Modal View detalhes -->
      <div id="modal-detalhes" class="modal amber hide-on-small-only" style="border-radius: 0px !important; height: auto !important; overflow: hidden">            
         <div class="modal-content white-text" style="border-radius: 0px !important">
            <div class="row">
               <div class="col s12 m12 l12">
                  <div class="card-panel white">

                     <table class="black-text table-condensed table-striped">
                        <thead class="blue-text">
                           <tr>
                              <th data-field="id">Nome</th>
                              <th data-field="name">Sobrenome</th>
                              <th data-field="cpf">CPF</th>
                           </tr>
                        </thead>

                        <tbody>
                           <tr>
                              <td data-ng-bind="gestor.nome"></td>
                              <td data-ng-bind="gestor.sobrenome"></td>
                              <td data-ng-bind="gestor.cpf"></td>
                           </tr>                          
                        </tbody>

                        <thead class="blue-text">
                           <tr>
                              <th data-field="telefone">Telefone</th>
                              <th></th>
                              <th data-field="E-mail">E-mail</th>
                           </tr>
                        </thead>

                        <tbody>
                           <tr>
                              <td data-ng-bind="gestor.telefone"></td>
                              <td></td>
                              <td data-ng-bind="gestor.email"></td>
                           </tr>                          
                        </tbody>
                     </table>
                  </div>
               </div>
            </div>
         </div>
         <div class="modal-footer">
            <a href="#!"  class=" modal-action modal-close waves-effect waves-green btn-flat">Fechar</a>
         </div>
      </div>



      <div class="card-panel ember">         
         <div class="card-content">
            <h6 data-ng-show="isGestorEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o existem gestores cadastrados!</strong></h6>          

            <table class="bordered">

               <thead data-ng-hide="isGestorEmpty()">
                  <tr>
                     <th>Nome</th>
                     <th class="hide-on-small-only">E-mail</th>
                     <th class="hide-on-small-only"></th>
                     <th></th>
                     <th></th>
                  </tr>
               </thead>


               <tbody>


                  <tr data-ng-repeat="gestor in _gestorList| orderBy:'nome' | paginate:rowsPerPage">
                     <!--// Modal de alerta de exclusão // -->

                     <td data-ng-bind="gestor.nome"></td>
                     <td class="hide-on-small-only" data-ng-bind="gestor.email"></td>
                     <td class="hide-on-small-only">                     
                        <a href="#modal-detalhes" id="btn-detalhes" data-ng-click="openModalDetalhes(gestor)" class="btn-floating  yellow accent-5 btn tooltipped" data-position="top"  data-tooltip="Detalhes">
                           <i class="tiny material-icons">zoom_in</i>
                        </a>
                     </td>
                     <td>
                        <a data-ng-href="/gerenciador/gestor/{{gestor.id}}/atualizar" class="btn-floating  blue btn btn-editar tooltipped" data-position="top" data-delay="50" data-tooltip="Alterar">
                           <i class="mdi-editor-mode-edit"></i>
                        </a>
                     </td>
                     <td>

                        <a href="#!" class="btn-floating  red btn-excluir btn tooltipped modal-trigger" id="btn-excluir-{{gestor.id}}" data-position="top" data-delay="50" data-tooltip="Excluir" >
                           <i class="mdi-navigation-close"></i>
                        </a>            

                     </td>   

                  </tr>

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