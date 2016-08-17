<%-- 
    Document   : new
    Created on : 30/07/2015, 13:59:17
    Author     : Aliane Leal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../imports/head-incubadora.jsp" %>
        <link rel="stylesheet" href="<c:url value="/css/acao/acao-style.css"/>"/>
        <script src="<c:url value="/js/acao/acao-eventos.js"/>"></script>
        <script src="<c:url value="/js/acao/acao-validacao.js"/>"></script>
        <title>Ação</title>

        <%@include file="../templates/basic-style.jsp"%>

    </head>
    <body>
        <%@include file="../templates/top-bar.jsp"%>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/empreendedor/home"/>">Home</a></li>
            <li><a href="<c:url value="/acao"/>">Ações</a></li>
            <li class="active">Novo</li>
        </ol>
        <%@include file="../templates/sidebar-candidato.jsp" %>

        <section class="area-principal col-lg-10 col-sm-8" id="planejamento-new"> 

            <div class="row">
                <div class="col-lg-12">
                    <form method="post" class="form-group" id="form-planejamento">
                        <fieldset>
                            <legend class="form-title">
                                A&ccedil;&atilde;o
                                <select class="categoria required" id="categoria" name="categoria.id">
                                    <option value="0">Escolha um tipo de Plano</option>
                                    <c:if test="${categorias != null}">
                                        <c:forEach var="categoria" items="${categorias}">
                                            <option value="${categoria.id}" 
                                                    <c:if test="${categoria.id eq acao.categoria.id}">
                                                        selected
                                                    </c:if>>
                                                ${categoria.nome}
                                            </option>
                                        </c:forEach>
                                    </c:if>
                                </select>                  
                            </legend>
                        </fieldset>   

                        <div class="form-inline-planejamento">
                            <input class="input-planejamento required"  name="nome"type="text" placeholder="Nome da Ação" value="${acao.nome}"/>               
                        </div>
                        <div class="form-inline-planejamento">
                            <input class="input-planejamento required" name="responsavel" type="text" placeholder="Responsável" value="${acao.responsavel}"/>
                        </div>

                        <div class="form-inline-planejamento input-group">                     
                            <input type="date" name="dataInicial" id="dataInicial" class="input-planejamento required"   <fmt:formatDate pattern="dd/MM/YYYY" value="${acao.dataInicio}" />  value="${acao.dataInicio}"/>               
                            <div class="input-group-addon">Data de In&iacute;cio</div>
                        </div>

                        <div class="form-inline-planejamento input-group">
                            <input type="date" name="dataFinal" id="dataFinal" class="input-planejamento required" <fmt:formatDate pattern="dd/MM/YYYY" value="${acao.dataFim}"/> value="${acao.dataFim}" />
                            <div class="input-group-addon">Data de T&eacute;rmino</div>
                        </div>

                        <div class="col-lg-offset-8">
                            <div id="orientacoes-popover" title="Orientações" data-content="${orientacao}" data-container="body" data-toggle="popover" data-placement="bottom">                     
                            </div>
                        </div>
                        <input type="text" hidden="true" id="status" value="${update}"/>
                        <button type="submit" id="btn-submit" class="btn botao">Salvar</button>
                        <a href="<c:url value="/acao"/>" class="btn btn-warning">Cancelar</a>
                    </form>            
                </div>            
            </div>
            <div class="row">
            </div>
        </section>

        <%@include file="../templates/basic-script.jsp"%>

    </body>
</html>
