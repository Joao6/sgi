<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ambiente Externo</title>
        <%@include file="../../templates/basic-style.jsp"%>
        <link rel="stylesheet" href="<c:url value="/css/analise-ambiente/ambiente-style.css"/>"/>

    </head>
    <body>
        <%@include file="../../templates/top-bar.jsp"%>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/empreendedor/home"/>">Home</a></li>
            <li class="active">Ambiente Externo</li>
        </ol>
        <%@include file="../../templates/sidebar-candidato.jsp"%>
        <section class="area-principal" id="externo-list">
            <a href="<c:url value="/ambiente-externo/novo"/>" class="button btn btn-primary">
                <i class="glyphicon glyphicon-plus"></i> Ambiente Externo</a>
            <br><br>
            <div class="col-lg-8">
                <div class="eixo">
                    <h3>Ambiente Externo</h3>
                    <div class="campo">
                        <h4>Oportunidades</h4>
                        <p>${ambienteExterno.oportunidades}</p>
                    </div>                          

                    <div class="campo">
                        <h4>Ameaças</h4>
                        <p>${ambienteExterno.ameacas}</p>
                    </div>
                </div>  
                <a href="<c:url value="/ambiente-externo/editar"/>" class="btn btn-primary" >Editar</a>
                <a href="<c:url value="/ambiente-externo"/>" class="btn btn-danger" >Voltar</a>

            </div>


        </section>

        <%@include file="../../templates/basic-script.jsp"%>
    </body>
</html>