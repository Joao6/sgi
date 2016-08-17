<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>Eixo</title>

      <%@include  file="../templates/basic-style.jsp"%>
      <%@include file="../templates/basic-script.jsp" %>
      <script src="<c:url value="/js/painel-administrativo/values/eixo-value.js"/>"></script>
      <script src="<c:url value="/js/painel-administrativo/service/eixo-service.js"/>"></script>
      <script src="<c:url value="/js/painel-administrativo/controller/eixo-controller.js"/>"></script>

      <script>
         $(document).ready(function () {
            $('.number').mask("00,00",{'translation': {0: {pattern: /[0-9*]/}}}, {reverse: true});
         });
      </script>

   </head>
   <body data-ng-app="painelAdmin">
      <%@include file="../templates/top-bar.jsp" %>
      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="<c:url value="/incubadora/home"/>" class="breadcrumb">Home</a>
               <a href="#!" class="breadcrumb"><strong>Modelo de Avalia&ccedil;&atilde;o</strong></a>
            </div>
         </div>
      </nav>
      <%@include file="../templates/side-bar.jsp" %>
      <%@include file="../templates/eixo/main-view-eixo-list.jsp" %>
   </body>
</html>