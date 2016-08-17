<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Prática-Chave</title>

      <%@include file="../templates/basic-style.jsp" %>
      <style>
         input[type=text]{
            margin-bottom: 0px !important;
            border-bottom: 1px solid #2648a6 !important;
         }

         .input-field{
            margin-top: 3rem !important;
         }

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
      <%@include file="../templates/pratica-chave/main-view-pratica-chave-list.jsp" %>
   </body>
</html>
