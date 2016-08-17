<%-- 
    Document   : img
    Created on : 19/05/2015, 14:00:51
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
    <form method="post" action="<c:url value="/imagem/upload/${empreendimento.id}"/>" enctype="multipart/form-data">
      <input type="file" name="logo" /><br/>
      <input type="submit" value="Salvar"/>
    </form>

    <img src="<c:url value="/logo/${empreendimento.id}"/>"/>
  </body>
</html>
