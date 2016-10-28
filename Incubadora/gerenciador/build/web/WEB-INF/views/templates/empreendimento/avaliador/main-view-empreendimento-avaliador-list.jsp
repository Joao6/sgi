
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="EmpCtrl">

    <div class="col s12 l8" >
        <input type="text" id="avaliador-id" hidden="true" value="${avaliador.id}"/>
        <div class="col s12 m12 l12">
            <div class="card-panel ember" style="padding-top: 10px !important">   
                <div class="card center white">
                    <div class="card-content black-text valign-wrapper">
                        <i class="material-icons small valign">business</i>&nbsp;&nbsp;
                        <span class="card-title"><strong class="hide-on-small-only">Empreendimentos</strong>&nbsp; </span>
                    </div>
                </div>
                
                <!-- MODAL DETALHES EMPREENDIMENTO -->
                    <div class="modal" style="background-color: #FFF !important" id="modal-1">
                        <div class="modal-body card white">
                            <blockquote class="grey lighten-4"><strong>Nome:&nbsp;</strong>  {{empreendimento.nome}} </blockquote>
                            <blockquote class="grey lighten-4"><strong>Ramo de Atividade</strong>  {{empreendimento.ramoAtividade.nome}}</blockquote>
                            <blockquote class="grey lighten-4"><strong>Raz&atilde;o Social:&nbsp;</strong>  {{empreendimento.razaoSocial}}</blockquote>                            
                            <blockquote class="grey lighten-4"><strong>Empreendedor(es):&nbsp;<br/></strong>
                                <span data-ng-repeat="empreendedor in empreendimento.empreendedorList">
                                    <span>{{empreendedor.nome}} {{empreendedor.sobrenome}}</span><br/>
                                </span>
                            </blockquote>                            
                            <h6 class="card card-panel" data-ng-if="!apresentacaoNegocio.miniCurriculo">Ainda não foi enviada a apresenta&ccedil;&atilde;o do neg&oacute;cio.</h6>
                            <div class="modal-body card white" data-ng-if="apresentacaoNegocio.miniCurriculo">
                                <blockquote class="card grey lighten-4"><strong>Proposta Cadastrada: </strong></blockquote>
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
                    </div>
                    <!-- FIM MODAL DETALHES EMPREENDIMENTO -->


                <c:forEach items="${empreendimentoList}" var="empreendimento">                    

                    <%--<c:if test="${empreendimento.status != 'Avaliação Realizada'}">--%>

                    <div class="card-content">
                        <!--<h6 data-ng-show="isEmpreendimentoListEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o h&aacute; empreendimentos encaminhados para voc&ecirc; nesse momento.</strong></h6>-->          


                        <ul class="collection card" data-collapsible="accordion">
                            <li class="collection-item">
                                <div class="card-panel valign-wrapper">
                                    <span class="valign-wrapper">
                                        <i class="material-icons valign">business</i>&nbsp;&nbsp;&nbsp;
                                        <span class="valign" style="font-size: 14pt;">${empreendimento.nome}</span>
                                    </span>                        

                                </div>
                                <div class="card-content">
                                    <c:if test="${empreendimento.status == 'Apresentação Agendada'}">
                                        <div class="row yellow accent-2">                                            
                                            <p class="left-align col l2 m6 l5 push-l1">Apresentação marcada para: <strong>${empreendimento.dataHoraApresentacao}</strong></p>
                                            <p class="left-align col l2 m6 l5 push-l1">Horário: <strong>${empreendimento.dataHoraApresentacao}</strong></p>
                                            <p class="left-align col l2 m6 l5 push-l1">Local: <strong>${empreendimento.localApresentacao}</strong></p>  
                                        </div>
                                    </c:if>
                                    <div class="row">
                                        <span class="col s12 m6 l6">Status:&nbsp; <strong>${empreendimento.status}</strong></span>
                                        <span class="col s12 m6 l6">Ramo de Atividade:&nbsp; <strong>${empreendimento.ramoAtividade.nome}</strong></span>                                               
                                    </div>
                                    <div class="row center">
                                        <hr class="divider white" style="border: 0px"/>
                                        <a href="/gerenciador/avaliador/empreendimento/${empreendimento.id}/avaliar" class="btn blue valign col s12 m4 l4 push-l1 push-m1 truncate" style="margin-left: 1rem; margin-top: 0.6rem; border-radius: 0px">Avaliar</a>
                                        <a href="#" id="btn-informacoes" class="btn yellow valign black-text accent-5 col s12 m4 l4 push-l2 push-m2 truncate" data-ng-click="openModal(1, ${empreendimento.id})" style="margin-left: 1rem; margin-top: 0.6rem; border-radius: 0px">Visualizar Informações</a> 
                                    </div>
                                    <div class="row hide-on-small-only">
                                        <hr class="divider white" style="border: 0px"/>


                                        <ul class="col s12 m12 l12">                                            

                                            <li class="col s2 m2 l2 marginTimeLine">
                                                <button class="btn-floating circle 
                                                        <c:if test="${empreendimento.status == 'Proposta Enviada' ||
                                                                      empreendimento.status == 'Apresentação Agendada' ||
                                                                      empreendimento.status == 'Apresentação Realizada' ||
                                                                      empreendimento.status == 'Avaliação Realizada' ||
                                                                      empreendimento.status == 'Aprovado' ||
                                                                      empreendimento.status == 'Reprovado'}">green</c:if>

                                                        <c:if test="${empty empreendimento.status}">grey</c:if>" style="height: 27px; width: 27px; line-height: normal;">
                                                            1              
                                                        </button>
                                                        <p class="hide-on-small-only" style="margin-top: 0.3rem">Proposta Enviada</p>
                                                </li>

                                                <li class="col s2 m2 l2 marginTimeLine">
                                                    <button class="btn-floating circle 
                                                    <c:if test="${empreendimento.status == 'Apresentação Agendada' ||
                                                                  empreendimento.status == 'Apresentação Realizada' ||
                                                                  empreendimento.status == 'Avaliação Realizada' ||
                                                                  empreendimento.status == 'Aprovado' ||
                                                                  empreendimento.status == 'Reprovado'}">green</c:if>
                                                    <c:if test="${empty empreendimento.status ||
                                                                  empreendimento.status == 'Proposta Enviada'}">grey</c:if>" style="height: 27px; width: 27px; line-height: normal;">
                                                        2
                                                    </button>
                                                    <p class="hide-on-small-only" style="margin-top: 0.3rem">Apresentação Agendada</p>
                                                </li>

                                                <li class="col s2 m2 l2 marginTimeLine">
                                                    <button class="btn-floating circle 
                                                    <c:if test="${empreendimento.status == 'Apresentação Realizada' ||
                                                                  empreendimento.status == 'Avaliação Realizada' ||
                                                                  empreendimento.status == 'Aprovado' ||
                                                                  empreendimento.status == 'Reprovado'}">green</c:if>
                                                    <c:if test="${empty empreendimento.status ||
                                                                  empreendimento.status == 'Apresentação Agendada' ||
                                                                  empreendimento.status == 'Proposta Enviada'}">grey</c:if>" style="height: 27px; width: 27px; line-height: normal;">
                                                        3
                                                    </button>
                                                    <p class="hide-on-small-only" style="margin-top: 0.3rem">Apresentação Realizada</p>
                                                </li>

                                                <li class="col s2 m2 l2 marginTimeLine">
                                                    <button class="btn-floating circle 
                                                    <c:if test="${empreendimento.status == 'Avaliação Realizada' ||
                                                                  empreendimento.status == 'Aprovado' ||
                                                                  empreendimento.status == 'Reprovado'}">green</c:if>
                                                    <c:if test="${empty empreendimento.status ||
                                                                  empreendimento.status == 'Apresentação Realizada' ||
                                                                  empreendimento.status == 'Proposta Enviada' ||
                                                                  empreendimento.status == 'Apresentação Agendada'}">grey</c:if>" style="height: 27px; width: 27px; line-height: normal;">
                                                        4
                                                    </button>
                                                    <p class="hide-on-small-only" style="margin-top: 0.3rem">Avaliação Realizada</p>
                                                </li> 

                                                <li class="col s2 m2 l2">
                                                    <button class="btn-floating circle 
                                                    <c:if test="${empreendimento.status == 'Aprovado'}">green</c:if>
                                                    <c:if test="${empty empreendimento.status ||
                                                                  empreendimento.status == 'Apresentação Realizada' ||
                                                                  empreendimento.status == 'Proposta Enviada' ||
                                                                  empreendimento.status == 'Apresentação Agendada' ||
                                                                  empreendimento.status == 'Avaliação Realizada'}">grey</c:if>
                                                    <c:if test="${empreendimento.status == 'Reprovado'}">red</c:if>"  style="height: 27px; width: 27px; line-height: normal;">        
                                                        5
                                                    </button>
                                                <c:if test="${empreendimento.status != 'Reprovado'}">
                                                    <p class="hide-on-small-only" style="margin-top: 0.3rem">Aprovado</p>
                                                </c:if>
                                                <c:if test="${empreendimento.status == 'Reprovado'}">
                                                    <p class="hide-on-small-only" style="margin-top: 0.3rem">Reprovado</p>
                                                </c:if>
                                            </li>

                                        </ul>
                                        <!--<ul clss="col s12 m12 l12">
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
                                                <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Apresentação Agendada'), 'grey':!validStatus(empreendimento, 'Apresentação Agendada')}" style="height: 27px; width: 27px; line-height: normal;">
                                                    2
                                                </button>
                                                <p class="hide-on-small-only" style="margin-top: 0.3rem">Apresenta&ccedil;&atilde;o Agendada</p>
                                            </li>
                                            <li class="col s2 m2 l2">                                 
                                                <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Apresentação Realizada'), 'grey':!validStatus(empreendimento, 'Apresentação Realizada')}" style="height: 27px; width: 27px; line-height: normal;">
                                                    3
                                                </button>
                                                <p class="hide-on-small-only" style="margin-top: 0.3rem">Apresenta&ccedil;&atilde;o Realizada</p>
                                            </li>
                                            <li class="col s2 m2 l2">                                 
                                                <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Avaliação Realizada'), 'grey':!validStatus(empreendimento, 'Avaliação Realizada')}" style="height: 27px; width: 27px; line-height: normal;">
                                                    4
                                                </button>
                                                <p class="hide-on-small-only" style="margin-top: 0.3rem">Avalia&ccedil;&atilde;o Realizada</p>
                                            </li>
                                            <li class="col s2 m2 l2">                                 
                                                <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Aprovado'), 'grey':!validStatus(empreendimento, 'Aprovado')}" style="height: 27px; width: 27px; line-height: normal;">
                                                    5
                                                </button>
                                                <p class="hide-on-small-only" style="margin-top: 0.3rem">Aprovado</p>
                                            </li>                             
                                        </ul>-->
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <%--</c:if>--%>
                </c:forEach>

                <!--<div class="card-content">
                   <h6 data-ng-show="isEmpreendimentoListEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o h&aacute; empreendimentos encaminhados para voc&ecirc; nesse momento.</strong></h6>          
    
    
                   <ul class="collection card" data-ng-hide="isEmpreendimentoListEmpty()" data-collapsible="accordion">
                      <li class="collection-item" data-ng-repeat="empreendimento in empreendimentoList" >
                         <div class="card-panel valign-wrapper">
                            <span class="valign-wrapper">
                               <i class="material-icons valign">business</i>&nbsp;&nbsp;&nbsp;
                               <span data-ng-bind="empreendimento.nome" class="valign" style="font-size: 14pt;"></span>
                            </span>                        
                          
                         </div>
                         <div class="card-content">
                            <div class="row yellow accent-2" data-ng-if="empreendimento.status == 'Apresentação Agendada' && empreendimento.dataHoraApresentacao!== undefined">
                               <p class="center-align">Apresentação marcada para <strong>{{empreendimento.dataHoraApresentacao}}</strong></p>
                               <p class="center-align">Local: <strong>{{empreendimento.localApresentacao}}</strong></p>                              
                            </div>
                            <div class="row">
                               <span class="col s12 m6 l6">Status:&nbsp; <strong>{{empreendimento.status}}</strong></span>
                               <span class="col s12 m6 l6">Ramo de Atividade:&nbsp; <strong>{{empreendimento.ramoAtividade.nome}}</strong></span>                                               
                            </div>
                            <div class="row center">
                               <hr class="divider white" style="border: 0px"/>
                               <a data-ng-href="/gerenciador/empreendimento/{{empreendimento.id}}/avaliar" class="btn blue valign col s12 m4 l4 push-l1 push-m1 truncate" style="margin-left: 1rem; margin-top: 0.6rem; border-radius: 0px">Avaliar</a>
                               <a href="#!" class="btn yellow valign black-text accent-5 col s12 m4 l4 push-l2 push-m2 truncate" style="margin-left: 1rem; margin-top: 0.6rem; border-radius: 0px">Visualizar Informações</a> 
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
                                     <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Apresentação Agendada'), 'grey':!validStatus(empreendimento, 'Apresentação Agendada')}" style="height: 27px; width: 27px; line-height: normal;">
                                        2
                                     </button>
                                     <p class="hide-on-small-only" style="margin-top: 0.3rem">Apresenta&ccedil;&atilde;o Agendada</p>
                                  </li>
                                  <li class="col s2 m2 l2">                                 
                                     <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Apresentação Realizada'), 'grey':!validStatus(empreendimento, 'Apresentação Realizada')}" style="height: 27px; width: 27px; line-height: normal;">
                                        3
                                     </button>
                                     <p class="hide-on-small-only" style="margin-top: 0.3rem">Apresenta&ccedil;&atilde;o Realizada</p>
                                  </li>
                                  <li class="col s2 m2 l2">                                 
                                     <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Avaliação Realizada'), 'grey':!validStatus(empreendimento, 'Avaliação Realizada')}" style="height: 27px; width: 27px; line-height: normal;">
                                        4
                                     </button>
                                     <p class="hide-on-small-only" style="margin-top: 0.3rem">Avalia&ccedil;&atilde;o Realizada</p>
                                  </li>
                                  <li class="col s2 m2 l2">                                 
                                     <button class="btn-floating circle" data-ng-class="{'green': validStatus(empreendimento, 'Aprovado'), 'grey':!validStatus(empreendimento, 'Aprovado')}" style="height: 27px; width: 27px; line-height: normal;">
                                        5
                                     </button>
                                     <p class="hide-on-small-only" style="margin-top: 0.3rem">Aprovado</p>
                                  </li>                             
                               </ul>
                            </div>
                         </div>
                      </li>
                   </ul>
                </div>-->
            </div>

        </div>
    </div>
</div>