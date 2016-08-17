<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="EmpreendedorCtrl">

   <div class="row">
      <div class="col s12 l8">

         <div class=" col s12 m12 l12 card-panel ember">    
            <div class="col s12 m12 l12">
               <div class="card center white">
                  <div class="card-content black-text">                     
                     <i class="small material-icons">work</i> &nbsp;&nbsp;
                     <span class="card-title truncate">Cadastrar Empreendedor</span>                  
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
               <div id="modal-success" class="modal amber modal-trigger">            
                  <div class="modal-content white-text">
                     <div class="row">
                        <div class="col s12 m12 l12">
                           <div class="card-panel white">
                              <img src="<c:url value="/img/logo.png"/>" class="responsive-img"/>
                              <h5 class="black-text">
                                 Empreendedor cadastrado com sucesso!
                              </h5>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Voltar</a>
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
                                 Erro ao tentar cadastrar Empreendedor!
                              </h5>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="modal-footer">
                     <a href="<c:url value="/incubadora/empreendedor"/>"  class=" modal-action modal-close waves-effect waves-green btn-flat btn-voltar">Voltar</a>
                  </div>
               </div>


            </div>
            <div class="card-content">

               <div class="row">
                  <form class="col s12" id="form-gestor">
                     <div class="row">

                        <div class="input-field col s6">
                           <input id="nome" name="nome" type="text" class="validate" data-ng-model="empreendedor.nome" />
                           <label for="nome">Nome</label>
                        </div>
                        <div class="input-field col s6">
                           <input id="sobrenome" name="sobrenome" type="text" class="validate" data-ng-model="empreendedor.sobrenome" />
                           <label for="sobrenome">Sobrenome</label>
                        </div>
                     </div>
                     <div class="row">
                        <div class="input-field col s6">
                           <input id="cpf" name="cpf" type="text" class="validate" data-ng-model="empreendedor.cpf" />
                           <label for="cpf">CPF</label>
                        </div>
                        <div class="input-field col s6">
                           <input id="telefone" name="telefone" type="text" class="validate" data-ng-model="empreendedor.telefone" />
                           <label for="telefone">Telefone</label>
                        </div>
                     </div>
                     <div class="row">
                        <div class="input-field col s6">
                           <input id="email" name="email" type="text" class="validate" data-ng-model="empreendedor.email" />
                           <label for="email">E-mail</label>
                        </div>
                        <div class="input-field col s6">
                           <input id="senha" name="senha" type="password" class="validate" data-ng-model="empreendedor.senha" />
                           <label for="senha">Senha</label>
                        </div>
                     </div>


                     <a href="<c:url value="/incubadora"/>" style="margin-top: 5%; margin-right: 5%;" class="btn-floating btn-large waves-effect waves-light  orange darken-4"><i class="material-icons">undo</i></a>

                     <button type="submit" data-ng-click="addEmpreendedor(empreendedor)" style="margin-top: 5%" class="btn-floating btn-large waves-effect waves-light blue"><i class="material-icons">done</i></button>                   

                  </form>
                     
               </div>


            </div>
         </div>
      </div>
   </div>
</div>
</div>
