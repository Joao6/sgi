<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<link rel="stylesheet" href="<c:url value="/css/empreendedor/empreendedor-style.css"/>"/>!-->

<style>
    @media(max-width: 767px){
        .menu-lateral{
            display: none;
        }

        .btn-collapse{
            display: inline-block;
        }
    }


    @media(min-width: 767px){
        .btn-collapse{
            display: none;
        }

        .menu-lateral{
            display: inline-block;
        }
    }


</style>

<button type="button" class="btn btn-default btn-collapse"><span class="glyphicon glyphicon-align-justify"></span></button>
<div class="col-lg-2 col-sm-4">
    <div class="menu-lateral">
        <ul class="nav nav-pills nav-stacked">
            <h1>MENU</h1>

            <a href="<c:url value="/empreendimento/empreendedor"/>" class="ativo">
                <li class="item-thumbs ativo">
                    Empreendimento
                </li>
            </a>
            <a href="<c:url value="/acao"/>" class="ativo">
                <li class="item-thumbs ativo">
                    Ações
                </li>
            </a>
            <li>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                Análise de Ambiente
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <li>
                                <a href="<c:url value="/analise-ambiente-interno"/>">Ambiente Interno</a>
                            </li>
                            <li>
                                <a href="<c:url value="/analise-ambiente-externo"/>">Ambiente Externo</a>
                            </li>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
