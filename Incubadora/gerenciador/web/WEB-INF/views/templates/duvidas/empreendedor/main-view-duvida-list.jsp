
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="DuvidaCtrl">

   <div class="col s12 l8">

      <div class="col s12 m12 l12">
         <div class="card center white">
            <div class="card-content black-text valign-wrapper">
               <i class="material-icons small ">live_help</i>&nbsp;&nbsp;
               <span class="card-title valign"><strong><span class="hide-on-small-only">Esclare&ccedil;a suas</span> D&uacute;vidas</strong> </span>
            </div>
         </div>
      </div>


      <div class="card-panel ember">         

         <div class="row">

            <div class="col s12 m12 l12">
               <form class="row valign-wrapper">
                  <div class="input-field col m12 s12 l11">
                     <textarea id="pergunta" class="materialize-textarea" data-ng-model="duvida"></textarea>
                     <label for="pergunta">Fa&ccedil;a uma pergunta</label>                     
                  </div>
                  <a class="btn-floating green" data-ng-click="addDuvida(duvida)" style="">
                     <i class="large material-icons valign">send</i>
                  </a>
               </form>

               <!-- // Início do card de pergunta -->
               <div class="col s12 m6 l12" data-ng-repeat="duvida in duvidaList">
                  <div class="card black-text " id="duvida-{{duvida.id}}">
                     <a class="btn-floating center-align red right" data-ng-click="removerDuvida(duvida)">
                        <i class="material-icons tiny">&#xE14C;</i>
                     </a>
                     <!-- // Ínicio Card de cabeçalho -->
                     <div class="card-content amber lighten-3">

                        <div class="valign-wrapper">
                           <img class="img-responsive circle"  src="<c:url value="/img/avatar-william.png"/>"/>
                           <span class="card-title "><strong class="valign">{{duvida.empreendedor.nome}}&nbsp;{{duvida.empreendedor.sobrenome}}</strong></span>
                        </div>
                        <!-- // Fim -->
                        <div>
                        <p class="text-duvida">{{duvida.duvida}}</p>
                        <small class="data-hora">{{duvida.dataHora}}</small>
                        </div>
                     </div>
                     <div  class="card-action black-text">
                        <form class="col s12 m12 l12" id="form-duvida">
                           <div class="row">

                              <div data-ng-repeat="resposta in duvida.respostaDuvidaList">
                                 <div class="valign-wrapper">
                                    <img class="img-responsive circle valign" style="width: 2.5em; height: 2.5em;" src="<c:url value="/img/avatar-william.png"/>"/>
                                    <span class="card-title" style="font-size: 14pt;"><strong>{{resposta.usuario.nome}}<span <span class="hide-on-med-and-down">&nbsp;{{resposta.usuario.sobrenome}}</span></strong></span>
                                 </div>
                                 <p class="text-resposta">{{resposta.resposta}}</p>
                                 <small class="data-hora">{{resposta.dataHora}}</small>

                                 <hr class="divisor"/>
                              </div>

                              <div class="input-field col s12 validade">
                                 <textarea  placeholder="Comentar" data-ng-model="resposta" length="120"  id="resposta-{{duvida.id}}" name="resposta" class="black-text materialize-textarea validate">
                                 
                                 </textarea>                          
                              </div>
                          
                              <a class="btn-floating green right" data-ng-click="responderDuvida(duvida, resposta)" style="">
                                 <i class="large material-icons">send</i>
                              </a>

                           </div>

                        </form>
                     </div>
                     </div>
                  </div>
               </div>

            </div>
            <!-- // Fim do card de pergunta -->
         </div>
      </div>

   </div>

</div>


