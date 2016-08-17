<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">

   <div class="col s4 left hide-on-med-and-down">
      <ul class="collapsible side-menu" data-collapsible="accordion">
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/candidato/detalhes"/>"><i class="material-icons">info</i>Informa&ccedil;&otilde;es</a>
         </li>

         <li>
            <a class="collapsible-header" href="<c:url value="/empreendedor/empreendimentos"/>"><i class="material-icons">business</i>Empreendimentos</a>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/ambiente-interno"/>"><i class="material-icons">&#xE5D1;</i>An&aacute;lise de Ambiente Interno</a>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/ambiente-externo"/>"><i class="material-icons">&#xE5D0;</i>An&aacute;lise de Ambiente Externo</a>
         </li>
         
         <li>
            <a class="collapsible-header" href="<c:url value="/acao"/>"><i class="material-icons">&#xE85D;</i>Planejamento</a>
         </li>
        
         <li>
            <a class="collapsible-header" href="<c:url value="/empreendedor/duvidas"/>"><i class="material-icons">live_help</i>D&uacute;vidas <%-- <span class="new badge" style=" padding: 2%; position:relative; margin-left: 50%" data-ng-bind="duvidas.quantidade"></span> --%></a>
         </li>
      </ul>
   </div>

