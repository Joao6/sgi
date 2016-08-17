<%-- 
    Document   : new
    Created on : 12/07/2015, 23:26:26
    Author     : William
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Novo Criterio de Avaliacao</title>

        <%@include  file="../templates/basic-style.jsp"%>
        <!-- Bootstrap -->        

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <%@include file="../templates/top-bar.jsp" %>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>      
            <li><a href="<c:url value="/eixo/list"/>">Modelo de avaliação</a></li>
            <li><a href="<c:url value="/eixo/${eixo.id}/criterioAvaliacao/list"/>">Criterio de Avaliacao</a></li>
            <li class="active">Novo</li>
        </ol>

        <%@include file="../templates/side-bar.jsp" %>

        <section class="area-principal col-lg-10 col-sm-8" id="incubadora-new">
            <form method="POST" id="incubadora-form">
                <fieldset><legend class="form-title">Novo criterio para ${eixo.nome}</legend></fieldset>
                <div class="form-group">
                    <input type="text" name="id" id="incubadora-id" value="${criterioAvaliacao.id}" hidden="true"/>
                    <input type="hidden" name="eixo.id" value="${criterioAvaliacao.eixo.id}">

                    <div class="form-group">
                        <label class="control-label" for="nome">Nome</label>
                        <input class="form-control input-sm input-item value" type="text" value="${criterioAvaliacao.nome}" id="nome" name="nome" placeholder="Nome">
                        <c:if test="${errors.nome != null}"><p class="text-danger">${errors.nome}</p></c:if>
                    </div>


                    <c:if test="${editarCriterio == true}">
                        <div class="radio">
                            <label>
                                <input type="radio" name="ativo" value="true" <c:if test="${criterioAvaliacao.ativo == true}">checked</c:if>>
                                    Ativo
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="ativo"  value="false" <c:if test="${criterioAvaliacao.ativo == false}">checked</c:if>>
                                    Inativo
                                </label>
                            </div>
                    </c:if>

                </div>		 
                <button type="submit" id="btn-submit" class="btn botao">Salvar</button>
            </form>  
        </section>


        <%@include file="../templates/basic-script.jsp" %>
    </body>
</html>