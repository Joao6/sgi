<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal">

   <div class="row"  data-ng-controller="GestaoIntefCtrl">
      <div class="col s12 l8">

         <div class=" col s12 m12 l12 card-panel ember">    
            <div class="col s12 m12 l12">
               <div class="card center white">
                  <div class="card-content black-text valign-wrapper">                     
                     <i class="small material-icons valign">supervisor_account</i> &nbsp;&nbsp;
                     <span class="card-title truncate valign">Cadastrar Gestor</span>                  
                  </div>
               </div>
               <!-- Modal Structure -->
               <div id="modal-progress" class="modal bottom-sheet" data-ng-show="ok">
                  <div class="modal-content">
                     <h5>Salvando...</h5>
                     <div class="progress amber lighten-4" data-ng-show="ok">
                        <div class="indeterminate amber"></div>
                     </div>
                     <input hidden="true" id="input-hidden" data-ng-model="ok"/>
                  </div>  
               </div>
               <!-- Message -->

               <!-- Modal Success -->
               <div id="modal-success" class="modal amber">            
                  <div class="modal-content white-text">
                     <div class="row">
                        <div class="col s12 m12 l12">
                           <div class="card-panel white">
                              <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                              <h5 class="black-text">
                                 Gestor cadastrado com sucesso!
                              </h5>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Fechar</a>
                  </div>
               </div>
               <!-- Modal Success update  -->
               <div id="modal-success-update" class="modal amber modal-trigger">            
                  <div class="modal-content white-text">
                     <div class="row">
                        <div class="col s12 m12 l12">
                           <div class="card-panel white">
                              <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                              <h5 class="black-text">
                                 Dados atualizados com sucesso!
                              </h5>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Fechar</a>
                  </div>
               </div>
                              
                              
               <!-- Modal Error -->
               <div id="modal-error" class="modal amber modal-trigger">            
                  <div class="modal-content white-text">
                     <div class="row">
                        <div class="col s12 m12 l12">
                           <div class="card-panel white">
                              <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                              <h5 class="black-text">
                                 Erro ao tentar cadastrar gestor!
                              </h5>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Fechar</a>
                  </div>
               </div>


            </div>
            <div class="card-content">

               <div class="row">
                  <form class="col s12" id="form-gestor" name="formGestor">
                     <!--  // GESTOR ID --// -->
                     <input type="text" hidden="true" id="gestor-id" value="${gestor.id}"/>
                     
                     <div class="row">      
                        <div class="input-field col s6">
                           <input id="nome" name="nome" type="text" placeholder="Nome" class="validate" data-ng-model="gestor.nome" data-ng-required="true" />
                           <label for="nome">Nome</label>
                        </div>
                        <div class="input-field col s6">
                           <input id="sobrenome" name="sobrenome" placeholder="Sobrenome" type="text" class="validate" data-ng-model="gestor.sobrenome" data-ng-required="true" />
                           <label for="sobrenome">Sobrenome</label>
                        </div>
                     </div>
                     <div class="row">
                        <div class="input-field col s6">
                           <input id="cpf" name="cpf" type="text" maxlength="14" placeholder="ex.: 00011122233 ou 000.111.222-33"  class="validate" data-ng-required="true" data-ng-model="gestor.cpf" data-ng-blur="validaCPF(gestor.cpf)" data-ng-class="{'red lighten-5':!validCPF}" />
                           <label for="cpf">CPF</label>
                        </div>
                        <div class="input-field col s6">
                           <input id="telefone" name="telefone" type="text" placeholder="(99)99999-9999" class="validate" data-ng-model="gestor.telefone" data-ng-required="true"  />
                           <label for="telefone">Telefone</label>
                        </div>
                     </div>
                     <div class="row">
                        <div class="input-field col s6">
                           <input id="email" name="email" type="text" class="validate" placeholder="fulano123@mail.com" data-ng-model="gestor.email" data-ng-required="true" data-ng-blur="checkEmail(gestor.email)"/>
                           <label for="email">E-mail</label>
                        </div>
                        <div class="input-field col s6">
                           <input id="senha" name="senha" type="password" placeholder="********" class="validate" data-ng-model="gestor.senha" data-ng-required="true" />
                           <label for="senha">Senha</label>
                        </div>
                     </div>


                     <a href="<c:url value="/incubadora"/>" style="margin-top: 5%; margin-right: 2%;" class="btn-floating btn-small waves-effect waves-light  orange darken-4"><i class="material-icons">undo</i></a>

                     <button type="submit" data-ng-click="addGestor(gestor)" data-ng-disabled="formGestor.$invalid || !validCPF || !emailOk" style="margin-top: 5%" class="btn-floating btn-small waves-effect waves-light green"><i class="material-icons">done</i></button>                   

                  </form>                    
               </div>


            </div>
         </div>
      </div>
   </div>
</div>
</div>
