
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="EmpreendimentoCtrl">

    <div class="col s12 l8" >

        <div class="col s12 m12 l12">
            <div class="card-panel ember" style="padding-top: 0px !important">   
                <div class="card center white">
                    <div class="card-content black-text valign-wrapper">
                        <i class="material-icons small valign">business</i>&nbsp;&nbsp;
                        <span class="card-title"><strong>Empreendimentos</strong>&nbsp; </span>
                    </div>

                </div>

                <div class="card-content">

                    <!-- MODAL PROPOSTA -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-1">
                        <h6 class="card card-panel" data-ng-if="!apresentacaoNegocio.miniCurriculo">Ainda não foi enviada a apresenta&ccedil;&atilde;o do neg&oacute;cio.</h6>
                        <div class="modal-body card white" data-ng-if="apresentacaoNegocio.miniCurriculo">
                            <blockquote class="card grey lighten-4"><strong>Mini Curr&iacute;culo: <br/><br/></strong>  {{apresentacaoNegocio.miniCurriculo}}</blockquote>
                            <blockquote class="card grey lighten-4"><strong>Disponibilidade e comprometimento para o desenvolvimento do neg&oacute;cio: <br/><br/></strong>  {{apresentacaoNegocio.disponibilidade}}</blockquote>
                            <blockquote class="card grey lighten-4"><strong>Descrição da inova&ccedil;&atilde;o do produto, servi&ccedil;o ou processo: <br/><br/></strong>  {{apresentacaoNegocio.inovacaoProduto}}</blockquote>
                            <blockquote class="card grey lighten-4"><strong>Tempo necess&aacute;rio para o desenvolvimento do produto, servi&ccedil;o ou processo: <br/><br/></strong>  {{apresentacaoNegocio.tempoDesenvolvimento}}</blockquote>
                            <blockquote class="card grey lighten-4"><strong>Investimento inicial a ser realizado: <br/><br/></strong>  {{apresentacaoNegocio.investimento}}</blockquote>
                            <blockquote class="card grey lighten-4"><strong>Identifica&ccedil;&atilde;o de clientes, concorrentes e fornecedores: <br/><br/></strong>  {{apresentacaoNegocio.identificacao}}</blockquote>
                            <blockquote class="card grey lighten-4"><strong>Conhecimento de mercado alvo: <br/><br/></strong>  {{apresentacaoNegocio.mercadoAlvo}}</blockquote>
                            <blockquote class="card grey lighten-4"><strong>Vantagem competitiva comparada &agrave; concorr&ecirc;ncia: <br/><br/></strong>  {{apresentacaoNegocio.vantagemCompetitiva}}</blockquote>
                            <blockquote class="card grey lighten-4"><strong>Parcerias previstas ou firmadas para o desenvolvimento do neg&oacute;cio: <br/><br/></strong>  {{apresentacaoNegocio.parcerias}}</blockquote>
                            <blockquote class="card grey lighten-4"><strong>Estrutura organizacional proposta: <br/><br/></strong>  {{apresentacaoNegocio.estruturaOrganizacional}}</blockquote>
                        </div>
                        <div class="row">
                            <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
                        </div>                  
                    </div>
                    <!-- FIM MODAL PROPOSTA -->        

                    <!-- MODAL APRESENTAÇÃO -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-apresentacao">
                        <h6 class="card card-panel">Agendar Apresenta&ccedil;&atilde;o</h6>
                        <div class="modal-body card white">
                            <form name="formApresentacao">
                                <div class="row">
                                    <div class="s12 m6 l6 input-field" style="padding-left: 2%; padding-right: 2%">
                                        <label for="dataApresentacao">Data de Apresenta&ccedil;&atilde;o</label>
                                        <input id="dataApresentacao" type="text" class="date" data-ng-required="true" data-ng-model="apresentacao.dataApresentacao" placeholder="dd/mm/aaaa" />
                                    </div>
                                    <div class="s12 m6 l6 input-field" style="padding-left: 2%; padding-right: 2%">
                                        <label for="horaApresentacao">Hora da Apresenta&ccedil;&atilde;o</label>
                                        <input id="horaApresentacao" type="text" class="time" data-ng-required="true" data-ng-model="apresentacao.horaApresentacao" placeholder="hh:mm" />
                                    </div>
                                    <div class="s12 m6 l6 input-field" style="padding-left: 2%; padding-right: 2%">
                                        <label for="localApresentacao">Local da Apresenta&ccedil;&atilde;o</label>
                                        <input id="localApresentacao" type="text" data-ng-required="true" data-ng-model="apresentacao.localApresentacao" placeholder="ex.: Av. Vista Alegre, 00 - Bairro Vista Alegre." />
                                    </div>                           
                                </div>
                            </form>
                            <!--// BARRA DE PROGRESSO PARA A CONCLUSÃO DO MÉTODO -->
                            <div class="row" data-ng-show="showProgress">
                                <div class="progress">
                                    <div class="indeterminate"></div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <button class="btn green col s12 m12 l12" style="border-radius: 0px" data-ng-disabled="!formApresentacao.$valid" data-ng-click="agendarApresentacao(apresentacao)">Agendar Apresenta&ccedil;&atilde;o</button>
                            <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
                        </div>                  
                    </div>
                    <!-- FIM MODAL APRESENTAÇÃO -->            

                    <!-- MODAL STATUS -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-2">
                        <div class="modal-body card-panel white">
                            <div class="row">
                                <div class="card-panel grey lighten-4">
                                    <h6>ALTERAR STATUS DO EMPREENDIMENTO: <strong>{{empreendimento.nome}}</strong></h6>
                                    <h6>STATUS ATUAL: <strong>{{empreendimento.status}}</strong></h6>
                                </div>                                
                                <button data-ng-click="setStatus(empreendimento.id)" data-ng-disabled="btnStatusOk, validStatus(empreendimento, 'Avaliação Realizada')" class="btn blue col s12 m12 l12 btn-status"></button>                                                                        
                                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
                                <!--// BARRA DE PROGRESSO PARA A CONCLUSÃO DO MÉTODO -->
                                <div class="row" data-ng-show="showProgress">
                                    <div class="progress">
                                        <div class="indeterminate"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>               
                    <!-- FIM MODAL STATUS -->

                    <!-- MODAL DETALHES EMPREENDIMENTO -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-3">
                        <div class="modal-body card white">
                            <blockquote class="grey lighten-4"><strong>Nome:&nbsp;</strong>  {{empreendimento.nome}} </blockquote>
                            <blockquote class="grey lighten-4"><strong>Ramo de Atividade</strong>  {{empreendimento.ramoAtividade.nome}}</blockquote>
                            <blockquote class="grey lighten-4"><strong>Raz&atilde;o Social:&nbsp;</strong>  {{empreendimento.razaoSocial}}</blockquote>                            
                            <blockquote class="grey lighten-4"><strong>Empreendedor(es):&nbsp;<br/></strong>
                                <span data-ng-repeat="empreendedor in empreendimento.empreendedorList">
                                    <span>{{empreendedor.nome}} {{empreendedor.sobrenome}}</span><br/>
                                </span>
                            </blockquote>

                            <div class="row">
                                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
                            </div>
                        </div>
                    </div>
                    <!-- FIM MODAL DETALHES EMPREENDIMENTO -->

                    <!-- MODAL AVALIADORES -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-4">
                        <div class="modal-body card white">
                            <div class="row">
                                <div class="card">
                                    <div class="card s12 m12 l12 center-align" style="padding-top: 1%; padding-bottom: 1%">
                                        <h6 style="font-size: 15pt">Associar Avaliadores</h6>
                                    </div>
                                    <form name="formAvalidor">
                                        <div class="card select-group card-content s12 m12 l12">
                                            <label for="avalidor" style="font-size: 13pt">Escolha os Avaliadores</label>
                                            <div class="input-field responsavel-field">
                                                <select id="avaliador" class="browser-default" data-ng-model="avaliador.id" data-ng-change="addAvaliador(avaliador)">
                                                    <option value="">Selecione</option>
                                                    <option data-ng-repeat="avaliador in avaliadores" value="{{avaliador.id}}">{{avaliador.nome}} {{avaliador.sobrenome}}</option>
                                                </select>        

                                                <div class="chip pratica-chip" data-ng-repeat="avaliador in empreendimento.avaliadorList"> 
                                                    <span>
                                                        <img data-ng-src="/gerenciador/img/ico-responsavel.png" alt="{{avaliador.nome}}">
                                                        <a data-ng-href="/gerenciador/avaliador/{{avaliador.id}}/view/notas/by/empreendimento/{{empreendimento.id}}"  class="white-text">{{avaliador.nome}}</a>
                                                    </span>
                                                    <i class="material-icons" data-ng-click="removeAvaliador(avaliador.id)">close</i>
                                                </div>
                                            </div>                              
                                            <button class="btn green lighten-1 large" data-ng-click="associarAvaliadores()" data-ng-disabled="empreendimento.avaliadorList.length < 1" style="width: 100%">Associar Avaliadores</button>
                                        </div>   
                                    </form>

                                </div>                                       
                                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
                            </div>
                        </div>       

                    </div>               
                    <!-- FIM MODAL AVALIADORES -->

                    <!-- MODAL EMPREENDEDORES (JP) -->
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
                    <!-- FIM MODAL EMPREENDEDORES (JP) -->

                    <!-- MODAL EXCLUIR EMPREENDIMENTO (JP) -->
                    <div class="modal alert-modal-excluir card-panel" id="modal-6">
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
                    <!-- FIM MODAL EXCLUIR EMPREENDIMENTO (JP)-->

                    <!-- MODAL REPROVAR EMPREENDIMENTO -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-7">
                        <!--<div class="modal-body card-panel white">-->
                        <div class="card-panel grey lighten-3">
                            <div class="card-title center">
                                <h6><strong>REPROVAÇÃO DE EMPREENDIMENTO</strong></h6>  
                            </div>
                        </div>                            
                        <div class="row">
                            <div class="card-panel grey lighten-4">             
                                <blockquote>
                                    <h6>NOME: <strong>{{empreendimento.nome}}</strong></h6>                                  
                                </blockquote>
                                <blockquote>
                                    <span><strong>INSIRA ABAIXO O MOTIVO DA REPROVAÇÃO:</strong></span>                                
                                </blockquote>                                                                
                                <div class="row">
                                    <form class="col s12 m12 l12" method="post" action="empreendimento/resultado-final">
                                        <div class="row">
                                            <div class="input-field col s12 m12 l12 left-align">                                                    
                                                <textarea id="inputReprova" rows="30" name="motivo" class="materialize-textarea"></textarea>
                                                <label for="inputReprova" class="black-text">Motivo</label>
                                                <input type="hidden" name="idEmpreendimento" id="idEmpreendimento" value="{{empreendimento.id}}">
                                                <input type="hidden" name="resultado" value="reprovado">
                                            </div>
                                        </div>
                                        <button data-ng-disabled="btnStatusOk" class="btn blue col s12 m12 l12 modal-close" style="width: 100% !important; margin-top: 1rem; border-color: transparent">CONFIRMAR</button>
                                        <a class="btn orange modal-close large col s12 m12 l12 white-text" style="width: 100% !important; margin-top: 1rem; border-color: transparent;">Fechar</a>
                                    </form>
                                </div>                                    
                            </div>                                
                        </div>
                        <!--</div>-->
                    </div>               
                    <!-- FIM MODAL REPROVAR EMPREENDIMENTO-->

                    <!-- MODAL APROVAR EMPREENDIMENTO -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-8">
                        <!--<div class="modal-body card-panel white">-->
                        <div class="card-panel grey lighten-3">
                            <div class="card-title center">
                                <h6><strong>APROVAÇÃO DE EMPREENDIMENTO</strong></h6>  
                            </div>
                        </div>                            
                        <div class="row">
                            <div class="card-panel grey lighten-4">   
                                <blockquote>
                                    <h6>NOME: <strong>{{empreendimento.nome}}</strong></h6>                                  
                                </blockquote>  
                                <blockquote>
                                    <span><strong>Informe ao(s) empreendedor(es) a aprovação deste empreendimento:</strong></span>                                 
                                </blockquote>                                
                                <div class="row">
                                    <form class="col s12 m12 l12" method="post" action="empreendimento/resultado-final">
                                        <div class="row">
                                            <div class="input-field col s12 m12 l12 left-align">                                                    
                                                <textarea id="inputAprova" rows="30" name="motivo" class="materialize-textarea"></textarea>
                                                <label for="inputAprova" class="black-text">Descrição</label>
                                                <input type="hidden" name="idEmpreendimento" id="idEmpreendimento" value="{{empreendimento.id}}">
                                                <input type="hidden" name="resultado" value="aprovado">
                                            </div>
                                        </div>
                                        <button data-ng-disabled="btnStatusOk" class="btn blue col s12 m12 l12 modal-close" style="width: 100% !important; margin-top: 1rem; border-color: transparent">CONFIRMAR</button>                                                                                
                                        <a class="btn orange modal-close large col s12 m12 l12 white-text" style="width: 100% !important; margin-top: 1rem; border-color: transparent;">Fechar</a>
                                    </form>
                                </div>                                    
                            </div>                                
                        </div>
                    </div>               
                    <!-- FIM MODAL REPROVAR EMPREENDIMENTO-->

                    <!-- MODAL APROVAR EMPREENDIMENTO-->
                    <!--                    <div class="modal alert-modal-excluir card-panel" id="modal-8">
                                            <div class="modal-content">
                                                <h3 class="valign-wrapper" style="font-size: 25pt"><i class="material-icons valign" style="margin-right:2%; font-size: 25pt !important">warning</i> <span class="valign">Aprovação de Empreendimento</span></h3>
                                                <h5>Você confirma que deseja aprovar o empreendimento e notificar os empreendedores relacionados a este empreendimento?</h5>
                                                <p>Obs.: esta operação não pode ser revertida</p>
                                            </div>
                                            <div class="modal-footer card-action">
                                                <a href="#!" data-ng-click="setStatusAprovado(empreendimento.id, 'Aprovado')" class="waves-effect waves-orange btn-flat modal-close">Aprovar</a>
                                                <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
                                            </div>
                                        </div>-->
                    <!-- FIM MODAL APROVAR EMPREENDIMENTO-->

                    <!-- MODAL STATUS FINAL -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-9">
                        <div class="modal-body card white">
                            <blockquote class="grey lighten-4"><strong>
                                    <span data-ng-if="!validStatusResult(empreendimento)">Altere o status deste empreendimento nos botões abaixo - "Aprovar ou Reprovar"&nbsp;</span>
                                    <span data-ng-if="validStatusResult(empreendimento)">Este empreendimento já finalizou todo o seu processo!&nbsp;</span>
                                </strong></blockquote>
                            <blockquote class="grey lighten-4"><strong>Empreendedor(es):&nbsp;<br/></strong>
                                <span data-ng-repeat="empreendedor in empreendimento.empreendedorList">
                                    <span>{{empreendedor.nome}} {{empreendedor.sobrenome}}</span><br/>
                                </span>
                            </blockquote>

                            <div class="row">
                                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
                            </div>
                        </div>
                    </div>
                    <!-- FIM MODAL STATUS FINAL -->

                    <!-- MODAL DESCRIÇÂO RESULTADO -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-10">
                        <div class="modal-body card white">
                            <blockquote class="grey lighten-4"><strong>Avaliador(es):&nbsp;<br/></strong>
                                <span data-ng-repeat="avaliador in empreendimento.avaliadorList">
                                    <span>{{avaliador.nome}} {{avaliador.sobrenome}}</span><br/>
                                </span>
                            </blockquote>
                            <blockquote class="grey lighten-4">
                                <strong>Descrição:&nbsp;<br/></strong>
                                <p>
                                    {{empreendimento.descricaoResultado}}
                                </p>
                            </blockquote>

                            <div class="row">
                                <button class="btn orange modal-close center large" style="width: 100% !important; margin-top: 1rem; border-color: transparent">Fechar</button>
                            </div>
                        </div>
                    </div>
                    <!-- FIM MODAL DESCRIÇÂO RESULTADO -->

                    <div class="modal" style="background-color: #FFF !important" id="modal-11">
                        <div class="modal-body card white">
                            <h5>Aguarde um instante...</h5>
                        </div>
                        <!--// BARRA DE PROGRESSO PARA A CONCLUSÃO DO MÉTODO -->
                        <div class="row" data-ng-show="showProgress">
                            <div class="progress">
                                <div class="indeterminate"></div>
                            </div>
                        </div>
                    </div>

                    <div class="card-content">
                        <h6 data-ng-show="isEmpreendimentoListEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o existem empreendimentos cadastrados!</strong></h6>          


                        <ul class="collection card" data-ng-hide="isEmpreendimentoListEmpty()">

                            <li class="collection-item" data-ng-repeat="empreendimento in empreendimentoList| orderBy:'nome'">
                                <div class="card-panel valign-wrapper">
                                    <span class="valign-wrapper">
                                        <i class="material-icons valign">business</i>&nbsp;&nbsp;&nbsp;
                                        <a href="#!" id="emp-{{empreendimento.id}}" class="emp-accordion-link"><span data-ng-bind="empreendimento.nome" class="valign" style="font-size: 14pt;"></span></a>
                                    </span>
                                    <!-- #################################################################################-->
                                    <a data-ng-href="#!" id="btn-excluir-empreendimento" data-ng-click="openModal(6, empreendimento)" class="waves-effect waves-light white grey-text"  style="right: 3rem; position: absolute"><i class="material-icons">clear</i></a>
                                </div>

                                <div class="empreendimento card-content">
                                    <div class="row yellow accent-2 center-align" data-ng-if="empreendimento.status == 'Apresentação Agendada' && empreendimento.dataHoraApresentacao !== undefined">

                                        <p class="center-align purple  accent-5 col s12 m12 l12" style="padding-bottom: 0.1rem !important">           
                                            <a href="#!" class="btn circle blue white-text center" style="border-radius: 0px !important; height: 22px !important; line-height: inherit !important" data-ng-click="desmarcarApresentacao(empreendimento)">Desmarcar</a>
                                            <br/>
                                        </p>
                                        <p class="left-align col l2 m6 l7 push-l1">Apresentação marcada para: <strong>{{empreendimento.dataHoraApresentacao}}hrs</strong></p>
                                        <!--<p class="left-align col l2 m6 l5 push-l1">Horário: <strong>{{empreendimento.dataHoraApresentacao}}</strong></p>-->
                                        <p class="left-align col l2 m6 l5 push-l1">Local: <strong>{{empreendimento.localApresentacao}}</strong></p>                              
                                    </div>
                                    <div class="row">
                                        <span class="col s12 m6 l6">Status:&nbsp; <strong>{{empreendimento.status}}</strong></span>
                                        <span class="col s12 m6 l6">Ramo de Atividade:&nbsp; <strong>{{empreendimento.ramoAtividade.nome}}</strong></span>                                               
                                    </div>
                                    <div class="row">
                                        <span class="col s12 m6 l6"><a href="/gerenciador/incubadora/empreendimento/{{empreendimento.id}}/atualizar">Atualizar dados do empreendimento</a></span>
                                    </div>

                                    <div class="row center">   
                                        <a data-ng-href="/gerenciador/avaliacao/empreendimento/{{empreendimento.id}}" data-ng-if="validStatus(empreendimento, 'Avaliação Realizada')" class="btn center yellow black-text col s12 m12 l12">Ver avalia&ccedil;&atilde;o</a>
                                        <hr class="divider white" style="border: 0px"/>                               
                                        <a data-ng-href="#!" id="btn-ver-proposta" data-ng-click="openModal(1, empreendimento)" class="btn modal-trigger blue white-text valign col s12 m2 l3 push-l1 push-m1 truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px">Proposta</a>
                                        <a data-ng-href="#!" id="btn-detalhes" data-ng-click="openModal(3, empreendimento)" class="btn blue valign white-text accent-5 col s12 m2 l3  push-l1  push-m1  tooltipped truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px;">Detalhes</a> 
                                        <a data-ng-href="#!" id="btn-status" data-ng-click="openModal(2, empreendimento)" data-ng-if="!validStatus(empreendimento, 'Avaliação Realizada')" class="btn blue valign white-text accent-5 col s12 m3 l3  push-l1  push-m1 truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px">
                                            <span data-ng-if="validStatus(empreendimento, 'Apresentação Agendada')"><span class="hide-on-small-only">Alterar</span> Status</span> 
                                            <span data-ng-if="!validStatus(empreendimento, 'Apresentação Agendada')">Apresentação</span> 
                                        </a>
                                        <a data-ng-href="#!" data-ng-if="validStatus(empreendimento, 'Avaliação Realizada')" id="btn-status-final" data-ng-click="openModal(9, empreendimento)" class="btn blue valign white-text accent-5 col s12 m2 l3  push-l1  push-m1  tooltipped truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px;">Alterar Status</a> 
                                        <!-- BOTAO EDITAR PODE SER REMOVIDO -->
                                        <!-- <a data-ng-href="#!" id="btn-editar" class="btn blue valign white-text accent-5 col s12 m2 l2  push-l1  push-m1 tooltipped truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px;">Editar</a> -->                                                                                
                                        <a data-ng-href="#!" id="btn-add-avaliador" class="btn blue valign white-text accent-5 col s12 m6 l3 push-l2 push-m3 tooltipped truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px;" data-ng-click="openModal(4, empreendimento)" data-ng-disabled="isAvaliadorListEmpty()"><span style="font-size: 16pt">+</span> Avaliador</a> 
                                        <a data-ng-href="#!" id="btn-add-empreendedor" class="btn blue white-text accent-5 col s12 m6 l3 push-12 push-m3 tooltipped truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px;" data-ng-click="openModal(5, empreendimento)" data-ng-disabled="isEmpreendedorListEmpty()"><span style="font-size: 16pt">+</span> Empreendedor</a>                                                                                                                                                                
                                    </div>                                    
                                    <!--NESTA DIV CONTEM OS BOTOES ONDE O GESTOR IRÁ APROVAR OU REPROVAR UM EMPREENDIMENTO-->
                                    <div class="row center">                                                                                
                                        <button data-ng-if="validStatus(empreendimento, 'Avaliação Realizada')"  data-ng-disabled="validStatusResult(empreendimento)" id="btn-aprovar" data-ng-click="openModal(8, empreendimento)" class="btn green valign white-text accent-5 col s12 m6 l3 push-l2 push-m3 truncate" data-ng-disabled="validStatusReprovado(empreendimento, 'Aprovado')" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px">Aprovar</button>                                        
                                        <button data-ng-if="validStatus(empreendimento, 'Avaliação Realizada')" data-ng-disabled="validStatusResult(empreendimento)" id="btn-reprovar" data-ng-click="openModal(7, empreendimento)" class="btn red valign white-text accent-5 col s12 m6 l3 push-l3 push-m3 truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px">Reprovar</button>                                                                                
                                        <button data-ng-if="validStatusResult(empreendimento)" class="btn center yellow black-text col s12 m12 l12" style="margin-top: 0.6rem;" data-ng-click="openModal(10, empreendimento)">Ver descrição do resultado final</button>
                                    </div>
                                    <div class="row hide-on-small-only">                                
                                        <hr class="divider white" style="border: 0px"/>
                                        <ul clss="col s12 m12 l12">                                            
                                            <li class="col s2 m2 l2 marginTimeLine">                                 
                                                <button class="btn-floating circle"  data-ng-class="{'green': empreendimento.apresentacaoNegocio.miniCurriculo !== undefined, 'grey':empreendimento.apresentacaoNegocio.miniCurriculo === undefined}" style="height: 27px; width: 27px; line-height: normal;">
                                                    1
                                                </button>
                                                <p class="hide-on-small-only" style="margin-top: 0.3rem">Proposta Enviada</p>
                                            </li>
                                            <li class="col s2 m2 l2 marginTimeLine">                                 
                                                <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Apresentação Agendada'), 'grey':!validStatus(empreendimento, 'Apresentação Agendada')}" style="height: 27px; width: 27px; line-height: normal;">
                                                    2
                                                </button>
                                                <p class="hide-on-small-only" style="margin-top: 0.3rem">Apresenta&ccedil;&atilde;o Agendada</p>
                                            </li>
                                            <li class="col s2 m2 l2 marginTimeLine">                                 
                                                <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Apresentação Realizada'), 'grey':!validStatus(empreendimento, 'Apresentação Realizada')}" style="height: 27px; width: 27px; line-height: normal;">
                                                    3
                                                </button>
                                                <p class="hide-on-small-only" style="margin-top: 0.3rem">Apresenta&ccedil;&atilde;o Realizada</p>
                                            </li>
                                            <li class="col s2 m2 l2 marginTimeLine">                                 
                                                <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Avaliação Realizada'), 'grey':!validStatus(empreendimento, 'Avaliação Realizada')}" style="height: 27px; width: 27px; line-height: normal;">
                                                    4
                                                </button>
                                                <p class="hide-on-small-only" style="margin-top: 0.3rem">Avalia&ccedil;&atilde;o Realizada</p>
                                            </li>
                                            <li class="col s2 m2 l2">                                 
                                                <button class="btn-floating circle" data-ng-class="{'red': validStatusReprovado(empreendimento), 'green': validStatusAprovado(empreendimento), 'grey': !validStatus(empreendimento, 'Aprovado')}" style="height: 27px; width: 27px; line-height: normal;">
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
</div>