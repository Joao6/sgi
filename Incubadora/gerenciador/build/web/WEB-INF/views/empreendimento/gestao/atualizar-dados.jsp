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
                                            <select id="edital" class="browser-default" name="edital">
                                                <option value="" disabled="">Selecione...</option>                                                
                                                <c:forEach items="${editalList}" var="edital">
                                                    <c:if test="${empreendimento.edital.id eq edital.id}">
                                                        <option selected="" value="${edital.id}">${edital.nome}</option>
                                                    </c:if>
                                                    <c:if test="${empreendimento.edital.id != edital.id}">
                                                        <option value="${edital.id}">${edital.nome}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>

                                            <label for="ramo">Ramo de Atividade&nbsp;<span class="orange-text">(Obrigat&oacute;rio)</span></label>
                                            <select id="ramo" class="browser-default" name="ramoAtividade">
                                                <option value="" disabled="">Selecione...</option>                                                
                                                <c:forEach items="${ramoList}" var="ramo">
                                                    <c:if test="${empreendimento.ramoAtividade.id eq ramo.id}">
                                                        <option selected="" value="${ramo.id}">${ramo.nome}</option>
                                                    </c:if>
                                                    <c:if test="${empreendimento.ramoAtividade.id != ramo.id}">
                                                        <option value="${ramo.id}">${ramo.nome}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>                                                      
                                        </div>
                                    </div>


                                    <div class="row">                        
                                        <div class="col s12 m6 l6">                          
                                            <div class="input-field">
                                                <input type="text" name="cnpj" id="razaoSocial" placeholder="ex.: 12.345.678/0001" value="${empreendimento.cnpj}">
                                                <label for="cnpj">CNPJ</label>
                                            </div>                           
                                        </div>  

                                        <div class="input-field col s12 m6 l6">
                                            <textarea name="missao" class="materialize-textarea" id="missao" style="max-height: 105px; overflow-y: auto;"  placeholder="Limite máximo de 120 caracteres">${empreendimento.missao}</textarea>
                                            <label for="missao">Miss&atilde;o</label>
                                        </div>

                                    </div>

                                    <div class="row">

                                        <div class="col s12 m6 l6"> 
                                            <div class="input-field">
                                                <input value="${empreendimento.inscricaoEstadual}" type="text" name="inscricaoEstadual" id="inscricaoEstadual" placeholder="ex.: 4125-55"/>
                                                <label for="inscricaoEstadual">Inscrição Estadual</label>
                                            </div>                            
                                        </div>                          

                                        <div class="col s12 m6 l6">
                                            <div class="input-field">
                                                <textarea name="visao" class="materialize-textarea" id="visao" style="max-height: 105px; overflow-y: auto;"  placeholder="Limite máximo de 120 caracteres">${empreendimento.visao}</textarea>
                                                <label for="visao">Vis&atilde;o</label>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col s12 m6 l6">
                                            <div class="input-field">
                                                <input value="${empreendimento.inscricaoMunicipal}" type="text" name="inscricaoMunicipal" id="inscricaoEstadual" placeholder="ex.: 4125-55"/>
                                                <label for="inscricaoMunicipal">Inscrição Municipal</label>
                                            </div> 
                                        </div>

                                        <div class="col s12 m6 l6">
                                            <div class="input-field">
                                                <textarea name="valores" class="materialize-textarea" id="visao" style="max-height: 105px; overflow-y: auto; "  placeholder="Limite máximo de 120 caracteres">${empreendimento.valores}</textarea>
                                                <label for="valores">Valores</label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col s12 m6 l6  grey  lighten-4">
                                            <div class="input-field">
                                                <input value="<fmt:formatDate pattern="dd/MM/yyyy" value="${empreendimento.dataAbertura}"/>" name="dataAbertura" type="text" class="data" placeholder="dd/mm/aaaa" id="dataAbertura"/>
                                                <label for="dataAbertura">Data de Abertura</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col s12 m6 l6  grey  lighten-4">
                                                <div class="input-field">
                                                    <input value="<fmt:formatDate pattern="dd/MM/yyyy" value="${empreendimento.dataIngresso}"/>" name="dataIngresso" type="text" class="data" placeholder="dd/mm/aaaa" id="dataIngresso"/>
                                                    <label for="dataIngresso">Data de Ingresso</label>


                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col s12 m6 l6 grey  lighten-4">
                                            <label for="prevGraduacao">Data de Previsão da Gradua&ccedil;&atilde;o</label>
                                            <input value="<fmt:formatDate pattern="dd/MM/yyyy" value="${empreendimento.dataPrevisaoGraduacao}"/>" name="dataPrevisaoGraduacao" id="prevGraduacao" type="text" class="data" placeholder="dd/mmm/aaaa"/>
                                        </div>

                                        <div class="s12 m6 l6">
                                            <div class="center">
                                                <a href="<c:url value="/incubadora/empreendimento"/>" style="margin-top: 5%; margin-right: 2%;" class="btn-floating btn-small waves-effect waves-light  orange darken-4"><i class="material-icons">undo</i></a>
                                                <button type="submit" style="margin-top: 5%" class="btn-floating btn-small waves-effect waves-light green"><i class="material-icons">done</i></button>                                        
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
