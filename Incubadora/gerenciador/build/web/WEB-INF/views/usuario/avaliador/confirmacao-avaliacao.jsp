<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Avaliar Proposta</title>

        <jsp:include page="../../templates/basic-style-empreendedor.jsp"/>

        <style>
            label{
                font-size: 12pt !important;           
            }
            button, a{
                border-radius: 0px !important;
            }


            @media screen and (min-width: 992px) {
                form .row .btn{
                    width: 49% !important;
                }
            }


            @media screen and (max-width: 992px) {
                form .row .btn{
                    margin-top: 1rem;
                }
            }

            .eixo{
                border: #CCC solid 1px;
                margin: 0 0 5px 0;

            }

            .criterio{
                border: #ddd solid 1px;                
            }

            .criterio p{
                text-align: center;
            }

            /*.input-criterio{
                float: right;
                width: 15%;
            }*/


            @media(min-width: 767px){

                .descricao{
                    margin: 0px;
                    width: 77%;
                    float: left;
                }

                .input-criterio{
                    width: 20%;
                    float: right;
                }
            }

            .clear{
                clear: both;
            }

            @media(max-width: 420px){

                #nomeEixo{
                    font-size: 14pt;
                    font-weight: bold;
                }
            }


        </style>

        <%@include file="../../templates/basic-script-empreendedor.jsp" %> 
        <script>
            $(document).ready(function () {
                $('ul.tabs').tabs('select_tab', 'tab_id');

            });
        </script>
        <%@include file="../../templates/top-bar.jsp" %>

        <script src="<c:url value="/js/painel-avaliador/app.js"/>"></script>
        <script src="<c:url value="/js/painel-avaliador/value/empreendimento-value.js"/>"></script>
        <script src="<c:url value="/js/painel-avaliador/service/empreendimento-services.js"/>"></script>
        <script src="<c:url value="/js/painel-avaliador/controller/empreendimento-controller.js"/>"></script>
        <script src="<c:url value="/js/painel-avaliador/filters/painel-avaliador-filters.js"/>"></script>

    </head>
    <body>

        <nav class="navbar-top amber">
            <div class="nav-wrapper amber">
                <div class="col s12 m12 l12">
                    <a href="<c:url value="/avaliador/home"/>" class="breadcrumb">Home</a>
                    <a href="<c:url value="/avaliador/empreendimentos"/>" class="breadcrumb">Empreendimentos</a>
                    <a href="#!" class="breadcrumb"><strong>Avaliar Empreendimento</strong></a>
                </div>
            </div>
        </nav>  
                    
        <%@include file="../../templates/sidebar-avaliador.jsp" %>
        
        <div class="col s12 m12 l8">
            <div class="card-panel ember">
                <div class="card center valign-wrapper">                    
                    <h4><span class="card-title">Empreendimento avaliado com sucesso, clique <a href="<c:url value="/avaliador/empreendimento/${empreendimento.id}/avaliar"/>">aqui</a> para ver as notas atribuídas à este empreendimento.</span></h4>
                </div>
            </div>
        </div>
        


    </body>
</html>