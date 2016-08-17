  /* 
   * Script de validação do formulário de cadastro de candidato
   */
  /* global Materialize */
  /* global angular */

  $(document).ready(function () {
     
     // Mascaras
     


     $(document).on('change', 'input,select,.datepicker', function (e) {
        var val = $(this).val();
        if (val !== undefined || val !== null || val !== '0') {
           $(this).addClass('white');
        } else {
           $(this).css({"background-color": "rgba(255, 192, 206, 0.4)"});
        }
     });

     //Checa se houve preencimento correto para submeter o formulário
     $(document).on('submit', '#form', function (e) {
        var _checkCPF = true;
        $(this).find('input,select, .datepicker').each(function () {
           if ($(this).val() === "" || $(this).val() === null ||
               $(this).val() === "0" || $(this).val() === undefined) {
              _checkCPF = false;
              
              e.preventDefault();
              $(this).css({"background-color": "rgba(255, 192, 206, 0.4) !important"});
           } else {
              $(this).css({"background-color": "#FFF"});
           }
        });

        if (_checkCPF) {

           //Caso o campo esteja em branco a mensagem de erro é exibida
//           var cpf = $("#cpf").val().toString();
//
//           var cpfReplaced = cpf.substr(0, 3);
//           cpfReplaced += cpf.substr(4, 3);
//           cpfReplaced += cpf.substr(8, 3);
//           cpfReplaced += cpf.substr(12, 2);

           //TODO NÚMERO MÍNIMO DE CARACTERES PARA A SENHA

//
//           //Validar CPF
//           if (!validaCPF(cpfReplaced)) {
//              e.preventDefault();
//              Materialize.toast('CPF Inválido!', 5000, 'rounded red');
//              $("#cpf").css({"background-color": "rgba(255, 192, 206, 0.4)"});
//           } else {
//              $("#cpf").addClass('white');
//           }
        }
        
        
     });


  });
