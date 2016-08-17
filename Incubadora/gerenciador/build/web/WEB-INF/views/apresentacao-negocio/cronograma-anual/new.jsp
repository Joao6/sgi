<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>Cronograma Anual</title>

      <%@include  file="../templates/basic-style.jsp"%>
      <style>
         .pratica-field,
         .responsavel-field{
            background-color: transparent !important;
            padding-bottom: 2% !important;
         }


         .card.select-group{
            background-color: rgba(58, 124, 183, 0.09) !important;
         }
         .chip {
            display: inline-block;
            height: 32px;
            font-size: 13px;
            font-weight: 500;
            color: rgb(255, 255, 255);
            line-height: 32px;
            padding: 0 12px;
            border-radius: 16px;
            background-color: rgba(96, 125, 139, 0.52);
            margin-right: 1%;
            margin-top: 2%;
         }

         /* .cad{
              box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.16), 0 2px 5px 0 rgba(0, 0, 0, 0.12) !important;
           }*/

      </style>
      <%@include file="../templates/basic-script.jsp" %>

      <!-- GESTOR -->
      <script src="<c:url value="/js/painel-administrativo/values/gestor-values.js"/>"></script>
      <!-- // Services // -->
      <script src="<c:url value="/js/painel-administrativo/service/gestor-service.js"/>"></script>
      <script src="<c:url value="/js/painel-administrativo/controller/gestor-intef-controller.js"/>"></script>


      <!-- PRÁTICA-CHAVE -->
      <!-- // Values // -->
      <script src="<c:url value="/js/painel-administrativo/values/pratica-chave-values.js"/>"></script>
      <!-- // Services // -->

      <script src="<c:url value="/js/painel-administrativo/service/pratica-chave-service.js"/>"></script>

      <!-- // Controllers // -->
      <script src="<c:url value="/js/painel-administrativo/controller/pratica-chave-controller.js"/>"></script>
      <!-- // // // //  -->

      <!-- // Values // -->
      <script src="<c:url value="/js/painel-administrativo/values/atividade-values.js"/>"></script>
      <!-- // Services // -->
      <script src="<c:url value="/js/painel-administrativo/service/atividades-service.js"/>"></script>
      <!-- // Controllers // -->
      <script src="<c:url value="/js/painel-administrativo/controller/painel-controller.js"/>"></script>

      <script src="<c:url value="/js/cronograma-anual/validacao-form.js"/>"></script>
      <script>
         $(document).ready(function () {
            $(".btn-voltar").click(function () {
               location.href = "/gerenciador/incubadora/cronograma-anual";
            });
         });
      </script>

   </head>
   <body data-ng-app="painelAdmin">
      <%@include file="../templates/top-bar.jsp" %>

      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="<c:url value="/incubadora/home"/>" class="breadcrumb">Home</a>
               <a href="<c:url value="/incubadora/cronograma-anual"/>" class="breadcrumb">Cronograma Anual</a>
               <a href="#!" class="breadcrumb"><strong>Nova Atividade</strong></a>
            </div>
         </div>
      </nav>

      <%@include file="../templates/side-bar.jsp" %>
      <%@include file="../templates/cronograma-anual/main-view-cronograma-anual-new.jsp" %>
   </body>
</html>
