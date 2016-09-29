<%-- 
    Document   : proposta-new-aviso
    Created on : 29/09/2016, 09:04:53
    Author     : Rafael-pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Empreendimento</title>

        <%@include  file="../../templates/basic-style-empreendedor.jsp"%>
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
        </style>
        <%@include file="../../templates/basic-script-empreendedor.jsp" %>

        <script src="<c:url value="/js/painel-empreendedor/values/empreendimento-values.js"/>"></script>
        <script src="<c:url value="/js/painel-empreendedor/services/empreendimento-services.js"/>"></script>

        <script src="<c:url value="/js/painel-empreendedor/controllers/empreendimento-controller.js"/>"></script>      
    </head>
    <body>
        <%@include file="../../templates/top-bar-empreendedor.jsp" %>

        <nav class="navbar-top amber">
            <div class="nav-wrapper amber">
                <div class="col s12 m12 l12">
                    <a href="<c:url value="/empreendedor/home"/>" class="breadcrumb">Home</a>
                    <a href="<c:url value="/empreendedor/empreendimentos"/>" class="breadcrumb">Empreendimentos</a>
                    <a href="#!" class="breadcrumb"><strong>Cadastrar Proposta</strong></a>
                </div>
            </div>
        </nav>              
        <%@include file="../../templates/sidebar-empreendedor.jsp" %>

        <div class="area-principal">
            <div class="col s12 l8">

                <div class="col s12 m12 l12">
                    <div class="card-panel ember" style="padding-top: 0px !important">   
                        <div class="card center white">
                            <div class="card-content black-text valign-wrapper">
                                <i class="material-icons small valign">business</i>&nbsp;&nbsp;
                                <span class="card-title" style="font-size: 13pt">Atenção, você já cadastrou uma proposta para o empreendimento &nbsp;<strong>${empreendimento.nome}</strong> </span>
                            </div>
                        </div>

                        <div class="card-panel">
                            <p style="font-size: 15pt;font-weight: 300;">Verificamos em nosso sistema que você já realizou o envio de uma proposta para este empreendimento. No entanto, você pode realizar o cadastro de outra proposta, porém, a proposta anterior será descartada, ficando apenas esta proposta como válida.</p>
                            <div class="row">
                                <a class="blue btn col s12 m4 l4 push-l2 push-m2 valign" href="<c:url value="/empreendimento/${empreendimento.id}/enviar-proposta?novaProposta=1"/>" style="margin-right: 0.6rem;">Cadastrar Nova Proposta</a>
                            <a class="btn amber col s12 m4 l4 push-l2 push-m2 valign" href="<c:url value="/empreendedor/empreendimentos"/>">Cancelar</a>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
