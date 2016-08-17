<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>Avaliador</title>
      <%@include file="../../templates/basic-style.jsp" %>
      <%@include file="../../templates/basic-script-avaliador.jsp" %>
   </head>
   <body data-ng-app="painelAvaliador">
      <%@include file="../../templates/top-bar.jsp" %>
      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="<c:url value="/avaliador/home"/>" class="breadcrumb"><strong>Home</strong></a>
               <a href="#!" class="breadcrumb"><strong>Informa&ccedil;&otilde;es</strong></a>
            </div>
         </div>
      </nav>
      <%@include file="../../templates/sidebar-avaliador.jsp" %>
      <%@include file="../../templates/avaliador/main-view-avaliador-info.jsp" %>
   </body>
</html>
