<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Novo Edital</title>

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
            <li><a href="<c:url value="/incubadora/empreendimento"/>">Empreendimentos</a></li>
            <li class="active">Novo</li>
        </ol>
        <%@include file="../../templates/sidebar-candidato.jsp" %>

        <section class="area-principal col-lg-10 col-sm-8" id="empreendimento-new">
            <form method="POST">
                <div>
                    <embed src="<c:url value="/pdf/exemplo.pdf"/>" width="800" height="500" alt="pdf" pluginspage="http://www.adobe.com/products/acrobat/readstep2.html">
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="aceite" value="true"> Aceito os termos do contrato
                    </label>
                </div>
                <button type="submit" class="btn btn-default">Pronto</button>
            </form>       
        </section>    

        <jsp:include page="../../templates/basic-script.jsp" />
        <script src="<c:url value="/js/jquery.mask.js"/>"></script>
        <script src="<c:url value="/js/jquery.validate.js"/>"></script>
<!--        <script src="<c:url value="/js/empreendimento/validacao-form.js"/>"></script>
        <script src="<c:url value="/js/empreendimento/empreendimentos-eventos.js"/>"></script>-->
    </body>
</html>