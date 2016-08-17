
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="PraticaChaveCtrl">

   <div class="col s12 l8">

      <div class="col s12 m12 l12">
         <div class="card center white">
            <div class="card-content black-text valign-wrapper">
               <i class="material-icons small">poll</i>&nbsp;&nbsp;
               <span class="card-title valign"><strong>Práticas-Chave</strong> | <span data-ng-bind="processoChave.nome"></span> &nbsp;<span style="color: #9F9F9F;" </span>
            </div>
         </div>
      </div>

      <!-- Modal Excluir  -->
      <div class="modal alert-modal-excluir card-panel">
         <div class="modal-content">
            <h3 class="valign-wrapper" style="font-size: 25pt"><i class="material-icons valign" style="margin-right:2%; font-size: 25pt !important">warning</i> <span class="valign">Alerta de Exclus&atilde;o</span></h3>
            <h5>Esta opera&ccedil;&atilde;o n&atilde;o poder&aacute; ser desfeita posteriormente.</h5>
            <p>Obs.: a pr&aacute;tica-chave ser&aacute; exclu&iacute;da de todos os locais onde foi utilizada e tam&eacute;m ser&atilde;o exclu&iacute;dos os arquivos anexados &agrave; ela.</p>
         </div>
         <div class="modal-footer card-action">
            <a href="#!" data-ng-click="deletePraticaChave(praticaIdToDelete)" class="waves-effect waves-orange btn-flat">Excluir</a>
            <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
         </div>
      </div>

      <!--// fim modal //-->

      <!-- // Modal Detail // -->

      <div class="modal amber alert-modal-detail">            
         <div class="modal-content white-text">
            <div class="row">
               <div class="col s12 m12 l12">

                  <div class="card-panel white" style="margin-top: 1.8rem">
                     <h5 class="card-title black-text valign-wrapper" style="font-size: 14pt"><i class="material-icons small valign">poll</i> <span class="truncate"><span class="hide-on-small-only">Prática-Chave: <strong id="pratica-detalhes"></strong></span></span></h5>
                     <div class="divider"></div>

                     <div class="row black-text">                      
                        <table class="tab-content bordered">                          
                           <tr>
                              <th class="blue-text">Est&aacute;gio de Evolu&ccedil;&atilde;o</th>
                              <td class="center-align" id="estagioEvolucao"></td>
                           </tr>
                           <tr>
                              <th class="blue-text">Data de Evolu&ccedil;&atilde;o</th>
                              <td class="center-align" id="dataEvolucao"></td>
                           </tr>
                           <tr>
                              <th class="blue-text">Data de Certifica&ccedil;&atilde;o</th>
                              <td class="center-align" id="dataCertificacao"></td>
                           </tr>
                           <tr>
                              <th class="blue-text">Descri&ccedil;&atilde;o</th>
                              <td id="descricao"></td>
                           </tr>                          
                        </table>

                        <a href="#!" id="linkAnexos" class="center btn blue center">Ver Anexos</a>


                        <button class="btn-floating circle orange modal-close right" data-ng-click="clearForm()"><i class="material-icons">undo</i></button>&nbsp;&nbsp;

                     </div>                      

                  </div>

               </div>
            </div>
         </div>
      </div>
      <!-- // ----------- // --->


      <!-- // Modal Anexo // -->

      <div id="modal-anexo" class="modal amber">            
         <div class="modal-content white-text">
            <div class="row">
               <div class="col s12 m12 l12">

                  <div class="card-panel white" style="margin-top: 1.8rem">
                     <h5 class="card-title black-text valign-wrapper"><i class="material-icons small valign">poll</i> <span class="truncate"><span class="hide-on-small-only"> Anexar arquivo &agrave; Pr&aacute;tica-Chave.</span></h5>
                     <div class="divider"></div>

                     <div class="row black-text">
                        <form method="POST" id="form-anexo" action="<c:url value="/pratica-chave/anexo"/>" enctype="multipart/form-data">
                           <div class="file-field input-field">
                              <div class="btn btn-floating circle blue" style="width: 37px !important; height: 37px !important" >
                                 <i class="material-icons">backup</i>
                                 <input type="file" id="arquivo" name="arquivo">                                 
                                 <input hidden="true" name="praticaId" value="{{praticaSelected}}"/>
                                 <input hidden="true" name="processoId" value="{{processoChave.id}}"/>
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



      <div class="card-panel ember">         
         <div class="card-content">
            <h6 data-ng-show="isPraticasChaveEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o existem Pr&Aacute;tica-Chave cadastradas!</strong></h6>
            <form action="#">
               <table class="bordered">

                  <thead data-ng-hide="isPraticasChaveEmpty()">
                     <tr>
                        <th>Nome</th>
                        <th class="center hide-on-small-only">Anexo</th>
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

                     <tr  data-ng-repeat="praticaChave in praticasChave| orderBy:'nome' | paginate:rowsPerPage">
                        <td>
                           <p style="padding: 0px; margin:0px" data-ng-hide="praticaChave.show" class="truncate">{{praticaChave.nome| truncate:30}}</p>
                           <!-- //Edit // -->
                        </td>

                        <td class="center hide-on-small-only">
                           <div class="file-field">
                              <a href="#modal-anexo" data-ng-click="showModalAnexo(praticaChave.id)" class="btn-floating waves-effect modal-trigger waves-grey lighten-0" data-ng-class="{'purple':hasAnexo(praticaChave.id), 'grey':!hasAnexo(praticaChave.id)}">
                                 <span><i class="material-icons">&#xE2BC;</i></span>
                              </a>                        
                           </div>                        
                        </td>
                        <td class="center hide-on-small-only">
                           <a href="#!" class="btn-floating btn-detail yellow accent-5 btn tooltipped" id="btn-detail-{{praticaChave.id}}" data-position="top" data-delay="50" data-tooltip="Detalhes">
                              <i class="tiny material-icons">zoom_in</i>
                           </a>
                        </td>
                        <td class="hide-on-small-only center">
                           <a class="btn-floating  blue btn tooltipped" data-ng-href="/gerenciador/pratica-chave/{{praticaChave.id}}/edit" data-position="top" data-delay="50" data-tooltip="Alterar">
                              <i class="material-icons">mode_edit</i>
                           </a>                           
                        </td>
                        <td class="center">
                           <a href="#!" class="btn-floating btn-excluir red btn tooltipped modal-trigger" id="btn-excluir-{{praticaChave.id}}" data-position="top" data-delay="50" data-tooltip="Excluir">
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