<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">

   <div class="col s4 left hide-on-med-and-down" data-ng-controller="SideMenuCtrl">
      <ul class="collapsible side-menu" data-collapsible="accordion">
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora"/>"><i class="mdi-action-supervisor-account"></i>Gest&atilde;o Intef</a>
         </li>         
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/empreendedor"/>"><i class="mdi-action-work"></i>Empreendedor</a>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/empreendimento"/>"><i class="mdi-communication-business"></i>Empreendimento</a>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/cronograma-anual"/>"><i class="mdi-notification-event-note"></i>Cronograma Anual</a>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/processo-chave"/>"><i class="mdi-social-poll"></i>Processo-Chave</a>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/orientacoes"/>"><i class="mdi-communication-comment"></i>Orienta&ccedil;&otilde;es</a>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/editais"/>"><i class="mdi-action-subject"></i>Editais</a>
         </li>

         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/avaliadores"/>"><i class="material-icons">gavel</i>Avaliador</a>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/eixo/list"/>"><i class="mdi-action-description"></i>Modelo de Avalia&ccedil;&atilde;o</a>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/gestor/duvidas"/>">
               <i class="mdi-communication-live-help"></i>D&uacute;vidas
               <span class="new badge" data-ng-if="newDuvidas.quantidade > 0" data-ng-bind="newDuvidas.quantidade" style=" padding: 1.1%; position:relative; margin-left: 30%"></span>
            </a>
         </li>
      </ul>
   </div>
