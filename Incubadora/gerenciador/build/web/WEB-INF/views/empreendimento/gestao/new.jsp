<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Novo Edital</title>

        <jsp:include page="../../templates/basic-style.jsp"/>
        <!-- Bootstrap -->        

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <%@include file="../../templates/top-bar.jsp" %>
        <ol class="breadcrumb">
            <li><a href="<c:url value="/incubadora/home"/>">Home</a></li>
            <li><a href="<c:url value="/incubadora/empreendimento"/>">Empreendimentos</a></li>
            <li class="active">Novo</li>
        </ol>
        <%@include file="../../templates/sidebar-candidato.jsp" %>

        <section class="area-principal col-lg-10 col-sm-8" id="empreendimento-new">
            <c:if test="${editalList != null}">
                <form class="form" method="post" id="form-empreendimento">
                    <fieldset><legend class="form-title">Cadastro de Empreendimento</legend></fieldset>

                    <div class="form-inline">
                        <!-- input hidden -->
                        <input type="text" id="empreendimento-id" hidden="true" value="${empreendimento.id}"/>
                        <input type="text" id="status-empreendimento" value="${status}" hidden="true"/>
                    </div>	




                    <div class="col-lg-6">
                        <c:if test="${editarEmpreendimento != true}">
                            <div class="form-group">
                                <label class="control-label" for="edital">Edital <span class="text-danger">*</span> </label>
                                <select class="form-control input-sm input-item value width-40" id="edital" name="editalFK"
                                        data-placement="bottom">
                                    <option value="0">Edital</option>
                                    <c:if test="${editalList != null}">
                                        <c:forEach var="edital" items="${editalList}">
                                            <option value="${edital.id}" 
                                                    <c:if test="${empreendimento.edital.id eq edital.id}"> 
                                                        selected
                                                    </c:if>>
                                                ${edital.nome}
                                            </option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                                <c:if test="${errors.edital != null}"><p class="text-danger">${errors.edital}</p></c:if>
                            </div>
                        </c:if>

                        <div class="form-group">
                            <label class="control-label" for="nome">Nome do Projeto<span class="text-danger">*</span> </label>
                            <input type="text" class="form-control input-sm input-item value width-40" name="nome"  
                                   placeholder="Nome" data-placement="top" id="nome" value="${empreendimento.nome}">
                            <c:if test="${errors.nome!= null}"><p class="text-danger">${errors.nome}</p></c:if>

                        </div>     

                        <div class="form-group col-lg-6">
                            <label class="control-label" for="razaoSocial">Razão Social</label>
                            <input type="text" class="form-control input-sm input-item width-40" name="razaoSocial"  
                                   placeholder="Razão Social" data-placement="top" id="razaoSocial" value="${empreendimento.razaoSocial}">
                        </div>

                        <div class="form-group col-lg-6">
                            <label class="control-label" for="ramoAtividade">Ramo de atividade <span class="text-danger">*</span> </label>
                            <select class="form-control input-sm input-item value width-40" id="ramoAtividade" name="ramoAtividadeFK"
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
                            <c:if test="${errors.ramoAtividade != null}"><p class="text-danger">${errors.ramoAtividade}</p></c:if>
                            
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="cnpj">CNPJ</label>
                            <input type="text" class="form-control input-sm input-item input-cnpj width-40" id="cnpj" name="cnpj"  
                                   placeholder="CNPJ" data-placement="bottom" value="${empreendimento.cnpj}">
                        </div>

                        <div class="form-group col-lg-6">
                            <label class="control-label" for="ie">Inscrição Estadual</label>
                            <input type="text" class="form-control input-sm input-item input-cnpj width-40" id="ie" name="inscricaoEstadual"  
                                   placeholder="IE" data-placement="bottom" value="${empreendimento.inscricaoEstadual}">
                        </div>

                        <div class="form-group col-lg-6">
                            <label class="control-label" for="im">Inscrição Municipal</label>
                            <input type="text" class="form-control input-sm input-item input-cnpj width-40" id="im" name="inscricaoMunicipal"  
                                   placeholder="IM" data-placement="bottom" value="${empreendimento.inscricaoMunicipal}">
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="missao">Missao</label>
                            <textarea id="missao" class="form-control input-sm" name="missao" cols="5" rows="3" value="${empreendimento.missao}">${empreendimento.missao}</textarea>
                        </div>





                    </div>

                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="control-label" for="visao">Visão</label>
                            <textarea id="visao" class="form-control input-sm" name="visao" cols="5" rows="3" value="${empreendimento.visao}">${empreendimento.visao}</textarea>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label" for="valores">Valores</label>
                            <textarea id="valores" class="form-control input-sm" name="valores" cols="5" rows="3" value="${empreendimento.valores}">${empreendimento.valores}</textarea>
                        </div>

                        <div class="form-group col-lg-6">
                            <label class="control-label" for="dataAbertura">Data de Abertura</label>
                            <input type="text" disabled class="form-control input-sm input-item input-data" 
                                    name="dataAbertura" id="dataAbertura"  
                                   placeholder="dd/mm/aaaa" value="<fmt:formatDate  pattern="dd/MM/yyyy" value="${empreendimento.dataAbertura}"/>" data-placement="bottom" />
                        </div>

                        <div class="form-group col-lg-6">
                            <label class="control-label" for="dataIngresso">Data de Ingresso</label>
                            <input type="text" disabled class="form-control input-sm input-item input-data"   
                                   name="dataIngresso" id="dataIngresso" 
                                   placeholder="Data de Ingresso" value="<fmt:formatDate  pattern="dd/MM/YYYY" value="${empreendimento.dataIngresso}"/>" data-placement="bottom" />
                        </div>

                        <div class="form-group">
                            <label class="control-label" for="dataPrevisaoGraduacao">Previsão da Graduacao</label>
                            <input type="date" disabled class="form-control input-sm input-item input-data" 
                                    name="dataPrevisaoGraduacao" id="dataPrevisaoGraduacao"  
                                   value="<fmt:formatDate  pattern="dd/MM/YYYY" value="${empreendimento.dataPrevisaoGraduacao}"/>" data-placement="bottom" />
                        </div>
                    </div>

                    <button type="submit" id="btn-submit" class="btn btn-primary">Salvar</button>
                    <a href="<c:url value="/empreendimentos"/>" class="btn btn-danger" >Voltar</a>
                </form>
            </c:if>
            
            <c:if test="${editalList == null}">
                <h1>Ainda não possui nenhum edital disponivel para cadastrar o empreendimento</h1>
            </c:if>
        </section>    

        <jsp:include page="../../templates/basic-script.jsp" />
    </body>
</html>