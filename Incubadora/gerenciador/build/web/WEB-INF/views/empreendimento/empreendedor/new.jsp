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
      <title>Novo Empreendimento</title>

      <%@include  file="../../templates/basic-style-empreendedor.jsp"%>
      <%@include file="../../templates/basic-script-empreendedor.jsp" %>

      <script src="<c:url value="/js/painel-empreendedor/values/empreendimento-values.js"/>"></script>
      <script src="<c:url value="/js/painel-empreendedor/services/empreendimento-services.js"/>"></script>
      <script src="<c:url value="/js/painel-empreendedor/controllers/empreendimento-controller.js"/>"></script>
   </head>
   <body data-ng-app="painelEmpreendedor">
      <%@include file="../../templates/top-bar-empreendedor.jsp" %>

      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="<c:url value="/empreendedor/home"/>" class="breadcrumb">Home</a>
               <a href="<c:url value="/empreendedor/empreendimentos"/>" class="breadcrumb">Empreendimentos</a>
               <a href="#!" class="breadcrumb"><strong>Novo</strong></a>
            </div>
         </div>
      </nav>
      <%@include file="../../templates/sidebar-empreendedor.jsp" %>
      <%@include file="../../templates/empreendimento/empreendedor/main-view-empreendimento-new.jsp" %>
      
   </body>
</html>
