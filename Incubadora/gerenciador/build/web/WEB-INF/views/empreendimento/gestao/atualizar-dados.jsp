<%-- 
    Document   : atualizar-dados
    Created on : 04/10/2016, 09:06:16
    Author     : Rafael-pc
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Atualizar Dados do Empreendimento</title>

        <%@include  file="../../templates/basic-style.jsp"%>
        <%@include file="../../templates/basic-script.jsp" %>

        <link rel="stylesheet" href="<c:url value="/css/painel-administrativo/update-empreendimento.css"/>" />

        <script src="<c:url value="/js/painel-administrativo/values/update-empreendimento-value.js"/>"></script>
        <script src="<c:url value="/js/painel-administrativo/service/update-empreendimento-service.js"/>"></script>
        <script src="<c:url value="/js/painel-administrativo/controller/update-empreendimento-controller.js"/>"></script>

        <script src="<c:url value="/js/mask.js"/>"></script>

    </head>
    <body data-ng-app="painelAdmin">
        <%@include file="../../templates/top-bar.jsp" %>
        <nav class="navbar-top amber">
            <div class="nav-wrapper amber">
                <div class="col s12 m12 l12">
                    <a href="<c:url value="/incubadora/home"/>" class="breadcrumb">Home</a>
                    <a href="<c:url value="/incubadora/empreendimento"/>" class="breadcrumb"><strong>Empreendimentos</strong></a>
                    <a href="#!" class="breadcrumb"><strong>Atualizar Dados</strong></a>
                </div>
            </div>
        </nav>

        <%@include file="../../templates/side-bar.jsp" %> 

        <div class="area-principal">

            <div class="col s12 l8" data-ng-controller="UpdateCtrl">

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

                <div class="col s12 m12 l12">
                    <div class="card-panel ember" style="padding-top: 0px !important">   
                        <div class="card center white">
                            <div class="card-content black-text valign-wrapper">
                                <i class="material-icons small valign">business</i>&nbsp;&nbsp;
                                <span class="card-title"><strong class="hide-on-small-only">Atualizar dados do empreendimento</strong>&nbsp; </span>
                                <input type="hidden" id="idEmpree" value="${empreendimento.id}"/>
                            </div>

                        </div>

                        <div class="card-content">
                            <div class="row">
                                <div class="card-panel">
                                    <div class="row">                                        
                                        <span class="col s12 m6 l6">Status atual:&nbsp; <strong>{{empreendimento.status}}</strong></span>
                                    </div>
                                    <div class="row center">                                        
                                        <a data-ng-href="#!" id="btn-ver-proposta" data-ng-click="openModal(1, empreendimento)" class="btn modal-trigger blue white-text valign col s12 m6 l5 push-l1 push-m1 truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px">Proposta</a>
                                        <a data-ng-href="#!" id="btn-detalhes" data-ng-click="openModal(3, empreendimento)" class="btn blue valign white-text accent-5 col s12 m6 l5  push-l1  push-m1   truncate" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px;">Detalhes</a> 
                                        <!--<a data-ng-href="#!" id="btn-add-avaliador" class="btn blue valign white-text accent-5 col s12 m6 l5 push-l1 push-m1 truncate" data-ng-class="{'disabled': empreendimento.status == 'Reprovado' || empreendimento.status == 'Aprovado'}" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px;" data-ng-click="openModal(4, empreendimento)" data-ng-disabled="isAvaliadorListEmpty()"><span style="font-size: 16pt">+</span> Avaliador</a>--> 
                                        <a data-ng-href="#!" id="btn-add-empreendedor" class="btn blue white-text accent-5 col s12 m6 l8 push-l2 push-m1  truncate" data-ng-class="{'disabled': empreendimento.status == 'Reprovado' || empreendimento.status == 'Aprovado'}" style="margin-right: 1rem; margin-top: 0.6rem; border-radius: 0px;" data-ng-click="openModal(5, empreendimento)" data-ng-disabled="isEmpreendedorListEmpty()"><span style="font-size: 16pt">+</span> Empreendedor</a>                                                                                                                                                                
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="card-content">                            
                            <div class="row">
                                <form name="formEmpreendimento" method="post" class="card card-panel col s12 m12 l12">
                                    <div class="card-title" style="font-weight: 400;">Informações Gerais</div>
                                    <div class="divider"></div>
                                    <br/>
                                    <input hidden="true" id="status" value="${status}"/>
                                    <div class="row">
                                        <div class="col s12 m6 l6">
                                            <div class="input-field">
                                                <input type="text" name="nome" id="nome" placeholder="ex.: BuskImóveis" data-ng-model="empreendimento.nome"/>
                                                <label for="nome" class="grey-text">Nome&nbsp;<span class="orange-text">(Obrigat&oacute;rio)</span></label>
                                            </div>

                                            <div class="input-field">
                                                <input type="text" name="razaoSocial" id="razaoSocial" placeholder="ex.: Buscador de Imóveis Ltda" data-ng-model="empreendimento.razaoSocial"/>
                                                <label for="razaoSocial">Raz&atilde;o Social</label>
                                            </div> 

                                        </div>
                                        <div class="col s12 m6 l6">
                                            <label for="edital">Edital&nbsp;<span class="orange-text">(Obrigat&oacute;rio)</span></label>
                                            <select id="edital" class="browser-default" name="edital" data-ng-model="empreendimento.edital.id">
                                                <option value="" disabled="">Selecione...</option>                                                
                                                <!--<option data-ng-repeat="edital in editalList" data-ng-selected="empreendimento.edital.id === edital.id" value="{{edital.id}}">{{edital.nome}}</option>-->
                                                <option selected="" value="{{empreendimento.edital.id}}">{{empreendimento.edital.nome}}</option>
                                            </select>

                                            <label for="ramo">Ramo de Atividade&nbsp;<span class="orange-text">(Obrigat&oacute;rio)</span></label>
                                            <select id="ramo" class="browser-default" name="ramoAtividade" data-ng-model="empreendimento.ramoAtividade.id">
                                                <option value="" disabled="">Selecione...</option>                                                
                                                <option selected="" value="{{empreendimento.ramoAtividade.id}}">{{empreendimento.ramoAtividade.nome}}</option>
                                            </select>                                                      
                                        </div>
                                    </div>


                                    <div class="row">                        
                                        <div class="col s12 m6 l6">                          
                                            <div class="input-field">
                                                <input type="text" name="cnpj" id="razaoSocial" placeholder="ex.: 12.345.678/0001" data-ng-model="empreendimento.cnpj">
                                                <label for="cnpj">CNPJ</label>
                                            </div>                           
                                        </div>  

                                        <div class="input-field col s12 m6 l6">
                                            <textarea name="missao" class="materialize-textarea" id="missao" style="max-height: 105px; overflow-y: auto;"  placeholder="Limite máximo de 120 caracteres" data-ng-model="empreendimento.missao"></textarea>
                                            <label for="missao">Miss&atilde;o</label>
                                        </div>

                                    </div>

                                    <div class="row">

                                        <div class="col s12 m6 l6"> 
                                            <div class="input-field">
                                                <input data-ng-model="empreendimento.inscricaoEstadual" type="text" name="inscricaoEstadual" id="inscricaoEstadual" placeholder="ex.: 4125-55"/>
                                                <label for="inscricaoEstadual">Inscrição Estadual</label>
                                            </div>                            
                                        </div>                          

                                        <div class="col s12 m6 l6">
                                            <div class="input-field">
                                                <textarea name="visao" class="materialize-textarea" id="visao" style="max-height: 105px; overflow-y: auto;"  placeholder="Limite máximo de 120 caracteres" data-ng-model="empreendimento.visao"></textarea>
                                                <label for="visao">Vis&atilde;o</label>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col s12 m6 l6">
                                            <div class="input-field">
                                                <input data-ng-model="empreendimento.inscricaoMunicipal" type="text" name="inscricaoMunicipal" id="inscricaoMunicipal" placeholder="ex.: 4125-55"/>
                                                <label for="inscricaoMunicipal">Inscrição Municipal</label>
                                            </div> 
                                        </div>

                                        <div class="col s12 m6 l6">
                                            <div class="input-field">
                                                <textarea name="valores" class="materialize-textarea" id="visao" style="max-height: 105px; overflow-y: auto; "  placeholder="Limite máximo de 120 caracteres" data-ng-model="empreendimento.valores"></textarea>
                                                <label for="valores">Valores</label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col s12 m6 l6  grey  lighten-4">
                                            <div class="input-field">
                                                <input data-ng-model="empreendimento.dataAbertura" name="dataAbertura" type="text" class="data" placeholder="dd/mm/aaaa" id="dataAbertura"/>
                                                <label for="dataAbertura">Data de Abertura</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s12 m6 l6  grey  lighten-4">
                                                <div class="input-field">
                                                    <input data-ng-model="empreendimento.dataIngresso" name="dataIngresso" type="text" class="data" placeholder="dd/mm/aaaa" id="dataIngresso"/>
                                                    <label for="dataIngresso">Data de Ingresso</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col s12 m6 l6 grey  lighten-4">                                            
                                            <div class="input-field">
                                                <input data-ng-model="empreendimento.dataPrevisaoGraduacao" name="dataPrevisaoGraduacao" id="dataPrevisaoGraduacao" type="text" class="data" placeholder="dd/mmm/aaaa"/>
                                                <label for="dataPrevisaoGraduacao">Data de Previsão da Gradua&ccedil;&atilde;o</label>
                                            </div>
                                        </div>

                                        <div class="s12 m6 l6">
                                            <div class="center">
                                                <a href="<c:url value="/incubadora/empreendimento"/>" style="margin-top: 5%; margin-right: 2%;" class="btn-floating btn-small waves-effect waves-light  orange darken-4"><i class="material-icons">undo</i></a>
                                                <button data-ng-click="updateEmpreendimento(empreendimento)" style="margin-top: 5%" class="btn-floating btn-small waves-effect waves-light green"><i class="material-icons">done</i></button>                                        
                                            </div>
                                        </div>

                                    </div>                     
                                </form>                  
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        </div>
    </body>
</html>
