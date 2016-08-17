
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="EmpreendimentoCtrl">

   <div class="col s12 l8" >

      <div class="col s12 m12 l12">
         <div class="card-panel ember" style="padding-top: 0px !important">   
            <div class="card center white">
               <div class="card-content black-text valign-wrapper">
                  <i class="material-icons small valign">business</i>&nbsp;&nbsp;
                  <span class="card-title"><strong class="hide-on-small-only">Empreendimento</strong>&nbsp; </span>
               </div>

            </div>

            <div class="card-content">
               <h6 data-ng-show="isEmpreendimentoListEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o existem empreendimentos cadastrados!</strong></h6>          

               <div class="row">
                  <form name="formEmpreendimento" method="post" class="card card-panel col s12 m12 l12">
                     <input hidden="true" id="status" value="${status}"/>
                     <div class="row">
                        <div class="col s12 m6 l6">
                           <div class="input-field">
                              <input type="text" name="nome" id="nome" placeholder="ex.: BuskImóveis" data-ng-required="true" data-ng-model="empreendimento.nome"/>
                              <label for="nome" class="grey-text">Nome&nbsp;<span class="orange-text">(Obrigat&oacute;rio)</span></label>
                           </div>

                           <div class="input-field">
                              <input type="text" name="razaoSocial" id="razaoSocial" placeholder="ex.: Buscador de Imóveis Ltda" data-ng-model="empreendimento.razaoSocial"/>
                              <label for="razaoSocial">Raz&atilde;o Social</label>
                           </div> 

                        </div>
                        <div class="col s12 m6 l6">
                           <label for="edial">Edital&nbsp;<span class="orange-text">(Obrigat&oacute;rio)</span></label>
                           <select id="edial" class="browser-default" data-ng-required="true" data-ng-model="empreendimento.edital.id">
                              <option value="">Selecione...</option>
                              <option value="{{edital.id}}" data-ng-repeat="edital in editalList" data-ng-bind="edital.nome"></option>
                           </select>

                           <label for="edial">Ramo de Atividade&nbsp;<span class="orange-text">(Obrigat&oacute;rio)</span></label>
                           <select id="edial" class="browser-default" data-ng-required="true" data-ng-model="empreendimento.ramoAtividade.id">
                              <option value="">Selecione...</option>
                              <option value="{{ramo.id}}" data-ng-repeat="ramo in ramoAtividadeList" data-ng-bind="ramo.nome"></option>
                           </select>                                                      

                        </div>
                     </div>

                     <div class="row">                        
                        <div class="col s12 m6 l6">                          
                           <div class="input-field">
                              <input type="text" name="cnpj" id="razaoSocial" placeholder="ex.: 12.345.678/0001" data-ng-model="empreendimento.cnpj"/>
                              <label for="cnpj">CNPJ</label>
                           </div>                           
                        </div>  

                        <div class="input-field col s12 m6 l6">
                           <textarea class="materialize-textarea" id="missao" style="max-height: 105px; overflow-y: auto;"  placeholder="Limite máximo de 120 caracteres" data-ng-model="empreendimento.missao"></textarea>
                           <label for="missao">Miss&atilde;o</label>
                        </div>

                     </div>

                     <div class="row">

                        <div class="col s12 m6 l6"> 
                           <div class="input-field">
                              <input type="text" name="inscricaoEstatual" id="inscricaoEstadual" placeholder="ex.: 4125-55" data-ng-model="empreendimento.inscricaoEstadual"/>
                              <label for="inscricaoEstadual">Inscrição Estadual</label>
                           </div>                            
                        </div>                          

                        <div class="col s12 m6 l6">
                           <div class="input-field">
                              <textarea class="materialize-textarea" id="visao" style="max-height: 105px; overflow-y: auto;"  placeholder="Limite máximo de 120 caracteres" data-ng-model="empreendimento.visao"></textarea>
                              <label for="visao">Vis&atilde;o</label>
                           </div>
                        </div>

                     </div>

                     <div class="row">
                        <div class="col s12 m6 l6">
                           <div class="input-field">
                              <input type="text" name="inscricaoMunicipal" id="inscricaoEstadual" placeholder="ex.: 4125-55" data-ng-model="empreendimento.inscricaoMunicipal" />
                              <label for="inscricaoMunicipal">Inscrição Municipal</label>
                           </div> 
                        </div>

                        <div class="col s12 m6 l6">
                           <div class="input-field">
                              <textarea class="materialize-textarea" id="visao" style="max-height: 105px; overflow-y: auto; "  placeholder="Limite máximo de 120 caracteres" data-ng-model="empreendimento.valores"></textarea>
                              <label for="valores">Valores</label>
                           </div>
                        </div>
                     </div>

                     <div class="row">
                        <div class="col s12 m6 l6  grey  lighten-4">
                           <div class="input-field">
                              <input type="text" class="datepicker" placeholder="ddd/mm/aaaa" id="dataAbertura" disabled="true" data-ng-model="empreendimento.dataAbertura"/>
                              <label for="dataAbertura">Data de Abertura</label>
                           </div>
                        </div>
                        <div class="row">
                           <div class="col s12 m6 l6  grey  lighten-4">
                              <div class="input-field">
                                 <input type="text" class="datepicker" placeholder="dd/mm/aaaa" id="dataIngresso" disabled="true"  data-ng-model="empreendimento.dataIngresso"/>
                                 <label for="dataIngresso">Data de Ingresso</label>
                              </div>
                           </div>
                        </div>
                     </div>

                     <div class="row">
                        <div class="col s12 m6 l6 grey  lighten-4">
                           <label for="prevGraduacao">Data de Previsão da Gradua&ccedil;&atilde;o</label>
                           <input id="prevGraduacao" type="text" class="datepicker" placeholder="dd/mmm/aaaa" disabled="true" data-ng-model="empreendimento.prevGraduacao"/>
                        </div>

                        <div class="s12 m6 l6">
                           <div class="center">
                              <a href="<c:url value="/empreendedor/empreendimentos"/>" style="margin-top: 5%; margin-right: 2%;" class="btn-floating btn-small waves-effect waves-light  orange darken-4"><i class="material-icons">undo</i></a>
                              <button type="submit" data-ng-click="save(empreendimento)" style="margin-top: 5%" class="btn-floating btn-small waves-effect waves-light green" data-ng-disabled="!formEmpreendimento.$valid"><i class="material-icons">done</i></button>                                        
                           </div>
                        </div>

                     </div>                     
                  </form>                  
               </div>
            </div>
         </div>

      </div>
   </div>
</div> 