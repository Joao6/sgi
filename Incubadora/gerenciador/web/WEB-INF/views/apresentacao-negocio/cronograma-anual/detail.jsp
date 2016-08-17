<%-- 
    Document   : detail
    Created on : 13/07/2015, 14:17:08
    Author     : Aliane Leal
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cronograma-Anual</title>
        <script src="<c:url value="/js/jquery.js"/>"></script>
        <%@include file="../imports/head-incubadora.jsp" %>
        <link rel="stylesheet" href="<c:url value="/css/cronograma-anual/cronograma-style.css"/>">
    </head>
    <body>
        <%@include file="../templates/top-bar.jsp"%>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>
            <li><a href="<c:url value="/incubadora/cronograma-anual"/>">Cronograma Anual</a></li>
            <li class="active">Detalhes</li>
        </ol> 
        <%@include file="../templates/side-bar.jsp"%>
        <section class="area-principal" id="cronograma-detail" style="padding-bottom: 10px">
            <h6 id="title-detail-cronograma">Atividade:<strong> ${cronograma.atividade}</strong></h6>
            <br><br>
            <table class="table table-striped">
                <tr>
                    <th>Início</th>
                    <th>Término</th>
                    <th>Comentário</th>
                </tr>

                <tr>
                    <td>
                        ${cronograma.dataInicio}
                    </td>

                    <td>
                        ${cronograma.dataFim}
                    </td>
                    <td style="max-width: 300px">
                        <textarea class="form-control" cols="1" rows="1" style="" readonly="true">
                            ${cronograma.comentario}
                        </textarea>
                    </td>
                </tr>
            </table>

            <table class="table table-striped" style="margin-bottom: 0px">
                <tr>
                    <th>Respons&aacute;vel</th>
                </tr>
                <c:if test="${cronograma.gestorList != null}">
                    <c:forEach var="responsavel" items="${cronograma.gestorList}">
                        <tr>
                            <td>${responsavel.nome}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>

        </section>

    </body>
</html>
