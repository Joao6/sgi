<nav class="white light" role="navigation">
   <div class="nav-wrapper container">
      <a id="logo-container" href="#" class="brand-logo">
         <img class="responsive-img" src="<c:url value="/img/logo.png"/>" />
      </a>
      <ul class="right hide-on-med-and-down">
         <li><a href="#" class="dropdown-button" data-activates='dropdown-login'>Entrar</a></li>
         <!-- Dropdown Structure -->
         <ul id='dropdown-login' class='dropdown-content'>
            <li><a href="#form-empreendedor" class="modal-trigger">Empreendedor</a></li>
            <li><a href="#form-avaliador" class="modal-trigger">Avaliador</a></li>
            <li class="divider"></li>
            <li><a href="#form-administrador" class="modal-trigger">Administrador</a></li>
         </ul>
      </ul>  

      <!-- // Mobile // -->
      <a href="#" data-activates="side-menu" class="button-collapse valign-wrapper amber-text" ><i class="material-icons valigin" style="margin: 0px !important;">menu</i></a>

      <ul class="side-nav collection" id="side-menu">
         <li class="center amber collection-item"><a href="#form-empreendedor" class="modal-trigger white-text" >Empreendedor</a></li>
         <li class="center amber collection-item"><a href="#form-avaliador" class="modal-trigger white-text" >Avaliador</a></li>
         <li class="center amber collection-item"><a href="#form-administrador" class="modal-trigger white-text" >Administrador</a></li>
      </ul>
</nav>

<ui-modal-login title="Administrador" tipo="1" user-type="administrador"></ui-modal-login>
<ui-modal-login title="Empreendedor" tipo="2" user-type="empreendedor"></ui-modal-login>
<ui-modal-login title="Avaliador" tipo="3" user-type="avaliador"></ui-modal-login>



