<%-- 
    Document   : painel-administrativo
    Created on : 01/12/2015, 23:16:17
    Author     : William
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" data-ng-app="painelAdmin">

   <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Home</title>

      <!-- Style -->
      <%@include file="../../templates/basic-style.jsp" %>
      <%@include file="../../templates/basic-script.jsp" %>

      <style>
         .card-image{
            background-color: #000;
         }
         .imagem-home{
            opacity: 0.6;
         }
      </style>


   </head>

   <body data-ng-app="painelAdmin">

      <!-- Topbar -->
      <%@include file="../../templates/top-bar.jsp" %>

      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="#!" class="breadcrumb"><strong>Home</strong></a>
            </div>
         </div>
      </nav>

      <!-- Menu Lateral -->
      <%@include file="../../templates/side-bar.jsp" %>

      <!-- Área Principal -->
      <%@include file="../../templates/incubadora/main-view-incubadora.jsp" %>

   </div>
</body>

</html>