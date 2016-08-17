<%-- 
   Document   : new
   Created on : 30/07/2015, 13:56:46
   Author     : Aliane Leal
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Análise do Ambiente</title>
        <%@include  file="../../templates/basic-style.jsp"%>

    </head>
    <body>
        <%@include file="../../templates/top-bar.jsp"%>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/empreendedor/home"/>">Home</a></li>
            <li><a href="<c:url value="/ambiente-externo"/>">Ambiente Externo</a></li>
            <li class="active">Nova Análise do Ambiente Externo</li>
        </ol>
        <%@include file="../../templates/sidebar-candidato.jsp" %>
        <section class="area-principal" id="ambiente-externo">
            <form method="post">
                <fieldset><legend class="form-title">Ambiente Externo</legend></fieldset>
                <div class="form-externo">
                    <label id="label-oportunidade">Oportunidades</label>
                    <label id="label-ameaca" style="margin-left: 21%;">Ameaças</label>
                    <br>
                    <textarea id="textarea-oportunidade" name="oportunidades" rows="4" cols="50" value="${ambienteExterno.oportunidades}"></textarea>         
                    <textarea id="textarea-ameaca" name="ameacas" rows="4" cols="50" value="${ambienteExterno.ameacas}"></textarea>
                </div> 
                <button type="submit" id="btn-submit" class="btn botao">Salvar</button>
                <a href="<c:url value="/ambiente-externo"/>" class="btn btn-warning">Cancelar</a>
            </form>
        </section>

        <%@include file="../../templates/basic-script.jsp"%>
    </body>
</html>
