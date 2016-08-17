<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Folhas de Estilo -->
<link rel="icon" type="image/png" sizes="32x20" href="<c:url value="/img/favicon-32x32.png"/>">
<!-- // Materialize // -->
<link rel="stylesheet" href="<c:url value="/css/materialize.css"/>" />
<!-- Compiled and minified CSS -->
<!-- // Style da aplicação // -->
<link rel="stylesheet" href="<c:url value="/css/painel-administrativo/app.css"/>" />

<style>
   label,
   label:hover,
   label:visited{
      color: blue !important;
   }
   h5{
      padding: 1%;
   }


   .collection .collection-item {
      background-color: rgba(96, 125, 139, 0.12);
      line-height: 1.5rem;
      padding: 10px 20px;
      margin-bottom: 1.2%;
      border-bottom: 1px solid #e0e0e0;
      
   }
   
   
   .collection{
         border:0px !important;
         border-left: 0px !important;
         border-right: 0px !important;
         padding-left: 1%;
         padding-right: 1%;
         padding-top: 1%;
         
      }
</style>

