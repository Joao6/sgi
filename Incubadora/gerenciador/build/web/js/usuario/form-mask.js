/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



jQuery(document).ready(function () {

    $(".input-cnpj").mask("99.999.999/9999-99");
    // $(".input-cpf").mask("999.999.999-99", {placeholder:"123.456.789-12"});

    $("#cpf").mask("999.999.999-99");    // Máscara para CP
    
    $("#dataNascimento").mask("99/99/9999"); // Mascara para data de nascimento
    
    $("#telefone").mask("(99) 9 9999-9999"); // Mascara para data de nascimento
});