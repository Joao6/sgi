<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Novo Candidato</title>

        <%@include file="../templates/basic-style.jsp"%>
        <link href="<c:url value="/css/apresentacao-negocio/apresentacao-negocio-style.css"/>" rel="stylesheet">

        <!-- Bootstrap -->        

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <%@include file="../templates/top-bar.jsp" %>


        <%@include file="../templates/sidebar-candidato.jsp" %>

        <section class="area-principal col-lg-10 col-md-8" id="empreendedor-new">
            <form method="POST">
                <fieldset>
                    <legend class="form-title">Cadastro de proposta para ${empreendimento.nome}</legend>
                    <%@include file="../templates/apresentacao-negocio/alert-proposta.jsp"%>
                    <%@include file="../templates/apresentacao-negocio/alert.jsp"%>
                    <div class="col-lg-8">
                        <div class="eixo">
                            <h3>Empreendedores</h3>
                            <div class="campo">
                                <h4>Mini-curriculo</h4>
                                <p>${apresentacao.miniCurriculo}</p>
                            </div>                          

                            <div class="campo">
                                <h4>
                                    Disponibilidade e comprometimento para o desenvolvimento para o desenvolvimento do negocio
                                </h4>
                                <p>${apresentacao.disponibilidade}</p>
                            </div>
                        </div>

                        <div class="eixo">
                            <h3>Aspectos tecnológicos</h3>
                            <div class="campo">
                                <h4>Descrição da inovação do produto, serviço ou processo</h4>
                                <p>${apresentacao.inovacaoProduto}</p>
                            </div>

                            <div class="campo">
                                <h4>Tempo necessário para o desenvolvimento do produto, serviço ou processo</h4>
                                <p>${apresentacao.tempoDesenvolvimento}</p>
                            </div>
                        </div>


                        <div class="eixo">
                            <h3>Aspectos financeiros</h3>
                            <div class="campo">
                                <h4>Investimento inicial a ser realizado</h4>
                                <p>${apresentacao.investimento}</p>
                            </div>

                            <div class="campo">
                                <h4>Retorno estimado do negócio</h4>
                                <p>${apresentacao.retorno}</p>
                            </div>
                        </div>


                        <div class="eixo">
                            <h3>Aspectos mercadológicos</h3>
                            <div class="campo">
                                <h4>Identificação de clientes, concorrentes e fornecedores</h4>
                                <p>${apresentacao.identificacao}</p>
                            </div>

                            <div class="campo">
                                <h4>Conhecimento do mercado alvo</h4>
                                <p>${apresentacao.mercadoAlvo}</p>
                            </div>

                            <div class="campo">
                                <h4>Vantagem competitiva comparada a concorrencia</h4>
                                <p>${apresentacao.vantagemCompetitiva}</p>
                            </div>
                        </div>



                        <div class="eixo">
                            <h3>Aspectos de gestão</h3>
                            <div class="campo">
                                <h4>Parcerias previstas ou firmadas para o desenvolvimento do negócio</h4>
                                <p>${apresentacao.parcerias}</p>
                            </div>

                            <div class="campo">
                                <h4>Estrutura organizacional proposta (pessoas e funcções)</h4>
                                <p>${apresentacao.estruturaOrganizacional}</p>
                            </div>
                        </div>
                        <c:if test="${empreendimento.status == null}">                          
                             <a href="#" data-toggle="modal" data-target="#modal-alert-proposta" class="btn btn-default">Enviar Proposta</a>
                        </c:if>
                            <c:if test="${empreendimento.status == 'Proposta Enviada' || empreendimento.status == 'Apresentação Agendada'}">
                            <a href="#" data-toggle="modal" data-target="#modal-alert" class="btn btn-default">Enviar Proposta Novamente</a>
                        </c:if>
                        <a href="<c:url value="/empreendimento/proposta/${apresentacao.empreendimento.id}/editar"/>" class="btn btn-primary" >Editar</a>
                        <a href="<c:url value="/empreendimentos"/>" class="btn btn-danger" >Voltar</a>
                    </div>                
                </fieldset>
            </form>
        </section>    

        <%@include  file="../templates/basic-script.jsp"%>
    </body>
</html>