
<!-- Área Principal -->
<div class="area-principal" data-ng-controller="EixoCtrl">

    <div class="col s12 l8">      

        <div class="col s12 m12 l12">
            <div class="card center white">
                <div class="card-content black-text valign-wrapper">
                    <i class="mdi-action-description small"></i>&nbsp;&nbsp;
                    <span class="card-title valign"><strong class="hide-on-small-only">Modelo de Avalia&ccedil;&atilde;o</strong> 
                        <a class="btn-floating waves-effect waves-light blue" data-ng-click="openModal(2)" href="#!"><i class="material-icons">add</i></a>
                </div>
            </div>
        </div>

        <!-- MODAL EXCLUIR EIXO -->
        <div class="modal card-panel" id="modal-1">
            <div class="modal-content">
                <h3 class="valign-wrapper" style="font-size: 25pt"><i class="material-icons valign" style="margin-right:2%; font-size: 25pt !important">warning</i> 
                    <span class="valign">Alerta de Exclus&atilde;o</span>
                </h3>
                <h5>Esta opera&ccedil;&atilde;o n&atilde;o poder&aacute; ser desfeita posteriormente.</h5>
            </div>
            <div class="modal-footer card-action">
                <a href="#!" data-ng-click="delete(eixoIdToDelete)" class="waves-effect waves-orange btn-flat">Excluir</a>
                <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
            </div>
        </div>
        <!--// FIM MODAL //-->
        
        <!-- MODAL EXCLUIR CRITERIO -->
        <div class="modal card-panel" id="modal-7">
            <div class="modal-content">
                <h3 class="valign-wrapper" style="font-size: 25pt"><i class="material-icons valign" style="margin-right:2%; font-size: 25pt !important">warning</i> 
                    <span class="valign">Alerta de Exclus&atilde;o</span>
                </h3>
                <h5>Esta opera&ccedil;&atilde;o n&atilde;o poder&aacute; ser desfeita posteriormente.</h5>
            </div>
            <div class="modal-footer card-action">
                <a href="#!" data-ng-click="deleteCriterio(criterioIdToDelete)" class="waves-effect waves-orange btn-flat">Excluir</a>
                <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
            </div>
        </div>
        <!--// FIM MODAL //-->

        <!-- MODAL NOVO EIXO -->
        <div class="modal" style="background-color: #FFF !important" id="modal-2">
            <h6 class="card card-panel">Novo Eixo</h6>
            <div class="modal-body card white">
                <form name="formEixo">
                    <div class="row">
                        <div class="s12 m6 l6 input-field" style="padding-left: 2%; padding-right: 2%">
                            <label for="nome">Nome</label>
                            <input id="nome" type="text"  data-ng-required="true" data-ng-model="eixo.nome" placeholder=" " />
                        </div>
                        <div class="s12 m6 l6 input-field" style="padding-left: 2%; padding-right: 2%">
                            <label for="peso">Peso</label>
                            <input id="peso" type="text" class="number"  data-ng-required="true" data-ng-model="eixo.peso" placeholder="ex.: 3,5" />
                        </div>                       
                    </div>
                </form>
            </div>
            <div class="row">
                <button class="btn green col s12 m12 l12" style="border-radius: 0px" data-ng-disabled="!formEixo.$valid" data-ng-click="addEixo(eixo)">Salvar</button>
                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
            </div>                  
        </div>
        <!-- FIM MODAL NOVO EIXO -->       

        <!-- MODAL EDITAR EIXO -->
        <div class="modal" style="background-color: #FFF !important" id="modal-3">
            <h6 class="card card-panel">Novo Eixo</h6>
            <div class="modal-body card white">
                <form name="formEixo">
                    <div class="row">
                        <div class="s12 m6 l6 input-field" style="padding-left: 2%; padding-right: 2%">
                            <label for="nome">Nome</label>
                            <input id="nomeEixo" type="text"  data-ng-required="true" data-ng-model="eixo.nome" placeholder=" " />
                        </div>
                        <div class="s12 m6 l6 input-field" style="padding-left: 2%; padding-right: 2%">
                            <label for="peso">Peso</label>
                            <input id="pesoEixo" type="text" class="number"  data-ng-required="true" data-ng-model="eixo.peso" placeholder="ex.: 3,5" />
                        </div>                       
                    </div>
                </form>
            </div>
            <div class="row">
                <button class="btn green col s12 m12 l12" style="border-radius: 0px" data-ng-disabled="!formEixo.$valid" data-ng-click="editEixo(eixo)">Salvar</button>
                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
            </div>                         
        </div>
        <!-- FIM MODAL EDITAR EIXO -->  

        <!-- MODAL EDITAR CRITERIO-->
        <div class="modal" style="background-color: #FFF !important" id="modal-criterio-edit">
            <h6 class="card card-panel">Alterar Informações</h6>
            <div class="modal-body card white">
                <form name="formCriterio">
                    <div class="row">
                        <div class="s12 m6 l6 input-field" style="padding-left: 2%; padding-right: 2%">
                            <label for="nomeCriterio">Nome</label>
                            <input id="nomeCriterio" type="text"  data-ng-required="true" data-ng-model="criterio.nome" placeholder=" " />
                        </div>                                     
                    </div>
                </form>
            </div>
            <div class="row">
                <button class="btn green col s12 m12 l12" style="border-radius: 0px" data-ng-click="saveCriterio(criterio)">Salvar</button>
                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
            </div>                         
        </div>
        <!-- FIM MODAL EDITAR CRITERIO -->  

        <!-- MODAL ADD CRITÉRIO -->
        <div class="modal" style="background-color: #FFF !important" id="modal-4">
            <h6 class="card card-panel">Novo Critério</h6>
            <div class="modal-body card white">
                <form name="formCriterio">
                    <div class="row">
                        <div class="s12 m6 l6 input-field" style="padding-left: 2%; padding-right: 2%">
                            <label for="nome">Nome</label>
                            <input id="nomeEixo" type="text"  data-ng-required="true" data-ng-model="criterioAvaliacao.nome" placeholder=" " />
                        </div>                   
                    </div>
                </form>
            </div>
            <div class="row">
                <button class="btn green col s12 m12 l12" style="border-radius: 0px" data-ng-disabled="!formCriterio.$valid" data-ng-click="addCriterio(criterioAvaliacao)">Salvar</button>
                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
            </div>       

        </div>
        <!-- FIM MODAL ADD CRITÉRIO -->       

        <!-- MODAL CRITÉRIO AVALIAÇÃO -->

        <div id="modal-criterio-detalhes" class="modal hide-on-small-only" style="border-radius: 0px !important; height: auto !important; overflow: auto">            

            <div class="row">                        
                <article class="black-text col s12 m12 l12 card" style="padding: 1%; font-size: 11pt" data-ng-if="eixoList.length > 0">
                    <h5 class="green-text">Eixo</h5>
                    <blockquote style="font-size: 14pt">Nome: <strong>{{eixo.nome}}</strong>&nbsp; Peso: <strong>{{eixo.peso}}</strong></blockquote>                                                  
                </article>                            
            </div>     
            <div class="row" data-ng-if="criterioList.length > 0 && (criterioList | byEixoId:eixo.id).length > 0">
                <article class="black-text col s12 m12 l12 card">
                    <h5 class="green-text">Critérios</h5>
                    <table class="bordered">
                        <tr>                     
                            <th>Nome</th>
                            <th class="center-align">Ativo</th>
                            <th class="center-align">Editar</th>
                            <th class="center-align">Excluir</th>
                        </tr>
                        <tr data-ng-repeat="criterio in criterioList| orderBy:'nome' | byEixoId:eixo.id">
                            <td data-ng-bind="criterio.nome"></td>
                            <td class="center-align">
                                <form name="formCbx">
                                    <input type="checkbox" id="cbx-{{criterio.id}}" data-ng-change="addCriterioListUpdate(criterio)" class="checkbox" data-ng-model="criterio.ativo"/>
                                    <label for="cbx-{{criterio.id}}"></label>
                                </form>
                            </td>
                            <td class="center-align">
                                <a href="#!" data-ng-click="getCriterio(criterio.id)" class="btn-floating btn-editar  blue btn tooltipped" id="btn-editar-{{criterio.id}}" data-position="top" data-delay="50" data-tooltip="Alterar">
                                    <i class="material-icons">mode_edit</i>
                                </a>
                            </td>
                            <td class="center-align">
                                <a href="#!" class="btn-floating red btn-criterio-excluir" id="btn-criterio-excluir-{{criterio.id}}" data-ng-click="openModal(7)"><i class="material-icons">clear</i></a>
                            </td>
                        </tr>

                    </table>
                    <button type="button" style="margin-bottom: 2%" class="btn block blue col s12 m12 l12" data-ng-click="saveAlteracoes()">Salvar Alterações</button>
                </article>  

            </div>
            <div class="modal-footer">
                <a href="#!"  class=" modal-action modal-close waves-effect waves-green btn-flat">Fechar</a>
            </div>
        </div>          
        <!-- FIM MODAL CRITÉRIO AVALIAÇÃO -->

        <div class="card-panel ember">         
            <div class="card-content">
                <table class="bordered">
                    <tr>
                        <th>Eixo </th>
                        <th>Peso</th>
                        <th class="center-align">Detalhes</th>
                        <th class="center-align">Add Crit&eacute;rio</th>
                        <th class="center-align">Editar</th>
                        <th class="center-align">Excluir</th>
                    </tr>
                    <tr data-ng-repeat="eixo in eixoList| orderBy:'nome'">
                        <td data-ng-bind="eixo.nome">
                        </td>
                        <td data-ng-bind="eixo.peso | dotToComma">
                        </td>
                        <td class="center-align">
                            <a class="btn-floating btn-detalhes waves-effect waves-light yellow" id="btn-detail-{{eixo.id}}" data-ng-click="openModal(5)" href="#!"><i class="material-icons">zoom_in</i></a>
                        </td>
                        <td class="center-align">
                            <a class="btn-floating btn-criterio waves-effect waves-light blue" id="btn-criterio-{{eixo.id}}" data-ng-click="openModal(4)" href="#!"><i class="material-icons">add</i></a>
                        </td>
                        <td class="center-align">
                            <a href="#!" data-ng-click="openModal(3)" class="btn-floating btn-editar  blue btn tooltipped" id="btn-editar-{{eixo.id}}" data-position="top" data-delay="50" data-tooltip="Alterar">
                                <i class="material-icons">mode_edit</i>
                            </a>
                        </td>
                        <td class="center-align">
                            <a href="#!" class="btn-floating btn-excluir red btn tooltipped modal-trigger" data-ng-click="openModal(1)" id="btn-excluir-{{eixo.id}}" data-position="top" data-delay="50" data-tooltip="Excluir">
                                <i class="material-icons">clear</i>
                            </a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>