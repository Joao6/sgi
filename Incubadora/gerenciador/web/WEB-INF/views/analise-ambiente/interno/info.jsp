<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include  file="../../templates/basic-style.jsp"%>
        <link rel="stylesheet" href="<c:url value="/css/analise-ambiente/ambiente-style.css"/>"/>

    </head>
    <body>
        <%@include file="../../templates/top-bar.jsp"%>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/empreendedor/home"/>">Home</a></li>   
            <li><a href="<c:url value="/ambiente-interno"/>">Ambiente Interno</a></li>   
            <li class="active">Info</li>
        </ol> 
        <%@include file="../../templates/sidebar-candidato.jsp"%>
        <section class="area-principal col-lg-10 col-md-8" id="interno-list">
            <a href="<c:url value="/ambiente-interno/novo"/>" class="button btn btn-primary">
                <i class="glyphicon glyphicon-plus"></i> Ambiente Interno</a> 
            <form method="POST">
                <fieldset>
                    <legend class="form-title">Ambiente Interno: ${empreendimento.nome}</legend>

                    <div class="col-lg-8">
                        <div class="eixo">
                            <h3>Empreendedores</h3>
                            <div class="campo">
                                <h4>Pontos Fortes do Empreendedor</h4>
                                <p>${ambienteInterno.empreendedorForte}</p>
                            </div>                          

                            <div class="campo">
                                <h4>Pontos Fracos do Empreendedor</h4>
                                <p>${ambienteInterno.empreendedorFraco}</p>
                            </div>
                        </div>

                        <div class="eixo">
                            <h3>Aspectos tecnológicos</h3>
                            <div class="campo">
                                <h4>Pontos Fortes Tecnológicos</h4>
                                <p>${ambienteInterno.tecnologicoForte}</p>
                            </div>

                            <div class="campo">
                                <h4>Pontos Fracos Tecnológicos</h4>
                                <p>${ambienteInterno.tecnologicoFraco}</p>
                            </div>
                        </div>


                        <div class="eixo">
                            <h3>Aspectos financeiros</h3>
                            <div class="campo">
                                <h4>Pontos Fortes de Capital</h4>
                                <p>${ambienteInterno.capitalForte}</p>
                            </div>

                            <div class="campo">
                                <h4>Pontos Fracos de Capital</h4>
                                <p>${ambienteInterno.capitalFraco}</p>
                            </div>
                        </div>


                        <div class="eixo">
                            <h3>Aspectos mercadológicos</h3>
                            <div class="campo">
                                <h4>Pontos Fortes de Mercado</h4>
                                <p>${ambienteInterno.mercadoForte}</p>
                            </div>

                            <div class="campo">
                                <h4>Pontos Fracos de Mercado</h4>
                                <p>${ambienteInterno.mercadoFraco}</p>
                            </div>
                        </div>

                        <div class="eixo">
                            <h3>Aspectos de gestão</h3>
                            <div class="campo">
                                <h4>Pontos Fortes de Gestão</h4>
                                <p>${ambienteInterno.gestaoForte}</p>
                            </div>

                            <div class="campo">
                                <h4>Pontos Fracos de Gestão</h4>
                                <p>${ambienteInterno.gestaoFraco}</p>
                            </div>
                        </div>

                        <a href="<c:url value="/ambiente-interno/editar"/>" class="btn btn-primary" >Editar</a>
                        <a href="<c:url value="/ambiente-interno"/>" class="btn btn-danger" >Voltar</a>

                    </div>                
                </fieldset>
            </form>

        </section>
        <%@include file="../../templates/basic-script.jsp"%>
    </body>
</html>
