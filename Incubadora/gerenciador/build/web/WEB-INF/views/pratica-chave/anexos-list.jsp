<%-- 
    Document   : anexos-list
    Created on : 12/02/2016, 00:53:23
    Author     : Developer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Anexos::Prática-Chave</title>
      <%@include file="../templates/basic-style.jsp" %>
      <style>
         .secondary-content.close{
            color:#AAA;
         }
         .secondary-content.close:hover{
            color:red;
         }
      </style>
      <%@include file="../templates/basic-script.jsp" %>
      <!-- // Values // -->
      <script src="<c:url value="/js/painel-administrativo/values/pratica-chave-values.js"/>"></script>
      <!-- // Services // -->

      <script src="<c:url value="/js/painel-administrativo/service/pratica-chave-service.js"/>"></script>

      <!-- // Controllers // -->
      <script src="<c:url value="/js/painel-administrativo/controller/pratica-chave-controller.js"/>"></script>
   </head>
   <body data-ng-app="painelAdmin">    
      <%@include file="../templates/top-bar.jsp" %>
      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="<c:url value="/incubadora/home"/>" class="breadcrumb"><strong>Home</strong></a>
               <a href="<c:url value="/incubadora/processo-chave"/>" class="breadcrumb"><strong>Processo-Chave</strong></a>
               <a href="<c:url value="/processo-chave/${idProcesso}/praticas-chave"/>" class="breadcrumb"><strong>Pr&aacute;tica-Chave</strong></a>
               <a href="#!" class="breadcrumb"><strong>Anexos</strong></a>
            </div>
         </div>
      </nav>
      <%@include file="../templates/side-bar.jsp" %>
      <!-- Área Principal -->
      <div class="area-principal" data-ng-controller="PraticaChaveCtrl">

         <div class="col s12 l8">

            <div class="col s12 m12 l12">
               <div class="card center white valign-wrapper">
                  <div class="card-content black-text valign-wrapper">
                     <i class="material-icons small">poll</i>&nbsp;&nbsp;
                     <span class="card-title valign"><strong>Anexos</strong> &nbsp;</span>
                  </div>                 
               </div>
            </div>
           

            <div class="card-panel ember">         
               <div class="card-content">
                  <ul class="collection">
                     <c:if test="${arquivos != null}">
                        <c:forEach var="arquivo" items="${arquivos}">
                           <li class="collection-item">
                              <a href="#modal-excluir-${arquivo.id}" class="secondary-content close modal-trigger"><i class="material-icons">clear</i></a>
                              <div class="valign-wrapper">
                                 <i class="material-icons valign green-text" style="margin-right:2%">description</i>
                                 <a class="valign" href="<c:url value="/pratica-chave/${idPratica}/anexo/${arquivo.id}/download"/>" target="_blank">${arquivo.path}</a>                                 
                              </div>

                              <!-- Alert de exclusão -->
                              <div class="modal card-panel" id="modal-excluir-${arquivo.id}">
                                 <div class="modal-content">
                                    <h3 class="valign-wrapper" style="font-size: 25pt"><i class="material-icons valign" style="margin-right:2%; font-size: 25pt !important">warning</i> <span class="valign">Alerta de Exclus&atilde;o</span></h3>
                                    <h5>Esta opera&ccedil;&atilde;o n&atilde;o poder&aacute; ser desfeita posteriormente.</h5>
                                 </div>
                                 <div class="modal-footer card-action">
                                    <a href="#!" id="anexo-${arquivo.id}" data-ng-click="deleteAnexo(${arquivo.id})" class="waves-effect waves-orange btn-flat">Excluir</a>
                                    <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn-flat">Cancelar</a>
                                 </div>
                              </div>
                              <!-- // -->
                           </li>
                        </c:forEach>
                     </c:if>
                     <c:if test="${arquivos eq null}">
                        <p>Não existem arquivos anexadas à essa práica-chave.</p>
                     </c:if>
                  </ul>
               </div>
            </div>

         </div>
      </div>
   </body>
</html>
