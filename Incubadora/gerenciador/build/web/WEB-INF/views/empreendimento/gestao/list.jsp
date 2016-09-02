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
        <style>
            label,
            label:hover,
            label:visited{
                color: blue !important;
            }
            h5{
                padding: 1%;
            }

            .collection .collection-item {
                background-color: rgba(96, 125, 139, 0.12);
                line-height: 1.5rem;
                padding: 10px 20px;
                margin-bottom: 1.2%;
                border-bottom: 1px solid #e0e0e0;

            }

            #btn-fechar:hover{
                box-shadow: 50px 50px 55px #888888;
            }


            .collection{
                border:0px !important;
                border-left: 0px !important;
                border-right: 0px !important;
                padding-left: 1%;
                padding-right: 1%;
                padding-top: 1%;

            }

            blockquote{
                border-color: #7986cb;
            }

            .pratica-field,
            .responsavel-field{
                background-color: transparent !important;
                padding-bottom: 2% !important;
            }


            .card.select-group{
                background-color: rgba(58, 124, 183, 0.09) !important;
            }
            .chip {
                display: inline-block;
                height: 32px;
                font-size: 13px;
                font-weight: 500;
                color: rgb(255, 255, 255);
                line-height: 32px;
                padding: 0 12px;
                border-radius: 16px;
                background-color: rgba(96, 125, 139, 0.52);
                margin-right: 1%;
                margin-top: 2%;
            }

            .chip span a:hover{
                color:#eeff41 !important;
            }

        </style>
        <%@include file="../../templates/basic-script.jsp" %>
        <script src="<c:url value="/js/painel-administrativo/values/empreendimento-values.js"/>"></script>
        <script src="<c:url value="/js/painel-administrativo/service/empreendimento-services.js"/>"></script>
        <script src="<c:url value="/js/painel-administrativo/controller/empreendimento-controller.js"/>"></script>

        <script src="<c:url value="/js/jquery.mask.js"/>"></script>
        <script>
            $(document).ready(function () {


                $("#txt-message").hide();

                $(".date").mask("00r00r0000", {
                    translation: {
                        'r': {
                            pattern: /[\/]/,
                            fallback: '-'
                        },
                        placeholder: "__/__/____"
                    }
                });

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