
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="MainCtrl">
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
                        Empreendedor exclu&iacute;do com sucesso!
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
                        Erro ao tentar excluir Empreendedor!
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
               <h5 class="s12 m12 l12 grey lighten-4 black-text z-depth-1 center">{{empreendedor.nome}} {{empreendedor.sobrenome}}</h5>            
            </div>
            <form name="empreendedorForm">
               <div class="row">
                  <div class="col s12 m6 l2 ">
                     <label for="cpf">CPF</label>
                     <input id="cpf" class="grey lighten-3" type="text"  data-ng-model="empreendedor.cpf"/>
                  </div>
                  <div class="col s12 m6 l2 ">
                     <label for="rg">RG</label>
                     <input id="rg" class="grey lighten-3" type="text" data-ng-model="empreendedor.rg"/>
                  </div>
                  <div class="col s12 m6 l2 ">
                     <label for="data" class="truncate">Data de Nascimento</label>
                     <input id="data" class="grey lighten-3 datepicker" type="date"  data-ng-model="empreendedor.dataNascimento" />
                  </div>
                  <div class="col s12 m6 l3 ">
                     <label for="escolaridade">Escolaridade</label>
                     <input id="escolaridade" class="grey lighten-3" type="text" data-ng-model="empreendedor.escolaridade"/>
                  </div>
                  <div class="col s12 m6 l3 ">
                     <label for="formacao">Forma&ccedil;&atilde;o</label>
                     <input id="formacao" class="grey lighten-3" type="text" data-ng-model="empreendedor.formacaoProfissional"/>
                  </div>
                  <div class="col s12 m6 l4 ">
                     <label for="ocupacao">Ocupa&ccedil;&atilde;o</label>
                     <input id="ocupacao" class="grey lighten-3" type="text" data-ng-model="empreendedor.ocupacao"/>
                  </div>
                  <div class="col s12 m6 l2 ">
                     <label for="endereco">Endere&ccedil;o</label>
                     <input id="endereco" class="grey lighten-3" type="text" data-ng-model="empreendedor.endereco.rua"/>
                  </div>
                  <div class="col s12 m6 l3 ">
                     <label for="fax">FAX</label>
                     <input id="fax" class="grey lighten-3" type="text" data-ng-model="empreendedor.fax"/>
                  </div>                                         

               </div>
               <div class="row">                  
                  <button class="btn btn-floating circle waves-effect waves-orange orange  darken-2 material-icons">undo</button>&nbsp;&nbsp;
                  <button class="btn btn-floating circle waves-effect waves-green green material-icons" data-ng-click="updateInfo(empreendedor)">done</button>
               </div>
            </form>
         </div>
      </div>
   </div>
</div>