
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-controller="DuvidaCtrl as ctrl">
   <input id="user-logado-id" hidden="true" value="${usuarioLogado.id}"/>
   <div class="col s12 l8">

      <div class="chat card-panel col s12 m12 l12 card-main">
         <div class="row">

            <div class="col s12 m12 l12">
               <div class="card center white">
                  <div class="card-content black-text valign-wrapper">
                     <i class="mdi-communication-live-help small "></i>&nbsp;&nbsp;
                     <span class="card-title valign"><strong>D&uacute;vidas<span class="hide-on-small-only"> Recebidas</span></strong> </span>
                  </div>
               </div>
            </div>

            <div class="col s12 m5 l5">

               <div data-ng-if="duvidas.length < 1" class="card card-content" style="padding: 5%; height: 100% !important;">
                  <div class="card-image">
                     <img data-ng-src="{{url_img_intef_src}}">                        
                  </div>
                  <div class="card-content">
                     <span style="font-size: 19pt">N&atilde;o recebemos nenhuma d&uacute;vida ainda. =)</span>
                  </div>
               </div>
               <div class="card-panel white chat card-list-people">




                  <ul class="collection">
                     <li data-ng-repeat="duvida in duvidas| unrepeated" class="collection-item valign-wrapper" data-ng-class="{'item-active':idByEmpreendedor == duvida.empreendedor.id}" data-ng-click="visualizar(duvida)">
                        <img data-ng-src="{{url_img_empreendedor_src}}" alt="" class="chat responsive-img circle">                           
                        <div class="header">
                           <a href="#!" class="valign-wrapper" data-ng-click="visualizar(duvida)">
                              <h6 class="truncate valign">
                                 <strong>{{duvida.empreendedor.nome}} {{duvida.empreendedor.sobrenome}}</strong>                                 
                              </h6>
                              <span class="valign new badge" style="width: 5rem; margin-top: 4%" data-ng-if="countDuvidasToAnswer(duvida) > 0" data-ng-bind="countDuvidasToAnswer(duvida)"></span>           
                           </a>                             
                           <span class="text-content  truncate">{{duvida.duvida| truncate:18 }}</span>
                        </div>
                     </li>
                  </ul>

               </div>
            </div>

            <div class="col s12 m12 l12 chat card no-duvidas" data-ng-if="false">
               <h6 class="black-text">Nenhuma dúvida enviada.</h6>
            </div>

            <div class="col l7 s12 m7 ">                   

               <div class="chat card-panel card-ask">
                  <div class="chat card-panel" data-ng-if="!clicked" style="background-color: rgba(255, 193, 7, 0.68)">
                     <div class="row">
                        <div class="col s12 m12">
                           <div class="card">
                              <div class="card-image" style="padding-top: 4% !important; margin-top: 3% !important">
                                 <img data-ng-src="{{url_img_chat_ico_src}}" class="center-align" style="height: 100% !important; width: 50% !important; margin-left: auto; margin-right: auto">
                                 <span class="card-title">

                                 </span>
                              </div>
                              <div class="card-content">
                                 <div class="divider grey-texte"></div>
                                 <p>TOTAL DE D&Uacute;VIDAS: <strong>{{duvidas.length}}</strong></p>
                                 <p>RESPONDIDAS: <strong>{{totalDuvidasRespondidas()}}</strong></p>                                 
                              </div>                
                           </div>
                        </div>
                     </div>
                  </div>

                  <!-- Pergunta -->
                  <div class="chat card-panel"  data-ng-repeat="duvida in duvidas| duvidaByEmp: idByEmpreendedor | orderBy: duvida.dataHora track by $index" data-ng-class="{'default':isEmptyResp(duvida)}"
                       data-ng-class="{'amber lighten-5':!isEmptyResp(duvida)}"> 
                     <!-- // Modal de alerta para exclusão da dúvida // -->

                     <!-- Modal Structure -->
                     <div id="alert-modal" class="modal card-panel">
                        <div class="modal-content">
                           <h3 class="valign-wrapper" style="font-size: 25pt"><i class="material-icons valign" style="margin-right:2%; font-size: 25pt !important">warning</i> <span class="valign">Alerta de Exclus&atilde;o</span></h3>
                           <h5>Esta opera&ccedil;&atilde;o n&atilde;o poder&aacute; ser desfeita posteriormente.</h5>
                           <p>Obs.: todo o hist&oacute;rico da conversa ser&aacute; exclu&iacute;do para voc&ecirc; e para todos os usu&aacute;rios presentes nele.</p>
                        </div>
                        <div class="modal-footer card-action">
                           <a href="#!" data-ng-click="removerDuvida(duvida.id)" class="waves-effect waves-orange btn-flat">Excluir</a>
                           <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
                        </div>
                     </div>

                     <div class="ask valign-wrapper">
                        <img data-ng-src="{{url_img_empreendedor_src}}" alt="" class="chat responsive-img circle">
                        <div class="valign" style="width:100%">

                           <!-- Botão de Fechar -->
                           <a class='chat btn-remove-duvida btn-floating z-depth-0 right btn red circle  modal-trigger'  data-ng-href='#alert-modal' >
                              <i class="mdi-navigation-close">close</i>
                           </a>


                           <div class="valign-wrapper duvida-head-card">
                              <h6 class="truncate">
                                 <strong class="valign">
                                    {{duvida.empreendedor.nome}} {{duvida.empreendedor.sobrenome}}
                                 </strong>                                 
                              </h6>
                              <small class="right time-ask valign">{{duvida.dataHora| date:"dd/MM/yyyy hh:mm:ss"}} </small>           
                           </div>

                           <div class="text-ask">
                              <span>{{duvida.duvida}}</span>
                           </div>
                        </div>
                     </div>

                     <hr class="divider divider-head" data-ng-show="!isDuvidasEmpty()"/>


                     <div data-ng-repeat="resposta in duvida.respostaDuvidaList| unrepeatedResp" class="chat resp valign-wrapper" data-ng-model="duvida.respostaDuvidaList">
                        <img data-ng-src="{{url_img_usuario_src}}" alt="" class="responsive-img circle" data-ng-class="{'seft-img':isUsuarioId(resposta.usuario.id)}"/>                                

                        <div class="valign" style="width:100%">
                           <div class="valign-wrapper">
                              <h6 class="truncate">
                                 <strong class="valign" style="width:100% !important;">
                                    {{resposta.usuario.nome}} {{resposta.usuario.sobrenome}}  
                                 </strong>
                              </h6>
                              <small class="align-right time-ask valign">{{resposta.dataHora| date:'dd/MM/yyyy hh:mm:ss'}}</small>
                           </div>

                           <div class="text-resposta" data-ng-model="resposta.resposta" data-ng-if="!resposta.show">{{resposta.resposta}}</div>                           
                           <div class="resp">                              
                              <textarea hidden="true" data-ng-required="true" class="materialize-textarea textarea-resposta validate" id="textarea-{{resposta.id}}"  data-ng-show="{{respostaSelecionada && isUsuarioId(resposta.usuario.id)}}"  placeholder="Digite uma resposta e tecle ENTER para enviar"  maxlength="512" data-ng-model="resposta.resposta">
                              {{resposta.resposta}}
                              </textarea>        
                           </div>
                           <!-- //Edit // -->
                           <div class="card col s12 m12 l12 white darken-2" data-ng-if="resposta.show" style="padding-bottom: 0%; padding-top: 5%; margin-top:5px !important; border-radius: 0px !important; width:98%" id="edit-{{resposta.id}}">                              
                              <div class="row">
                                 <textarea type="text" style="margin-bottom: 0px !important;" autofocus="true" id="textarea-{{resposta.id}}" class="materialize-textarea textarea-resposta  blue-text col l11 s11 m11" data-ng-model="resposta.resposta">
                                 {{resposta.resposta}}
                                 </textarea>
                                 <button type="button" style="width: 27px; height: 27px; top: -10px !important; margin-left: 1% !important; position: absolute"  class="chat btn red btn-edit-mode btn-floating right" data-ng-click="exitEditMode(resposta)"><i class="material-icons white-text" style="font-size: 18px; margin: -10px !important">clear</i></button>
                                 <button type="button" style="width: 27px; height: 27px; margin-top: 8% !important;"  class="btn-floating right green black-text" data-ng-click="updateResposta(resposta)"><i class="material-icons white-text" style="font-size: 18px; margin: -10px !important">done</i></button>
                              </div>
                           </div>

                           <div class="valign-wrapper" data-ng-if="!resposta.show && isUsuarioId(resposta.usuario.id)">
                              <a data-ng-href="#!" class="comentario-link z-depth-0 btn-editar valign-wrapper align-center blue" id="btn-editar-{{resposta.id}}" data-ng-click="showResposta(resposta)" >
                                 Editar
                              </a>
                              <a data-ng-href="#!" class="comentario-link z-depth-0 valign-wrapper align-center" data-ng-click="removerResposta(resposta.id)">
                                 Excluir
                              </a>
                           </div>
                           <hr class="divider divider-resposta"  data-ng-show="!isDuvidasEmpty() && !resposta.show" />
                        </div>     

                     </div>


                     <div class="resp">
                        <textarea class="materialize-textarea send-resposta" data-ng-required="true" data-ng-model="resposta.resposta" id="resp-duvida-{{duvida.id}}" data-ng-keyup="sendAsk(duvida, resposta)"
                                  placeholder="Enviar resposta" rows="4" cols="1">
                           {{resposta.duvida}}
                        </textarea>

                     </div>

                  </div>

               </div>
            </div> <!-- // fim .col // -->

         </div><!-- // fim .row // -->

      </div> <!-- // fim do .card-main // -->


   </div>

</div>


