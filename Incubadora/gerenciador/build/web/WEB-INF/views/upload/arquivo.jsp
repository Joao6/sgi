<%-- 
    Document   : arquivo
    Created on : 19/05/2015, 14:01:07
    Author     : William
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <h1>Hello World!</h1>
    <img src="<c:url value="/logo/${empreendimento.id}"/>"/>
  </body>
</html>
