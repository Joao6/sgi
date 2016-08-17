
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
     <!--<link rel="stylesheet" href="<c:url value="/css/analise-ambiente/analise-ambiente-style.css"/> "/>--!>
     
      
        <jsp:include page="../../templates/basic-style.jsp"/>
      <link rel="stylesheet" href="<c:url value="/css/analise-ambiente/ambiente-style.css"/>"/>
        <!-- Bootstrap -->        

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    </ol>
    <%@include file="../../templates/sidebar-candidato.jsp" %>

    <section class="area-principal col-lg-10 col-sm-8" id="interno-new">
        <form method="POST">
            <fieldset>
                <legend class="form-title">Cadastro de Ambiente Interno para ${empreendimento.nome}</legend>

                <div class="col-lg-8">

                    <div class="eixo eixo1">
                        <h3>Empreendedores</h3>
                        <div class="form-group">
                            <label class="control-label" for="empreendedorForte">Pontos Fortes do Empreendedor</label>
                            <textarea id="empreendedorForte" class="form-control input-sm" name="empreendedorForte" cols="5" rows="3" value="${ambienteInertno.empreendedorForte}">${ambienteInertno.empreendedorForte}</textarea>
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="empreendedorFraco">
                                Pontos Fracos do Empreendedor
                            </label>
                            <textarea id="empreendedorFraco" class="form-control input-sm" name="empreendedorFraco" cols="5" rows="3" value="${ambienteInterno.empreendedorFraco}">${ambienteInterno.empreendedorFraco}</textarea>
                        </div>

                        <button type="button" class="btn btn-primary next2">Avançar</button>
                    </div>

                    <div class="eixo eixo2">
                        <h3>Aspectos tecnológicos</h3>
                        <div class="form-group">
                            <label class="control-label" for="tecnologicoForte">Pontos Fortes Tecnológicos</label>
                            <textarea id="tecnologicoForte" class="form-control input-sm" name="tecnologicoForte" cols="5" rows="3" value="${ambienteInterno.tecnologicoForte}">${ambienteInterno.tecnologicoForte}</textarea>
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="tecnologicoFraco">
                                Pontos Fracos Tecnológicos
                            </label>
                            <textarea id="tecnologicoFraco" class="form-control input-sm" name="tecnologicoFraco" cols="5" rows="3" value="${ambienteInterno.tecnologicoFraco}">${ambienteInterno.tecnologicoFraco}</textarea>
                        </div>
                        <button type="button" class="btn btn-primary previous1">Voltar</button>
                        <button type="button" class="btn btn-primary next3">Avançar</button>
                    </div>

                    <div class="eixo eixo3">
                        <h3>Aspectos financeiros</h3>
                        <div class="form-group">
                            <label class="control-label" for="capitalForte">Pontos Fortes de Capital</label>
                            <textarea id="capitalForte" class="form-control input-sm" name="capitalForte" cols="5" rows="3" value="${ambienteInterno.capitalForte}">${ambienteInterno.capitalForte}</textarea>
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="capitalFraco">
                                Pontos Fracos de Capital
                            </label>
                            <textarea id="capitalFraco" class="form-control input-sm" name="capitalFraco" cols="5" rows="3" value="${ambienteInterno.capitalFraco}">${ambienteInterno.capitalFraco}</textarea>
                        </div>
                        <button type="button" class="btn btn-primary previous2">Voltar</button>
                        <button type="button" class="btn btn-primary next4">Avançar</button>
                    </div>

                    <div class="eixo eixo4">
                        <h3>Aspectos mercadológicos</h3>
                        <div class="form-group">
                            <label class="control-label" for="mercadoForte">Pontos Fortes de Mercado</label>
                            <textarea id="mercadoForte" class="form-control input-sm" name="mercadoForte" cols="5" rows="3" value="${ambienteInterno.mercadoForte}">${ambienteInterno.mercadoForte}</textarea>
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="mercadoFraco">
                                Pontos Fracos de Mercado
                            </label>
                            <textarea id="mercadoFraco" class="form-control input-sm" name="mercadoFraco" cols="5" rows="3" value="${ambienteInterno.mercadoFraco}">${ambienteInterno.mercadoFraco}</textarea>
                        </div>
                        <button type="button" class="btn btn-primary previous3">Voltar</button>
                        <button type="button" class="btn btn-primary next5">Avançar</button>
                    </div>

                    <div class="eixo eixo5">
                        <h3>Aspectos gerenciais</h3>
                        <div class="form-group">
                            <label class="control-label" for="gestaoForte">Pontos Fortes de Gestão</label>
                            <textarea id="gestaoForte" class="form-control input-sm" name="gestaoForte" cols="5" rows="3" value="${ambienteInterno.gestaoForte}">${ambienteInterno.gestaoForte}</textarea>
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="gestaoFraco">
                                Pontos Fracos de Gestão
                            </label>
                            <textarea id="gestaoFraco" class="form-control input-sm" name="gestaoFraco" cols="5" rows="3" value="${ambienteInterno.gestaoFraco}">${ambienteInterno.gestaoFraco}</textarea>
                        </div>
                        <button type="button" class="btn btn-primary previous4">Voltar</button>
                        <button type="submit" id="btn-submit" class="btn botao">Salvar</button>
                    </div>
                    <a href="<c:url value="/ambiente-interno"/>" class="btn btn-danger" >Voltar</a>
                </div>
            </fieldset>
        </form>
    </section>

    <jsp:include page="../../templates/basic-script.jsp"/>      
    <jsp:include page="../../templates/basic-script.jsp"/>    

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