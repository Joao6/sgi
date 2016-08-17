<%-- 
    Document   : detail
    Created on : 13/07/2015, 14:24:32
    Author     : Aliane Leal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empreendimento</title>
        <jsp:include page="../../templates/basic-style.jsp"/>
    </head>
    <body>
        <style>
            .check-empreendedor{
                display: inline;
            }
        </style>
        <%@include file="../../templates/top-bar.jsp"%>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>
            <li><a href="<c:url value="/incubadora/empreendimento"/>">Empreendimento</a></li>
            <li><a href="#">${empreendimento.nome}</a></li>
            <li class="active">Adicionar avaliador</li>
        </ol>    

        <c:if test="${usuarioLogado.tipoUsuario == 1}">
            <%@include file="../../templates/side-bar.jsp" %>
        </c:if>

        <section class="col-lg-10 col-sm-8 area-principal">
            <c:if test="${not empty avaliadorList}">
                <h1>Vincular avaliadores ao ${empreendimento.nome}</h1>

                <form method="POST">
                    <c:forEach var="avaliador" items="${avaliadorList}">
                        <div class="checkbox">
                            <label>
                                <input class="check-empreendedor" type="checkbox" value="${avaliador.id}" name="avaliador"
                                       <c:forEach var="emprendimentoAvaliador" items="${empreendimento.avaliadorList}">
                                           <c:if test="${emprendimentoAvaliador.id eq avaliador.id}">
                                               checked="true"
                                           </c:if>
                                       </c:forEach>
                                >
                                ${avaliador.nome}
                            </label>
                        </div>
                    </c:forEach>
                    <button type="submit" class="btn btn-default">Salvar</button>
                    <a href="<c:url value="/incubadora/empreendimento"/>" class="btn btn-default">Voltar</a>
                </form>
            </c:if>
            <c:if test="${empty avaliadorList}">
                <h1>Nenhum avaliador cadastrado!!</h1>
            </c:if>
        </section>

        <jsp:include page="../../templates/basic-script.jsp"/>
    </body>
</html>
