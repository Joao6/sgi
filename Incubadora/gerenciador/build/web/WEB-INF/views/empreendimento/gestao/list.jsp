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
        <title>Empreendimentos</title>

        <%@include file="../../templates/basic-style.jsp" %>
        <link rel="stylesheet" href="<c:url value="/css/materializeGeeksLabs.css"/>" />


        <%@include file="../../templates/basic-script.jsp" %>
        <script src="<c:url value="/js/painel-administrativo/values/empreendimento-values.js"/>"></script>
        <script src="<c:url value="/js/painel-administrativo/service/empreendimento-services.js"/>"></script>
        <script src="<c:url value="/js/painel-administrativo/controller/empreendimento-controller.js"/>"></script>

        <script src="<c:url value="/js/jquery.mask.js"/>"></script>
        <script>
            $(document).ready(function () {


                $("#txt-message").hide();

                $(".date").mask("99/99/9999");

                $('.time').mask('00:00');
            });
            //            $('#textarea1').val('New Text');
            //            $('#textarea1').trigger('autoresize');
        </script>

    </head>
    <body data-ng-app="painelAdmin">

        <%@include file="../../templates/top-bar.jsp" %>
        <nav class="navbar-top amber">
            <div class="nav-wrapper amber">
                <div class="col s12 m12 l12">
                    <a href="<c:url value="/incubadora/home"/>" class="breadcrumb">Home</a>
                    <a href="#!" class="breadcrumb"><strong>Empreendimentos</strong></a>
                </div>
            </div>
        </nav>
        <%@include file="../../templates/side-bar.jsp" %> 
        <%@include file="../../templates/empreendimento/gestao/main-view-empreendimento-gestao-list.jsp" %>
    </body>
</html>