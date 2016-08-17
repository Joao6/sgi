
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="AvaliadorCtrl">

   <div class="col s12 l8">

      <div class="col s12 m12 l12">
         <div class="card center white">
            <div class="card-content black-text valign-wrapper">
               <i class="material-icons small">gavel</i>&nbsp;&nbsp;
               <span class="card-title valign"><strong class="hide-on-small-only">Avaliador</strong> &nbsp;<span style="color: #9F9F9F" class="hide-on-small-only">|</span>&nbsp; Adicionar &nbsp; </span>
               <a class="btn-floating waves-effect waves-light blue" href="<c:url value='/avaliador/novo'/>"><i class="material-icons">add</i></a>
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
                        Avaliador exclu&iacute;do com sucesso!
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
                        Erro ao tentar excluir Avaliador!
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
            <p>Obs.: os dados gerados por esse avaliador também serão exclu&iacute;dos.</p>
         </div>
         <div class="modal-footer card-action">
            <a href="#!" data-ng-click="removerAvaliador(avaliadorIdToDelete)" class="waves-effect waves-orange btn-flat">Excluir</a>
            <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
         </div>
      </div>

      <!--// fim modal //-->
      <!-- Modal View detalhes -->
      <div id="modal-detalhes" class="modal amber hide-on-small-only" style="border-radius: 0px !important; height: auto !important; overflow: auto">            
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
                              <td data-ng-bind="avaliador.nome"></td>
                              <td data-ng-bind="avaliador.sobrenome"></td>
                              <td data-ng-bind="avaliador.cpf"></td>
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
                              <td data-ng-bind="avaliador.telefone"></td>
                              <td></td>
                              <td data-ng-bind="avaliador.email"></td>
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
            <h6 data-ng-show="isAvaliadoresEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o existem avaliadores cadastrados!</strong></h6>
            <table class="bordered">

               <thead data-ng-hide="isAvaliadoresEmpty()">
                  <tr>
                     <th>Nome</th>
                     <th class="hide-on-small-only">E-mail</th>
                     <th></th>
                     <th></th>
                     <th></th>
                  </tr>
               </thead>


               <tbody>


                  <tr data-ng-repeat="avaliador in avaliadorList| paginate:rowsPerPage">
                     <td>{{avaliador.nome}}</td>
                     <td class="hide-on-small-only">{{avaliador.email}}</td>
                     <td>                     
                        <a href="#!" class="btn-floating btn-detail yellow accent-5  btn tooltipped"  data-ng-click="openModalDetalhes(avaliador)"data-position="top" data-delay="50" data-tooltip="Detalhes">
                           <i class="tiny material-icons">zoom_in</i>
                        </a>
                     </td>
                     <td>
                        <a data-ng-href="/gerenciador/avaliador/{{avaliador.id}}/atualizar" class="btn-floating  blue btn btn-editar tooltipped" data-position="top" data-delay="50" data-tooltip="Alterar">
                           <i class="material-icons">edit</i>
                        </a>
                     </td>
                     <td>

                        <a href="#!" class="btn-floating  btn-excluir  red btn tooltipped modal-trigger"  id="btn-excluir-{{avaliador.id}}" data-position="top" data-delay="50" data-tooltip="Excluir">
                           <i class="material-icons">clear</i>
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