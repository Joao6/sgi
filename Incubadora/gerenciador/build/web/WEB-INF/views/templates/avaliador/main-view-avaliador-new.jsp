<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="AvaliadorCtrl">

   <div class="row">
      <div class="col s12 l8">

         <div class=" col s12 m12 l12 card-panel ember">    
            <div class="col s12 m12 l12">
               <div class="card center white">
                  <div class="card-content black-text valign-wrapper">                     
                     <i class="small material-icons valigin">gavel</i> &nbsp;&nbsp;
                     <span class="card-title truncate valign">Cadastrar Avaliador</span>                  
                  </div>
               </div>
               <!-- Modal Structure -->
               <div id="modal-progress" class="modal bottom-sheet" data-ng-show="ok">
                  <div class="modal-content">
                     <h5>Salvando...</h5>
                     <div class="progress amber lighten-4" dta-ng-show="ok">
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
                                 Avaliador cadastrado com sucesso!
                              </h5>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <a href="<c:url value="/incubadora/avaliadores"/>" class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Voltar</a>
                  </div>
               </div>
               <!-- Modal Success  update -->
               <div id="modal-success-update" class="modal amber">            
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
                     <a href="<c:url value="/incubadora/avaliadores"/>" class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Voltar</a>
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
                                 Erro ao tentar cadastrar Avaliador!
                              </h5>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <a href="<c:url value="/incubadora/avaliadores"/>"  class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Voltar</a>
                  </div>
               </div>


            </div>
            <div class="card-content">

               <div class="row">
                  <form class="col s12" name="formAvaliador" id="form-avaliador" method="post" name='formAvaliador'>
                     <input hidden="true" id="avaliador-id" value="${avaliador.id}"/>
                     <div class="row">

                        <div class="input-field col s6">
                           <input id="nome" name="nome" type="text" data-ng-required="true" placeholder="ex: João" class="validate" data-ng-model="avaliador.nome" />
                           <label for="nome">Nome</label>
                        </div>
                        <div class="input-field col s6">
                           <input id="sobrenome" name="sobrenome" data-ng-required="true" type="text" placeholder="ex.: Silva" class="validate" data-ng-model="avaliador.sobrenome" />
                           <label for="sobrenome">Sobrenome</label>
                        </div>
                     </div>
                     <div class="row">
                        <div class="input-field col s6">
                           <input id="cpf" name="cpf" type="text" data-ng-required="true" class="validate" placeholder="ex.: 123.456.789-10" data-ng-model="avaliador.cpf" data-ng-blur="validaCPF(avaliador.cpf)" data-ng-class="{'red lighten-5':!validCPF && && !formAvaliador.$durty}"/>
                           <label for="cpf">CPF</label>
                        </div>
                        <div class="input-field col s6">
                           <input id="telefone" name="telefone" data-ng-required="true" type="text" placeholder="ex.: (35)98888-7777" class="validate" data-ng-model="avaliador.telefone"  />
                           <label for="telefone">Telefone</label>
                        </div>
                     </div>
                     <div class="row">
                        <div class="input-field col s6">
                           <input id="email" name="email" type="text" data-ng-required="true" placeholder="ex.: fulano@mail.com" class="validate" data-ng-model="avaliador.email"  data-ng-blur="checkEmail(avaliador.email)"/>
                           <label for="email">E-mail</label>
                        </div>
                        <div class="input-field col s6">
                           <input id="senha" name="senha" type="password" data-ng-required="true" placeholder="ex.: ********" class="validate" data-ng-model="avaliador.senha" />
                           <label for="senha">Senha</label>
                        </div>
                     </div>


                     <a href="<c:url value="/incubadora"/>" style="margin-top: 5%; margin-right: 2%;" class="btn-floating btn-small waves-effect waves-light  orange darken-4"><i class="material-icons">undo</i></a>

                     <button type="submit" data-ng-click="addAvaliador(avaliador)" style="margin-top: 5%" data-ng-disabled="formAvaliador.$invalid || !validCPF || !emailOk" class="btn-floating btn-small waves-effect waves-light blue"><i class="material-icons">done</i></button>                   

                  </form>
                     
               </div>


            </div>
         </div>
      </div>
   </div>
</div>
</div>
