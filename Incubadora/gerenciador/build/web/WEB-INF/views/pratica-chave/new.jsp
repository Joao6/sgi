<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Prática-Chave</title>
      <%@include file="../templates/basic-style.jsp" %>
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
               <a href="#!" class="breadcrumb"><strong>Pr&aacute;tica-Chave</strong></a>
            </div>
         </div>
      </nav>
      <%@include file="../templates/side-bar.jsp" %>
      <%@include file="../templates/pratica-chave/main-view-pratica-chave-new.jsp" %>
   </body>
</html>