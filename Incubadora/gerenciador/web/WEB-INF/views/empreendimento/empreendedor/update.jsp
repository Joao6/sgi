<%-- 
    Document   : new
    Created on : 18/08/2015, 10:01:51
    Author     : Aliane Leal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empreendimento</title>
        <%@include file="../../imports/head-incubadora.jsp"%>
        <link rel="stylesheet" href="<c:url value="/css/empreendimento/empreendimento-style.css"/>"/>

        <jsp:include page="../../templates/basic-style.jsp"/>

    </head>
    <body>
        <%@include file="../../templates/top-bar.jsp"%>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/empreendedor/home"/>">Home</a></li>
            <li style="color:#FFF"><a href="<c:url value="/empreendimento/empreendedor"/>">Empreendimento</a></li>
            <li style="color:#FFF" class="active">Atualizar</li>
        </ol>    
        <%@include file="../../templates/empreendedor/side-bar.jsp"%>
        <section class="area-principal" id="empreendedor-form-new">

            <form class="form" method="post" id="empreendimento-form">
                <fieldset><legend class="form-title">Empreendimento</legend></fieldset>

                <div class="form-inline-empreendedor">
                    <input type="text" id="empreendimento-id" hidden="true" value="${empreendimento.id}"/>
                    <input type="text" id="status-empreendimento" value="${status}" hidden="true"/>
                    <input type="text" class="input-item value width-40" name="nome"  
                           placeholder="Nome" data-placement="top" id="nome" value="${empreendimento.nome}">
                    <input type="text" class="input-item width-40" name="razaoSocial"  
                           placeholder="Razão Social" data-placement="top" id="razaoSocial" value="${empreendimento.razaoSocial}">
                </div>

                <div class="form-inline-empreendedor">
                    <input type="text" class="input-item input-cnpj width-40" id="cnpj" name="cnpj"  
                           placeholder="CNPJ" data-placement="bottom" value="${empreendimento.cnpj}">

                    <select class="input-item value width-40" id="ramoAtividade" name="ramoAtividade.id"
                            data-placement="bottom">
                        <option value="0">Ramo de Atividade</option>
                        <c:if test="${ramoAtividadeList != null}">
                            <c:forEach var="ramoAtividade" items="${ramoAtividadeList}">
                                <option value="${ramoAtividade.id}" 
                                        <c:if test="${empreendimento.ramoAtividade.id eq ramoAtividade.id}"> 
                                            selected
                                        </c:if>>
                                    ${ramoAtividade.nome}
                                </option>
                            </c:forEach>
                        </c:if>
                    </select> 
                </div>	
                <div class="form-inline-empreendedor">
                    <textarea rows="3" placeholder="Missão"></textarea>
                    <textarea  id="visao" rows="3" placeholder="Visão"></textarea>
                </div>
                <div id="textarea">         
                    <textarea id="valores" rows="3" placeholder="Valores"></textarea>   
                </div>
                <button type="submit" id="btn-submit-salvar" class="btn botao">Salvar</button>
            </form>
        </section>

        <jsp:include page="../../templates/basic-script.jsp"/>
    </body>
</html>
