
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- �rea Principal -->
<div class="area-principal" data-ng-controller="EmpreendimentoCtrl">

    <div class="col s12 l8" >

        <div class="col s12 m12 l12">
            <div class="card-panel ember" style="padding-top: 0px !important">   
                <div class="card center white">
                    <div class="card-content black-text valign-wrapper">
                        <i class="material-icons small valign">business</i>&nbsp;&nbsp;
                        <span class="card-title"><strong class="hide-on-small-only">Empreendimento</strong>&nbsp; </span>
                        <a class="btn-floating waves-effect waves-light blue" data-ng-click="setCreateMode()" href="<c:url value='/empreendimento/novo'/>"><i class="mdi-content-add"></i></a>
                    </div>

                </div>

                <div class="card-content">
                    <!-- INICIO MODAL EXCLUSAO DE EMPREENDIMENTO (JP) -->
                    <div class="modal alert-modal-excluir card-panel" id="modal-1">
                        <div class="modal-content">
                            <h3 class="valign-wrapper" style="font-size: 25pt"><i class="material-icons valign" style="margin-right:2%; font-size: 25pt !important">warning</i> <span class="valign">Alerta de Exclus&atilde;o</span></h3>
                            <h5>Esta opera&ccedil;&atilde;o n&atilde;o poder&aacute; ser desfeita posteriormente.</h5>
                            <p>Obs.: os dados deste empreendimento ser&atilde;o exclu&iacute;dos de todos os locais.</p>
                        </div>
                        <div class="modal-footer card-action">
                            <a href="#!" data-ng-click="remover(empreendimento.id)" class="waves-effect waves-orange btn-flat modal-close">Excluir</a>
                            <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
                        </div>
                    </div>
                    <!-- FIM MODAL EXCLUSAO DE EMPREENDIMENTO (JP) -->
                    
                    <!-- MODAL + EMPREENDEDOR (JP) -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-5">
                        <div class="modal-body card white">
                            <div class="row">
                                <div class="card">
                                    <div class="card s12 m12 l12 center-align" style="padding-top: 1%; padding-bottom: 1%">
                                        <h6 style="font-size: 15pt">Associar Empreendedores</h6>
                                    </div>
                                    <form name="formEmpreendedor">
                                        <div class="card select-group card-content s12 m12 l12">
                                            <label for="empreendedor" style="font-size: 13pt">Escolha os Empreendedores</label>
                                            <div class="input-field responsavel-field">
                                                <select id="empreendedor" class="browser-default" data-ng-model="empreendedor.id" data-ng-change="addEmpreendedor(empreendedor)">
                                                    <option value="">Selecione</option>
                                                    <option data-ng-repeat="empreendedor in empreendedores" value="{{empreendedor.id}}">{{empreendedor.nome}} {{empreendedor.sobrenome}}</option>
                                                </select>        

                                                <div class="chip pratica-chip" data-ng-repeat="empreendedor in empreendimento.empreendedorList"> 
                                                    <span>
                                                        <img data-ng-src="/gerenciador/img/ico-responsavel.png" alt="{{empreendedor.nome}}">
                                                        <a data-ng-href="/gerenciador/empreendedor/{{empreendedor.id}}/view/notas/by/empreendimento/{{empreendimento.id}}"  class="white-text">{{empreendedor.nome}}</a>
                                                    </span>
                                                    <i class="material-icons" data-ng-click="removeEmpreendedor(empreendedor.id)">close</i>
                                                </div>
                                            </div>                              
                                            <button class="btn green lighten-1 large" data-ng-click="associarEmpreendedores()" data-ng-disabled="empreendimento.empreendedorList.length < 1" style="width: 100%">Associar Empreendedores</button>
                                        </div>   
                                    </form>

                                </div>                                       
                                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
                            </div>
                        </div>       

                    </div>               
                    <!-- FIM MODAL + EMPREENDEDOR (JP) -->
                    
                    <!-- MODAL DESCRI��O RESULTADO -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-6">
                        <div class="modal-body card white">
                            <blockquote class="grey lighten-4"><strong>Avaliador(es):&nbsp;<br/></strong>
                                <span data-ng-repeat="avaliador in empreendimento.avaliadorList">
                                    <span>{{avaliador.nome}} {{avaliador.sobrenome}}</span><br/>
                                </span>
                            </blockquote>
                            <blockquote class="grey lighten-4">
                                <strong>Descri��o:&nbsp;<br/></strong>
                                <p>
                                    {{empreendimento.descricaoResultado}}
                                </p>
                            </blockquote>

                            <div class="row">
                                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
                            </div>
                        </div>
                    </div>
                    <!-- FIM MODAL DESCRI��O RESULTADO -->

                    <h6 data-ng-show="isEmpreendimentoListEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o existem empreendimentos cadastrados!</strong></h6>          


                    <ul class="collection card" data-ng-hide="isEmpreendimentoListEmpty()" data-collapsible="accordion">
                        <li class="collection-item" data-ng-repeat="empreendimento in empreendimentoList">
                            <div class="card-panel valign-wrapper">
                                <span class="valign-wrapper">
                                    <i class="material-icons valign">business</i>&nbsp;&nbsp;&nbsp;
                                    <span data-ng-bind="empreendimento.nome" class="valign" style="font-size: 14pt;"></span>
                                </span>                        
                                <a data-ng-href="#!" id="btn-exclui-empreendimento" data-ng-click="openModal(1, empreendimento)" class="waves-effect waves-light white grey-text"  style="right: 3rem; position: absolute"><i class="material-icons">clear</i></a>

                            </div>
                            <div class="card-content">

                                <div class="row yellow accent-2 center-align" data-ng-if="empreendimento.status == 'Apresenta��o Agendada' && empreendimento.dataHoraApresentacao !== undefined">                                    
                                    <p class="left-align col l2 m6 l5 push-l1">Apresenta��o marcada para: <strong>{{empreendimento.dataHoraApresentacao}}</strong></p>
                                    <p class="left-align col l2 m6 l5 push-l1">Hor�rio: <strong>{{empreendimento.dataHoraApresentacao| date:'hh:mm'}}</strong></p>
                                    <p class="left-align col l2 m6 l5 push-l1">Local: <strong>{{empreendimento.localApresentacao}}</strong></p>                              
                                </div>
                                <div class="row">
                                    <span class="col s12 m6 l6">Status:&nbsp; <strong>{{empreendimento.status}}</strong></span>
                                    <span class="col s12 m6 l6">Ramo de Atividade:&nbsp; <strong>{{empreendimento.ramoAtividade.nome}}</strong></span>                                               
                                </div>
                                <div class="row center">
                                    <hr class="divider white" style="border: 0px"/>
                                    <button data-ng-if="validStatusResult(empreendimento)" class="btn center yellow black-text col s12 m12 l12" style="margin-top: 0.6rem;" data-ng-click="openModal(6, empreendimento)">Ver descri��o do resultado final</button>
                                    <a data-ng-href="/gerenciador/empreendimento/{{empreendimento.id}}/enviar-proposta" class="btn blue valign col s12 m4 l4 push-l1 push-m1 truncate" style="margin-left: 1rem; margin-top: 0.6rem; border-radius: 0px">Cadastrar Proposta</a>                                    
                                    <a href="#!" class="btn yellow valign black-text accent-5 col s12 m4 l4 push-l2 push-m2 truncate" style="margin-left: 1rem; margin-top: 0.6rem; border-radius: 0px">Visualizar Informa��es</a>                                     
                                </div>
                                <div class="row center">
                                    <a data-ng-href="#!" id="btn-add-empreendedor" class="btn teal white-text accent-5 col s12 m6 l4 push-l4 push-m4 tooltipped truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px;" data-ng-click="openModal(5, empreendimento)" data-ng-disabled="isEmpreendedorListEmpty()"><span style="font-size: 16pt">+</span> Empreendedor</a>
                                </div>
                                <div class="row hide-on-small-only">
                                    <hr class="divider white" style="border: 0px"/>
                                    <ul clss="col s12 m12 l12">
                                        <li class="col s2 m2 l1">                                 
                                            &nbsp;
                                        </li>
                                        <li class="col s2 m2 l2">                                 
                                            <button class="btn-floating circle"  data-ng-class="{'green': validStatus(empreendimento, 'Proposta Enviada'), 'grey':!validStatus(empreendimento, 'Proposta Enviada')}" style="height: 27px; width: 27px; line-height: normal;">
                                                1
                                            </button>
                                            <p class="hide-on-small-only" style="margin-top: 0.3rem">Proposta Enviada</p>
                                        </li>
                                        <li class="col s2 m2 l2">                                 
                                            <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Apresenta��o Agendada'), 'grey':!validStatus(empreendimento, 'Apresenta��o Agendada')}" style="height: 27px; width: 27px; line-height: normal;">
                                                2
                                            </button>
                                            <p class="hide-on-small-only" style="margin-top: 0.3rem">Apresenta&ccedil;&atilde;o Agendada</p>
                                        </li>
                                        <li class="col s2 m2 l2">                                 
                                            <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Apresenta��o Realizada'), 'grey':!validStatus(empreendimento, 'Apresenta��o Realizada')}" style="height: 27px; width: 27px; line-height: normal;">
                                                3
                                            </button>
                                            <p class="hide-on-small-only" style="margin-top: 0.3rem">Apresenta&ccedil;&atilde;o Realizada</p>
                                        </li>
                                        <li class="col s2 m2 l2">                                 
                                            <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Avalia��o Realizada'), 'grey':!validStatus(empreendimento, 'Avalia��o Realizada')}" style="height: 27px; width: 27px; line-height: normal;">
                                                4
                                            </button>
                                            <p class="hide-on-small-only" style="margin-top: 0.3rem">Avalia&ccedil;&atilde;o Realizada</p>
                                        </li>
                                        <li class="col s2 m2 l2">                                 
                                            <button class="btn-floating circle" data-ng-class="{'red':validStatusReprovado(empreendimento),'green': validStatusAprovado(empreendimento), 'grey':!validStatus(empreendimento, 'Aprovado')}" style="height: 27px; width: 27px; line-height: normal;">
                                                5
                                            </button>
                                            <p class="hide-on-small-only" style="margin-top: 0.3rem">
                                                <span data-ng-if="validStatusAprovado(empreendimento)">Aprovado</span>
                                                <span data-ng-if="validStatusReprovado(empreendimento)">Reprovado</span>
                                                <span data-ng-if="!validStatus(empreendimento, 'Aprovado')">Resultado Final</span>
                                            </p>
                                        </li>                             
                                    </ul>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
    </div>
</div>