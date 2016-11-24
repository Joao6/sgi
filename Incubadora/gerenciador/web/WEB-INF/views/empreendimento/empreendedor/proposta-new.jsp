<%-- 
    Document   : list
    Created on : 19/02/2016, 11:49:28
    Author     : Developer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Empreendimento</title>

        <%@include  file="../../templates/basic-style-empreendedor.jsp"%>
        <style>
            label{
                font-size: 12pt !important;           
            }
            button, a{
                border-radius: 0px !important;
            }


            @media screen and (min-width: 992px) {
                form .row .btn{
                    width: 49% !important;
                }
            }


            @media screen and (max-width: 992px) {
                form .row .btn{
                    margin-top: 1rem;
                }
            }
        </style>
        <%@include file="../../templates/basic-script-empreendedor.jsp" %>

        <script src="<c:url value="/js/painel-empreendedor/values/empreendimento-values.js"/>"></script>
        <script src="<c:url value="/js/painel-empreendedor/services/empreendimento-services.js"/>"></script>

        <script src="<c:url value="/js/painel-empreendedor/controllers/empreendimento-controller.js"/>"></script>


    </head>
    <body data-ng-app="painelEmpreendedor" >
        <%@include file="../../templates/top-bar-empreendedor.jsp" %>

        <nav class="navbar-top amber">
            <div class="nav-wrapper amber">
                <div class="col s12 m12 l12">
                    <a href="<c:url value="/empreendedor/home"/>" class="breadcrumb">Home</a>
                    <a href="<c:url value="/empreendedor/empreendimentos"/>" class="breadcrumb">Empreendimentos</a>
                    <a href="#!" class="breadcrumb"><strong>Cadastrar Proposta</strong></a>
                </div>
            </div>
        </nav>              
        <%@include file="../../templates/sidebar-empreendedor.jsp" %>

        <input hidden="true" id="empreendimento-id" value="${empreendimento.id}"/>
        <!-- Área Principal -->
        <div class="area-principal" data-ng-controller="EmpreendimentoCtrl">

            <div class="col s12 l8" >

                <div class="col s12 m12 l12">
                    <div class="card-panel ember" style="padding-top: 0px !important">   
                        <div class="card center white">
                            <div class="card-content black-text valign-wrapper">
                                <i class="material-icons small valign">business</i>&nbsp;&nbsp;
                                <span class="card-title" style="font-size: 13pt">Cadastro de proposta para o empreendimento &nbsp;<strong>${empreendimento.nome}</strong> </span>
                            </div>

                        </div>

                        <h6 data-ng-show="isEmpreendimentoListEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o existem empreendimentos cadastrados!</strong></h6>          
                        <!-- FORM 1 -->
                        <ul class="collection card">
                            <li class="collection-item" data-ng-show="isActive(1)">
                                <div class="card-panel valign-wrapper">
                                    <span class="valign-wrapper">
                                        <i class="material-icons valign">group</i>&nbsp;&nbsp;&nbsp;
                                        <span class="valign" style="font-size: 14pt;">Empreendedores</span>
                                    </span>                        
                                    <span style="height: 27px; width: 27px; line-height: normal; right: 3rem; position: absolute"><i class="material-icons purple-text small">looks_one</i></span>
                                </div>
                                <div class="card card-content">
                                    <form name="form1">
                                        <div class="row">
                                            <div class="col s12 m12 l12">
                                                <label for="miniCurriculo">Mini curr&iacute;culo</label>
                                                <textarea id="miniCurriculo" placeholder="Máx. 400 caracteres." data-ng-required="true" data-ng-model="apresentacaoNegocio.miniCurriculo" class="materialize-textarea validate" maxlength="400"></textarea>

                                                <label for="disponibilidade" style="margin-top: 2rem;">Disponibilidade e comprometimento para o desenvolvimento do negócio</label>
                                                <textarea id="disponibilidade" style="margin-bottom: 2rem !important" placeholder="Máx. 400 caracteres." data-ng-required="true" data-ng-model="apresentacaoNegocio.disponibilidade" class="materialize-textarea" maxlength="400"></textarea>

                                                <a href="/gerenciador/empreendedor/empreendimentos" class="btn orange valign white-text accent-5 col s12 m12 l6 truncate">Cancelar</a> 
                                                <button data-ng-click="setNextForm(2)" data-ng-disabled="!form1.$valid" class="btn blue valign col s12 m12 l6 right truncate">Pr&oacute;ximo</button>

                                            </div>
                                        </div>
                                    </form>                     
                                </div>
                            </li>
                            <!-- FORM 2 -->
                            <li class="collection-item" data-ng-show="isActive(2)">
                                <div class="card-panel valign-wrapper">
                                    <span class="valign-wrapper">
                                        <i class="material-icons valign">memory</i>&nbsp;&nbsp;&nbsp;
                                        <span class="valign" style="font-size: 14pt;">Aspectos Tecnol&oacute;gicos</span>
                                    </span>                        
                                    <span style="height: 27px; width: 27px; line-height: normal; right: 3rem; position: absolute"><i class="material-icons purple-text small">looks_two</i></span>
                                </div>
                                <div class="card card-content">
                                    <form name="form2">
                                        <div class="row">
                                            <div class="col s12 m12 l12">
                                                <label for="descricao">Descri&ccedil;&atilde;o da inova&ccedil;&atilde;o do produto, servi&ccedil;o ou processo.</label>
                                                <textarea id="descricao" placeholder="Máx. 400 caracteres." data-ng-required="true" data-ng-model="apresentacaoNegocio.inovacaoProduto" class="materialize-textarea validate" maxlength="400"></textarea>

                                                <label for="disp" style="margin-top: 2rem">Tempo necess&aacute;rio para o desenvolvimento do produto, servi&ccedil;o ou processo</label>
                                                <textarea id="disp" placeholder="Máx. 400 caracteres." style="margin-bottom: 2rem !important" data-ng-required="true" data-ng-model="apresentacaoNegocio.tempoDesenvolvimento" class="materialize-textarea" maxlength="400"></textarea>


                                                <button data-ng-click="setNextForm(1)" class="btn blue valign white-text accent-5 col s12 m12 l6 truncate">Voltar</button> 
                                                <button data-ng-click="setNextForm(3)" data-ng-disabled="!form2.$valid" class="btn blue valign col s12 m12 l6 right truncate">Pr&oacute;ximo</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <!-- FORM 3 -->
                            <li class="collection-item" data-ng-show="isActive(3)">
                                <div class="card-panel valign-wrapper">
                                    <span class="valign-wrapper">
                                        <i class="material-icons">local_atm</i>&nbsp;&nbsp;&nbsp;
                                        <span class="valign" style="font-size: 14pt;">Aspectos Financeiros</span>
                                    </span>                        
                                    <span style="height: 27px; width: 27px; line-height: normal; right: 3rem; position: absolute"><i class="material-icons purple-text small">looks_3</i></span>
                                </div>
                                <div class="card card-content">
                                    <form name="form3">
                                        <div class="row">
                                            <div class="col s12 m12 l12">
                                                <label for="investimento">Investimento inicial a ser realizado.</label>
                                                <textarea id="investimento" placeholder="Máx. 400 caracteres." data-ng-required="true" data-ng-model="apresentacaoNegocio.investimento" class="materialize-textarea validate" maxlength="400"></textarea>

                                                <label for="resumo" style="margin-top: 2rem">Retorno estimado do neg&oacute;cio.</label>
                                                <textarea id="resumo" placeholder="Máx. 400 caracteres." style="margin-bottom: 2rem !important"  data-ng-required="true" data-ng-model="apresentacaoNegocio.retorno" class="materialize-textarea" maxlength="400"></textarea>

                                                <button data-ng-click="setNextForm(2)" class="btn blue valign white-text accent-5 col s12 m12 l6 truncate">Voltar</button> 
                                                <button data-ng-click="setNextForm(4)" data-ng-disabled="!form3.$valid" class="btn blue valign col s12 m12 l6 right truncate">Pr&oacute;ximo</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>

                            <!-- FORM 4 -->
                            <li class="collection-item" data-ng-show="isActive(4)">
                                <div class="card-panel valign-wrapper">
                                    <span class="valign-wrapper">
                                        <i class="material-icons">trending_up</i>&nbsp;&nbsp;&nbsp;
                                        <span class="valign" style="font-size: 14pt;">Aspectos Mercadol&oacute;gicos</span>
                                    </span>                        
                                    <span style="height: 27px; width: 27px; line-height: normal; right: 3rem; position: absolute"><i class="material-icons purple-text small">looks_4</i></span>
                                </div>
                                <div class="card card-content">
                                    <form name="form4">
                                        <div class="row">
                                            <div class="col s12 m12 l12">
                                                <label for="identificacao">Identifica&ccedil;&atilde;o de clientes, concorrentes e fornecedores.</label>
                                                <textarea id="identificacao" placeholder="Máx. 400 caracteres." data-ng-required="true" data-ng-model="apresentacaoNegocio.identificacao" class="materialize-textarea validate" maxlength="400"></textarea>

                                                <label for="conhecimento" style="margin-top: 2rem">Conhecimento de mercado alvo.</label>
                                                <textarea id="conhecimento" placeholder="Máx. 400 caracteres." data-ng-required="true" data-ng-model="apresentacaoNegocio.mercadoAlvo" class="materialize-textarea" maxlength="400"></textarea>

                                                <label for="vantagem" style="margin-top: 2rem;">Vantagem competitiva comparada &agrave; concorr&ecirc;ncia.</label>
                                                <textarea id="vantagem" placeholder="Máx. 400 caracteres." style="margin-bottom: 2rem !important"  data-ng-required="true" data-ng-model="apresentacaoNegocio.vantagemCompetitiva" class="materialize-textarea" maxlength="400"></textarea>                                 

                                                <button data-ng-click="setNextForm(3)" class="btn blue valign white-text accent-5 col s12 m12 l6 truncate">Voltar</button> 
                                                <button data-ng-click="setNextForm(5)" data-ng-disabled="!form4.$valid" class="btn blue valign col s12 m12 l6 right truncate">Pr&oacute;ximo</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>

                            <li class="collection-item" data-ng-show="isActive(5)">
                                <div class="card-panel valign-wrapper">
                                    <span class="valign-wrapper">
                                        <i class="material-icons">dashboard</i>&nbsp;&nbsp;&nbsp;
                                        <span class="valign" style="font-size: 14pt;">Aspectos de Gest&atilde;o</span>
                                    </span>                        
                                    <span style="height: 27px; width: 27px; line-height: normal; right: 3rem; position: absolute"><i class="material-icons purple-text small">looks_5</i></span>
                                </div>
                                <div class="card card-content">
                                    <form name="form5">
                                        <div class="row">
                                            <div class="col s12 m12 l12">
                                                <label for="parcerias">Parcerias previstas ou firmadas para o desenvolvimento do neg&oacute;cio.</label>
                                                <textarea id="parcerias" placeholder="Máx. 400 caracteres." data-ng-required="true" data-ng-model="apresentacaoNegocio.parcerias" class="materialize-textarea validate" maxlength="400"></textarea>

                                                <label for="estrutura" style="margin-top: 2rem">Estrutura organizacional proposta.</label>
                                                <textarea id="estrutura" placeholder="Máx. 400 caracteres." style="margin-bottom: 2rem !important" data-ng-required="true" data-ng-model="apresentacaoNegocio.estruturaOrganizacional" class="materialize-textarea" maxlength="400"></textarea>


                                                <button data-ng-click="setNextForm(4)" class="btn blue valign white-text accent-5 col s12 m12 l6 truncate">Voltar</button> 
                                                <a data-ng-href="/gerenciador/empreendedor/empreendimentos" class="btn orange valign col s12 m12 l6 right truncate">Cancelar</a>                                 
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s12 m12 l12">
                                                <button data-ng-click="addApNegocio(apresentacaoNegocio)" id="btn-salvar-apNegocio" data-ng-disabled="!form5.$valid" class="btn green valign white-text accent-5 truncate" style="width: 100% !important">Salvar</button> 
                                                <!--// BARRA DE PROGRESSO PARA A CONCLUSÃO DO MÉTODO -->
                                                <div class="row" data-ng-show="showProgress">
                                                    <div class="progress">
                                                        <div class="indeterminate"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>

                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>   
</body>
</html>
