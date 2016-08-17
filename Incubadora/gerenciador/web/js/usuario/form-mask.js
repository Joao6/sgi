/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {

    $('.input-data').mask("00r00r0000", {
        translation: {
            'r': {
                pattern: /[\/]/,
                fallback: '/'
            },
            placeholder: "__/__/____"
        }
    });

    $(".input-cnpj").mask("99.999.999/9999-99");
    // $(".input-cpf").mask("999.999.999-99", {placeholder:"123.456.789-12"});

    $("#cpf").mask("999.999.999-99");    // Máscara para CP
});