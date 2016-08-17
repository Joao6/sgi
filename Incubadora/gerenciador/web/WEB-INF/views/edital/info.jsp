<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <link rel="stylesheet" href="<c:url value="/css/edital/edital-style.css"/>"/>
        <title>Edital</title>
        
        <jsp:include page="../templates/basic-style.jsp"/>
        <!-- Bootstrap -->        

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body >
        <jsp:include page="../templates/top-bar.jsp" />

        <ol class="breadcrumb" >
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>      
            <li class="active"><a href="<c:url value="/incubadora/editais"/>">Edital</a></li>
            <li class="active">${edital.nome}</li>
        </ol>

        <jsp:include page="../templates/side-bar.jsp" />

        <div class="area-principal container">      
            <div class="col-lg-5 edital-info">

                <h3>Edital <span class="titulo">${edital.nome}</span></h3>

                <fmt:formatDate pattern="dd/MM/yyyy" value="${edital.dataInicio}" var="dataInicio"/>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${edital.dataFim}" var="dataFim"/>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${edital.dataProrrogacao}" var="dataProrrogacao"/>

                <span class="titulo">Duracao:</span>
                <p>${dataInicio} a ${dataFim} </p>

                <c:if test="${dataProrrogacao != null}">
                    <span class="titulo">Prorrogado até:</span>
                    <p> ${dataProrrogacao}</p>
                </c:if>

                <span class="titulo">Cadastrado por: </span>
                <p>${edital.gestor.nome}</p>

                <span class="titulo">Descrição:</span>
                <p>${edital.resumo}</p>
            </div>

            <div class="col-lg-5">
                <h3>Opcoes</h3>
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="<c:url value="/incubadora/edital/atualizar/${edital.id}"/>"><span class="glyphicon glyphicon-pencil"></span>Editar</a></li>
                    <li>
                        <a href="#" class="opcaoExcluir"><span class="glyphicon glyphicon-trash"></span>Excluir</a>
                        <div class="invisible" id="menuExcluir">
                            <p>Excluir?</p>
                            <a href="<c:url value="/incubadora/edital/${edital.id}/excluir"/>" class="btn btn-success">Sim</a>
                            <button class="btn btn-warning">Não</button>
                        </div>
                    </li>
                    <c:if test="${dataProrrogacao == null}">
                        <li>                    
                            <a href="#" class="opcaoProrrogar"><span class="glyphicon glyphicon-time"></span>Prorrogar</a>
                            <div class="input-group invisible" id="form-prorrogacao">
                                <form method="POST" action="<c:url value="/incubadora/edital/prorrogar"/>">                        
                                    <input type="hidden" id="id" name="id" value="${edital.id}"/>
                                    <div class="input-group">
                                        <input type="date" id="input-prorrogacao" name="dataProrrogacao" class="form-control">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default btn-prorrogar" type="submit">Prorrogar</button>
                                        </span>
                                    </div>
                                </form>
                            </div>
                        </li>
                    </c:if>
                    <li><a href="<c:url value="/incubadora/editais"/>"><span class="glyphicon glyphicon-list"></span>Voltar</a></li>
                </ul>
            </div>
        </div>

        <jsp:include page="../templates/basic-script.jsp"/>
        <script>
            $('.opcaoProrrogar').click(function () {
                $('#form-prorrogacao').toggleClass('invisible');
            });

            $('.opcaoExcluir').click(function () {
                $('#menuExcluir').toggleClass('invisible');
            });

            $('.btn-warning').click(function () {
                $('#menuExcluir').toggleClass('invisible');
            });
        </script>
    </body>
</html>