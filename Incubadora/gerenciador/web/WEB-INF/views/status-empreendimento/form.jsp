<%-- 
    Document   : detail
    Created on : 13/07/2015, 14:24:32
    Author     : Edgard Lopes
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/css/status-empreendimento/status-style.css"/>"/>
        <title>Empreendimento</title>
        <jsp:include page="../templates/basic-style.jsp"/>
    </head>
    <body>
        <%@include file="../templates/top-bar.jsp"%>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>
            <li><a href="<c:url value="/incubadora/empreendimento" />">Empreendimento</a></li>
            <li><a href="#">${empreendimento.nome}</a></li>
            <li class="active">Alterar status</li>
        </ol>    

        <c:if test="${usuarioLogado.tipoUsuario == 1}">
            <%@include file="../templates/side-bar.jsp" %>
        </c:if>

        <section class="col-lg-10 col-sm-8 area-principal">
            <h1>Alterar status para ${empreendimento.nome}</h1>

            <h1>Status: ${empreendimento.status} <c:if test="${empreendimento.status == 'Apresentação Agendada'}"><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${empreendimento.dataHoraApresentacao}" /></c:if></h1>

            <c:choose>
                <c:when test="${empreendimento.status == 'Apresentação Agendada'}">
                    <a href="<c:url value="/empreendimento/${empreendimento.id}/status/3" />" class="btn btn-primary">Apresentacao realizada</a>
                </c:when>

                <c:when test="${empreendimento.status == 'Apresentação Realizada'}">
                    <p>É necessário aguardar a avaliação</p>
                </c:when>
                    
                <c:when test="${empreendimento.status == 'Avaliação Realizada'}">
                    <a href="<c:url value="/empreendimento/${empreendimento.id}/status/5"/>" class="btn btn-primary">Aprovado</a>
                    <a href="<c:url value="/empreendimento/${empreendimento.id}/status/6"/>" class="btn btn-primary">Reprovado</a>
                </c:when>
                    
                <c:when test="${empreendimento.status == 'Aprovado' ||  empreendimento.status == 'Não Aprovado' || empreendimento.status == 'Cancelado'}" >
                    <p>Processo finalizado</p>
                </c:when>
                    
                <c:when test="${empreendimento.status == null}">
                    <a href="<c:url value="/empreendimento/${empreendimento.id}/status/7"/>" class="btn btn-primary">Cancelar proposta</a>
                </c:when>
            </c:choose>
        </section>

        <jsp:include page="../templates/basic-script.jsp"/>
        <script>
            $('button.btn-agendar').click(function () {
                $('.form-agenda').toggleClass('invisible');
            });
        </script>
    </body>
</html>
