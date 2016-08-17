<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>Gest&atilde;o Intef</title>

      <%@include  file="../../templates/basic-style.jsp"%>
      <%@include file="../../templates/basic-script.jsp" %>
      <!-- // Values // -->
      <script src="<c:url value="/js/painel-administrativo/values/gestor-values.js"/>"></script>
      <!-- // Services // -->
      <script src="<c:url value="/js/painel-administrativo/service/gestor-service.js"/>"></script>
      <script src="<c:url value="/js/painel-administrativo/controller/gestor-intef-controller.js"/>"></script>

      <script>
         $(document).ready(function () {
            $('.tooltipped').tooltip({delay: 5});

         });

      </script>

   </head>
   <body data-ng-app="painelAdmin">
      <%@include file="../../templates/top-bar.jsp" %>

      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="<c:url value="/incubadora/home"/>" class="breadcrumb">Home</a>
               <a href="#!" class="breadcrumb"><strong>Gest&atilde;o Intef</strong></a>
            </div>
         </div>
      </nav>               
      <%@include file="../../templates/side-bar.jsp" %>
      <%@include file="../../templates/incubadora/main-view-incubadora-list.jsp" %>
   </body>
</html>
