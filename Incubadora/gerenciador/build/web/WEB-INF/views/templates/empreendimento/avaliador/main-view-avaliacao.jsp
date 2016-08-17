
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="EmpCtrl">

   <div class="col s12 l8" >
      <input type="text" id="avaliador-id" hidden="true" value="${avaliador.id}"/>
      <div class="col s12 m12 l12">
         <div class="card-panel ember" style="padding-top: 0px !important">   
            <div class="card center white">
               <div class="card-content black-text valign-wrapper">
                  <i class="material-icons small valign">gavel</i>&nbsp;&nbsp;
                  <span class="card-title truncate">Avalia&ccedil;&atilde;o do empreendimento:<strong class="hide-on-small-only">&nbsp;${empreendimento.nome}</strong>&nbsp; </span>
               </div>

            </div>

            <div class="card-content">
               <h6 data-ng-show="isEmpreendimentoListEmpty()" class="center-align" style="text-transform: uppercase;"><strong>N&atilde;o h&aacute; empreendimentos encaminhados para voc&ecirc; nesse momento.</strong></h6>          


               <ul class="collection card" data-ng-hide="isEmpreendimentoListEmpty()" data-collapsible="accordion">
                  <li class="collection-item" data-ng-repeat="empreendimento in empreendimentoList" >
                     <div class="card-panel " style="padding-top: 0px !important; padding-bottom: 0px !important">
                        <h5 class="card-title center-align"><strong>&nbsp;Inovação</strong></h5>
                     </div>
                     <div class="card-content">
                        <ul class="collection">
                           <li class="collection-item card-panel white" style="padding-bottom: 0px; padding-top: 0px">
                              <span class="valign-wrapper row" style="width: 100%;  margin-bottom: 0px">                           
                                 <span data-ng-bind="empreendimento.nome" class="valign col s12 m10 l10" style="font-size: 14pt;"></span>
                                 <input type="text" class="align-right col s12 m2 l2" style="text-align: center;" placeholder="Nota"/>
                              </span>                        
                           </li>
                                                
          
                        </ul>                    
                        <div class="row center">
                           <hr class="divider white" style="border: 0px"/>
                           <a data-ng-href="/gerenciador/empreendimento/{{empreendimento.id}}/avaliar" class="btn blue valign col s12 m12 l12 truncate" style=" margin-top: 0.6rem; border-radius: 0px">Avaliar</a>
                        </div>

                     </div>
                  </li>
               </ul>
            </div>
         </div>

      </div>
   </div>
</div>