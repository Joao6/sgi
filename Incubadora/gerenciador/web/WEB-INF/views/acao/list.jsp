<%-- 
    Document   : list
    Created on : 13/08/2015, 11:53:44
    Author     : Aliane Leal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ação</title>
        <%@include file="../templates/basic-style.jsp" %>
        
    </head>
    <body>
        <%@include file="../templates/top-bar.jsp"%>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/empreendedor/home"/>">Home</a></li>
            <li class="active">Ação</li>
        </ol>
        <%@include file="../templates/sidebar-candidato.jsp"%>
        <section class="area-principal col-lg-10 col-sm-8" id="planejamento-list">
            <a href="<c:url value="/acao/novo"/>" class="btn-add btn btn-primary">
                <i class="glyphicon glyphicon-plus"></i> Adicionar Nova Ação</a>
            <br><br>  
            <table class="table table-striped table-hover">
                <tr>
                    <th>Ação</th>
                    <th>Responsável</th>
                    <th>Plano</th>
                    <th>Detalhes</th>
                    <th>Aterar</th>
                    <th>Excluir</th>
                </tr>
                <c:if test="${acaoList != null}">
                    <c:forEach var="acao" items="${acaoList}">
                        <%@include file="../templates/acao/alert.jsp" %>
                        <tr>
                            <td>
                                ${acao.nome}
                            </td>
                            <td>
                                ${acao.responsavel}
                            </td>
                            <td>
                                ${acao.categoria.nome}
                            </td>
                            <td>
                                <a href="<c:url value="/acao/${acao.id}/detalhes"/>">
                                    <i class="glyphicon glyphicon-search"></i>
                                </a>
                            </td>
                            <td>
                                <a href="<c:url value="/acao/${acao.id}/atualizar"/>">
                                    <i class="glyphicon glyphicon-edit"></i>
                                </a>
                            </td>
                            <td>
                                <a href="#" style="outline: transparent" data-toggle="modal" data-target="#modal-alert-${acao.id}">
                                    <i class="glyphicon glyphicon-trash"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </section>
            <%@include  file="../templates/basic-script.jsp"%>
    </body>
</html>
