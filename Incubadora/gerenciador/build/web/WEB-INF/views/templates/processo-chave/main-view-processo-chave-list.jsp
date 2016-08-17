
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="ProcessoChaveCtrl">

   <div class="col s12 l8">

      <div class="col s12 m12 l12">
         <div class="card center white valign-wrapper">
            <div class="card-content black-text valign-wrapper">
               <i class="material-icons small">poll</i>&nbsp;&nbsp;
               <span class="card-title valign"><strong>Processo-Chave</strong> &nbsp;</span>
               <a href="#modal-cad-processo" class="btn-floating waves-effect waves-light blue modal-trigger" ><i class="material-icons">add</i></a>
            </div>
            <span class="valign hide-on-small-only" style="width: 40%; margin-left: 8%;">
               <div class="input-field col s12">
                  <i class="material-icons prefix">search</i>
                  <input id="icon_prefix" type="text" class="validate" data-ng-model="criterioDeBusca">
                  <label for="icon_prefix">Digite o nome do Processo-Chave</label>
               </div>
            </span>
         </div>
      </div>

      <!-- Modal Cadastro -->
      <div id="modal-cad-processo" class="modal amber">            
         <div class="modal-content white-text">
            <div class="row">
               <div class="col s12 m12 l12">

                  <div class="card-panel white">
                     <h5 class="card-title black-text valign-wrapper"><i class="material-icons small valign">poll</i> <span class="truncate"><span class="hide-on-small-only"> Cadastro de</span> Processo-Chave</span></h5>
                     <div class="divider"></div>
                     <form>
                        <div class="row">
                           <div class="col s12 m12 l12 input-field">
                              <input id="nome" type="text" data-ng-model="processoChave.nome"/>
                              <label for="nome" class="truncate">Nome do Processo-Chave</label>
                           </div>

                           <button class="btn-floating circle green right"  data-ng-click="addProcessoChave(processoChave)"><i class="material-icons" style="margin-right: 20px">done</i></button>
                           <button class="btn-floating circle orange modal-close right" data-ng-click="clearForm()"><i class="material-icons">undo</i></button>&nbsp;&nbsp;
                        </div>                      
                     </form>
                  </div>

               </div>
            </div>
         </div>
      </div>

      <!-- Modal Excluir  -->
      <div class="modal alert-modal-excluir card-panel">
         <div class="modal-content">
            <h3 class="valign-wrapper" style="font-size: 25pt"><i class="material-icons valign" style="margin-right:2%; font-size: 25pt !important">warning</i> <span class="valign">Alerta de Exclus&atilde;o</span></h3>
            <h5>Esta opera&ccedil;&atilde;o n&atilde;o poder&aacute; ser desfeita posteriormente.</h5>
            <p>Obs.: o processo-chave ser&aacute; exclu&iacute;do de todos os locais onde foi utilizado e tam&eacute;m o arquivo anexado a ele.</p>
         </div>
         <div class="modal-footer card-action">
            <a href="#!" data-ng-click="deleteProcessoChave(processoIdToDelete)" class="waves-effect waves-orange btn-flat">Excluir</a>
            <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
         </div>
      </div>

      <!--// fim modal //-->

      <!-- // Modal Anexo // -->

      <div id="modal-anexo" class="modal amber">            
         <div class="modal-content white-text">
            <div class="row">
               <div class="col s12 m12 l12">

                  <div class="card-panel white" style="margin-top: 1.8rem">
                     <h5 class="card-title black-text valign-wrapper"><i class="material-icons small valign">poll</i> <span class="truncate"><span class="hide-on-small-only"> Anexar arquivo ao Processo-Chave</span></h5>
                     <div class="divider"></div>

                     <div class="row black-text">
                        <form method="POST" id="form-anexo" action="<c:url value="/processo-chave/anexo"/>" enctype="multipart/form-data">
                           <div class="file-field input-field">
                              <div class="btn btn-floating circle blue" style="width: 37px !important; height: 37px !important" >
                                 <i class="material-icons">backup</i>
                                 <input type="file" id="arquivo" name="arquivo">                                 
                                 <input hidden="true" name="processoId" value="{{processoSelected}}"/>
                              </div>

                              <div class="file-path-wrapper">
                                 <input class="file-path validate" type="text">
                              </div>
                           </div>
                           <small class="grey-text darken-1" style="margin-left: 10%; font-size: 10pt">Obs.: carregue apenas arquivos PDF. O tamanho m&aacute;ximo suportado é 25MB.</small>



                           <button type="submit" class="btn-floating circle green right"><i class="material-icons" style="margin-right: 20px">done</i></button>
                           <button class="btn-floating circle orange modal-close right" data-ng-click="clearForm()"><i class="material-icons">undo</i></button>&nbsp;&nbsp;
                        </form>
                     </div>                      

                  </div>

               </div>
            </div>
         </div>
      </div>
      <!-- // ----------- // --->


      <!-- // Modal Detail // -->

      <div class="modal amber alert-modal-detail">            
         <div class="modal-content white-text">
            <div class="row">
               <div class="col s12 m12 l12">

                  <div class="card-panel white" style="margin-top: 1.8rem">
                     <h5 class="card-title black-text valign-wrapper" style="font-size: 14pt"><i class="material-icons small valign">poll</i> <span class="truncate"><span class="hide-on-small-only">Processo-Chave: <strong id="processo-detalhes"></strong></span></span></h5>
                     <div class="divider"></div>

                     <div class="row black-text">                      
                        <ul class="collection s12 m12 l12 white black-text">
                           <li class="collection-item white">                             
                              <div class="valign-wrapper" id="arq-anexo-content" ><i class="material-icons valign">insert_drive_file</i><span class="valign">Arquivo anexado: &nbsp;</span><a id="linkAnexo" href="#!" target="_blank" class="secondary-content valign"> <span id="nameFile" class="truncate"> </span></a> </div>
                           </li>
                           <li class="collection-item white" style="text-align: center !important">
                              <a href="#!" id="praticasChave" class="btn blue waves-effect waves-blue center white-text">Ver Pr&aacute;ticas-Chave cadastradas</a>
                           </li>
                        </ul>
          
                        <button class="btn-floating circle orange modal-close right" data-ng-click="clearForm()"><i class="material-icons">undo</i></button>&nbsp;&nbsp;

                     </div>                      

                  </div>

               </div>
            </div>
         </div>
      </div>
      <!-- // ----------- // --->


      <div class="card-panel ember">         
         <div class="card-content">
            <h6 data-ng-show="isProcessosChaveEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o existem Processos-Chave cadastrados!</strong></h6>
            <form action="#">              
               <table class="bordered">

                  <thead data-ng-hide="isProcessosChaveEmpty()">
                     <tr>                        
                        <th><a href="#!" data-ng-click="setCriterio('nome')">Nome</a></th>
                        <th class="center">cad. Pr&aacute;tica-Chave</th>
                        <th class="center hide-on-small-only"><a href="#!" data-ng-click="setCriterio('anexo')">Anexo</a></th>
                        <th class="center hide-on-small-only">
                           Visualizar
                        </th>
                        <th class="hide-on-small-only center">
                           Alterar
                        </th>
                        <th class="center">
                           Excluir
                        </th>
                     </tr>
                  </thead>

                  <tbody>

                     <tr  data-ng-repeat="processoChave in processosChave| orderBy:'id'| filter:criterioDeBusca | paginate:rowsPerPage">
                        <td>
                           <p style="padding: 0px; margin:0px" data-ng-hide="processoChave.show" class="truncate" data-ng-bind="processoChave.nome | truncate:30"></p>
                           <!-- //Edit // -->
                           <div class="card col s12 m12 l12 white darken-2 hide-on-small-only" data-ng-if="processoChave.show" style="padding-bottom: 1%; margin:0px !important; border-radius: 0px !important" id="edit-{{processoChave.id}}">                              
                              <div class="row">
                                 <input type="text" style="margin-bottom: 0px !important; " id="input-edit" class="blue-tex col l10 s10 m10" data-ng-model="processoChave.nome" maxlength="40"/>
                                 <button type="button" style="width: 27px; height: 27px; margin-top: 8% !important;"  class="btn-floating right green black-text" data-ng-click="updateProcessoChave(processoChave)"><i class="material-icons white-text" style="font-size: 18px; margin: -10px !important">done</i></button>
                              </div>
                           </div>
                        </td>
                        <td class="center">
                           <a data-ng-href="/gerenciador/processo-chave/{{processoChave.id}}/pratica-chave/add" class="btn-floating blue">
                              <i class="material-icons">add</i>
                           </a>
                        </td>
                        <td class="center hide-on-small-only">
                           <div class="file-field">
                              <a href="#modal-anexo" data-ng-click="showModalAnexo(processoChave.id)" class="btn-floating waves-effect modal-trigger waves-grey lighten-0" data-ng-class="{'purple':hasAnexo(processoChave.id), 'grey':!hasAnexo(processoChave.id)}">
                                 <span><i class="material-icons">&#xE2BC;</i></span>

                              </a>                        
                           </div>                        

                        </td>
                        <td class="center hide-on-small-only">
                           <a href="#!" class="btn-floating btn-detail yellow accent-5  btn tooltipped" id="btn-detail-{{processoChave.id}}" data-position="top" data-delay="50" data-tooltip="Detalhes">
                              <i class="tiny material-icons">zoom_in</i>
                           </a>
                        </td>
                        <td class="hide-on-small-only center">
                           <a class="btn-floating  blue btn tooltipped" data-ng-click="showProcesso(processoChave)" data-position="top" data-delay="50" data-tooltip="Alterar">
                              <i class="material-icons">mode_edit</i>
                           </a>                           
                        </td>
                        <td class="center">
                           <a href="#!" class="btn-floating btn-excluir red btn tooltipped modal-trigger" id="btn-excluir-{{processoChave.id}}" data-position="top" data-delay="50" data-tooltip="Excluir">
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
            </form>


         </div>
      </div>
   </div>
</div>