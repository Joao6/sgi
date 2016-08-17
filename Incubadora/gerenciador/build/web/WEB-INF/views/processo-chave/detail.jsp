<%-- 
    Document   : detail
    Created on : 09/02/2016, 20:43:33
    Author     : William
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Processo-Chave::Detalhes</title>
      <%@include file="../templates/basic-style.jsp" %>
      <%@include file="../templates/basic-script.jsp" %>
   </head>
   <body data-ng-app="painelAdmin">
      <%@include file="../templates/top-bar.jsp" %>
      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="<c:url value="/incubadora/home"/>" class="breadcrumb"><strong>Home</strong></a>
               <a href="<c:url value="/incubadora/processo-chave"/>" class="breadcrumb"><strong>Processo-Chave</strong></a>
               <a href="#!" class="breadcrumb">Detalhes</a>
            </div>
         </div>
      </nav>
      <%@include file="../templates/side-bar.jsp" %>
      <%@include file="../templates/pratica-chave/main-view-pratica-chave-list.jsp" %>
   </body>
</html>
