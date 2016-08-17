<%-- 
    Document   : info
    Created on : 18/08/2015, 10:01:10
    Author     : Aliane Leal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Empreendimento</title>
        <%@include file="../../imports/head-incubadora.jsp" %>
        <link rel="stylesheet" href="<c:url value="/css/empreendimento/empreendimento-style.css"/>"/>

        <jsp:include page="../../templates/basic-style.jsp"/>

    </head>
    <body>
        <%@include file="../../templates/top-bar.jsp"%>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/empreendedor/home"/>">Home</a></li>
            <li style="color:#FFF" class="active">Empreendimento</li>
        </ol>
        <%@include file="../../templates/empreendedor/side-bar.jsp"%>
        <section class="area-principal" id="empreendimento-info">
            <fieldset>
                <legend class="form-title" id="empreendimento-nome">${empreendimento.nome}</legend>        
            </fieldset>
            <a href="<c:url value="/empreendedor/empreendimento/${empreendimento.id}/atualizar"/>" id="atualizar">
                Atualizar
            </a>
            <form class="info-empreendimento">
                <table class="table">
                    <tr>
                        <th>Razão Social</th>
                        <th>CNPJ</th>
                        <th>Ramo de Atividade</th>
                    </tr>
                    <tr>
                        <td>
                            ${empreendimento.razaoSocial}
                        </td>
                        <td>
                            ${empreendimento.cnpj}
                        </td>
                        <td>
                            ${empreendimento.ramoAtividade.nome}
                        </td>
                    </tr>
                </table>

                <div class="emp-info">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingOne">
                                <h4 class="panel-title">
                                    <a class="info" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                        Missão
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                <div class="panel-body">
                                    ${empreendimento.missao}
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingTwo">
                                <h4 class="panel-title">
                                    <a class="info" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        Visão
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTow">
                                <div class="panel-body">
                                    ${empreendimento.visao}
                                </div>
                            </div>
                        </div>          
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingThree">
                                <h4 class="panel-title">
                                    <a class="info" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                        Valores
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                <div class="panel-body">
                                    ${empreendimento.valores}
                                </div>
                            </div>
                        </div>
                    </div>      
                    <div class="empreendedor-info">
                        <c:if test="${empreendimento.empreendedorList != null}">
                            <label id="label-empreendedor">Empreendedor(es):</label><br>
                            <c:forEach var="empreendedor" items="${empreendimento.empreendedorList}">
                                <label class="info-empreendedor">                  
                                    ${empreendedor.nome}
                                </label><br>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </form>
        </section> 
        <jsp:include page="../../templates/basic-script.jsp"/>
    </body>
</html>
