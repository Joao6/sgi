<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>SGI - Home</title>
      <%@include file="../../templates/basic-style-empreendedor.jsp" %>
      <%@include file="../../templates/basic-script-empreendedor.jsp" %>
   </head>
   <body data-ng-app="painelEmpreendedor">
      <%@include file="../../templates/top-bar-empreendedor.jsp"%>

      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="#!" class="breadcrumb"><strong>Home</strong></a>
            </div>
         </div>
      </nav>

      <%@include file="../../templates/sidebar-empreendedor.jsp" %>  
      <%@include file="../../templates/empreendedor/main-view-empreendedor-home.jsp" %>
      
   </body>
</html>