<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group col-lg-2">
    <label class="control-label" for="uf">UF <span class="text-danger">*</span> </label>
    <select class="form-control input-sm input-item value" id="uf" 
            name="uf" >

        <option value="">Selecione...</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'AC'}">selected</c:if> value="AC">AC</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'AL'}">selected</c:if> value="AL">AL</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'AM'}">selected</c:if> value="AM">AM</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'AP'}">selected</c:if> value="AP">AP</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'BA'}">selected</c:if> value="BA">BA</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'CE'}">selected</c:if> value="CE">CE</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'DF'}">selected</c:if> value="DF">DF</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'ES'}">selected</c:if> value="ES">ES</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'GO'}">selected</c:if> value="GO">GO</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'MA'}">selected</c:if> value="MA">MA</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'MG'}">selected</c:if> value="MG">MG</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'MS'}">selected</c:if> value="MS">MS</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'MT'}">selected</c:if> value="MT">MT</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'PA'}">selected</c:if> value="PA">PA</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'PB'}">selected</c:if> value="PB">PB</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'PE'}">selected</c:if> value="PE">PE</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'PI'}">selected</c:if> value="PI">PI</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'PR'}">selected</c:if> value="PR">PR</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'RJ'}">selected</c:if> value="RJ">RJ</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'RN'}">selected</c:if> value="RN">RN</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'RO'}">selected</c:if> value="RO">RO</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'RR'}">selected</c:if> value="RR">RR</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'RS'}">selected</c:if> value="RS">RS</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'SC'}">selected</c:if> value="SC">SC</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'SE'}">selected</c:if> value="SE">SE</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'SP'}">selected</c:if> value="SP">SP</option>
        <option <c:if test="${empreendedor.endereco.uf eq 'TO'}">selected</c:if> value="TO">TO</option>
        </select>

    </div>

    <div class="form-group col-lg-10">
        <label class="control-label" for="cidade">Cidade <span class="text-danger">*</span> </label>
        <input type="text" class="form-control input-sm input-item value" id="cidade" 
               name="cidade" placeholder="Cidade" value="${empreendedor.endereco.cidade}"/>   


</div>

<div class="form-group col-lg-10">
    <label class="control-label" for="rua">Rua <span class="text-danger">*</span> </label>
    <input type="text" class="form-control input-sm input-item value" id="rua" 
           name="rua" placeholder="Rua" value="${empreendedor.endereco.rua}"/>  


</div>
           
<div class="form-group col-lg-2">
    <label class="control-label" for="numero">Nº <span class="text-danger">*</span> </label>
    <input type="text" class="form-control input-sm input-item value" id="numero"
           name="numero" placeholder="Nº" value="${empreendedor.endereco.numero}"/>

</div>

<div class="form-group">
    <label class="control-label" for="bairro">Bairro <span class="text-danger">*</span> </label>
    <input type="text" class="form-control input-sm input-item value" id="bairro" 
           name="bairro" placeholder="Bairro" value="${empreendedor.endereco.bairro}"/>

</div>

