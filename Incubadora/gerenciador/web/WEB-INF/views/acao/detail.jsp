<%-- 
    Document   : detail
    Created on : 17/08/2015, 15:22:57
    Author     : Aliane Leal
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ação</title>
        <%@include file="../imports/head-incubadora.jsp" %>
        <link rel="stylesheet" href="<c:url value="/css/acao/acao-style.css"/>"/>

        <%@include  file="../templates/basic-style.jsp"%>

    </head>
    <body>
        <%@include file="../templates/top-bar.jsp"%>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/empreendedor/home"/>">Home</a></li>
            <li><a href="<c:url value="/acao"/>">Ações</a></li>
            <li class="active">Detalhes</li>
        </ol>
        <%@include file="../templates/sidebar-candidato.jsp"%>
        <section class="area-principal" id="planejamento-detail">
            <h6 id="title-menu">Ação:<strong> ${acao.nome}</strong></h6>
            <br><br>   
            <table class="table table-striped">
                <tr>
                    <th>Data de Início</th>
                    <th>Data de Término</th>
                </tr>

                <tr>
                    <td>${acao.dataInicio}</td>
                    <td>${acao.dataFim}</td>
                </tr>
            </table>      
        </section>

        <%@include file="../templates/basic-script.jsp"%>

    </body>
</html>
