<%-- 
    Document   : main-view-avaliador-info
    Created on : 13/05/2016, 14:42:26
    Author     : Joao
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="MainCtrlAvaliador">
    <div class="col s12 l8">

        <div class="col s12 m12 l12">
            <div class="card center white">
                <div class="card-content black-text valign-wrapper">
                    <i class="material-icons small material-icons">info</i>&nbsp;&nbsp;
                    <span class="card-title valign"><strong class="hide-on-small-only">Informa&ccedil;&otilde;es</strong>
                </div>
            </div>
        </div>

        <!-- Modal Success -->
        <div id="modal-success" class="modal amber">            
            <div class="modal-content white-text">
                <div class="row">
                    <div class="col s12 m12 l12">
                        <div class="card-panel white">
                            <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                            <h5 class="black-text">
                                Avaliador exclu&iacute;do com sucesso!
                            </h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="#!"  class=" modal-action modal-close waves-effect waves-green btn-flat">Voltar</a>
            </div>
        </div>

        <!-- Modal Error -->
        <div id="modal-error" class="modal amber">            
            <div class="modal-content white-text">
                <div class="row">
                    <div class="col s12 m12 l12">
                        <div class="card-panel white">
                            <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                            <h5 class="black-text">
                                Erro ao tentar excluir Avaliador!
                            </h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="#!"  class=" modal-action modal-close waves-effect waves-green btn-flat">Voltar</a>
            </div>
        </div>


        <div class="card-panel ember">         
            <div class="card-content">
                <div class="row">
                    <h5 class="s12 m12 l12 grey lighten-4 black-text z-depth-1 center">${avaliador.nome} ${avaliador.sobrenome}</h5>            
                </div>
                <form name="avaliadorForm">
                    <div class="row">
                        <div class="col s12 m6 l6">
                            <label for="cpf">CPF</label>
                            <input id="cpf" class="grey lighten-3" type="text" value="${avaliador.cpf}"/>
                        </div>                                                                                                                                                
                        <div class="col s12 m6 l6">
                            <label for="telefone">Telefone</label>
                            <input id="telefone" class="grey lighten-3" type="text" value="${avaliador.telefone}"/>
                        </div>                           
                    </div>
                    <div class="row">                  
                        <button class="btn btn-floating circle waves-effect waves-orange orange  darken-2 material-icons">undo</button>&nbsp;&nbsp;
                        <button class="btn btn-floating circle waves-effect waves-green green material-icons" data-ng-click="updateInfo(avaliador)">done</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>