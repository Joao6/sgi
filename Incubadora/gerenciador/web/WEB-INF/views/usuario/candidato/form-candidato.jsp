<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="form-group col-lg-6">
   <label class="control-label" for="nome">Nome <span class="text-danger">*</span> </label>
   <input type="text" class="form-control input-sm" id="nome"
          name="nome" placeholder="Nome" value="${empreendedor.nome}"/>   

</div>

<div class="form-group col-lg-6">
   <label class="control-label" for="sobrenome">Sobrenome <span class="text-danger">*</span> </label>
   <input type="text" class="form-control input-sm input-item value" id="sobrenome" 
          name="sobrenome" placeholder="Sobrenome" value="${empreendedor.sobrenome}"
          />                    

</div>

<div class="form-group col-lg-6">
   <label class="control-label" for="rg">RG <span class="text-danger">*</span> </label>
   <input type="text" class="form-control input-sm input-item value campoRG" id="rg" 
          name="rg" placeholder="RG" value="${empreendedor.rg}"
          />                   

</div>

<div class="form-group col-lg-6">
   <label class="control-label" for="cpf">CPF <span class="text-danger">*</span> </label>
   <input type="text" class="form-control input-sm input-item value campoCPF" id="cpf"
          name="cpf" placeholder="CPF" value="${empreendedor.cpf}" 
          />                    

</div>

<div class="form-group">

   <label class="control-label" for="dataNascimento">Data de Nascimento <span class="text-danger">*</span> </label>
   <input type="date" <fmt:formatDate pattern="dd/MM/yyyy" value="${empreendedor.dataNascimento}" var="data"/> class="form-control input-sm input-item value campoData" id="dataNascimento" 
          name="dataNascimento" placeholder="dd/MM/yyyy" value="${empreendedor.dataNascimento}"/>


</div>

<div class="form-group">
   <label class="control-label" for="escolaridade">Escolaridade <span class="text-danger">*</span> </label>
   <select class="form-control input-sm input-item value" id="escolaridade" name="escolaridade" placeholder="Escolaridade">
      <option value="">Selecione...</option>

   </select>


</div>

<div class="form-group">
   <label class="control-label" for="formacaoProfissional">Formação Profissional</label>
   <input type="text" class="form-control input-sm input-item " id="formacaoProfissional" data-placement="top" />                    

</div>

<div class="form-group">
   <label class="control-label" for="ocupacao">Ocupação</label>
   <input type="text" class="form-control input-sm input-item " id="ocupacao" data-placement="top"
          name="ocupacao" placeholder="Ocupacao" value="${empreendedor.ocupacao}"
          /> 
</div>




