<%-- 
    Document   : new
    Created on : 17/08/2015, 15:05:57
    Author     : William
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Orientações</title>
        <jsp:include page="../templates/basic-style.jsp"/>
        <!-- Bootstrap -->        

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <%@include file="../templates/top-bar.jsp" %>

        <ol class="breadcrumb">
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>
            <li><a href="<c:url value="/incubadora/orientacoes"/>">Orientações</a></li>
            <li class="active">Add</li>
        </ol>

        <%@include file="../templates/side-bar.jsp" %>      

        <section class="area-principal col-lg-10 col-sm-8" id="pratica-new">
            <form  class="ls-form ls-form-horizontal row" method="post" id="orientacoes-form">
                <fieldset><legend class="form-title">Cadastrar Orientações</legend></fieldset>

                <div class="form-group">
                    <label class="control-label" for="plano">Plano</label>
                    <select id="plano"  class="select-categoria form-control input-sm" name="categoria.id" <c:if test="${orientacao != null}"> readonly="true"</c:if>>
                            <option value="0">Plano...</option>
                        <c:if test="${categorias != null}">
                            <c:forEach var="categoria" items="${categorias}">
                                <option value="${categoria.id}" 
                                        <c:if test="${orientacao.categoria.id eq categoria.id}">selected</c:if>
                                        >${categoria.nome}
                                </option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>

                <div class="form-group">
                    <label class="control-label" for="descricao">Descrição</label>
                    <textarea class="form-control input-sm"  maxlength="255" cols="45" id="descricao" rows="5" onkeyup="mostrarResultado(this.value, 255, 'spcontando');
                            contarCaracteres(this.value, 140, 'sprestante')" name="descricao">${orientacao.descricao}</textarea>

                    <span id="spcontando" style="font-family:sans-serif; margin-left: 5%; font-size: 10pt; text-align: right"></span><br />
                </div>
                    <button type="submit" class="btn botao">Salvar</button>
                <a href="<c:url value="/incubadora/orientacoes"/>" class="btn btn-warning">Cancelar</a>
            </form>
        </section>
        <script src="<c:url value="/js/orientacoes/validacao-form.js"/>"></script>
        
        <script>
                        function mostrarResultado(box, num_max, campospan) {
                            var contagem_carac = box.length;
                            if (contagem_carac !== 0) {
                                document.getElementById(campospan).innerHTML = contagem_carac + " caracteres digitados";
                                if (contagem_carac === 1) {
                                    document.getElementById(campospan).innerHTML = contagem_carac + " caracter digitado";
                                }
                                if (contagem_carac >= num_max) {
                                    document.getElementById(campospan).innerHTML = "Limite de caracteres excedido!";
                                }
                            } else {
                                document.getElementById(campospan).innerHTML = "";
                            }
                        }

        </script>

    </body>
</html>

