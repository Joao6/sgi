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
      <link rel="stylesheet" href="<c:url value="/css/duvidas/duvidas-style.css"/>"/>
      <%@include file="../../templates/basic-script.jsp" %>
      <script>
         $(document).ready(function () {            
            $('textarea.materialize-textarea').characterCounter();
            $('.collapsible').collapsible({
               accordion: false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
            });
         });
      </script>



   </head>
   <body data-ng-app="painelAdmin">
      <%@include file="../../templates/top-bar.jsp" %>

      <nav class="navbar-top amber">
         <div class="nav-wrapper amber">
            <div class="col s12 m12 l12">
               <a href="<c:url value="/incubadora/home"/>" class="breadcrumb">Home</a>
               <a href="#!" class="breadcrumb"><strong>D&uacute;vidas</strong></a>
            </div>
         </div>
      </nav>               
      <%@include file="../../templates/side-bar.jsp" %>
      <%@include file="../../templates/duvidas/gestor/main-view-duvida-list.jsp" %>

   </body>
</html>
