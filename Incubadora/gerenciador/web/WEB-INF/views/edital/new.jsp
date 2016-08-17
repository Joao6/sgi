<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>Edital</title>

      <%@include file="../templates/basic-style.jsp" %>
      <style>

         .modal .modal-close {
            border: 2px solid transparent;
            cursor: pointer;
            border-radius: 50% !important;
            margin-right: 3%;
         }

         .modal-footer .modal-close{
            border: 2px solid #A0A691 !important;
            border-radius: 0px !important;
         }

      </style>
      <%@include file="../templates/basic-script.jsp" %>
      
      <!-- values -->
      <script src="<c:url value="/js/painel-administrativo/values/edital-values.js"/>"></script>
      <!-- Services -->
      <script src="<c:url value="/js/painel-administrativo/service/edital-service.js"/>"></script>
      <!-- Controllers -->
      <script src="<c:url value="/js/painel-administrativo/controller/edital-controller.js"/>"></script>
      <!-- Bootstrap -->        

      <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
      <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
      <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
   </head>
   <body>

      <%@include file="../templates/top-bar.jsp" %>

      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="<c:url value="/incubadora/home"/>" class="breadcrumb"><strong>Home</strong></a>
               <a href="<c:url value="/incubadora/editais"/>" class="breadcrumb"><strong>Edital</strong></a>
               <a href="#!" class="breadcrumb"><strong>Novo</strong></a>
            </div>
         </div>
      </nav>

      <%@include file="../templates/side-bar.jsp" %>
      <%@include file="../templates/edital/main-view-new.jsp"%>
   </body>
</html>