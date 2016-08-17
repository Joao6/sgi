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
        <title>Novo Eixo</title>

        <jsp:include page="../templates/basic-style.jsp"/>
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
            <li><a href="<c:url value="/eixo/novo"/>">Eixo</a></li>
            <li class="active">Novo</li>
        </ol>

        <%@include file="../templates/side-bar.jsp" %>

        <section class="area-principal col-lg-10 col-sm-8" id="incubadora-new">
            <form method="POST" id="incubadora-form">
                <fieldset><legend class="form-title">Cadastro de Eixo</legend></fieldset>
                <div class="form-group">
                    <input type="text" name="id" id="incubadora-id" value="${eixo.id}" hidden="true"/>

                    <div class="form-group">
                        <label class="control-label" for="nome">Nome</label>
                        <input class="form-control input-sm input-item value" type="text" value="${eixo.nome}" id="nome" name="nome" placeholder="Nome do Eixo">
                        <c:if test="${errors.nome != null}"><p class="text-danger">${errors.nome}</p></c:if>
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="peso">Peso</label>
                            <input class="form-control input-sm input-item value" type="text" value="${eixo.peso}" id="peso" name="peso" placeholder="Peso">
                        <c:if test="${errors.peso != null}"><p class="text-danger">${errors.peso}</p></c:if>
                        </div>
                    </div>		 
                    <button type="submit" id="btn-submit" class="btn botao">Salvar</button>
                    <a href="<c:url value="/eixo/list"/>" class="btn btn-warning">Cancelar</a>
            </form>  
        </section>


        <jsp:include page="../templates/basic-script.jsp" />
    </body>
</html>