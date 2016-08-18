<%-- 
    Document   : list
    Created on : 19/02/2016, 11:49:28
    Author     : Developer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>Empreendimento</title>

      <%@include  file="../../templates/basic-style-empreendedor.jsp"%>
      <%@include file="../../templates/basic-script-avaliador.jsp" %>

      <script src="<c:url value="/js/painel-avaliador/value/empreendimento-value.js"/>"></script>
      <script src="<c:url value="/js/painel-avaliador/service/empreendimento-services.js"/>"></script>
      <script src="<c:url value="/js/painel-avaliador/controller/empreendimento-controller.js"/>"></script>
      
      <script>
          $(document).ready(function () {
            // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
            $('.modal-trigger').leanModal();
        });
      </script>

   </head>
   <body data-ng-app="painelAvaliador">
      <%@include file="../../templates/top-bar-empreendedor.jsp" %>

      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="<c:url value="/avaliador/home"/>" class="breadcrumb">Home</a>
               <a href="#!" class="breadcrumb"><strong>Empreendimentos</strong></a>
            </div>
         </div>
      </nav>
      <%@include file="../../templates/sidebar-avaliador.jsp" %>
      <%@include file="../../templates/empreendimento/avaliador/main-view-empreendimento-avaliador-list.jsp" %>

   </body>
</html>
