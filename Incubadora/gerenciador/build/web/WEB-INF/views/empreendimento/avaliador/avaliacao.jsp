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
        <title>Avaliar Proposta</title>

        <jsp:include page="../../templates/basic-style-empreendedor.jsp"/>

        <style>
            label{
                font-size: 13pt !important;           
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
                margin: 0 0 5px 0;

            }

            .criterio{
                border: #ddd solid 1px;                
            }

            .criterio p{
                text-align: center;
            }

            .input-criterio{
                float: right;
                width: 15%;
            }


            @media(min-width: 767px){

                .descricao{
                    margin: 0px;
                    width: 77%;
                    float: left;                                      
                }

                .input-criterio{
                    width: 30%;
                    float: right;
                }
            }

            .clear{
                clear: both;
            }

            .input-nota{
                margin: 0;
                width: 50%;
                float: left;
            }

            @media(max-width: 420px){

                #nomeEixo{
                    font-size: 14pt;
                    font-weight: bold;
                }

                .input-nota{
                    margin: 0;
                    width: 100%;
                    float: left;
                }
                
                .descricao{
                    margin: 0px;
                    width: 77%;
                    float: left;                                      
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
            <div class="card-panel ember" style="padding-top: 5px !important">   
                <div class="card center white">
                    <div class="card-content black-text valign-wrapper">
                        <i class="material-icons small valign">gavel</i>&nbsp;&nbsp;
                        <span class="card-title" style="font-size: 13pt">Avalia&ccedil;&atilde;o do empreendimento &nbsp;<strong>${empreendimento.nome}</strong> </span>
                    </div>
                </div>
                <c:if test="${eixoListSize < 1}">
                    <h5 class="center-align card-title card">Nenhum eixo cadastrado para avaliação.</h5>
                </c:if>

                <c:if test="${erro == true}">
                    <!--<h1>Campos Obrigatórios!</h1>-->
                    <script> Materialize.toast('Todos os campos são obrigatórios!', 3000, 'rounded red');</script>
                </c:if>                         

                <c:if test="${eixoListSize > 0}">
                    <c:if test="${avaliacao == false}">
                        <div class="card-panel white">
                            <div class="card-content valign-wrapper">
                                <form method="post" class="col s12 m12 l12">
                                    <c:forEach items="${eixoMap}" var="eixo" varStatus="id">
                                        <div class="eixo eixo${id.index} col s12 m12 l12">
                                            <h2 id="nomeEixo">Eixo ${eixo.key.nome}</h2>
                                            <table class="centered table">
                                                <th>Critério de Avaliação</th>
                                                <th>Nota</th>


                                                <c:forEach items="${eixo.value}" var="criterio">
                                                    <tr>
                                                        <c:set var="criterioID" value="${criterio.id}"/>
                                                        <c:if test="${criterio.ativo == true}">
                                                        <div class="criterio">                                                                                                            
                                                            <td><label for="${criterio.id}" class="black-text left-align descricao">${criterio.nome}</label></td>

                                                            <div class="input-criterio">                                                                
                                                                <div class="col s12 m12 l12">
                                                                    <input type="hidden" name="criterioID" value="${criterio.id}">                                                                    
                                                                    <td class="input-nota"><input type="text" name="criterioNota" id="${criterio.id}" placeholder="Insira a nota"></td>
                                                                    <!--value="<c:if test="${not empty notaMap}">${notaMap[criterio.id]}</c:if><c:if test="${empty notaMap}">${fields[criterio.id]}</c:if>"-->
                                                                    <%--<c:if test="${errors[criterio.id] != null}"><p class="text-danger">${errors[criterio.id]}</p></c:if>--%>
                                                                </div>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </div>
                                                    </c:if>
                                                    </tr>
                                                </c:forEach>

                                            </table>

                                            <c:if test="${id.index != 0}">
                                                <button type="button" class="btn btn-default amber darken-1 btn-previous col s12 m4 l2" style="margin: 0px 2px 5px 0px;">Anterior</button>
                                            </c:if>
                                            <c:if test="${id.index != eixoMapSize - 1}">        
                                                <button type="button" class="btn btn-default blue btn-next" style="margin-bottom: 5px;">Próximo</button>
                                            </c:if>
                                            <c:if test="${id.index == eixoMapSize - 1}">        
                                                <button type="submit" class="btn btn-default green btn-finalize col s12 m7 l4" style="margin-bottom: 5px;">Finalizar Avaliação</button>
                                            </c:if>

                                        </div>
                                    </c:forEach>

                                </form>
                            </div>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${avaliacao == true}">
                    <div class="card-panel grey lighten-3">
                        <div class="card-content">                                                         
                            <div class="card center white">
                                <div class="card-content black-text valign-wrapper">
                                    <i class="material-icons small valign">done</i>&nbsp;&nbsp;
                                    <span class="card-title" style="font-size: 13pt">Você já avaliou este empreendimento</span>
                                </div>
                            </div>
                            <div class="card-panel">
                                <div class="card-content">                                                                                                                                                                
                                    <!--<span class="card-title" style="font-size: 13pt">Estas foram as suas notas!</span>-->                                        

                                    <table class="table bordered">
                                        <thead>
                                            <tr>
                                                <th>Critério</th>                                            
                                                <th>Nota Atribuida</th>
                                            </tr>
                                        </thead>

                                        <c:forEach items="${avaliacaoEmpreendimento}" var="avaliacao">
                                            <tbody>
                                                <tr>
                                                    <td>${avaliacao.key}</td>
                                                    <td>${avaliacao.value}</td>
                                                </tr>
                                            </tbody>                                
                                        </c:forEach>                                                                                           
                                    </table>
                                </div>
                            </div>
                        </div>                            
                    </div>
                </c:if>
            </div>
        </div>                         

        <jsp:include page="../../templates/basic-script.jsp" />
        <script>
            $('.eixo').hide();
            $('.eixo0').show();

            $('.btn-next').click(function () {
                $(this).parent('.eixo').hide();
                $(this).parent('.eixo').next('.eixo').show();
            });

            $('.btn-previous').click(function () {
                $(this).parent('.eixo').hide();
                $(this).parent('.eixo').prev('.eixo').show();
            });

            $(document).ready(function () {
                $('.tooltipped').tooltip({delay: 50});
            });
        </script>

        <script src="<c:url value="/js/jquery.mask.js"/>"></script>
        <script src="<c:url value="/js/jquery.validate.js"/>"></script>


    </body>
</html>