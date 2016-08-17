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

        <%@include  file="../templates/basic-style.jsp"%>
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

                    <div class="col-lg-8">
                        <div class="eixo eixo1">
                            <h3>Eixo 1 de 5: Empreendedores</h3>
                            <div class="form-group">
                                <label class="control-label" for="miniCurriculo">Mini-curriculo</label>
                                <textarea id="miniCurriculo" class="form-control input-sm" name="miniCurriculo" data-toggle="tooltip" data-placement="top"
                                          data-original-title="Apresentar um resumo do curriculum"
                                          cols="5" rows="3" value="${apresentacao.miniCurriculo}">${apresentacao.miniCurriculo}</textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label" for="disponibilidade">
                                    Disponibilidade e comprometimento para o desenvolvimento para o desenvolvimento do negócio
                                </label>
                                <textarea id="disponibilidade" class="form-control input-sm" name="disponibilidade" data-toggle="tooltip" data-placement="top"
                                          data-original-title="Disponibilidade para o empreendimento"
                                          cols="5" rows="3" value="${apresentacao.disponibilidade}">${apresentacao.disponibilidade}</textarea>
                            </div>

                            <button type="button" class="btn btn-primary next2">Avançar</button>
                        </div>

                        <div class="eixo eixo2">
                            <h3>Eixo 2 de 5: Aspectos tecnológicos</h3>
                            <div class="form-group">
                                <label class="control-label" for="inovacaoProduto">Descrição da inovação do produto, serviço ou processo</label>
                                <textarea id="inovacaoProduto" class="form-control input-sm" name="inovacaoProduto" data-toggle="tooltip" data-placement="top"
                                          data-original-title="Qual o seu produto/serviço/processo ?"
                                          cols="5" rows="3" value="${apresentacao.inovacaoProduto}">${apresentacao.inovacaoProduto}</textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label" for="tempoDesenvolvimento">
                                    Tempo necessário para o desenvolvimento do produto, serviço ou processo
                                </label>
                                <textarea id="tempoDesenvolvimento" class="form-control input-sm" name="tempoDesenvolvimento" data-toggle="tooltip" data-placement="top"
                                          data-original-title="Tempo de incubação necessária"
                                          cols="5" rows="3" value="${apresentacao.tempoDesenvolvimento}">${apresentacao.tempoDesenvolvimento}</textarea>
                            </div>


                            <button type="button" class="btn btn-primary previous1">Voltar</button>
                            <button type="button" class="btn btn-primary next3">Avançar</button>
                        </div>


                        <div class="eixo eixo3">
                            <h3>Eixo 3 de 5: Aspectos financeiros</h3>
                            <div class="form-group">
                                <label class="control-label" for="investimento">Investimento inicial a ser realizado</label>
                                <textarea id="investimento" class="form-control input-sm" name="investimento" data-toggle="tooltip" data-placement="top"
                                          data-original-title="Capital inicial"
                                          cols="5" rows="3" value="${apresentacao.investimento}">${apresentacao.investimento}</textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label" for="retorno">
                                    Retorno estimado do negócio
                                </label>
                                <textarea id="retorno" class="form-control input-sm" name="retorno" data-toggle="tooltip" data-placement="top"
                                          data-original-title="O valor esperado do retorno"
                                          cols="5" rows="3" value="${apresentacao.retorno}">${apresentacao.retorno}</textarea>
                            </div>

                            <button type="button" class="btn btn-primary previous2">Voltar</button>
                            <button type="button" class="btn btn-primary next4">Avançar</button>
                        </div>


                        <div class="eixo eixo4">
                            <h3>Eixo 4 de 5: Aspectos mercadológicos</h3>
                            <div class="form-group">
                                <label class="control-label" for="identificacao">Identificação de clientes, concorrentes e fornecedores</label>
                                <textarea id="identificacao" class="form-control input-sm" name="identificacao" data-toggle="tooltip" data-placement="top"
                                          data-original-title="Stakeholders"
                                          cols="5" rows="3" value="${apresentacao.identificacao}">${apresentacao.identificacao}</textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label" for="mercadoAlvo">
                                    Conhecimento do mercado alvo
                                </label>
                                <textarea id="mercadoAlvo" class="form-control input-sm" name="mercadoAlvo" data-toggle="tooltip" data-placement="top"
                                          data-original-title="Entendimento do ramo do empreendimento"
                                          cols="5" rows="3" value="${apresentacao.mercadoAlvo}">${apresentacao.mercadoAlvo}</textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label" for="vantagemCompetitiva">
                                    Vantagem competitiva comparada a concorrência
                                </label>
                                <textarea id="vantagemCompetitiva" class="form-control input-sm" name="vantagemCompetitiva"  data-toggle="tooltip" data-placement="top"
                                          data-original-title="Diferencial em relação a concorrência" 
                                          cols="5" rows="3" value="${apresentacao.vantagemCompetitiva}">${apresentacao.vantagemCompetitiva}</textarea>
                            </div>

                            <button type="button" class="btn btn-primary previous3">Voltar</button>
                            <button type="button" class="btn btn-primary next5">Avançar</button>
                        </div>



                        <div class="eixo eixo5">
                            <h3>Eixo 5 de 5: Aspectos de gestão</h3>
                            <div class="form-group">
                                <label class="control-label" for="parcerias">Parcerias previstas ou firmadas para o desenvolvimento do negócio</label>
                                <textarea id="parcerias" class="form-control input-sm" name="parcerias" data-toggle="tooltip" data-placement="top"
                                          data-original-title="Sócios"
                                          cols="5" rows="3" value="${apresentacao.parcerias}">${apresentacao.parcerias}</textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label" for="estruturaOrganizacional">
                                    Estrutura organizacional proposta (pessoas e funções)
                                </label>
                                <textarea id="estruturaOrganizacional" class="form-control input-sm" name="estruturaOrganizacional" data-toggle="tooltip" data-placement="top"
                                          data-original-title="Matriz de responsabilidade"
                                          cols="5" rows="3" value="${apresentacao.estruturaOrganizacional}">${apresentacao.estruturaOrganizacional}</textarea>
                            </div>

                            <button type="button" class="btn btn-primary previous4">Voltar</button>
                            <button type="submit" class="btn btn-success">Concluir</button>
                        </div>

                        <a href="<c:url value="/empreendimentos"/>" class="btn btn-danger" >Voltar</a>
                    </div>

                </fieldset>
            </form>
        </section>    

        <%@include file="../templates/basic-script.jsp"%>
        <script>
            $('.eixo').hide();
            $('.eixo1').show();

            $('.next2').click(function () {
                $('.eixo1').hide();
                $('.eixo2').show();
            });

            $('.next3').click(function () {
                $('.eixo2').hide();
                $('.eixo3').show();
            });

            $('.previous1').click(function () {
                $('.eixo2').hide();
                $('.eixo1').show();
            });

            $('.next4').click(function () {
                $('.eixo3').hide();
                $('.eixo4').show();
            });

            $('.previous2').click(function () {
                $('.eixo3').hide();
                $('.eixo2').show();
            });

            $('.next5').click(function () {
                $('.eixo4').hide();
                $('.eixo5').show();
            });

            $('.previous3').click(function () {
                $('.eixo4').hide();
                $('.eixo3').show();
            });

            $('.previous4').click(function () {
                $('.eixo5').hide();
                $('.eixo4').show();
            });
    
           

        </script>
    </body>
</html>