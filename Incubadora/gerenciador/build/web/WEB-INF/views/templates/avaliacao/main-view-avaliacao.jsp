
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="EmpreendimentoCtrl">

    <div class="col s12 l8" >

        <div class="col s12 m12 l12">
            <div class="card-panel ember" style="padding-top: 0px !important">   
                <div class="card center white">
                    <div class="card-content black-text valign-wrapper">
                        <i class="material-icons small valign">business</i>&nbsp;&nbsp;
                        <span class="card-title"><strong>Avaliação do Empreendimento ${empreendimento.nome}</strong>&nbsp; </span>
                    </div>
                </div>

                <div class="card-content">                                                        
                    <div class="card-panel grey lighten-1">                          
                        <div class="card-content">
                            <div class="card-panel grey lighten-3">
                                <div class="card center white">
                                    <div class="card-content black-text valign-wrapper">
                                        <i class="material-icons small valign">description</i>&nbsp;&nbsp;
                                        <span class="card-title"><strong>Avaliação por avaliador&nbsp;</strong></span>
                                    </div>
                                </div>                                
                                <c:forEach items="${avaliacaoAvaliador}" var="notaEmpre">
                                    <div class="card-content">
                                        <div class="card-panel">
                                            <spam><strong>Avaliador ${notaEmpre.key}</strong></spam>
                                            <hr>
                                            <table class="table bordered">
                                                <thead>                                                    
                                                    <tr>
                                                        <th>Eixo</th>
                                                        <th>Critério</th>
                                                        <th>Nota</th>                                                        
                                                    </tr>
                                                </thead>
                                                <c:forEach items="${notaEmpre.value}" var="mapNota">
                                                    <tbody>                                                        
                                                        <tr>                                                            
                                                            <td>${mapNota.criterioAvaliacao.eixo.nome}</td>
                                                            <td>${mapNota.criterioAvaliacao.nome}</td>
                                                            <td>${mapNota.nota}</td>                                                            
                                                        </tr>  
                                                    </tbody>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="card-panel grey lighten-3">
                            <div class="card-content">
                                <div class="card-panel">
                                    <div class="card-content  black-text valign-wrapper">
                                        <i class="material-icons small valign">description</i>&nbsp;&nbsp;
                                        <span class="card-title" style="font-size: 19pt;"><strong>Nota final por avaliador por eixo</strong></span>
                                    </div>
                                </div>
                                <c:forEach items="${notaPorEixoAvalidor}" var="mapNotas">                                    
                                    <div class="card-panel">
                                        <div class="card-content">
                                            <table class="table bordered">
                                                <spam><strong>Avaliador ${mapNotas.key}</strong></spam>
                                                <hr>
                                                <thead>
                                                    <tr>
                                                        <th>Eixo</th>
                                                        <th>Nota</th>
                                                    </tr>
                                                </thead>
                                                <c:forEach items="${mapNotas.value}" var="eixo">
                                                    <tbody>
                                                        <tr>
                                                            <td>${eixo.key}</td>
                                                            <td>${eixo.value}</td>
                                                        </tr>
                                                    </tbody>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="card-content">
                            <div class="card-panel grey lighten-3">
                                <div class="card center white">
                                    <div class="card-content black-text valign-wrapper">
                                        <i class="material-icons small valign">description</i>&nbsp;&nbsp;
                                        <span class="card-title"><strong>Nota final por eixo&nbsp;</strong></span>
                                    </div>
                                </div> 
                                <div class="card-content">
                                    <div class="card-panel">
                                        <table class="table bordered">
                                            <thead>
                                                <tr>
                                                    <th>Eixo</th>                                            
                                                    <th>Nota Final</th>
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
                    </div>
                </div>
            </div>
        </div>
    </div>