
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Área Principal -->
<div class="area-principal" data-ng-app="painelAdmin">   

   <div class="col s12 l8" data-ng-controller="EditalCtrl">   
      <div class="col s12 m12 l12">
         <div class="card center white">
            <div class="card-content black-text valign-wrapper">
               <i class="material-icons small">event_note</i>&nbsp;&nbsp;
               <span class="card-title valign"><strong class="hide-on-small-only">Cadastrar Edital</strong> 
            </div>
         </div>
      </div>

      <div class="card-panel ember">         
         <div class="card-content">
            <form method="POST"  enctype="multipart/form-data" name="formEdital"  action="<c:url value="/incubadora/edital/novo"/>">

               <div class="form-group">
                  <input type="text" name="id" id="incubadora-id" value="${edital.id}" hidden="true"/>

                  <div class="form-group">
                     <label class="control-label" for="nome">Nome</label>
                     <input class="form-control" type="text" data-ng-required="true" value="${edital.nome}" id="nome" name="nome" value="${edital.nome}" placeholder="Nome do Edital">

                  </div>

                  <div class="form-group">
                     <label class="control-label" for="resumo">Breve resumo</label>
                     <textarea class="materialize-textarea" data-ng-required="true" id="resumo" name="resumo" placeholder="Resumo" value="${edital.resumo}">${edital.resumo}</textarea>                    

                  </div>

                  <label for="dataInicio">Data Inicio</label>
                  <input  name="dataInicial" class="datepicker" type="text" data-ng-required="true"  placeholder="dd/MM/yyyy" value="${edital.dataInicio}" >

                  <label for="dataFim">Data Fim</label>
                  <input class="datepicker" id="dataFim" name="dataFinal"  type="text" data-ng-required="true"  placeholder="dd/MM/yyyy" value="${edital.dataFim}"> 

                  <div class="file-field input-field">
                     <div class="btn btn-floating circle blue" style="width: 37px !important; height: 37px !important" >
                        <i class="material-icons">&#xE2BC;</i>
                        <input type="file" id="pdf" name="path">                                 
                     </div>

                     <div class="file-path-wrapper"> 
                        <input class="file-path validate" type="text">
                     </div>
                  </div>
                  <button type="submit" onclick="sendForm()" data-ng-disabled="formEdital.$invalid" class="btn-floating btn-enviar circle green right" style="margin-right: 20px; margin-left: 2% !important; margin-top: -1%"><i class="material-icons">done</i></button>
                  <button class="btn-floating circle orange modal-close right" data-ng-href="/gerenciador/incubadora/editais" style="margin-top: -1% !important"><i class="material-icons">undo</i></button>&nbsp;&nbsp;

               </div>		           
            </form>  
            <script>
                 
            </script>

         </div>
      </div>
   </div>
</div>