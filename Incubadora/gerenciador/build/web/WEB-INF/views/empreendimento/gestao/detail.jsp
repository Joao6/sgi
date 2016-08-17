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
        <%@include file="../../templates/top-bar.jsp"%>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>
            <li><a href="<c:url value="/incubadora/empreendimento"/>">Empreendimento</a></li>
            <li class="active">Detalhes</li>
        </ol>    

        <c:if test="${usuarioLogado.tipoUsuario == 2}">
            <%@include file="../../templates/sidebar-candidato.jsp" %>
        </c:if>
        <c:if test="${usuarioLogado.tipoUsuario == 1}">
            <%@include file="../../templates/side-bar.jsp" %>
        </c:if>
        <c:if test="${usuarioLogado.tipoUsuario == 3}">
            <%@include file="../../templates/side-bar-avaliador.jsp" %>
        </c:if>
        <section class="col-lg-10 col-sm-8 area-principal">


            <h3 id="title-detail-empreendimento">Empreendimento:<strong> ${empreendimento.nome}</strong></h3>
            <br><br>   

            <table class="table table-striped">
                <tr>
                    <th>CNPJ</th>
                    <th>Data de Abertura</th>
                    <th>Data de ingresso</th>
                </tr>

                <tr>
                    <td>
                        ${empreendimento.cnpj}
                    </td>
                    <td>
                        ${empreendimento.dataAbertura}
                    </td>
                    <td>
                        ${empreendimento.dataIngresso}
                    </td>
                </tr>
            </table>
        </section>

        <jsp:include page="../../templates/basic-script.jsp"/>
    </body>
</html>
