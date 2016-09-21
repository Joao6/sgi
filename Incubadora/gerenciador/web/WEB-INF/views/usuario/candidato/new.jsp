<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="candidatoApp">
   <head>

      <%@include file="../../templates/basic-header-home.jsp" %>
      <link rel="stylesheet" href="<c:url value="/css/candidato-cad-app/css/candidato-app.css"/>"/>   
      <!-- // Core Candidato APP // -->
      <script src="<c:url value="/js/candidato-app/js/app/candidato-app.js"/>"></script>
      <!-- // VALUES // -->
      <script src="<c:url value="/js/candidato-app/js/values/candidato-value.js"/>"></script>
      <!-- // SERVICES // -->
      <script src="<c:url value="/js/candidato-app/js/services/candidato-service.js"/>"></script>
      <!-- // CONTROLLERS // -->
      <script src="<c:url value="/js/candidato-app/js/controllers/candidato-controller.js"/>"></script>
      <!-- // VALIDAÇÃO FORMULÁRIO // -->
       <!--<script src="<c:url value="/js/usuario/candidato/validacao-form.js"/>"></script>--> 
      <!-- // MÁSCARAS // -->
       <script src="<c:url value="/js/usuario/form-mask.js"/>"></script> 
      
   </head>
   <body data-ng-controller="CandidatoCtrl">
      <!-- // TOPBAR // -->
      <%@include file="../../templates/home/top-bar.jsp" %>
      
      <!--// FORMULÁRIO 1 // -->
      <form name="candidatoForm" id="candidatoForm">
         <div class="row"> 
            <div class="card-panel z-depth-2 col s12 m12 l6" id="form-1">
               <div class="card-title">
                  <h5>Usuário</h5>
                  <div class="form divider"></div>
               </div>
               <div class="card-content">                 
                  <div class="row">
                     <div class="input-field col s12 m6 l6">
                        <input placeholder="ex.: João" id="nome" name="nome" data-ng-model="candidato.nome" type="text" class="validate"  data-ng-required="true">
                        <label for="nome">Nome</label>
                     </div>
                     <div class="input-field col s12 m6 l6">
                        <input placeholder="ex.: Silva" id="sobrenome" name="sobrenome" data-ng-model="candidato.sobrenome" type="text" class="validate"  data-ng-required="true">
                        <label for="sobrenome">Sobrenome</label>
                     </div>
                  </div>


                  <div class="row">
                     <div class="input-field col s12 m6 l6">
                        <input placeholder="ex.: fulano@outlook.com" id="email" name="email" data-ng-model="candidato.email" type="text" class="validate" data-ng-required="true" data-ng-blur="checkEmail(candidato.email)">
                        <label for="email">Email</label>
                     </div>
                     <div class="input-field col s12 m6 l6">
                        <input placeholder="min 8 caracteres" id="senha" name="senha" data-ng-model="candidato.senha" type="password" class="validate"  data-ng-required="true">
                        <label for="senha">Senha</label>
                     </div>
                  </div>

                  <div class="row">
                     <div class="input-field col s12 m12 l6">
                        <input type="text" placeholder="ex (35) 9 9999-99999" id="telefone" name="telefone" data-ng-model="candidato.telefone" class="validate"  data-ng-required="true" />
                        <label for="telefone">Telefone</label>
                     </div>
                     <div class="input-field col s12 m12 l6">
                        <input placeholder="ex (35)9 888-9999" id="fax" name="fax" type="text" data-ng-model="candidato.fax" class="input-telefone">
                        <label for="fax">FAX <span class="green-text">(N&atilde;o obrigat&oacute;rio)</span></label>
                     </div>
                  </div>
               </div>

            </div>

            <!--// FORMULÁRIO 2 //-->

            <div class="card-panel z-depth-2 col s12 m12 l5" id="form-2">
               <div class="card-title">
                  <h5>Endere&ccedil;o</h5>
                  <div class="form divider"></div>
               </div>
               <div class="card-content">                
                  <div class="row">

                     <div class="input-field col s12 m6 l6">
                        <input placeholder="ex.: Rio Branco" id="rua" name="rua" data-ng-model="candidato.endereco.rua" type="text" class="validate"  data-ng-required="true">
                        <label for="rua">Rua</label>
                     </div>

                     <div class="input-field col s12 m6 l6">
                        <input placeholder="ex.: 300" id="numero" name="numero" data-ng-model="candidato.endereco.numero" type="text" class="validate"  data-ng-required="true">
                        <label for="numero">N&uacute;mero</label>
                     </div>

                  </div>   

                  <div class="input-field col s12 m12 l12">
                     <input placeholder="ex.: São João" id="bairro" name="bairro" data-ng-model="candidato.endereco.bairro" type="text" class="validate"  data-ng-required="true">
                     <label for="bairro">Bairro</label>
                  </div>

                  <div class="row">
                     <div class="input-field col s12 m6 l6">
                        <input placeholder="ex.: Santa Rita do Sapucaí" type="text" class="cidade validate" id="cidade" name="cidade" data-ng-model="candidato.endereco.cidade"  data-ng-required="true">
                        <label for="cidade">Cidade</label>
                     </div>

                     <div class="input-field col s12 m6 l6">

                        <select class="browser-default validate" id="uf" name="uf" data-ng-model="candidato.endereco.uf"  data-ng-required="true">
                           <option value=""  selected>UF</option>
                           <option data-ng-repeat="estado in estados" value="{{estado.sigla}}">{{estado.sigla}}</option>
                        </select>                                 
                     </div>
                  </div>

               </div>   
            </div>
         </div>
         <div class="row">
            <!--// FORMULÁRIO 3 //-->

            <div class="card-panel z-depth-2 col s12 m12 l12" id="form-3">
               <div class="card-title">
                  <h5>Empreendedor</h5>
                  <div class="form divider"></div>
               </div>
               <div class="card-content">                 
                  <div class="row">

                     <div class="input-field col s12 m6 l6">
                        <input placeholder="ex.: 000.000.000-00" id="cpf" data-ng-model="candidato.cpf" data-ng-keyup="validaCPF(candidato.cpf)" data-ng-class="{'red lighten-5':!validCPF}"  data-ng-required="true" name="cpf" type="text" class="validate input-cpf">
                        <label for="cpf">CPF</label>
                     </div>

                     <div class="input-field col s12 m6 l6">
                        <input placeholder="ex.: 99.999.999" id="rg" name="rg" data-ng-model="candidato.rg" type="text" class="validate input-rg"  data-ng-required="true">
                        <label for="rg">RG</label>
                     </div>

                  </div>   

                  <div class="row">
                     <div class="input-field col s12 m6 l6">
                        <input type="text" class="validate" placeholder="Data de Nascimento:  dd/mm/aaaa"  id="dataNascimento" name="dataNascimento" data-ng-model="candidato.dataNascimento"  data-ng-required="true">                        
                        <label for="dataNascimento">Data de Nascimento</label>
                     </div>

                     <div class="input-field col s12 m6 l6">
                        <select name="escolaridade" id="escolaridade" data-ng-model="candidato.escolaridade" class="browser-default validate" data-ng-model="nivel"  data-ng-required="true">
                           <option value="" selected>Escolaridade</option>    
                           <option data-ng-repeat="escolaridade in escolaridades" value="{{escolaridade.nivel}}">{{escolaridade.nivel}}</option>
                        </select>                                             
                     </div>

                  </div>


                  <div class="input-field col s12 m6 l6">
                     <input placeholder="ex.: Bacharel em Sistemas de Informação" id="formacao" name="formacaoProfissional" data-ng-model="candidato.formacaoProfissional" type="text" class="validate"  data-ng-required="true">
                     <label for="formacao">Forma&ccedil;&atilde;o Profissional</label>
                  </div>

                  <div class="input-field col s12 m6 l6">
                     <input placeholder="ex.: Desenvolvedor Web" id="ocupacao" name="ocupacao" type="text" data-ng-model="candidato.ocupacao" class="validate"  data-ng-required="true">
                     <label for="ocupacao">Ocupa&ccedil;&atilde;o Profissional</label>
                  </div>

                  <a  href="<c:url value="/"/>" class="hide-on-med-and-down"><button type="button" class="btn btn-large btn-cancelar orange col s12 m12 l4 ">Cancelar</button></a>
                  <button id="btn-cadastrar" type="button" class="btn btn-large green col s12 m12 l8" data-ng-disabled="candidatoForm.$invalid || !validCPF || !emailOk" data-ng-click="create(candidato)">Cadastrar Empreendedor</button>
                  <a href="<c:url value="/"/>" class="show-on-med-and-down"> <button type="button" class="btn btn-large btn-cancelar orange col s12 m12 l4">Cancelar</button></a>
               </div>                 
            </div>
         </div>
             
      </form>        


   </body>
</html>