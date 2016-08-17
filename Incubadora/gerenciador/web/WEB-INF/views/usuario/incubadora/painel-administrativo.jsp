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
      <title>Painel Administrativo</title>

      <!-- Style -->
      <%@include file="../../templates/basic-style.jsp" %>
      <!-- // Style da aplicação // -->
      <link rel="stylesheet" href="<c:url value="/css/painel-administrativo/app.css"/>" />
      
      <!-- Scripts -->
      <%@include file="../../templates/basic-script.jsp" %>
      

     
   </head>

   <body data-ng-controller="PainelCtrl">

      <!-- Topbar -->
      <%@include file="../../templates/top-bar.jsp" %>

      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="#!" class="breadcrumb">Home</a>
               <a href="#!" class="breadcrumb"><strong>Cronograma Anual</strong></a>
            </div>
         </div>
      </nav>

      <!-- Menu Lateral -->
      <%@include file="../../templates/side-bar.jsp" %>

      <!-- Área Principal -->
      <%@include file="../../templates/cronograma-anual/main-view-cronograma-anual.jsp" %>

   </div>
</body>

</html>