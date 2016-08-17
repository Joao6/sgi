<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Empreendedor</title>

        <jsp:include page="../../templates/basic-style.jsp"/>
        <!-- Bootstrap -->        

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <%@include file="../../templates/top-bar.jsp" %>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>
            <li class="active">Empreendedores</li>
        </ol>

        <%@include file="../../templates/side-bar.jsp" %>

        <section class="area-principal col-lg-10 col-sm-8" id="empreendedor-list">
           
                <c:if test="${empreendedorList != null}">

                <table class="table table-striped table-hover">
                    <tr>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Telefone</th>
                        <th>CPF</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                    <c:forEach var="empreendedor" items="${empreendedorList}">
                        <tr>

                            <td>${empreendedor.nome}</td>
                            <td>${empreendedor.email}</td>
                            <td>${empreendedor.telefone}</td>
                            <td>${empreendedor.cpf}</td>
                            <td>
                                <a href="<c:url value="/empreendedor/atualizar/${empreendedor.id}"/>"><i class="glyphicon glyphicon-edit"></i></a>
                            </td>
                            <td>
                                <a href="#" style="outline: transparent" data-toggle="modal" data-target="#modal-alert-${empreendedor.id}"><i class="glyphicon glyphicon-trash"></i></a>
                            </td>
                        </tr>
                        <%@include file="../../templates/empreendedor/alert.jsp" %>
                    </c:forEach>
                </table> 

            </c:if>
            <c:if test="${empreendedorList eq null}">
                <h3>Não há empreendedor cadastrado</h3>
            </c:if>
        </section>
        <jsp:include page="../../templates/basic-script.jsp"/>
    </body>
</html>
