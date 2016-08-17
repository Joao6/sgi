
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="EmpreendedorCtrl">
   <div class="col s12 l8">

      <div class="col s12 m12 l12">
         <div class="card center white">
            <div class="card-content black-text valign-wrapper">
               <i class="material-icons small material-icons">work</i>&nbsp;&nbsp;
               <span class="card-title valign"><strong class="hide-on-small-only">Empreendedor</strong>
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
                        Empreendedor exclu&iacute;do com sucesso!
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
                        Erro ao tentar excluir Empreendedor!
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
            <p>Obs.: os dados deste empreendedor, bem como os dados gerados por ele (ex.: postagem de d&uacute;vidas), ser&atilde;o exclu&iacute;dos
               de todos os locais.</p>
         </div>
         <div class="modal-footer card-action">
            <a href="#!" data-ng-click="removerEmpreendedor(empreendedorIdToDelete)" class="waves-effect waves-orange btn-flat">Excluir</a>
            <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
         </div>
      </div>

      <!--// fim modal //-->

      <!-- // Detalhes // -->
      <ui-info></ui-info>

      <div class="card-panel ember">         
         <div class="card-content">
            <h6 data-ng-show="isEmpreendedorEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o existem empreendedores cadastrados!</strong></h6>
            <table class="bordered">

               <thead data-ng-hide="isEmpreendedorEmpty()">
                  <tr>
                     <th>Nome</th>
                     <th class="hide-on-small-only">E-mail</th>
                     <th></th>
                     <th></th>
                  </tr>
               </thead>


               <tbody>


                  <tr data-ng-repeat="empreendedor in empreendedorList| orderBy:'nome' | paginate:rowsPerPage">
                     <td data-ng-bind="empreendedor.nome"></td>
                     <td class="hide-on-small-only" data-ng-bind="empreendedor.email"></td>
                     <td>                     
                        <a class="btn-floating  yellow accent-5  btn tooltipped" id="btn-detalhes" data-ng-click="openModalDetalhes(empreendedor)" >
                           <i class="tiny mdi-action-search"></i>
                        </a>
                     </td>
                     <td>
                        <a href="#!" class="btn-floating  red btn tooltipped modal-trigger btn-excluir"  id="btn-excluir-{{empreendedor.id}}" data-position="top" data-delay="50" data-tooltip="Excluir">
                           <i class="mdi-navigation-close"></i>
                        </a>
               <ui-alert-empreendedor></ui-alert-empreendedor>
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