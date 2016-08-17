
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Área Principal -->
<div class="area-principal" >

   <div class="col s12 l8">

      <div class="col s12 m12 l12">
         <div class="card center white">
            <div class="card-content black-text valign-wrapper">
               <i class="material-icons small">event_note</i>&nbsp;&nbsp;
               <span class="card-title valign"><strong class="hide-on-small-only">Edital</strong> 
                  <a class="btn-floating waves-effect waves-light green" data-ng-href="/gerenciador/incubadora/edital/novo"><i class="material-icons">add</i></a>
            </div>
         </div>
      </div>
      <div class="card-panel ember">         
         <div class="card-content">
            <table class="table table-striped">
               <thead>
                  <tr>
                     <th>Nome</th>
                     <th class="hidden-xs">Data Inicio</th>
                     <th class="hidden-xs">Data Fim</th>
                     <th>Editar</th>
                     <th>Info</th>
                     <th>Excluir</th>
                  </tr>
               </thead>
               <tbody>

                  <c:forEach var="edital" items="${editalList}">
                     <jsp:useBean id="now" class="java.util.Date"/>
                     <c:if test="${edital.dataProrrogacao != null}">
                        <c:set value="${edital.dataProrrogacao}" var="data" />
                     </c:if>
                     <c:if test="${edital.dataProrrogacao == null}">                                
                        <c:set value="${edital.dataFim}" var="data" />
                     </c:if>

                     <tr <c:if test="${now < data}">class="success"</c:if>>
                        <td>${edital.nome}</td>
                        <td class="hidden-xs"><fmt:formatDate pattern="dd/MM/yyyy" value="${edital.dataInicio}"/></td>
                  <td class="hidden-xs">
                     <c:if test="${edital.dataProrrogacao != null}">
                     <fmt:formatDate pattern="dd/MM/yyyy" value="${edital.dataProrrogacao}"/>
                  </c:if>
                  <c:if test="${edital.dataProrrogacao == null}">                                
                     <fmt:formatDate pattern="dd/MM/yyyy" value="${edital.dataFim}"/>
                  </c:if>
                  </td>
                  <td><a href="<c:url value="/incubadora/edital/atualizar/${edital.id}"/>"><span class="material-icons">edit</span></a></td>
                  <td>
                     <a href="#" style="outline: transparent" data-toggle="modal" data-target="#modal-alert-${edital.id}">
                        <span class="glyphicon glyphicon-trash"></span>
                     </a>
                  </td>
                  <td>
                     <a href="<c:url value="/incubadora/edital/${edital.id}/excluir"/>"><span class="glyphicon glyphicon-minus-sign">excluir</span></a>
                  </td>
                 
                  </tr>
               </c:forEach>

               </tbody>
            </table>

         </div>

      </div>
   </div>
</div>
