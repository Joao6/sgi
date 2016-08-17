<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Empreendimento</title>

        <jsp:include page="../templates/basic-style.jsp"/>
        <link href="<c:url value="/css/style-progress.css"/>" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <style>
            .duvida{
                background: peachpuff;
                border: #000 solid 1px;
            }

            .pergunta{
                background: #31b0d5;
            }

            .resposta{
                background: #438912;
            }
        </style>

        <%@include file="../templates/top-bar.jsp"%>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>
            <li><a href="<c:url value="/duvidas"/>">Duvidas</a></li>
            <li class="active">Responder</li>
        </ol>

        <c:if test="${usuarioLogado.tipoUsuario == 1}">
            <%@include file="../templates/side-bar.jsp" %>
        </c:if>

        <section class="col-lg-10 col-sm-8 area-principal" >
            <form method="POST">
                <br>
                <p>${duvida.duvida}</p>
                <div class="form-group">
                    <label for="resposta" class="control-label">Resposta</label>
                    <textarea class="form-control" rows="3" name="resposta" id="resposta"></textarea>
                    <c:if test="${errors.resposta != null}"><p class="text-danger">${errors.resposta}</p></c:if>
                    </div>

                    <button type="submit" class="btn btn-default">Enviar</button>

                </form>


            </section>


        <jsp:include page="../templates/basic-script.jsp"/>
    </body>
</html>