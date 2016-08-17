<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Criterio de Avaliacao</title>

        <%@include  file="../templates/basic-style.jsp"%>
        <!-- Bootstrap -->        

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body >

        <%@include  file="../templates/top-bar.jsp" %>

        <ol class="breadcrumb" >
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>      
            <li><a href="<c:url value="/eixo/list"/>">Modelo de avaliação</a></li>
            <li class="active">Criterio de Avaliacao</li>
        </ol>

        <%@include  file="../templates/side-bar.jsp" %>
        <a href="<c:url value="/eixo/${eixo.id}/criterioAvaliacao/novo"/>" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus"></span>
            Novo Criterio
        </a>
        <div class="area-principal col-lg-10 col-sm-8">
            <h1>Critérios cadastrados para ${eixo.nome}</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Ativo</th>                       
                        <th>Excluir</th>                       
                        <th>Editar</th>                       
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="criterioAvaliacao" items="${criterioAvaliacaoList}">
                        <%@include file="../templates/criterio-avaliacao/alert.jsp" %>
                        <tr>
                            <td>${criterioAvaliacao.nome}</td>
                            <td>
                                <input type="checkbox" <c:if test="${criterioAvaliacao.ativo == true}">checked</c:if>>
                                </td>
                                <td>
                                    <a href="#" data-toggle="modal" data-target="#modal-alert-${criterioAvaliacao.id}" style="outline: transparent"><i class="glyphicon glyphicon-trash"></i></a>

                            </td>
                            <td>
                                <a href="<c:url value="/criterioAvaliacao/${criterioAvaliacao.id}/editar"/>"><span class="glyphicon glyphicon-pencil"></span></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="<c:url value="/eixo/list" />" class="btn btn-default">Voltar</a>
        </div>

        <%@include file="../templates/basic-script.jsp"%>
    </body>
</html>