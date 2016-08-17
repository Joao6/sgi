<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class=" navbar-top white">
   <div class="nav-wrapper">
      <a href="#!" class="brand-logo">
         <img src="<c:url value="/img/logo.png"/>" alt="" class="img-responsive"/>
      </a>

      <!-- Dropdown Trigger -->
      <a class='dropdown-button btn white black-text right hide-on-med-and-down' href='#' data-activates='dropdown1'>${usuarioLogado.nome}</a>

      <!-- Dropdown Structure -->
      <ul id='dropdown1' class='dropdown-content'>
         <li><a href="#!">Perfil</a></li>
         <li><a href="#!">Conta</a></li>
         <li class="divider"></li>
         <li><a href="<c:url value="/logout"/>">Sair</a></li>
      </ul>

      <a href="#" data-activates="mobile-demo" class="button-collapse black-text"><i class="material-icons">menu</i></a>
      <ul class="side-nav collapsible black-text" id="mobile-demo" data-collapsible="accordion">
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora"/>"><i class="material-icons">supervisor_account</i>&nbsp;</a>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/empreendedor"/>"><i class="material-icons">work</i>&nbsp;</a>
         </li>
         <li>
            <div class="collapsible-header"><i class="material-icons">business</i>&nbsp;</div>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/cronograma-anual"/>"><i class="material-icons">event_note</i>&nbsp;</a>
         </li>
         <li>
            <div class="collapsible-header"><i class="material-icons">poll</i>&nbsp;</div>
         </li>
         <li>
            <div class="collapsible-header"><i class="material-icons">comment</i>&nbsp;</div>
         </li>
         <li>
            <a class="collapsible-header" href="<c:url value="/incubadora/avaliadores"/>"><i class="material-icons">gavel</i>&nbsp;</a>
         </li>
         <li>
            <div class="collapsible-header"><i class="material-icons">description</i>&nbsp;</div>
         </li>
         <li>
            <div class="collapsible-header"><i class="material-icons">description</i>&nbsp;</div>
         </li>
      </ul>

   </div>
</nav>