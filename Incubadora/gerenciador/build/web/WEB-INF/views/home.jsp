

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title></title>
        <%@include file="templates/basic-header-home.jsp" %>
    </head>

    <body data-ng-app="loginApp">
        <!-- // TOPBAR // -->
        <%@include file="../views/templates/home/top-bar.jsp" %>

        <!-- // PARALAX // -->
        <div id="index-banner" class="parallax-container">
            <div class="slider">
                <ul class="slides">
                    <li>
                        <img src="/gerenciador/img/home/bg-tec.jpg"> <!-- random image -->
                        <div class="amber-text caption center-align">
                            <h3>Incubadora de Empresas de Base Tecnológica da FAI</h3>
                            <h5 class="light grey-text text-lighten-3">Edital Aberto, inscreva seu projeto. Para vê-lo, click <a href="#!">aqui</a></h5>
                            <a href="<c:url value="/incubadora/candidato/novo"/>" style="margin-top: 5%" class="btn-large waves-effect waves-light teal amber accent-4" >Inscreva-se</a>
                        </div>


                    </li>
                    <li>
                        <img src="/gerenciador/img/home/intef-bg.jpg"> <!-- random image -->
                        <div class="amber-text caption center-align">
                            <h3>Seja um empreendedor de sucesso!</h3>
                            <h5 class="light grey-text text-lighten-3">Edital Aberto, inscreva seu projeto.</h5>
                            <a href="<c:url value="/incubadora/candidato/novo"/>" style="margin-top: 5%" class="btn-large waves-effect waves-light teal amber accent-4" >Inscreva-se</a>
                        </div>
                    </li>                 
                </ul>
            </div>
        </div>


        <div class="container">
            <div class="section">

                <!--   Icon Section   -->
                <div class="row">
                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center amber-text"><i class="mdi-action-account-balance"></i></h2>
                            <h5 class="center">FAI</h5>

                            <p class="light">A FAI forma mais de X empreendedores por ano, possui conceito máximo de qualidade na avaliação do MEC.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center amber-text"><i class="mdi-hardware-memory"></i></h2>
                            <h5 class="center">Incubadora de base tecnol&oacute;gica</h5>

                            <p class="light">Profissionais altamente experientes em projetos de base tecnol&oacute;gica lhe dar&atilde;o todo o suporte necess&aacute;rio
                                entrar e sobreviver no mercado.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center amber-text"><i class="mdi-image-wb-incandescent"></i></h2>
                            <h5 class="center">O mais importante &eacute; a sua ideia.</h5>

                            <p class="light">N&oacute;s lhe daremos todo o suporte para criar seu Plano de Neg&oacute;cio caso ainda n&atilde;o tenha um.</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>


        <div class="parallax-container valign-wrapper">
            <div class="section no-pad-bot">
                <div class="container">
                    <div class="row center">
                        <h4 class="header col s12 light amber-text">Possu&iacute;mos parcerias para incentivo a empresas de base tecnol&oacute;gica</h4>
                    </div>
                </div>
            </div>
            <div class="parallax"><img src="<c:url value="/img/home/background1.jpg"/>" alt="Unsplashed background img 2"></div>
        </div>

        <div class="container">
            <div class="section">

                <div class="row">
                    <div class="col s12 center">
                        <h3><i class="mdi-content-send amber-text" style="font-size: 48px"></i></h3>
                        <h4>Entre em contato</h4>
                        <p class="center-align light" text-amber>Estamos &agrave; disposi&ccedil;&atilde;o para esclarecer suas d&uacute;vidas. Entre em contato enviando um e-mail para
                            <strong>intef@fai-mg.br</strong>.
                        </p>
                    </div>
                </div>

            </div>
        </div>


        <footer class="page-footer amber">
            <div class="container">
                <div class="row">
                    <div class="col l6 s12">
                        <h5 class="white-text">Sobre n&oacute;s</h5>
                        <p class="grey-text text-lighten-4">Somos uma empresa consolidada a mais de 25 anos nas &aacute;reas de Gest&atilde;o e Tecnologia.</p>
                    </div>
                </div>
            </div>
            <div class="footer-copyright">
                <div class="container black-text ">
                    FAI - <a class=" black-text text-lighten-3" href="http://www.fai-mg.br">Centro de Ensino Superior em Gest&atilde;o, Tecnologia e 
                        Educa&ccedil;&atilde;o.</a>
                </div>
            </div>
        </footer>


        <!--  Scripts-->
        <script src="<c:url value="/js/materialize.js"/>"></script>
        <script src="<c:url value="/js/init.js"/>"></script>


    </body>

</html>